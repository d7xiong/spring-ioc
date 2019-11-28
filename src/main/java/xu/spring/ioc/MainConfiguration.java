package xu.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import xu.spring.ioc.bean.Car;
import xu.spring.ioc.bean.ColorFactoryBean;
import xu.spring.ioc.bean.Dog;
import xu.spring.ioc.importbean.MyImportBeanDefinitionRegistrar;
import xu.spring.ioc.importbean.MyImportSelector;

/**
 * @author xu
 * @date 2018/12/24 18:26
 * @description:
 */

@Configuration
@ComponentScan("xu.spring.ioc.beanpostprocessor")
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

}
