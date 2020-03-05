package xu.spring.ioc.cglib.callback;

import org.springframework.cglib.proxy.LazyLoader;

/**
 * @author xu
 * @date 2020/3/5 14:50
 * @description:
 */
public class LazyLoaderCallback implements LazyLoader{
    @Override
    public Object loadObject() throws Exception {
        CallbackBean callbackBean = new CallbackBean();
        return callbackBean;
    }
}
