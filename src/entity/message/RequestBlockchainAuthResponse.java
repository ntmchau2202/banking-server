package entity.message;

public class RequestBlockchainAuthResponse extends ResponseMessage {
	
	RequestBlockchainAuthResponse(String resp) {
		super(resp);
		if(this.getStatus().equals(Status.success)) {
			// TODO: need to unify the response of blockchain server for this message.
		}
	}

}
