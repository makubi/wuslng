package at.makubi.wuslng.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import at.makubi.webuntis.model.SchoolClass;
import at.makubi.wuslng.UntisWsClient;
import at.makubi.wuslng.services.SchoolClassService;

import com.sun.jersey.api.Responses;

public class SchoolClassServiceTest {

	private SchoolClassService schoolClassService;
	private UntisWsClient untisWsClientMock;
	
	private final Collection<SchoolClass> schoolClassCollection = new ArrayList<SchoolClass>();
	private final SchoolClass schoolClass1 = new SchoolClass();
	private final SchoolClass schoolClass2 = new SchoolClass();
	
	@Before
	public void setUp() {
		untisWsClientMock = mock(UntisWsClient.class);
		schoolClassService = new SchoolClassService(untisWsClientMock);
				
		schoolClass1.setBackColor("" + 1);
		schoolClass1.setForeColor("" + 1);
		schoolClass1.setName("" + 1);

		schoolClass2.setBackColor("" + 2);
		schoolClass2.setForeColor("" + 2);
		schoolClass2.setName("" + 2);

		schoolClassCollection.add(schoolClass1);
		schoolClassCollection.add(schoolClass2);
	}
	
	@Test
	public void testGetAllTeacherAsJson() {
		when(untisWsClientMock.getAllSchoolClasses()).thenReturn(schoolClassCollection);
		
		final Collection<SchoolClass> allTeachersAsJson = schoolClassService.getAllSchoolClassesAsJson();
		
		assertEquals(2, allTeachersAsJson.size());
		assertTrue(allTeachersAsJson.contains(schoolClass1));
		assertTrue(allTeachersAsJson.contains(schoolClass2));
		verify(untisWsClientMock, times(1)).getAllSchoolClasses();
		verifyNoMoreInteractions(untisWsClientMock);
	}

	@Test
	public void expectStatus404WhenTeacherDoesNotExist() {
		when(untisWsClientMock.getAllSchoolClasses()).thenReturn(schoolClassCollection);
		
		final Response teacherByName = schoolClassService.getSchoolClassByName("notExists");
		
		assertEquals(Responses.NOT_FOUND, teacherByName.getStatus());
	}
	
	@Test
	public void expectStatus200AndTeacherWhenTeacherExists() {		
		when(untisWsClientMock.getAllSchoolClasses()).thenReturn(schoolClassCollection);
		
		final Response teacherByName = schoolClassService.getSchoolClassByName(schoolClass1.getName());
		
		assertEquals(200, teacherByName.getStatus());
		assertEquals(schoolClass1, teacherByName.getEntity());
	}

}
