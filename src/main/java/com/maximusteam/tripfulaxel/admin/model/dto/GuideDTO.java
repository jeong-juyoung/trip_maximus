package com.maximusteam.tripfulaxel.admin.model.dto;

import java.sql.Date;

public class GuideDTO {

    private int guideNo;
    private String guideId;
    private String guideEnrollStatus; //EXAMINE_REQUEST_YN 등록 여부
    private String guideApproveStatus; //심사 승인 여부
    
    private String guideName;// 가이드 이름
    private String guideGender;// 가이드 성별
    private String guideNickname;// 가이드 활동명
    private String guideExp;// 가이드 경험 여부
    private String guideCar;// 개인차량 여부
    private String guideIntro;// 가이드 소개
    private String guideTravel; // 가이드 여행소개
    private int requestCode; // 가이드 접수 요청번호
	
    private int responseCode;
    private String responseContent;
    private java.sql.Date responseDate;
    private int examineCode;
    private int guideCode;
    private int userCode;
    private int registTypeCode;
    
    private String meetLocation;
    private int payment;
    private int maximum;
    private int minimum;
    
    
//    private String guideIdPicture;// 신분증 사진
//    private String guidePicture;// 가이드 사진
//    private String guideMotionPicture;// 활동 사진

    public GuideDTO() {}


	public GuideDTO(int guideNo, String guideId, String guideEnrollStatus, String guideApproveStatus, String guideName,
			String guideGender, String guideNickname, String guideExp, String guideCar, String guideIntro,
			String guideTravel, int requestCode, int responseCode, String responseContent, Date responseDate,
			int examineCode, int guideCode, int userCode, int registTypeCode, String meetLocation, int payment,
			int maximum, int minimum) {
		super();
		this.guideNo = guideNo;
		this.guideId = guideId;
		this.guideEnrollStatus = guideEnrollStatus;
		this.guideApproveStatus = guideApproveStatus;
		this.guideName = guideName;
		this.guideGender = guideGender;
		this.guideNickname = guideNickname;
		this.guideExp = guideExp;
		this.guideCar = guideCar;
		this.guideIntro = guideIntro;
		this.guideTravel = guideTravel;
		this.requestCode = requestCode;
		this.responseCode = responseCode;
		this.responseContent = responseContent;
		this.responseDate = responseDate;
		this.examineCode = examineCode;
		this.guideCode = guideCode;
		this.userCode = userCode;
		this.registTypeCode = registTypeCode;
		this.meetLocation = meetLocation;
		this.payment = payment;
		this.maximum = maximum;
		this.minimum = minimum;
	}


	public int getGuideNo() {
		return guideNo;
	}


	public void setGuideNo(int guideNo) {
		this.guideNo = guideNo;
	}


	public String getGuideId() {
		return guideId;
	}


	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}


	public String getGuideEnrollStatus() {
		return guideEnrollStatus;
	}


	public void setGuideEnrollStatus(String guideEnrollStatus) {
		this.guideEnrollStatus = guideEnrollStatus;
	}


	public String getGuideApproveStatus() {
		return guideApproveStatus;
	}


	public void setGuideApproveStatus(String guideApproveStatus) {
		this.guideApproveStatus = guideApproveStatus;
	}


	public String getGuideName() {
		return guideName;
	}


	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}


	public String getGuideGender() {
		return guideGender;
	}


	public void setGuideGender(String guideGender) {
		this.guideGender = guideGender;
	}


	public String getGuideNickname() {
		return guideNickname;
	}


	public void setGuideNickname(String guideNickname) {
		this.guideNickname = guideNickname;
	}


	public String getGuideExp() {
		return guideExp;
	}


	public void setGuideExp(String guideExp) {
		this.guideExp = guideExp;
	}


	public String getGuideCar() {
		return guideCar;
	}


	public void setGuideCar(String guideCar) {
		this.guideCar = guideCar;
	}


	public String getGuideIntro() {
		return guideIntro;
	}


	public void setGuideIntro(String guideIntro) {
		this.guideIntro = guideIntro;
	}


	public String getGuideTravel() {
		return guideTravel;
	}


	public void setGuideTravel(String guideTravel) {
		this.guideTravel = guideTravel;
	}


	public int getRequestCode() {
		return requestCode;
	}


	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}


	public int getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}


	public String getResponseContent() {
		return responseContent;
	}


	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}


	public java.sql.Date getResponseDate() {
		return responseDate;
	}


	public void setResponseDate(java.sql.Date responseDate) {
		this.responseDate = responseDate;
	}


	public int getExamineCode() {
		return examineCode;
	}


	public void setExamineCode(int examineCode) {
		this.examineCode = examineCode;
	}


	public int getGuideCode() {
		return guideCode;
	}


	public void setGuideCode(int guideCode) {
		this.guideCode = guideCode;
	}


	public int getUserCode() {
		return userCode;
	}


	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}


	public int getRegistTypeCode() {
		return registTypeCode;
	}


	public void setRegistTypeCode(int registTypeCode) {
		this.registTypeCode = registTypeCode;
	}


	public String getMeetLocation() {
		return meetLocation;
	}


	public void setMeetLocation(String meetLocation) {
		this.meetLocation = meetLocation;
	}


	public int getPayment() {
		return payment;
	}


	public void setPayment(int payment) {
		this.payment = payment;
	}


	public int getMaximum() {
		return maximum;
	}


	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}


	public int getMinimum() {
		return minimum;
	}


	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}


	@Override
	public String toString() {
		return "GuideDTO [guideNo=" + guideNo + ", guideId=" + guideId + ", guideEnrollStatus=" + guideEnrollStatus
				+ ", guideApproveStatus=" + guideApproveStatus + ", guideName=" + guideName + ", guideGender="
				+ guideGender + ", guideNickname=" + guideNickname + ", guideExp=" + guideExp + ", guideCar=" + guideCar
				+ ", guideIntro=" + guideIntro + ", guideTravel=" + guideTravel + ", requestCode=" + requestCode
				+ ", responseCode=" + responseCode + ", responseContent=" + responseContent + ", responseDate="
				+ responseDate + ", examineCode=" + examineCode + ", guideCode=" + guideCode + ", userCode=" + userCode
				+ ", registTypeCode=" + registTypeCode + ", meetLocation=" + meetLocation + ", payment=" + payment
				+ ", maximum=" + maximum + ", minimum=" + minimum + "]";
	}

	

	
	

	

	
	
    
    
    
    
}
