package xu.spring.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author xu
 * @date 2019/11/28 15:17
 * @description: 工厂Bean
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    public Color getObject() throws Exception {
        System.out.println("====create ColorFactoryBean");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
