package at.makubi.wuslng.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.makubi.webuntis.model.SchoolTeacher;
import at.makubi.wuslng.untiswsclient.UntisWsClient;

import com.sun.jersey.api.Responses;

@Service
class SchoolTeacherServiceImpl implements SchoolTeacherService {
	
	private UntisWsClient untisWsClient;
	
	@Autowired 
	public SchoolTeacherServiceImpl(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@Override
	public Collection<SchoolTeacher> getAllSchoolTeachersAsJson() throws IllegalAccessException, InvocationTargetException{
		Collection<SchoolTeacher> allSchoolTeachers = new ArrayList<SchoolTeacher>();
		
		for (at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolTeacher jsonSchoolTeacher : untisWsClient.getAllSchoolTeachers()) {
			SchoolTeacher schoolTeacher = new SchoolTeacher();
			BeanUtils.copyProperties(schoolTeacher, jsonSchoolTeacher);
			allSchoolTeachers.add(schoolTeacher);
		}
		
		return allSchoolTeachers;
	}
	
	@Override
	public Response getSchoolTeacherByName(String schoolTeacherName) throws IllegalAccessException, InvocationTargetException{
		SchoolTeacher responseData = null;
		
		for(at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolTeacher schoolTeacher : untisWsClient.getAllSchoolTeachers()){
			if(schoolTeacher.getName().equals(schoolTeacherName)){
				responseData = new SchoolTeacher();
				BeanUtils.copyProperties(responseData, schoolTeacher);
				break;
			}
		}
		
		return responseData != null ? Response.ok().entity(responseData).build() : Response.status(Responses.NOT_FOUND).entity("No class with name "+schoolTeacherName+" found").build();
	}
	
}
