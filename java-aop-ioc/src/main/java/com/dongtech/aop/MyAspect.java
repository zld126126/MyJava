package com.dongtech.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class MyAspect {
    private Logger logger = LoggerFactory.getLogger(MyAspect.class);

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
//    @Pointcut("execution(public * com.dongtech.aop..*.*(..))")
//    public void sayHello(){
//        System.out.println("执行了webLog方法");
//    }
//
//    /**
//     * 前置通知：在连接点之前执行的通知
//     * @param joinPoint
//     * @throws Throwable
//     */
//    @Before("sayHello()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//
//    @AfterReturning(returning = "ret",pointcut = "sayHello()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
//    }

    //配置方法2
    @Around("execution(public * com.dongtech.aop..*.*(..))")
    public Object monitor(ProceedingJoinPoint point) throws Throwable {
        System.out.println("before");
        try {
            return point.proceed();
        } finally {
            System.out.println("after");
        }
    }
}
