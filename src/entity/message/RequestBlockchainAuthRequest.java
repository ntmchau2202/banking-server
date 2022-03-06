package entity.message;

public class RequestBlockchainAuthRequest extends RequestMessage {
	private String bankAccountNumber;
	private String savingsAccountNumber;
	private String initialAmount;
	private String interestRate;
	private String savingsPeriod;
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}


	public String getSavingsAccountNumber() {
		return savingsAccountNumber;
	}


	public String getInitialAmount() {
		return initialAmount;
	}


	public String getInterestRate() {
		return interestRate;
	}


	public String getSavingsPeriod() {
		return savingsPeriod;
	}


	public String getCurrency() {
		return currency;
	}


	private String currency;
	
	
	RequestBlockchainAuthRequest(Command cmd, String bankAccNum, String savingsAccNum, float initialAmt, float interestRate, int savingsPeriod, String currency) {
		super(cmd);
		this.bankAccountNumber = bankAccNum;
		this.savingsAccountNumber = savingsAccNum;
		this.initialAmount = Float.toString(initialAmt);
		this.interestRate = Float.toString(interestRate);
		this.savingsPeriod = Integer.toString(savingsPeriod);
		this.currency = currency;
	}

	
	
}

