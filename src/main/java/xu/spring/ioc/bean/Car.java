package xu.spring.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author xu
 * @date 2018/12/19 15:03
 * @description:
 */
public class Car implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Autowired
    Dog dog;

    public Car() {
        System.out.println("car   constructor...");
    }

    public void go() {
        System.out.println("cat to go!");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("car   setApplicationContext by ApplicationContextAware");
        this.applicationContext = applicationContext;

    }
}
