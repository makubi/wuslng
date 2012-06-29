package at.makubi.wuslng.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import at.makubi.wuslng.untiswsclient.UntisWsClient;

import com.googlecode.jsonrpc4j.JsonRpcClientException;

@Aspect
public class WebUntisRequestThrowingAspect {

	private UntisWsClient untisWsClient;
	
	@Autowired
	public WebUntisRequestThrowingAspect(UntisWsClient untisWsClient) {
		this.untisWsClient = untisWsClient;
	}
	
	@AfterThrowing(
			pointcut="within(at.makubi.wuslng.services..*)",
			throwing="e"			
			)
	public JoinPoint authenticateAndRetry(JoinPoint pjp, JsonRpcClientException e) {
		if(e.getCode() == -8520) {
			if(e.getMessage().equals("not authenticated")) {
				untisWsClient.authenticate();
				return pjp;
			}
		}
		System.out.println("LOGGING "+e.getMessage() + "===================================");
		throw e;
	}
}
