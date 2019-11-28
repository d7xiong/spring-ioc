package xu.spring.ioc.importbean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import xu.spring.ioc.bean.Blue;
import xu.spring.ioc.bean.Red;
import xu.spring.ioc.bean.Yellow;

/**
 * @author xu
 * @date 2019/11/28 15:02
 * @description: 向IOC容器导入bean全类名
 */
public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{
                Blue.class.getName(), Red.class.getName(), Yellow.class.getName()
        };
    }

}
