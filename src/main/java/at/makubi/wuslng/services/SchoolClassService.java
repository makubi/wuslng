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

import at.makubi.webuntis.model.SchoolClass;
import at.makubi.wuslng.UntisWsClient;

import com.sun.jersey.api.Responses;

@Service
@Path("schoolClasses")
public class SchoolClassService {

	private final UntisWsClient untisWsClient;

	@Autowired
	public SchoolClassService(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SchoolClass> getAllSchoolClassesAsJson(){
		return untisWsClient.getAllSchoolClasses();
	}
	
	@GET
	@Path("/{schoolClassName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchoolClassByName(@PathParam("schoolClassName") String schoolClassName){
		SchoolClass responseData = null;
		
		for(SchoolClass schoolClass : untisWsClient.getAllSchoolClasses()){
			if(schoolClass.getName().equals(schoolClassName)){
				responseData = schoolClass;
				break;
			}
		}
		
		return responseData != null ? Response.ok().entity(responseData).build() : Response.status(Responses.NOT_FOUND).entity("No class with name "+schoolClassName+" found").build();
	}

}