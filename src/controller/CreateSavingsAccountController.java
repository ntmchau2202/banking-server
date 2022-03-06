package controller;

import java.util.concurrent.Semaphore;

import entity.customer.CustomerFactory;
import entity.message.CreateSavingsAccountRequest;
import entity.message.CreateSavingsAccountResponse;
import entity.message.ResponseMessage.Status;
import entity.savingsaccount.SavingsAccount;
import entity.savingsproduct.SavingsProductFactory;

public class CreateSavingsAccountController extends ServiceController {
	private static Semaphore mutex = new Semaphore(1);
	private static int savingsAccountID = 0;
	
	public CreateSavingsAccountController(CreateSavingsAccountRequest req) {
		super(req);
	}
	
	public CreateSavingsAccountResponse createNewSavingsAccount() throws Exception {
		CreateSavingsAccountRequest req = (CreateSavingsAccountRequest) this.getRequest();
		mutex.acquire();
		savingsAccountID++;
		mutex.release();
		String savingsAccountIDStr = Integer.toString(savingsAccountID);
		SavingsAccount acc = new SavingsAccount(savingsAccountIDStr,
				SavingsProductFactory.getSavingsProductByName(req.getSavingsType()),
				req.getSavingsAmount(),
				req.getSavingsPeriod(),
				req.getEstimatedInterestAmount(),
				CustomerFactory.getCustomerByPhone(req.getCustomerPhone()),
				"", // time
				req.getSettleInstruction());
		// TODO: save to database;
		CreateSavingsAccountResponse resp = new CreateSavingsAccountResponse(Status.success, savingsAccountIDStr);
		return resp;
	}
	
}
