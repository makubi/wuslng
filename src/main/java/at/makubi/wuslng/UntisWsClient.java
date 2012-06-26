package at.makubi.wuslng;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class UntisWsClient {
	public Collection<Teacher> getAllTeachers(){
		// TODO call webuntis web service.
		
		Teacher a = new Teacher("a", 23);
		Teacher b = new Teacher("b", 42);
		
		Collection<Teacher> returnValue = new ArrayList<Teacher>();
		
		returnValue.add(a);
		returnValue.add(b);
		
		return returnValue;
	}
}
