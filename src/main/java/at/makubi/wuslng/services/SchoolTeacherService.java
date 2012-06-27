package at.makubi.wuslng.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.makubi.webuntis.model.SchoolTeacher;
import at.makubi.wuslng.untiswsclient.UntisWsClient;

import com.sun.jersey.api.Responses;

@Service
@Path("schoolTeachers")
public class SchoolTeacherService {
	
	private final UntisWsClient untisWsClient;

	@Autowired
	public SchoolTeacherService(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SchoolTeacher> getAllSchoolTeachersAsJson() throws IllegalAccessException, InvocationTargetException{
		Collection<SchoolTeacher> allSchoolTeachers = new ArrayList<SchoolTeacher>();
		
		for (at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolTeacher jsonSchoolTeacher : untisWsClient.getAllSchoolTeachers()) {
			SchoolTeacher schoolTeacher = new SchoolTeacher();
			BeanUtils.copyProperties(schoolTeacher, jsonSchoolTeacher);
			allSchoolTeachers.add(schoolTeacher);
		}
		
		return allSchoolTeachers;
	}
	
	@GET
	@Path("/{schoolTeacherName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchoolTeacherByName(@PathParam("schoolTeacherName") String schoolTeacherName) throws IllegalAccessException, InvocationTargetException{
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
