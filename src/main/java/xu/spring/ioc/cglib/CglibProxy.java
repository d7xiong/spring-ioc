package xu.spring.ioc.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xu
 * @date 2018/12/19 14:46
 * @description:
 */
public class CglibProxy implements MethodInterceptor {

    private Object object;
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class c){
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        methodProxy.invokeSuper(o, objects);

        return null;
    }
}
