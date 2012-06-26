package at.makubi.wuslng;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.Responses;

@Service
@Path("teachers")
public class TeacherService {
	
	private final UntisWsClient untisWsClient;

	@Autowired
	public TeacherService(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Teacher> getAllTeacherAsJson(){
		return untisWsClient.getAllTeachers();
	}
	
	@GET
	@Path("/{teacherName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeacherByName(@PathParam("teacherName") String teacherName){
		Teacher toReturn = null;
		for(Teacher teacher : untisWsClient.getAllTeachers()){
			if(teacher.getName().equals(teacherName)){
				toReturn = teacher;
				break;
			}
		}
		
		if(toReturn == null){
			return Response.status(Responses.NOT_FOUND).entity("no teacher with the name "+teacherName+"  found").build();
		} else {
			return Response.ok().entity(toReturn).build();
		}
	}
	
}
