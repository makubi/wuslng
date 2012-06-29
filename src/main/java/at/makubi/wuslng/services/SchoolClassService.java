package at.makubi.wuslng.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.makubi.webuntis.model.SchoolClass;

@Path("schoolClasses")
public interface SchoolClassService {

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Collection<SchoolClass> getAllSchoolClassesAsJson()
			throws IllegalAccessException, InvocationTargetException;

	@GET
	@Path("/{schoolClassName}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getSchoolClassByName(
			@PathParam("schoolClassName") String schoolClassName)
			throws IllegalAccessException, InvocationTargetException;

}