package forum.controller;

import forum.dataSet.ImageProfileUser;
import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceImpl;
import forum.service.ForumService;
import forum.service.ForumServiceImpl;
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
                                   @RequestParam("email") String email,
                                   Map<String, Object> model) {

        ForumService forumService = ForumServiceImpl.instance();

        if (!forumService.loginValidator(login)) {
            model.put("message", "Логин должен быть более пяти и меньше двадцати символов без пробелов.");
            return "registration";
        }

        if (!forumService.loginValidator(pass)) {
            model.put("message", "Пароль должен быть более пяти и меньше двадцати символов без пробелов.");
            return "registration";
        }

        if (!forumService.emailValidator(email)) {
            model.put("message", "Неверный email.");
            return "registration";
        }

        DBService dbService = DBServiceImpl.instance();

        if (dbService.getUserByLogin(login) != null) {
            model.put("message", "Логин уже используется.");
            return "registration";
        }

        if (dbService.getUserByEmail(email) != null) {
            model.put("message", "Email уже используется.");
            return "registration";
        }

        ImageProfileUser imageProfileUser = (ImageProfileUser) dbService.getObjectById(ImageProfileUser.class.getName(), 1);
        User user = new User(login, pass, email, imageProfileUser);
        dbService.save(user);
        forumService.sendMessageOnMail(email, "Добро пожаловать", "Приветствуем на нашем форуме.");
        httpSession.setAttribute("login", login);

        return "redirect:/";
    }

}
