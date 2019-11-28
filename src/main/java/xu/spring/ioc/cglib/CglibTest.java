package xu.spring.ioc.cglib;

import xu.spring.ioc.bean.Car;

/**
 * @author xu
 * @date 2018/12/19 15:02
 * @description:
 */
public class CglibTest {


    public static void main(String[] args) {

        CglibProxy cglibProxy = new CglibProxy();
        Car car = (Car) cglibProxy.getProxy(Car.class);
        car.go();


    }
}
