package xu.spring.ioc;

import org.springframework.context.annotation.*;
import xu.spring.ioc.aop.LogAspects;
import xu.spring.ioc.aop.MathCalculator;
import xu.spring.ioc.bean.Car;
import xu.spring.ioc.bean.ColorFactoryBean;
import xu.spring.ioc.bean.ColorSmartFactoryBean;
import xu.spring.ioc.bean.Dog;
import xu.spring.ioc.importbean.MyImportBeanDefinitionRegistrar;
import xu.spring.ioc.importbean.MyImportSelector;

/**
 * @author xu
 * @date 2018/12/24 18:26
 * @description:
 */

@Configuration
@ComponentScan({"xu.spring.ioc.beanpostprocessor", "xu.spring.ioc.aop"})
@EnableAspectJAutoProxy
@Import({Dog.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfiguration {

    @Bean
    public Car car() {
        return new Car();
    }

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }

    @Bean
    public ColorSmartFactoryBean colorSmartFactoryBean(){
        return new ColorSmartFactoryBean();
    }

    /*=========AOP===========*/

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

}
