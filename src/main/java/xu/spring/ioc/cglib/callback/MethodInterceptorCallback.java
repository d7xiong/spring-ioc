package xu.spring.ioc.cglib.callback;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xu
 * @date 2020/3/5 14:53
 * @description:
 */
public class MethodInterceptorCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======MethodInterceptor前置通知======");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("======MethodInterceptor后置通知======");
        return obj;
    }
}
