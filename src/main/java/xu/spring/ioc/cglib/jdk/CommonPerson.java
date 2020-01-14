package xu.spring.ioc.cglib.jdk;

/**
 * @author xu
 * @date 2020/1/14 16:44
 * @description: 普通人买票
 */
public class CommonPerson implements BuyTicket {
    public void buyTicket() {
        System.out.println("JDK实际代理类(买到票了!) ");
    }
}
