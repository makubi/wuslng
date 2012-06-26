package at.makubi.wuslng;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.Responses;

public class TeacherServiceTest {

	private TeacherService teacherService;
	private UntisWsClient untisWsClientMock;
	
	private final Collection<Teacher> teacherCollection = new ArrayList<Teacher>();
	private final Teacher foo = new Teacher("foo", 23);
	private final Teacher bar = new Teacher("bar", 42);
	
	@Before
	public void setUp() {
		untisWsClientMock = mock(UntisWsClient.class);
		teacherService = new TeacherService(untisWsClientMock);
		
		teacherCollection.add(foo);
		teacherCollection.add(bar);
	}
	
	@Test
	public void testGetAllTeacherAsJson() {		
		final Collection<Teacher> allTeachersAsJson = teacherService.getAllTeacherAsJson();
		
		when(untisWsClientMock.getAllTeachers()).thenReturn(teacherCollection);
		
		assertEquals(2, allTeachersAsJson.size());
		assertTrue(allTeachersAsJson.contains(foo));
		assertTrue(allTeachersAsJson.contains(bar));
		verify(untisWsClientMock, times(1)).getAllTeachers();
		verifyNoMoreInteractions(untisWsClientMock);
	}

	@Test
	public void expectStatus404WhenTeacherDoesNotExist() {
		final Response teacherByName = teacherService.getTeacherByName("notExists");
		
		when(untisWsClientMock.getAllTeachers()).thenReturn(teacherCollection);
		
		assertEquals(Responses.NOT_FOUND, teacherByName.getStatus());
	}
	
	@Test
	public void expectStatus200AndTeacherWhenTeacherExists() {		
		when(untisWsClientMock.getAllTeachers()).thenReturn(teacherCollection);
		final Response teacherByName = teacherService.getTeacherByName(foo.getName());
		
		assertEquals(200, teacherByName.getStatus());
		assertEquals(foo, teacherByName.getEntity());
	}

}
