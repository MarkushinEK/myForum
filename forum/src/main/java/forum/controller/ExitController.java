package forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ExitController {
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }

}
