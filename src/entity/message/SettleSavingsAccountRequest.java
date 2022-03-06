package entity.message;

public class SettleSavingsAccountRequest extends RequestMessage {
	private String customerPhone;
	private String customerName;
	private String bankAccNumber;
	private String timeIssued;
	private String savingsType;
	private float savingsAmount;
	private float actualInterestAmount;
	private String currency;

	public SettleSavingsAccountRequest(String req) throws Exception {
		super(req);
		checkCommand(Command.SETTLE_ONLINE_SAVINGS_ACCOUNT);
		this.customerPhone = this.getMessageObject().getString("customer_phone");
		this.customerName = this.getMessageObject().getString("customer_name");
		this.bankAccNumber = this.getMessageObject().getString("bank_account");
		this.timeIssued = this.getMessageObject().getString("time_issued");
		this.savingsType = this.getMessageObject().getString("savings_type");	
		
		try {
			String savingsAmount = this.getMessageObject().getString("savings_amount");
			this.savingsAmount = Float.parseFloat(savingsAmount);
		} catch (Exception e){
			this.throwBadRequestException("savings_amount");
		}
		
		try {
			String actualInterestAmount = this.getMessageObject().getString("estimated_interest_amount");
			this.actualInterestAmount = Float.parseFloat(actualInterestAmount);
		} catch (Exception e) {
			this.throwBadRequestException("estimated_interest_amount");
		}
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getBankAccNumber() {
		return bankAccNumber;
	}

	public String getTimeIssued() {
		return timeIssued;
	}

	public String getSavingsType() {
		return savingsType;
	}

	public float getSavingsAmount() {
		return savingsAmount;
	}

	public float getActualInterestAmount() {
		return actualInterestAmount;
	}

	public String getCurrency() {
		return currency;
	}
}
