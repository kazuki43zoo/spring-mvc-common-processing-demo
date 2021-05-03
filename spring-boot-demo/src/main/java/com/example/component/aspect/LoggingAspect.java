package com.example.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution(* *..*Service.*(..))")
  public void startLog(JoinPoint jp) {
    logger.debug("@Before : {}", jp.getSignature());
  }

  @AfterReturning(pointcut = "execution(* *..*Service.*(..))", returning = "ret")
  public void normalEndLog(JoinPoint jp, Object ret) {
    logger.debug("@AfterReturning : {}", jp.getSignature() + " ret: " + ret);
  }

  @AfterThrowing(pointcut = "execution(* *..*Service.*(..))", throwing = "t")
  public void failureEndLog(JoinPoint jp, Throwable t) {
    logger.debug("@AfterThrowing : {}", jp.getSignature() + " t: " + t);
  }

  @After("execution(* *..*Service.*(..))")
  public void completeLog(JoinPoint jp) {
    logger.debug("@After : {}", jp.getSignature());
  }

  @Around("execution(* *..*Service.*(..))")
  public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
    Object ret;
    try {
      logger.debug("Before by @Around : {}", jp.getSignature());
      ret = jp.proceed();
      logger.debug("AfterReturning by @Around : {} ret:{}", jp.getSignature(), ret);
    } catch (Throwable t) {
      logger.debug("AfterThrowing by @Around : {}", jp.getSignature(), t);
      throw t;
    } finally {
      logger.debug("After by @Around : {}", jp.getSignature());
    }
    return ret;
  }

}
