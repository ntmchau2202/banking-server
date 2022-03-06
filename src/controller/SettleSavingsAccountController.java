package controller;

import entity.message.RequestMessage;
import entity.message.SettleSavingsAccountRequest;
import entity.message.SettleSavingsAccountResponse;
import entity.message.ResponseMessage.Status;

public class SettleSavingsAccountController extends ServiceController {

	public SettleSavingsAccountController(RequestMessage req) {
		super(req);
	}
	
	public SettleSavingsAccountResponse settleAccount() throws Exception {
		SettleSavingsAccountRequest req = (SettleSavingsAccountRequest) this.getRequest();
		// TODO: do something to modify the savings account
		// should save a list of savings account in system?
		// anw must modify in the database
		return new SettleSavingsAccountResponse(Status.success);
	}
	
}
