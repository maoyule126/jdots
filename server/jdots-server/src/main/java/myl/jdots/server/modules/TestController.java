package myl.jdots.server.modules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * create by maoyule on 2019/5/3
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String testA(HttpServletRequest request){
        return "../static/index.html";
    }
}
