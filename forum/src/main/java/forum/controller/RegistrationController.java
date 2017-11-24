package forum.controller;

import forum.dataSet.ImageProfileUser;
import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String initRegistrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationUser(HttpSession httpSession,
                                   @RequestParam("login") String login,
                                   @RequestParam("pass") String pass,
                                   Map<String, Object> model) {

        DBService dbService = DBServiceImpl.instance();
        if (dbService.getUserByLogin(login) == null) {
            ImageProfileUser imageProfileUser = (ImageProfileUser) dbService.getObjectById(ImageProfileUser.class.getName(), 1);
            User user = new User(login, pass, imageProfileUser);
            dbService.save(user);
            httpSession.setAttribute("login", login );
            return "redirect:/";
        }
        model.put("message", "Логин занят.");

        return "registration";
    }

}
