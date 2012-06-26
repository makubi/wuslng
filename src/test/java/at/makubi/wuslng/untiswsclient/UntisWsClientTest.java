package at.makubi.wuslng.untiswsclient;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import at.makubi.wuslng.untiswsclient.jsonrpcmodel.SchoolClass;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class UntisWsClientTest {

	private JsonRpcHttpClient client;
	private WebUntisService webUntisService;

	@Before
	public void setUp() throws Exception {
		client = new JsonRpcHttpClient(new URL("http://webuntis.grupet.at:8080/WebUntis/jsonrpc.do?school=demo"));
		webUntisService = ProxyUtil.createProxy(getClass().getClassLoader(), WebUntisService.class, client);
	}

	@Test
	public void testGetSchoolClassList() {
		final AuthenticationToken token = webUntisService.authenticate("user", "");
		System.out.println(token.getSessionId());
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Cookie", "JSESSIONID="+token.getSessionId());
		
		client.setHeaders(headers);
		final Collection<SchoolClass> clazzes = webUntisService.getKlassen();
		for (final SchoolClass schoolClass : clazzes) {
			System.out.println(schoolClass.getName());
		}
	}

}
