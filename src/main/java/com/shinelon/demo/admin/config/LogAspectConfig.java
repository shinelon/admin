package com.shinelon.demo.admin.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Order(5)
@Component
public class LogAspectConfig {
    private static final Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    private List<String> ignorePaths = new ArrayList<>();

    {
        ignorePaths.add("/error");
        ignorePaths.add("/common/404");
        ignorePaths.add("/common/500");
        ignorePaths.add("/static/");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String path = request.getServletPath();
        if (ignorePaths.contains(path)) {
            return;
        }
        StringBuilder afterMethod = new StringBuilder(joinPoint.getSignature().getDeclaringTypeName()).append(".")
                .append(joinPoint.getSignature().getName());
        afterMethod = afterMethod.append("     RESUL : ").append(ret);
        afterMethod = afterMethod.append("     SPEND TIME : ").append(System.currentTimeMillis() - startTime.get());
        logger.info(afterMethod.toString());
    }

    /**
     * controller前
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String path = request.getServletPath();
        if (ignorePaths.contains(path)) {
            return;
        }
        // 记录下请求内容
        logger.info("URL : {}", request.getRequestURL().toString());
        logger.info("HTTP_METHOD : {}", request.getMethod());
        logger.info("IP : {}", request.getRemoteAddr());
        logger.info("CLASS_METHOD : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            sb.append(headerName).append(":").append(headerValue).append(", ");
        }
        logger.info("HEADERS : [{}]", sb.toString());

        Map<String, String[]> map = request.getParameterMap();
        sb = new StringBuilder();
        if (null != map && map.size() > 0) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String[] values = entry.getValue();
                String value;
                if (values.length > 1) {
                    value = Arrays.toString(entry.getValue());
                } else {
                    value = values[0];
                }
                sb.append(entry.getKey()).append(":").append(value).append(", ");
            }
        }
        logger.info("PARAMS : [{}]", sb.toString());

    }

    /**
     * 拦截位置,拦截所有controller
     */
    @Pointcut("execution(public * com.shinelon.demo.admin.web..*.*(..))")
    public void doLog() {
    }

    @Pointcut("doLog()")
    public void webLog() {

    }

}
