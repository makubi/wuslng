package at.makubi.wuslng.services;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.makubi.webuntis.model.SchoolTeacher;
import at.makubi.wuslng.UntisWsClient;

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
	public Collection<SchoolTeacher> getAllSchoolTeachersAsJson(){
		return untisWsClient.getAllSchoolTeachers();
	}
	
	@GET
	@Path("/{schoolTeacherName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchoolTeacherByName(@PathParam("schoolTeacherName") String schoolTeacherName){
		SchoolTeacher responseData = null;
		
		for(SchoolTeacher teacher : untisWsClient.getAllSchoolTeachers()){
			if(teacher.getName().equals(schoolTeacherName)){
				responseData = teacher;
				break;
			}
		}
		
		return responseData != null ? Response.ok().entity(responseData).build() : Response.status(Responses.NOT_FOUND).entity("No teacher with name "+schoolTeacherName+" found").build();
	}
	
}
