package com.maximusteam.tripfulaxel.livechat.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.maximusteam.tripfulaxel.livechat.model.dto.ChatJoinUserDTO;
import com.maximusteam.tripfulaxel.livechat.model.dto.ChatMessageDTO;
import com.maximusteam.tripfulaxel.livechat.model.dto.ChatRoomDTO;
import com.maximusteam.tripfulaxel.livechat.model.service.ChatService;
import com.maximusteam.tripfulaxel.livechat.model.service.ChatServiceImpl;

@Controller
public class ChatController {

	private ChatService chatService;
	SimpMessagingTemplate template;
	
	@Autowired
	public ChatController(ChatService chatService, SimpMessagingTemplate template) {
		this.chatService = chatService;
		this.template = template;
	}
	
	@MessageMapping("/message")
	public void chat(ChatMessageDTO message) {
		int messageResult = 0;
		int joinResult = 0;
		int deleteJoinResult = 0;
		
		if(message.getMessageType().equals("join")) {
			
			ChatJoinUserDTO user = new ChatJoinUserDTO();
			
			user.setRoomCode(message.getRoomCode());
			user.setUserCode(message.getUserCode());
			
			joinResult = chatService.insertChatJoin(user);
			
//			user chatroom 접속시 접속 데이터 저장
			if(joinResult > 0) {
				
				String joinMessage = message.getUserEmail() + " 님이 입장 하셨습니다.";
				message.setMessageContent(joinMessage);
				
				this.template.convertAndSend("/topic/group/" + message.getRoomCode(), message);
			} else {
				System.out.println("유저 채팅 접속 실패!!!");
			}
			
			
		} else if(message.getMessageType().equals("out")){
			
			String outMessage = message.getUserEmail() + " 님이 퇴장 하셨습니다.";
			message.setMessageContent(outMessage);
			
			ChatJoinUserDTO user = new ChatJoinUserDTO();
			
			user.setRoomCode(message.getRoomCode());
			user.setUserCode(message.getUserCode());
			
			deleteJoinResult = chatService.deleteJoinUser(user);
			
			if(deleteJoinResult > 0) {
				this.template.convertAndSend("/topic/group/" + message.getRoomCode(), message);
				
			} else {
				System.out.println("채팅방 퇴장에 실패 하셨습니다.");
			}
			
		} else {
			messageResult = chatService.insertMessage(message);
			
		}
		if(messageResult > 0) {
			this.template.convertAndSend("/topic/group/" + message.getRoomCode(), message);
		}
	}
	
	@PostMapping("share/insert/imgMessage")
	public void insertImgMessage(Model model, 
			@RequestParam(name="imgMessage", required=false) MultipartFile imgMessage, 
			HttpServletResponse response, HttpServletRequest request) {
		
		/*
		 * multipart로 전송된 request에 대한 인코딩 처리를 해주어야 하는데 일반 인코딩 필터보다 구현하기 힘들다.
		 * 스프링에서 인코딩 필터를 제공한다. --> web.xml에 필터를 등록
		 */
		System.out.println("imgMessage : " + imgMessage);
		
		/*
		 * 파일을 저장할 경로 설 
		 */
		String root = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("root : " + root);
		
		String filePath = root + "/images/message";
		
		// 객체 생성할때 경로가 있나 확인
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdir();
		}
		
		// 파일명 변경 처리
		String orignFileName = imgMessage.getOriginalFilename();
		String ext = orignFileName.substring(orignFileName.lastIndexOf("."));
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		// 파일을 저장한다.
		
		try {
			imgMessage.transferTo(new File(filePath + "/" + saveName));
			
			model.addAttribute("message", "파일 업로드 성공!!");
			
			response.getWriter().print(saveName);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
//			실패시 파일 삭제
			new File(filePath + "/" + saveName).delete();
		}
		
	}
	
	@RequestMapping("/share/insert/chatRoom")
	public String insertChatRoom(Model model, @RequestParam String roomTitle, int userCode) {
		
		ChatJoinUserDTO user = new ChatJoinUserDTO();
		user.setUserCode(userCode);
		
		List<ChatJoinUserDTO> joinUser = new ArrayList<ChatJoinUserDTO>();
		joinUser.add(user);
		
		ChatRoomDTO room = new ChatRoomDTO();
		room.setRoomTitle(roomTitle);
		room.setJoinUserList(joinUser);
		
		int insertRoomResult = chatService.insertRoom(room);
		
		user.setRoomCode(room.getRoomCode());
		
		int insertChatJoinResult = 0;
		if(insertRoomResult > 0) {
			insertChatJoinResult = chatService.insertChatJoin(user);
		}
		if(insertChatJoinResult > 0) {
			model.addAttribute("room", room);
			return "user/livechat/chat";
		} else {
			return "fail";
		}
		
	}
	
	@RequestMapping("/share/select/chatRoom")
	public String selectChatRoomList(Model model, @RequestParam int roomCode, @RequestParam(required = false) int userCode) {
		
		Map<String, Integer> parameter = new HashedMap();
		parameter.put("roomCode", roomCode);
		parameter.put("userCode", userCode);
		
		List<ChatRoomDTO> roomList = chatService.selectChatRoom(parameter);
		
		System.out.println(roomList);
//		내 채팅과 접속유저들의 채팅을 구분해서 나눔 처리
		List<ChatMessageDTO> myChat = new ArrayList<ChatMessageDTO>();
		List<ChatMessageDTO> youChat = new ArrayList<ChatMessageDTO>();
		
		if(roomCode != 0 && userCode != 0) {
			
			ChatRoomDTO room = roomList.get(0);
			
			List<ChatJoinUserDTO> joinList = chatService.selectJoinList(parameter);
			
			room.setJoinUserList(joinList);
			
			for(ChatMessageDTO message : room.getMessageList()) {
				
				if(message.getUserCode() == userCode) {
					myChat.add(message);
				} else {
					youChat.add(message);
				}
			}
			
			model.addAttribute("room", room);
			model.addAttribute("myChat", myChat);
			model.addAttribute("youChat", youChat);
			
			return "user/livechat/chat";
		} else {
			model.addAttribute("roomList", roomList);
			
			return "user/livechat/chatList";
		}
	}
	
}
