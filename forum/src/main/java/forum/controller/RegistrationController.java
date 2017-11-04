package forum.controller;

import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String initRegistrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationUser(@RequestParam("login") String login, @RequestParam("pass") String pass) {
        User user = new User(login, pass);
        DBService dbService = DBServiceimpl.instance();
        dbService.save(user);
        return "registration";
    }
}
