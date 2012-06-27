package at.makubi.wuslng.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import at.makubi.wuslng.untiswsclient.UntisWsClient;
import at.makubi.webuntis.model.SchoolClass;

import com.sun.jersey.api.Responses;

public class SchoolClassServiceTest {

	private SchoolClassService schoolClassService;
	private UntisWsClient untisWsClientMock;
	
	private final Collection<SchoolClass> schoolClassCollection = new ArrayList<SchoolClass>();
	private final SchoolClass schoolClass1 = new SchoolClass();
	private final SchoolClass schoolClass2 = new SchoolClass();
	
	private final Collection<at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass> jsonSchoolClassCollection = new ArrayList<at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass>();
	private final at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass jsonSchoolClass1 = new at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass();
	private final at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass jsonSchoolClass2 = new at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass();
	
	private final String schoolClass1Name = "1";
	private final String schoolClass1BackColor = "1";
	private final String schoolClass1ForeColor = "1";
	
	private final String schoolClass2Name = "2";
	private final String schoolClass2BackColor = "2";
	private final String schoolClass2ForeColor = "2";
	
	@Before
	public void setUp() {
		untisWsClientMock = mock(UntisWsClient.class);
		schoolClassService = new SchoolClassService(untisWsClientMock);
		
		setUpSchoolClassCollection();
		setUpJSONSchoolClassCollection();
	}
	
	private void setUpSchoolClassCollection() {
		schoolClass1.setBackColor(schoolClass1BackColor);
		schoolClass1.setForeColor(schoolClass1ForeColor);
		schoolClass1.setName(schoolClass1Name);

		schoolClass2.setBackColor(schoolClass2BackColor);
		schoolClass2.setForeColor(schoolClass2ForeColor);
		schoolClass2.setName(schoolClass2Name);

		schoolClassCollection.add(schoolClass1);
		schoolClassCollection.add(schoolClass2);
	}
	
	private void setUpJSONSchoolClassCollection() {
		jsonSchoolClass1.setBackColor(schoolClass1BackColor);
		jsonSchoolClass1.setForeColor(schoolClass1ForeColor);
		jsonSchoolClass1.setName(schoolClass1Name);

		jsonSchoolClass2.setBackColor(schoolClass2BackColor);
		jsonSchoolClass2.setForeColor(schoolClass2ForeColor);
		jsonSchoolClass2.setName(schoolClass2Name);

		jsonSchoolClassCollection.add(jsonSchoolClass1);
		jsonSchoolClassCollection.add(jsonSchoolClass2);
	}
	
	@Test
	public void testGetAllTeacherAsJson() throws IllegalAccessException, InvocationTargetException {
		when(untisWsClientMock.getAllSchoolClasses()).thenReturn(jsonSchoolClassCollection);
		
		final Collection<at.makubi.webuntis.model.SchoolClass> allTeachersAsJson = schoolClassService.getAllSchoolClassesAsJson();
		
		assertEquals(2, allTeachersAsJson.size());
		assertTrue(collectionHasSchoolClass(allTeachersAsJson, schoolClass1Name));
		assertTrue(collectionHasSchoolClass(allTeachersAsJson, schoolClass2Name));
		assertTrue(!collectionHasSchoolClass(allTeachersAsJson, "notExists"));
		verify(untisWsClientMock, times(1)).getAllSchoolClasses();
		verifyNoMoreInteractions(untisWsClientMock);
	}

	private boolean collectionHasSchoolClass(
			Collection<SchoolClass> allTeachersAsJson, String schoolClass1Name) {
		for (SchoolClass schoolClass : allTeachersAsJson) {
			if(schoolClass1Name.equals(schoolClass.getName()))
				return true;
		}
		return false;
	}

	@Test
	public void expectStatus404WhenTeacherDoesNotExist() throws IllegalAccessException, InvocationTargetException {
		when(untisWsClientMock.getAllSchoolClasses()).thenReturn(jsonSchoolClassCollection);
		
		final Response teacherByName = schoolClassService.getSchoolClassByName("notExists");
		
		assertEquals(Responses.NOT_FOUND, teacherByName.getStatus());
	}
	
	@Test
	public void expectStatus200AndTeacherWhenTeacherExists() throws IllegalAccessException, InvocationTargetException {		
		when(untisWsClientMock.getAllSchoolClasses()).thenReturn(jsonSchoolClassCollection);
		
		final Response schoolClassByName = schoolClassService.getSchoolClassByName(schoolClass1.getName());
		
		assertEquals(200, schoolClassByName.getStatus());
		assertEquals(schoolClass1.getName(), ((SchoolClass)schoolClassByName.getEntity()).getName());
	}

}
