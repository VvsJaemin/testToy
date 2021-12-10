package kr.bora.api.common.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class ParameterAop {


    @Pointcut("execution(* kr.bora.api.*.controller..*.*(..))")
    private void cut() {

    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info(method.getName());

        Object[] args = joinPoint.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Object obj : args) {
            log.info("type : " + obj.getClass().getSimpleName());
            log.info("value : " + objectMapper.writeValueAsString(obj));

        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(Object returnObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("return Obj");
        log.info(objectMapper.writeValueAsString(returnObj));

    }

}
