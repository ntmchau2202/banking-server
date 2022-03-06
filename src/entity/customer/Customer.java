package entity.customer;

import entity.bankaccount.BankAccount;
import entity.savingsaccount.SavingsAccount;

import java.util.ArrayList;

public class Customer {
    private int customerID;
    private ArrayList<BankAccount> listBankAccount = new ArrayList<BankAccount>();
    
    private String username, phoneNumber;
    private ArrayList<SavingsAccount> listSavingsAccount = new ArrayList<SavingsAccount>();

    public Customer(String username, int customerID, String phoneNumber) {
        this.username = username;
        this.customerID = customerID;
        this.phoneNumber = phoneNumber;
    }
    
    public void addBankAccount(BankAccount bankAccount) {
    	for(BankAccount acc : listBankAccount) {
    		if(acc.getAccNumber().equals(bankAccount.getAccNumber())) {
    			return;
    		}
    	}
    	listBankAccount.add(bankAccount);
    }

    public void addSavingAccount(SavingsAccount newAccount) {
        if (listSavingsAccount.contains(newAccount)) {
            return;
        }
        listSavingsAccount.add(newAccount);
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public ArrayList<BankAccount> getListBankAccount() {
        return this.listBankAccount;
    }
    
    public String getCustomerName() {
    	return this.username;
    }
    
    public String getCustomerPhoneNumber() {
    	return this.phoneNumber;
    }

    public ArrayList<SavingsAccount> getListSavingsAccount() {
        return this.getListSavingsAccount();
    }

    public boolean hasSavingsAccount(String savingAccountID) {
        for(SavingsAccount a: listSavingsAccount) {
            if(a.getSavingAccountID().equals(savingAccountID)) {
                return true;
            }
        }
        return false;
    }
}
