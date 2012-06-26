package at.makubi.wuslng.untiswsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.springframework.stereotype.Component;

import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass;
import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolRoom;
import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolSubject;
import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolTeacher;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

@Component
public class UntisWsClient {
	
	private JsonRpcHttpClient client;
	private WebUntisService webUntisService;
	
	public UntisWsClient() throws MalformedURLException {
		// TODO statische url
		client = new JsonRpcHttpClient(new URL("http://example.com/UserService.json"));
		webUntisService = ProxyUtil.createProxy(getClass().getClassLoader(), WebUntisService.class, client);
	}
	
	public Collection<SchoolTeacher> getAllSchoolTeachers(){	
		return webUntisService.getTeachers();
	}

	public Collection<SchoolClass> getAllSchoolClasses() {
		return webUntisService.getKlassen();
	}
	
	public Collection<SchoolSubject> getAllSchoolSubjects() {
		return webUntisService.getSubjects();
	}
	
	public Collection<SchoolRoom> getAllSchoolRooms() {
		return webUntisService.getRooms();
	}
}
