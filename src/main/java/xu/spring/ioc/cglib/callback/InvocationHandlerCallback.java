package xu.spring.ioc.cglib.callback;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author xu
 * @date 2020/3/5 14:49
 * @description:
 */
public class InvocationHandlerCallback implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invocationHandlerCallback Before....");
        method.invoke(proxy.getClass().getSuperclass().newInstance(), args);
        //会无限循环
        //method.invoke(proxy,args);
        System.out.println("invocationHandlerCallback after....");
        return null;
    }
}
