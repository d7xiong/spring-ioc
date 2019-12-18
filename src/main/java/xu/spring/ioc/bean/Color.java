package xu.spring.ioc.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xu
 * @date 2019/11/28 15:17
 * @description:
 */
public class Color {

    /**
     * Color由FactoryBean创建时，@Autowired无效
     */
    @Autowired
    private Dog dog;


    public Dog getDog() {
        return dog;
    }
}
