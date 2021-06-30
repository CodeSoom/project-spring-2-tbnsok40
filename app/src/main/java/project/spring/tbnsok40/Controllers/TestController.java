package project.spring.tbnsok40.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/")
    public String testMethod(){
        return "Lim's Project";
    }
}
