package entity.message;

public class FetchSavingsAccountListRequest extends RequestMessage {
	private String customerPhone;
	private String bankAccountID;
	FetchSavingsAccountListRequest(String req) throws Exception {
		super(req);
		checkCommand(Command.FETCH_LIST_SAVINGS_ACCOUNT);
		this.customerPhone = this.getMessageObject().getString("customer_phone");
		this.bankAccountID = this.getMessageObject().getString("bank_account");
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public String getBankAccountID() {
		return bankAccountID;
	}

	
}
