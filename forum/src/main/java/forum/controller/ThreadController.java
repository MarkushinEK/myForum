package forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ThreadController {
    @RequestMapping(value = "/{tag}", method = RequestMethod.GET)
    public String exit(HttpSession httpSession, @PathVariable("tag") String tag, Map<String, Object> model) {
        model.put("tag", tag);
        return "thread";
    }
}
