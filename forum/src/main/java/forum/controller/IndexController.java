package forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(HttpSession httpSession, Map<String, Object> model) {
        model.put("login", httpSession.getAttribute("login"));
        model.put("message", httpSession.getAttribute("message"));
        httpSession.removeAttribute("message");

        return "index";
    }

}
