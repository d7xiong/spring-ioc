
spring ioc


Import, ImportSelector, ImportBeanDefinitionRegistrar

BeanPostProcessor, AutowiredAnnotationBeanPostProcessor



doCreateBean

applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
populateBean(beanName, mbd, instanceWrapper);
initializeBean(beanName, exposedObject, mbd);
    applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
    invokeInitMethods(beanName, wrappedBean, mbd);
        ((InitializingBean) bean).afterPropertiesSet();
        invokeCustomInitMethod(beanName, bean, mbd);
    applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);




