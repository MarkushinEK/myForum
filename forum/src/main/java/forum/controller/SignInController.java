package forum.controller;

import forum.service.DBService;
import forum.service.DBServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signInUser(HttpSession httpSession,
                             @RequestParam("login") String login,
                             @RequestParam("pass") String pass) {

        DBService dbService = DBServiceimpl.instance();
        if (dbService.getUserByLogin(login) != null && dbService.getUserByLogin(login).getPassword().equals(pass)) {
            httpSession.setAttribute("login", login);
            return "redirect:/";
        }
        httpSession.setAttribute("message", "Неверный логин или пароль.");

        return "redirect:/";
    }

}
