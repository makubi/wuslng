package at.makubi.wuslng.untiswsclient;

import java.util.List;

import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;

public interface WebUntisService {
	@JsonRpcErrors({ @JsonRpcError(exception=
			RuntimeException.class, code=-8520, message="You are not authenticated for this lulz.") })
	public List<SchoolClass> getKlassen();
	
	public AuthenticationToken authenticate(String user, String password);

}
