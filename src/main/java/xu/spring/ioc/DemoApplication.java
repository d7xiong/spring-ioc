package xu.spring.ioc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xu.spring.ioc.aop.MathCalculator;
import xu.spring.ioc.bean.Car;
import xu.spring.ioc.bean.Dog;

public class DemoApplication {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(MainConfiguration.class);

        applicationContext.refresh();

        printBeanNames(applicationContext);

        factoryBean(applicationContext);

        bean(applicationContext);

        aopBean(applicationContext);

        applicationContext.stop();

    }

    private static void aopBean(ApplicationContext applicationContext){

        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(2,1);
        System.out.println("===============aopBean end========================");


    }

    private static void factoryBean(ApplicationContext applicationContext){

        // 获取工厂bean创建的对象
        Object bean = applicationContext.getBean("colorFactoryBean");

        // 获取工厂bean本身,加一个&标识
        Object beanFactory = applicationContext.getBean("&colorFactoryBean");
        System.out.println("colorFactoryBean创建的bean:"+bean.getClass());
        System.out.println("colorFactoryBean工厂bean:"+beanFactory.getClass());
        System.out.println("===============factoryBean end========================");

    }

    private static void bean(ApplicationContext applicationContext){
        Car car = applicationContext.getBean(Car.class);

        car.go();

        Dog dog = applicationContext.getBean(Dog.class);

        dog.go();

        System.out.println("===============bean end========================");

    }

    private static void printBeanNames(ApplicationContext applicationContext) {
        System.out.println("===============bean names start======================");
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        System.out.println("===============bean names end========================");
    }
}
