package xu.spring.ioc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {


    @RequestMapping(value = {"/", "/hello", "/hi"})
    public String say() {
        return "hi you!!";
    }

}
