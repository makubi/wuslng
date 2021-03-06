package at.makubi.wuslng.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.makubi.webuntis.model.SchoolClass;
import at.makubi.wuslng.untiswsclient.UntisWsClient;

import com.sun.jersey.api.Responses;

@Service
class SchoolClassServiceImpl implements SchoolClassService {
	
	private UntisWsClient untisWsClient;
	
	@Autowired 
	public SchoolClassServiceImpl(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@Override
	public Collection<SchoolClass> getAllSchoolClassesAsJson() throws IllegalAccessException, InvocationTargetException{
		Collection<SchoolClass> allSchoolClasses = new ArrayList<SchoolClass>();
		
		for (at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass jsonSchoolClass : untisWsClient.getAllSchoolClasses()) {
			SchoolClass schoolClass = new SchoolClass();
			BeanUtils.copyProperties(schoolClass, jsonSchoolClass);
			allSchoolClasses.add(schoolClass);
		}
		
		return allSchoolClasses;
	}
	
	@Override
	public Response getSchoolClassByName(String schoolClassName) throws IllegalAccessException, InvocationTargetException{
		SchoolClass responseData = null;
		
		for(at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass schoolClass : untisWsClient.getAllSchoolClasses()){
			if(schoolClass.getName().equals(schoolClassName)){
				responseData = new SchoolClass();
				BeanUtils.copyProperties(responseData, schoolClass);
				break;
			}
		}
		
		return responseData != null ? Response.ok().entity(responseData).build() : Response.status(Responses.NOT_FOUND).entity("No class with name "+schoolClassName+" found").build();
	}

}
