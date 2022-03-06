package entity.message;

import org.json.JSONObject;

public abstract class ResponseMessage {
	public enum Status {
		error,
		success;
		
		public static Status toStatus(String stat) {
			for(Status s : Status.values()) {
				if(s.toString().equals(stat)) {
					return s;
				}
			}
			return null;
		}
	}
	private Status status;
	String errorMessage;
	private JSONObject details;
	
	ResponseMessage(Status status) {
		this.status = status;
	}
	
	ResponseMessage(String resp) {
		JSONObject obj = new JSONObject(resp);
		this.status = Status.toStatus(obj.getString("status"));
		this.details = obj.getJSONObject("details");
		if(status.equals(Status.error)) {
			setErrorMessage(details.getString("message"));
		}
	}
	
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("status", status.toString());
		json.put("details", details);
		
		return json.toString();
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public Object getDetails() {
		return this.details;
	}
	
	void setDetails(JSONObject details) {
		this.details = details;
	}
	
	void setErrorMessage(String msg) {
		this.errorMessage = msg;
		this.details.put("message", msg);
	}
	
	public String getErrorMessage() {
		if (this.status.equals(Status.error)) {
			return this.errorMessage;
		}
		
		return null;
	}
	
}
