package entity.strategy.interest;

public class NormalInterestStrategy implements IInterestByCustomerGenreCalculator {

    public NormalInterestStrategy() {

    }


    @Override
    public float getEffectiveInterest(float baseInterest) {
        return (float) (baseInterest + 0.2);
    }
}
