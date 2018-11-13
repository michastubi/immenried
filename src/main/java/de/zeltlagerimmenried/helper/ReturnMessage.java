package de.zeltlagerimmenried.helper;

public class ReturnMessage {

	private String status;
	
	public ReturnMessage(){
		status = "success";
	}
	
	public ReturnMessage(boolean success){
		if (success) {
			status = "success";
		}
		else {
			status = "error";
		}
	}
	
	public ReturnMessage(String msg){
		this.setStatus(msg);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSuccess() {
		this.status = "success";
	}
}
