package xu.spring.ioc.importbean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import xu.spring.ioc.bean.Blue;
import xu.spring.ioc.bean.RainBow;
import xu.spring.ioc.bean.Red;

/**
 * @author xu
 * @date 2019/11/28 15:09
 * @description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        boolean definition1 = registry.containsBeanDefinition(Red.class.getName());
        boolean definition2 = registry.containsBeanDefinition(Blue.class.getName());

        if (definition1 && definition2) {

            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition(RainBow.class.getName(), beanDefinition);
        }
    }
}
