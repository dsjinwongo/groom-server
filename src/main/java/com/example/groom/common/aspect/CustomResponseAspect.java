//package com.example.groom.common.aspect;
//
//import com.example.groom.common.ResponseDto;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class CustomResponseAspect {
//    /* controller 패키지에 포함된 public 메서드와 매칭 */
//    @Pointcut("execution( * *..*Controller.*(..) )")
//    public void onRequest() { }
//
//    @Around("onRequest()")
//    public ResponseDto<Object> wrapToResponseDto(ProceedingJoinPoint joinPoint)throws Throwable{
//        Object result = null;
//        try {
//
//            result = joinPoint.proceed();
//
//        }catch (Exception e){
//            throw e;
//        }
//        return new ResponseDto(result);
//    }
//}
