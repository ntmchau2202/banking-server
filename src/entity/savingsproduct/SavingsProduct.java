package entity.savingsproduct;

import java.util.HashMap;
import java.util.Map;

public abstract class SavingsProduct {
    private String savingProductName;
    private String productID;
    private Map<Integer,Float> interestRateMap = new HashMap<Integer, Float>();


    public SavingsProduct(String savingProductName, String productID) {
        this.savingProductName = savingProductName;
        this.productID = productID;
    }

    public void addNewBaseInterestRate(int month, float interest) {
        if (interestRateMap.containsKey(month)) {
            return;
        }

        interestRateMap.put(month, interest);
    }

    public String getSavingProductName() {
        return this.savingProductName;
    }

    public float getBaseInterestRate(int month) {
        return this.interestRateMap.get(month);
    }
    
    public String getSavingsProductID() {
    	return this.productID;
    }
}
