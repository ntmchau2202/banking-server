package controller;

import entity.message.RequestBlockchainAuthResponse;

public class ResponseBlockchainAuthController extends ServiceController {
	public ResponseBlockchainAuthController(RequestBlockchainAuthResponse resp) {
		super(resp);
	}
	
	public void updateTransactionStatus() {
		// TODO: update account confirmation status;
	}
	
}
