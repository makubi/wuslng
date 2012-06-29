package at.makubi.wuslng.untiswsclient;

import java.util.Collection;

import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass;
import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolRoom;
import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolSubject;
import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolTeacher;

import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;

public interface WebUntisService {
	
	
	public Collection<SchoolClass> getKlassen();
	
	public Collection<SchoolTeacher> getTeachers();
	
	public Collection<SchoolRoom> getRooms();
	
	public Collection<SchoolSubject> getSubjects();
	
	public AuthenticationToken authenticate(String user, String password);
	
}
