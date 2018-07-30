//package com.lakala.sh.sao.common.config.db;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
///**
// * @author steellee
// * @date 2018/06/28
// * @description：
// * 使用@Before在切入点开始处切入内容
// * 使用@After在切入点结尾处切入内容
// * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
// * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
// * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
// */
////@Order(1) // 注解来标识切面的优先级。i的值越小，优先级越高
//@Aspect // 定义为切面类
//@Component
//public class DynamicDataSourceAspect {
//
//
//    // 定义一个切入点，可以是一个规则表达式
////    @Pointcut("execution(public * com.lakala.sh.sao.demo.controller.*.*(..))")
////    public void DynamicDataSourceAspect(){}
//
//    /**
//     * 在切入点开始处切入内容
//     * @param point
//     */
//    @Before("@annotation(DS)")
//    public void beforeSwitchDS(JoinPoint point){
//
//        //获得当前访问的class
//        Class<?> className = point.getTarget().getClass();
//
//        //获得访问的方法名
//        String methodName = point.getSignature().getName();
//        //得到方法的参数的类型
//        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
//        String dataSource = DataSourceContextHolder.DEFAULT_DS;
//        try {
//            // 得到访问的方法对象
//            Method method = className.getMethod(methodName, argClass);
//
//            // 判断是否存在@DS注解
//            if (method.isAnnotationPresent(DS.class)) {
//                DS annotation = method.getAnnotation(DS.class);
//                // 取出注解中的数据源名
//                dataSource = annotation.value();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 切换数据源
//        DataSourceContextHolder.setDB(dataSource);
//    }
//
//    /**
//     * 在切入点结尾处切入内容
//     * @param point
//     */
//    @After("@annotation(DS)")
//    public void afterSwitchDS(JoinPoint point){
//        DataSourceContextHolder.clearDB();
//    }
//}