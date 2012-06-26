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

import at.makubi.webuntis.model.SchoolTeacher;
import at.makubi.wuslng.services.SchoolTeacherService;
import at.makubi.wuslng.untiswsclient.UntisWsClient;

import com.sun.jersey.api.Responses;

public class SchoolTeacherServiceTest {

	private SchoolTeacherService schoolTeacherService;
	private UntisWsClient untisWsClientMock;
	
	private final Collection<SchoolTeacher> schoolTeacherCollection = new ArrayList<SchoolTeacher>();
	private final SchoolTeacher schoolTeacher1 = new SchoolTeacher();
	private final SchoolTeacher schoolTeacher2 = new SchoolTeacher();
	
	@Before
	public void setUp() {
		untisWsClientMock = mock(UntisWsClient.class);
		schoolTeacherService = new SchoolTeacherService(untisWsClientMock);
				
		schoolTeacher1.setBackColor("" + 1);
		schoolTeacher1.setForeColor("" + 1);
		schoolTeacher1.setName("" + 1);

		schoolTeacher2.setBackColor("" + 2);
		schoolTeacher2.setForeColor("" + 2);
		schoolTeacher2.setName("" + 2);

		schoolTeacherCollection.add(schoolTeacher1);
		schoolTeacherCollection.add(schoolTeacher2);
	}
	
	@Test
	public void testGetAllTeacherAsJson() {
		when(untisWsClientMock.getAllSchoolTeachers()).thenReturn(schoolTeacherCollection);
		
		final Collection<SchoolTeacher> allTeachersAsJson = schoolTeacherService.getAllSchoolTeachersAsJson();
		
		assertEquals(2, allTeachersAsJson.size());
		assertTrue(allTeachersAsJson.contains(schoolTeacher1));
		assertTrue(allTeachersAsJson.contains(schoolTeacher2));
		verify(untisWsClientMock, times(1)).getAllSchoolTeachers();
		verifyNoMoreInteractions(untisWsClientMock);
	}

	@Test
	public void expectStatus404WhenTeacherDoesNotExist() {
		when(untisWsClientMock.getAllSchoolTeachers()).thenReturn(schoolTeacherCollection);
		
		final Response teacherByName = schoolTeacherService.getSchoolTeacherByName("notExists");
		
		assertEquals(Responses.NOT_FOUND, teacherByName.getStatus());
	}
	
	@Test
	public void expectStatus200AndTeacherWhenTeacherExists() {		
		when(untisWsClientMock.getAllSchoolTeachers()).thenReturn(schoolTeacherCollection);
		
		final Response teacherByName = schoolTeacherService.getSchoolTeacherByName(schoolTeacher1.getName());
		
		assertEquals(200, teacherByName.getStatus());
		assertEquals(schoolTeacher1, teacherByName.getEntity());
	}

}
