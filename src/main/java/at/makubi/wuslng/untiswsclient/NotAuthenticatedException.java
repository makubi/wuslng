package at.makubi.wuslng.untiswsclient;

import com.googlecode.jsonrpc4j.JsonRpcClientException;

public class NotAuthenticatedException extends JsonRpcClientException {
	
	private static final long serialVersionUID = 1L;

	public NotAuthenticatedException(JsonRpcClientException e) {
		super(e.getCode(), e.getMessage(), e.getData());
	}

}
