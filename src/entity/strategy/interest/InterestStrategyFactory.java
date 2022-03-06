package entity.strategy.interest;

import entity.customer.Customer;
import entity.customer.NormalCustomer;
import entity.savingsproduct.SavingsProduct;
import entity.savingsproduct.TraditionalOnlineSavingsProduct;

public class InterestStrategyFactory {

    public static IInterestByProductCalculator getStrategyByProduct(SavingsProduct product, int period) {
       if (product instanceof TraditionalOnlineSavingsProduct) {
           return new OnlineInterestStrategy(product, period);
       }

       return null;
    }

    public static IInterestByCustomerGenreCalculator getStrategyByCustomerGenre(Customer customer) {
        if (customer instanceof NormalCustomer) {
            return new NormalInterestStrategy();
        }
        return null;
    }
}
