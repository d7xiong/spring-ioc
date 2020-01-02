package xu.spring.ioc.bean;

import org.springframework.beans.factory.SmartFactoryBean;

/**
 * @author xu
 * @date 2019/11/28 15:17
 * @description: 工厂Bean
 */
public class ColorSmartFactoryBean implements SmartFactoryBean<Color> {

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


    public boolean isEagerInit() {
        return true;
    }
}
