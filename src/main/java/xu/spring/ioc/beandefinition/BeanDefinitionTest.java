package xu.spring.ioc.beandefinition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;


/**
 * @author xu
 * @date 2021/2/25 17:34
 * @description:
 */
public class BeanDefinitionTest {

    public static void main(String[] args) {

        scanBeanDefinitionTest();
        genericBeanDefinitionTest();

    }

    public static void scanBeanDefinitionTest(){
        GenericApplicationContext ctx = new GenericApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(ctx);
        scanner.scan("xu.spring.ioc.beandefinition");

        ctx.refresh();

//        printBeanDefinitionNames(ctx);


        BeanDefinition bd = ctx.getBeanDefinition("beanDefinitionTest.GenericConfig");
        BeanDefinition bd1 = ctx.getBeanDefinition("definition1");
        BeanDefinition bd2 = ctx.getBeanDefinition("xu.spring.ioc.beandefinition.DevConfig");


        System.out.println("-----------------------------");
        System.out.println(bd.getClass().getSimpleName());
        System.out.println(bd1.getClass().getSimpleName());
        System.out.println(bd2.getClass().getSimpleName());
        System.out.println("-----------------------------");

    }

    public static void genericBeanDefinitionTest() {
        GenericApplicationContext ctx = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(ctx);
        reader.register(GenericConfig.class);

        ctx.refresh();

        BeanDefinition bd = ctx.getBeanDefinition("beanDefinitionTest.GenericConfig");
        BeanDefinition bd1 = ctx.getBeanDefinition("definition1");
        BeanDefinition bd2 = ctx.getBeanDefinition("xu.spring.ioc.beandefinition.DevConfig");

//        printBeanDefinitionNames(ctx);

        System.out.println("-----------------------------");
        System.out.println(bd.getClass().getSimpleName());
        System.out.println(bd1.getClass().getSimpleName());
        System.out.println(bd2.getClass().getSimpleName());
        System.out.println("-----------------------------");
    }

    private static void printBeanDefinitionNames(ApplicationContext applicationContext) {
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    @Configuration
    @Import({DevConfig.class, ProdConfig.class})
    static class GenericConfig {

        @Bean
        public DefinitionBean definition1() {
            return new DefinitionBean();
        }

    }
}
