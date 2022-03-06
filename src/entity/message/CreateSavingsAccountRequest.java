package entity.message;

import entity.savingsaccount.SavingsAccount;

public class CreateSavingsAccountRequest extends RequestMessage {
	private String customerPhone;
	private String customerName;
	private String bankAccNumber;
	private String timeIssued;
	private String savingsType;
	private float savingsAmount;
	private int savingsPeriod;
	private float interestRate;
	private float estimatedInterestAmount;
	private String currency;
	private SavingsAccount.SettleType settleInstruction;
	

	public CreateSavingsAccountRequest(String req) throws Exception{
		super(req);
		checkCommand(Command.CREATE_ONLINE_SAVINGS_ACCOUNT);
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
			String savingsPeriod = this.getMessageObject().getString("savings_period");
			this.savingsPeriod = Integer.parseInt(savingsPeriod);
		} catch (Exception e) {
			this.throwBadRequestException("savings_period");
		}
		
		try {
			String interestRate = this.getMessageObject().getString("interest_rate");
			this.interestRate = Float.parseFloat(interestRate);
		} catch (Exception e) {
			this.throwBadRequestException("interest_rate");
		}
		
		try {
			String estimatedInterestAmount = this.getMessageObject().getString("estimated_interest_amount");
			this.estimatedInterestAmount = Float.parseFloat(estimatedInterestAmount);
		} catch (Exception e) {
			this.throwBadRequestException("estimated_interest_amount");
		}
		
		try {
			this.settleInstruction = SavingsAccount.SettleType.convertToSettleType(this.getMessageObject().getString("settle_instruction"));
		} catch (Exception e) {
			this.throwBadRequestException("settle_type");
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


	public int getSavingsPeriod() {
		return savingsPeriod;
	}


	public float getInterestRate() {
		return interestRate;
	}


	public float getEstimatedInterestAmount() {
		return estimatedInterestAmount;
	}


	public String getCurrency() {
		return currency;
	}


	public SavingsAccount.SettleType getSettleInstruction() {
		return settleInstruction;
	}
	
}
