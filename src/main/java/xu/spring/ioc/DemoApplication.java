package xu.spring.ioc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import xu.spring.ioc.aop.MathCalculator;
import xu.spring.ioc.bean.Car;
import xu.spring.ioc.bean.Color;
import xu.spring.ioc.bean.Dog;
import xu.spring.ioc.beandefinition.BeanDefinitionTest;
import xu.spring.ioc.cglib.ProxyTest;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author xu
 */
public class DemoApplication {

    public static void main(String[] args) {

        beanDefinitionTest();
        myTest();

    }

    private static void beanDefinitionTest() {

        BeanDefinitionTest.scanBeanDefinitionTest();
        BeanDefinitionTest.genericBeanDefinitionTest();

    }

    private static void myTest() {
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

        multiTurning();

        classLoaderResourceTest();
    }


    private static void classLoaderResourceTest() {

        String RESOURCE_LOCATION = "META-INF/spring.handlers";
        Enumeration<URL> urls;
        try {
            urls = DemoApplication.class.getClassLoader().getResources(RESOURCE_LOCATION);

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {

                    System.out.println(entry.getKey() + "\t" + entry.getValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 堆外内存
     */


    private static void byteBufferTest() {


//        ByteBuffer b  = ByteBuffer.allocate(1024);


    }

    private static void multiTurning() {

        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();

        Thread t1 = new Thread(new Lock(obj1, obj2));
        Thread t2 = new Thread(new Lock(obj2, obj3));
        Thread t3 = new Thread(new Lock(obj3, obj1));

        t1.start();
        t2.start();
        t3.start();
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


    private static class Lock implements Runnable {

        private Object current;
        private Object next;

        Lock(Object current, Object next) {
            this.current = current;
            this.next = next;
        }

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                synchronized (next) {
                    synchronized (current) {
                        System.out.println(Thread.currentThread().getName());
                        current.notifyAll();
                    }
                    try {
                        next.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
