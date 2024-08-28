package org.example.replicadb.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Aspect
@Component
@Order(0)
@Slf4j
public class DBRoutingInterceptor {
    private final Pattern readPattern = Pattern.compile("get.*|find.*");

    @Pointcut("execution(* org.springframework.data.repository.Repository+.*(..))")
    void datasourceMapping() {
    }

    @Around("datasourceMapping()")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            try {
                MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
                Method method = methodSignature.getMethod();

                DynamicRoutingDataSource.setReadonlyDataSource(readPattern.matcher(method.getName()).matches());
            } catch (Exception e) {
                log.error("Exception occurred during datasource mapping: {}", e.getMessage());
            }

            return proceedingJoinPoint.proceed();
        } finally {
            // Reset back to the read-write datasource
            DynamicRoutingDataSource.resetDataSourceType();
        }
    }
}
