package entity.strategy.interest;

public class VIPInterestStrategy implements IInterestByCustomerGenreCalculator {
    public static float additionalInterest = (float) 0.2;
    public VIPInterestStrategy() {

    }

    @Override
    public float getEffectiveInterest(float baseInterest) {
        return baseInterest + additionalInterest;
    }
}
