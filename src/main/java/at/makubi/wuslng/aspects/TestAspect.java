package at.makubi.wuslng.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import at.makubi.wuslng.untiswsclient.UntisWsClient;

import com.googlecode.jsonrpc4j.JsonRpcClientException;

@Aspect
public class TestAspect {
	
	private UntisWsClient untisWsClient;
	
	@Autowired
	public TestAspect(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@Around("within(at.makubi.wuslng.services..*)")
	public Object aroundServiceCalls(ProceedingJoinPoint pjp) throws Throwable {
		try {
			return pjp.proceed();
		}
		catch (JsonRpcClientException e) {
			handleServiceException(e);
			return pjp.proceed();
		}
	}

	private void handleServiceException(JsonRpcClientException e) {
		if(e.getCode() == -8520) {
			if(e.getMessage().equals("not authenticated")) {
				System.out.println("Reauthenticating");
				untisWsClient.authenticate();
			}
		}
	}
}
