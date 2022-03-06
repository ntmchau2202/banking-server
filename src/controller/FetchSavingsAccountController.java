package controller;

import entity.customer.Customer;
import entity.customer.CustomerFactory;
import entity.message.FetchSavingsAccountListRequest;
import entity.message.FetchSavingsAccountListResponse;
import entity.message.RequestMessage;
import entity.message.ResponseMessage.Status;

public class FetchSavingsAccountController extends ServiceController {

	public FetchSavingsAccountController(RequestMessage req) {
		super(req);
	}
	
	public FetchSavingsAccountListResponse fetchSavingsAccounts() throws Exception {
		FetchSavingsAccountListRequest req = (FetchSavingsAccountListRequest) this.getRequest();
		Customer customer = CustomerFactory.getCustomerByPhone(req.getCustomerPhone());
		FetchSavingsAccountListResponse resp = new FetchSavingsAccountListResponse(Status.success, customer.getListSavingsAccount());
		return resp;
	}

}
