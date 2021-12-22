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

    ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(* kr.bora.api.*.controller..*.*(..))")
    private void cut() {

    }

    // 파라미터 입력 값
    @Before("cut()")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {

        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            log.info("type : " + obj.getClass().getSimpleName());
            log.info("value : " + objectMapper.writeValueAsString(obj));

        }
    }

    // 파라미터 입력 후 호출 결과 값
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(Object returnObj) throws JsonProcessingException {
        log.info("return Obj");
        log.info(objectMapper.writeValueAsString(returnObj));

    }

}
