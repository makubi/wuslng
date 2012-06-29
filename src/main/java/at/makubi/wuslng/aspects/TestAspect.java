package at.makubi.wuslng.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TestAspect {
	
	@Around("within(at.makubi.wuslng.services..*)")
	public Object aroundServiceCalls(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}
}
