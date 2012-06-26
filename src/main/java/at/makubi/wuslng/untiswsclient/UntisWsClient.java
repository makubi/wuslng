package at.makubi.wuslng.untiswsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import at.makubi.webuntis.model.SchoolClass;
import at.makubi.webuntis.model.SchoolTeacher;

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
		// TODO call webuntis web service.
		
//		GetSchoolClassListRequest user = client.invoke("createUser", new Object[] { "bob", "the builder" }, User.class);
		
		webUntisService.getKlassen();
		
		Collection<SchoolTeacher> schoolTeacherCollection = new ArrayList<SchoolTeacher>();
		
		SchoolTeacher schoolTeacher1 = new SchoolTeacher();
		schoolTeacher1.setBackColor(""+1);
		schoolTeacher1.setForeColor(""+1);
		schoolTeacher1.setName(""+1);
		
		SchoolTeacher schoolTeacher2 = new SchoolTeacher();
		schoolTeacher2.setBackColor(""+2);
		schoolTeacher2.setForeColor(""+2);
		schoolTeacher2.setName(""+2);
		
		schoolTeacherCollection.add(schoolTeacher1);
		schoolTeacherCollection.add(schoolTeacher2);
		
		return schoolTeacherCollection;
	}

	public Collection<SchoolClass> getAllSchoolClasses() {
		Collection<SchoolClass> schoolClassCollection = new ArrayList<SchoolClass>();
		
		SchoolClass schoolClass1 = new SchoolClass();
		schoolClass1.setBackColor(""+1);
		schoolClass1.setForeColor(""+1);
		schoolClass1.setName(""+1);
		
		SchoolClass schoolClass2 = new SchoolClass();
		schoolClass2.setBackColor(""+2);
		schoolClass2.setForeColor(""+2);
		schoolClass2.setName(""+2);
		
		schoolClassCollection.add(schoolClass1);
		schoolClassCollection.add(schoolClass2);
		
		return schoolClassCollection;
	}
	
	public Collection<SchoolClass> getAllSchoolSubjects() {
		return null;
	}
	
	public Collection<SchoolClass> getAllSchoolRooms() {
		return null;
	}
}
