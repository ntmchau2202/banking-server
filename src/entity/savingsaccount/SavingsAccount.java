package entity.savingsaccount;

import entity.customer.Customer;
import entity.savingsproduct.SavingsProduct;

public class SavingsAccount {
    public enum SettleType {
        SETTLE_ALL,
        ROLLOVER_PRINCIPLE,
        ROLLOVER_PRINCIPLE_INTEREST;
        
        public static SettleType convertToSettleType(String type) {
        	for (SettleType t: SettleType.values()) {
        		if(type.equalsIgnoreCase(t.toString())) {
        			return t;
        		}
        	}
        	return null;
        }
    }


    private String savingAccountID;
    private SavingsProduct productType;
    private float savingAmount;
    private float interestAmount;
    private String startDate, endDate;
    private int savingsPeriod;
    private SettleType settleType;
    private Customer customer;

    public SavingsAccount(String savingAccountID, SavingsProduct savingsProduct, float savingAmount, int savingsPeriod, float interestAmount, Customer customer, String startTime, SettleType settleInstruction) {
        this.savingAccountID = savingAccountID;
        this.productType = savingsProduct;
        this.savingAmount = savingAmount;
        this.savingsPeriod = savingsPeriod;
        this.interestAmount = interestAmount;
        this.customer = customer;
        this.startDate = startTime;
        this.settleType = settleInstruction;
    }

    public String getSavingAccountID() {
        return this.savingAccountID;
    }

    public SavingsProduct getProductType() {
        return this.productType;
    }

    public float getSavingAmount() {
        return this.savingAmount;
    }

    public float getInterestAmount() {
        return this.interestAmount;
    }

    public int getSavingsPeriod() {
        return this.savingsPeriod;
    }

    public SettleType getSettleType() {
        return this.settleType;
    }

    public int getOwnerID() {
        return this.customer.getCustomerID();
    }
    
    public String getOpenDate() {
    	return this.startDate;
    }
    
    public String getSettleDate() {
    	return this.endDate;
    }
}
