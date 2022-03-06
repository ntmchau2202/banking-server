package controller;

import org.json.JSONObject;

import database.DBConnector;
import entity.customer.Customer;
import entity.customer.CustomerFactory;
import entity.message.LoginRequest;
import entity.message.LoginResponse;
import entity.message.ResponseMessage;

public class LoginController extends ServiceController {
	private String customerPhone;
	private String password;
	
	public LoginController(LoginRequest request) {
		super(request);
		this.customerPhone = request.getLoginPhone();
		this.password = request.getLoginPassword();
	}
	
	private boolean isPasswordMatch(String customerPhone, String password) throws Exception {
		JSONObject cre = DBConnector.getDatabaseConnection().getCustomerLoginDetails(customerPhone);
		String registeredPass = cre.getString("password");
		if(password.equals(registeredPass)) {
			return true;
		}
		return false;
	}
	
	public LoginResponse login() throws Exception {
		if(isPasswordMatch(customerPhone, password)) {
			Customer customer = CustomerFactory.getCustomerByPhone(customerPhone);
			return new LoginResponse(ResponseMessage.Status.success, customer);
		} else {
			return new LoginResponse(ResponseMessage.Status.error, "invalid phone number or password");
		}
	}
}
