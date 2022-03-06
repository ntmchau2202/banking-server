package entity.message;

import org.json.JSONObject;

import entity.customer.Customer;

public class LoginResponse extends ResponseMessage {

	public LoginResponse(Status status, String message) {
		super(status);
		super.setErrorMessage(message);
	}
	
	public LoginResponse(Status status, Customer customer) {
		super(status);
		JSONObject customerJSON = new JSONObject();
		customerJSON.put("customer", customer);
		super.setDetails(customerJSON);
	}
	
}
