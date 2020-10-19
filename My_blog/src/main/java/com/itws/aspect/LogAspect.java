package com.itws.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 创建一个日志处理的切面类
 */
@Aspect
@Component
public class LogAspect {

    //获得日志对象
    public final Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 定义一个切入点表达式
     */
    @Pointcut("execution(* com.itws.web.*.*(..))")
    public void log(){}

    /**
     * 定义一个前置通知
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
//        logger.info("-----before------");
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        //将获取到的这些信息存请求日志类中
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("requestLog:{}",requestLog);
    }

    /**
     * 定义一个最终通知
     */
    @After("log()")
    public void doAfter(){
        logger.info("------after-----");
    }

    /**
     * 定义一个后置通知；用户记录返回的结果信息
     * @param result
     */
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result:{}",result);

    }

    /**
     * 定义一个内部类；用户封装我们要记录日志的内容
     */
    private class RequestLog{
        private String url;  //请求的路径
        private String ip;   //请求的ip
        private String classMethod;  //请求的方法名
        private Object[] args;  //请求的参数


        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
