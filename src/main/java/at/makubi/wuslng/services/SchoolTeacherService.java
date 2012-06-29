package at.makubi.wuslng.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.makubi.webuntis.model.SchoolTeacher;

@Path("schoolTeachers")
public interface SchoolTeacherService {

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Collection<SchoolTeacher> getAllSchoolTeachersAsJson()
			throws IllegalAccessException, InvocationTargetException;

	@GET
	@Path("/{schoolTeacherName}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Response getSchoolTeacherByName(
			@PathParam("schoolTeacherName") String schoolTeacherName)
			throws IllegalAccessException, InvocationTargetException;

}