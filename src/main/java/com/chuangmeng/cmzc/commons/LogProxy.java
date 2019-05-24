package com.chuangmeng.cmzc.commons;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogProxy {

    Logger logger=Logger.getLogger(LogProxy.class);
    @Around("execution(public * com.chuangmeng.cmzc.*.service.Impl.*.*(..))")
    public Object logger(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>");
        try {
            String kind = proceedingJoinPoint.getKind();
            //方法入参
            Object[] args = proceedingJoinPoint.getArgs();
            logger.debug(kind+":-----"+args);
            //返回值
            Object proceed = proceedingJoinPoint.proceed();
            logger.debug("return:-----"+proceed);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.warn(throwable.getMessage());
        }
        return null;
    }
}
