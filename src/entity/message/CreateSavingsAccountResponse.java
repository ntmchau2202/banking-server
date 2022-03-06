package entity.message;

import org.json.JSONObject;

public class CreateSavingsAccountResponse extends ResponseMessage {
	private String savingsAccountID;
	public CreateSavingsAccountResponse(Status status, String message) throws Exception {
		super(status);
		JSONObject obj = new JSONObject();
		if(status.equals(Status.error)) {
			this.setErrorMessage(message);
		}
		
		if (status.equals(Status.success)) {
			obj.put("savingsaccount_id", message);
			savingsAccountID = message;
		}
	}
	
	public String getSavingsAccountID() {
		return this.savingsAccountID;
	}
	
}
