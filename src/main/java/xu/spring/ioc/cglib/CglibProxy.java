package xu.spring.ioc.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import xu.spring.ioc.cglib.callback.MethodInterceptorCallback;

import java.lang.reflect.Method;

/**
 * @author xu
 * @date 2018/12/19 14:46
 * @description:
 */
public class CglibProxy {


    public Object getProxy(Class c) {
        Enhancer enhancer = new Enhancer();
        MethodInterceptor methodInterceptor = new MethodInterceptorCallback();

        enhancer.setSuperclass(c);
        enhancer.setCallback(methodInterceptor);
        return enhancer.create();
    }
}
