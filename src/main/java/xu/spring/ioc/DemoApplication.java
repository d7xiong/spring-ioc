package xu.spring.ioc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xu.spring.ioc.aop.MathCalculator;
import xu.spring.ioc.bean.Car;
import xu.spring.ioc.bean.Color;
import xu.spring.ioc.bean.Dog;
import xu.spring.ioc.cglib.ProxyTest;

import java.nio.ByteBuffer;
import java.util.concurrent.*;

/**
 * @author xu
 */
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

        jdkAndCglibProxy();

        lambda();

        byteBufferTest();
    }


    /**
     * 堆外内存
     */


    private static void byteBufferTest() {


//        ByteBuffer b  = ByteBuffer.allocate(1024);


    }

    private static void lambda() {

        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println("I'm lambdaRunnable"));
        singleThreadPool.shutdown();


    }

    private static void jdkAndCglibProxy() {
        ProxyTest.cglibProxy();
        ProxyTest.jdkProxy();
    }

    private static void aopBean(ApplicationContext applicationContext) {

        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(2, 1);
        System.out.println("===============aopBean end========================");


    }

    private static void factoryBean(ApplicationContext applicationContext) {

        // 获取工厂bean创建的对象
        Object bean = applicationContext.getBean("colorFactoryBean");

        // 实现SmartFactoryBean再创建工厂对象时，直接把内置对象也创建出来
        Object smartFactoryBean = applicationContext.getBean("&colorSmartFactoryBean");

        // 获取工厂bean本身,加一个&标识。不会创建内置对象
        Object factoryBean = applicationContext.getBean("&colorFactoryBean");
        System.out.println("colorFactoryBean创建的bean:" + bean.getClass());
        System.out.println("colorFactoryBean创建的bean内@Autowired注入的Dog失效,dog:" + ((Color) bean).getDog());
        System.out.println("colorFactoryBean工厂bean:" + factoryBean.getClass());
        System.out.println("colorsmartFactoryBean工厂bean:" + smartFactoryBean);
        System.out.println("===============factoryBean end========================");

    }

    private static void bean(ApplicationContext applicationContext) {
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
