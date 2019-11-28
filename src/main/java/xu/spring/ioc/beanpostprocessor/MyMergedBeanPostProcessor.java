package xu.spring.ioc.beanpostprocessor;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author xu
 * @date 2019/11/28 15:36
 * @description:
 */
public class MyMergedBeanPostProcessor implements MergedBeanDefinitionPostProcessor {
    public void postProcessMergedBeanDefinition(RootBeanDefinition rootBeanDefinition, Class<?> aClass, String s) {

    }
}
