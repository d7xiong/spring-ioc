package xu.spring.ioc.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import xu.spring.ioc.bean.Car;
import xu.spring.ioc.cglib.callback.*;
import xu.spring.ioc.cglib.jdk.BuyTicket;
import xu.spring.ioc.cglib.jdk.CommonPerson;
import xu.spring.ioc.cglib.jdk.HuangNiu;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xu
 * @date 2018/12/19 15:02
 * @description:
 */
public class ProxyTest {


    public static void main(String[] args) {
        cglibProxy();
        jdkProxy();
        testCglibCallback();
    }

    public static void cglibProxy() {
        CglibProxy cglibProxy = new CglibProxy();
        Car car = (Car) cglibProxy.getProxy(Car.class);
        car.go();
    }

    public static void jdkProxy() {

        // 需要被代理的类
        CommonPerson commonPerson = new CommonPerson();

        // 代理类
        HuangNiu huangNiu = new HuangNiu(commonPerson);

        // 生成代理对象
        BuyTicket buyTicket = (BuyTicket) Proxy.newProxyInstance(CommonPerson.class.getClassLoader(), new Class[]{BuyTicket.class}, huangNiu);

        // 调用代理对象方法
        buyTicket.buyTicket();
    }


    public static void testCglibCallback() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CallbackBean.class);
        enhancer.setCallbacks(initCallBacks());
        enhancer.setCallbackFilter(initCallbackFilter());

        CallbackBean callbackBean = (CallbackBean) enhancer.create();

        callbackBean.methodForNoop();
        callbackBean.methodForInterceptor();
        callbackBean.methodForLazy();
        callbackBean.methodForDispatcher();
        callbackBean.methodForInvocationHandler();
        callbackBean.methodForFixValue();

    }

    /****
     * 初始化callback拦截数组
     * @return
     */
    private static final Callback[] initCallBacks() {
        MethodInterceptorCallback methodInterceptorCallback = new MethodInterceptorCallback();
        LazyLoaderCallback lazyLoaderCallback = new LazyLoaderCallback();
        InvocationHandlerCallback invocationHandlerCallback = new InvocationHandlerCallback();
        FixValueCallback fixValueCallback = new FixValueCallback();
        DispatcherCallBack dispatcherCallBack = new DispatcherCallBack();
        Callback[] callbacks = new Callback[]{NoOp.INSTANCE, methodInterceptorCallback, lazyLoaderCallback, dispatcherCallBack, invocationHandlerCallback, fixValueCallback};
        return callbacks;
    }

    private static final CallbackFilter initCallbackFilter(){
        return new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getName().equals("methodForNoop")){
                    return 0;
                }
                if (method.getName().equals("methodForInterceptor")){
                    return 1;
                }
                if (method.getName().equals("methodForLazy")){
                    return 2;
                }
                if (method.getName().equals("methodForDispatcher")){
                    return 3;
                }
                if (method.getName().equals("methodForInvocationHandler")){
                    return 4;
                }
                if (method.getName().equals("methodForFixValue")){
                    return 5;
                }
                return 0;
            }
        };

    }
}
