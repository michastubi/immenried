package de.zeltlagerimmenried.helper;

public class ReturnMessage {

	private String message;
	
	public ReturnMessage(){
	}
	
	public ReturnMessage(boolean success){
		if (success) {
			message = "success";
		}
		else {
			message = "failure";
		}
	}
	
	public ReturnMessage(String msg){
		this.setMessage(msg);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setSuccess() {
		this.message = "Success";
	}
}
