package entity.message;

import org.json.JSONObject;

public abstract class RequestMessage {
	public enum Command {
		LOGIN,
		SETTLE_ONLINE_SAVINGS_ACCOUNT,
		CREATE_ONLINE_SAVINGS_ACCOUNT,
		FETCH_LIST_SAVINGS_ACCOUNT;
		
		public static Command toCommand(String cmd) {
			for(Command c: Command.values()) {
				if(c.toString().equals(cmd)) {
					return c;
				}
			}
			return null;
		}
	}
	
	private Command command;
	JSONObject request;
	RequestMessage(String req) {
		request = new JSONObject(req);
		command = Command.toCommand(request.getString("command"));
	}
	
	RequestMessage(Command cmd)	{
		command = cmd;
	}
	public Command getCommand() {
		return this.command;
	}
	
	JSONObject getMessageObject() {
		return this.request;
	}
	
	void checkCommand(Command targetCmd) throws Exception {
		if(this.command == null) {
			throw new Exception("missing command");
		}
		
		if(!this.command.equals(targetCmd)) {
			throw new Exception("command does not match");			
		}
	}
	
	void throwBadRequestException() throws Exception {
		throw new Exception("bad request");
	}
	
	void throwBadRequestException(String msg) throws Exception {
		throw new Exception("bad request for field " + msg);
	}
}
