package xu.spring.ioc.cglib;

import xu.spring.ioc.cglib.jdk.BuyTicket;
import xu.spring.ioc.cglib.jdk.CommonPerson;
import xu.spring.ioc.cglib.jdk.HuangNiu;

import java.lang.reflect.Proxy;

/**
 * @author xu
 * @date 2018/12/19 15:02
 * @description:
 */
public class CglibTest {


    public static void main(String[] args) {

        jdkProxy();

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
}
