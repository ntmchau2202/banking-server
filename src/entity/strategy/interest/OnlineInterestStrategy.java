package entity.strategy.interest;

import entity.savingsproduct.SavingsProduct;

public class OnlineInterestStrategy implements IInterestByProductCalculator {
    public static float additionalInterest = (float) 0.3;
    private SavingsProduct product;
    private int savingsPeriod;
    public OnlineInterestStrategy(SavingsProduct product, int period) {
        this.product = product;
        this.savingsPeriod = period;
    }

    @Override
    public float getEffectiveInterest() {
        return product.getBaseInterestRate(this.savingsPeriod) + additionalInterest;
    }
}
