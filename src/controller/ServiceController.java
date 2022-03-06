package controller;

import entity.message.RequestMessage;
import entity.message.ResponseMessage;

public abstract class ServiceController {
	RequestMessage request;
	ResponseMessage response;
	
	public ServiceController() {
		
	}
	
	public ServiceController(RequestMessage req) {
		this.request = req;
	}
	
	public ServiceController(ResponseMessage resp) {
		this.response = resp;
	}
	
	public ServiceController(RequestMessage req, ResponseMessage resp) {
		this.request = req;
		this.response = resp;
	}

	public RequestMessage getRequest() {
		return request;
	}

	public ResponseMessage getResponse() {
		return response;
	}
	
	
}
