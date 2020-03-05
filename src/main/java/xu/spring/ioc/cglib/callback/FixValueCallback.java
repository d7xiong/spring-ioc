package xu.spring.ioc.cglib.callback;

import org.springframework.cglib.proxy.FixedValue;

/**
 * @author xu
 * @date 2020/3/4 20:46
 * @description:
 */
public class FixValueCallback implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        System.out.println("this is fixvalue callback .....    overwrite the code....");
        return true;
    }
}
