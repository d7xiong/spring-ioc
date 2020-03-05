package xu.spring.ioc.cglib.callback;

import org.springframework.cglib.proxy.Dispatcher;

/**
 * @author xu
 * @date 2020/3/4 20:41
 * @description:
 */
public class DispatcherCallBack implements Dispatcher {
    @Override
    public Object loadObject() throws Exception {
        return new CallbackBean();
    }
}
