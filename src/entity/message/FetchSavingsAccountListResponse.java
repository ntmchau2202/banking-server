package entity.message;

import java.util.ArrayList;

import org.json.JSONObject;

import entity.savingsaccount.SavingsAccount;

public class FetchSavingsAccountListResponse extends ResponseMessage {
	private ArrayList<SavingsAccount> listSavingsAccount = new ArrayList<SavingsAccount>();
	public FetchSavingsAccountListResponse(Status status, String message) {
		super(status);
		if (status.equals(Status.error)) {
			this.setErrorMessage(message);
		}
	}
	
	public FetchSavingsAccountListResponse(Status status, ArrayList<SavingsAccount> details) {
		super(status);
		if(status.equals(Status.success)) {
			this.listSavingsAccount = details;
			JSONObject listAcc = new JSONObject();
			listAcc.put("savings_accounts", details);
		}
	}
	
	public ArrayList<SavingsAccount> getListSavingsAccount() {
		return this.listSavingsAccount;
	}
	
	
}
