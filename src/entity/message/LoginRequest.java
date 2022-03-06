package entity.message;

public class LoginRequest extends RequestMessage {
	private String customerPhone;
	private String password;
	
	public LoginRequest(String req) throws Exception {
		super(req);
		checkCommand(Command.LOGIN);	
		this.customerPhone = this.getMessageObject().getString("customer_phone");
		this.password = this.getMessageObject().getString("password");
	}
	
	public String getLoginPhone() {
		return this.customerPhone;
	}
	
	public String getLoginPassword() {
		return this.password;
	}
	
	
}
