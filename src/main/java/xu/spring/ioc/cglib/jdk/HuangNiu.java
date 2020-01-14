package xu.spring.ioc.cglib.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xu
 * @date 2020/1/14 16:45
 * @description: 黄牛代理买票
 */
public class HuangNiu implements InvocationHandler {

    private CommonPerson target;

    public HuangNiu(CommonPerson target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("HuangNiu: 黄牛帮忙代购");

        Object obj = method.invoke(target, args);

        return obj;
    }
}
