package entity.bankaccount;

import entity.customer.Customer;

public class BankAccount {
	private Customer customer;
	private String bankAccNumber;
	private float balance;
	
	public BankAccount(Customer customer, String bankAccNumber, float balance) {
		this.customer = customer;
		this.bankAccNumber = bankAccNumber;
		this.balance = balance;
	}
	
	public Customer getOwner() {
		return this.customer;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public String getAccNumber() {
		return this.bankAccNumber;
	}
}
