package entity.customer;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import database.DBConnector;
import entity.bankaccount.BankAccount;
import entity.savingsaccount.SavingsAccount;
import entity.savingsproduct.SavingsProductFactory;

public class CustomerFactory {
	
	private static ArrayList<String> CustomerType = new ArrayList<String>();
	public static boolean isCustomerTypeExist(String type) {
		return CustomerType.contains(type);
	}
	
	public static void putCustomerType(String type) {
		if(isCustomerTypeExist(type)) {
			return;
		}
		
		CustomerType.add(type);
	}
	
	
	public static Customer getCustomerByPhone(String phone) throws Exception {
		JSONObject json = DBConnector.getDatabaseConnection().getCustomerByPhone(phone);
		String customerType = json.getString("customer_type");
		if (!isCustomerTypeExist(customerType)) {
			return null;
		}
		Class<?> clz = Class.forName(customerType);
		Constructor<?> cstr = clz.getConstructor(String.class, int.class, String.class);
		Customer customer = (Customer) cstr.newInstance(json.getString("customer_name"),
											json.getInt("customer_id"),
											json.getString("customer_phone"));
		// get bank account
		json = DBConnector.getDatabaseConnection().getBankAccountOfCustomer(customer.getCustomerID());
		JSONArray bankAccountList = json.getJSONArray("bankaccount_id");
		for(int i = 0; i < bankAccountList.length(); i++) {
			JSONObject bankAccRawInf = bankAccountList.getJSONObject(i);
			BankAccount acc = new BankAccount(customer, bankAccRawInf.getString("bankaccount_id"), (float)bankAccRawInf.getDouble("bankaccount_balance"));
			customer.addBankAccount(acc);			
		}
		
		// get savings account
		json = DBConnector.getDatabaseConnection().getSavingsAccountOfCustomer(customer.getCustomerID());
		JSONArray savingsAccountList = json.getJSONArray("savingsaccount_id");
		for(int i = 0; i < savingsAccountList.length(); i++) {
			String savingsAccNum = savingsAccountList.getString(i);
			JSONObject savingsAccRawInf = DBConnector.getDatabaseConnection().getSavingsAccountByID(savingsAccNum);
			SavingsAccount savingsAcc = new SavingsAccount(savingsAccRawInf.getString("savingsaccount_id"),
							SavingsProductFactory.getSavingsProductByName(savingsAccRawInf.getString("type")),
							(float)savingsAccRawInf.getDouble("amount"),
							savingsAccRawInf.getInt("period"),
							(float)savingsAccRawInf.getDouble("interest_amount"),
							customer,
							savingsAccRawInf.getString("open_time"),
							SavingsAccount.SettleType.convertToSettleType(savingsAccRawInf.getString("settle_instruction")));
			customer.addSavingAccount(savingsAcc);
		}
		
		return customer;
	}
	
	
}
