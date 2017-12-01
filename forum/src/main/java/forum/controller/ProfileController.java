package forum.controller;

import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceImpl;
import forum.service.ForumService;
import forum.service.ForumServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ProfileController {

    @RequestMapping(value = "/profile/{login}", method = RequestMethod.GET)
    public String innitProfile(HttpSession httpSession,
                               HttpServletResponse response,
                               @PathVariable("login") String login,
                               Map<String, Object> model) {

        DBService dbService = DBServiceImpl.instance();
        User user = dbService.getUserByLogin(login);

        if (user != null) {
            model.put("login", login);
            model.put("email", user.getEmail());
            model.put("imageName", user.getImageProfileUser().getId() + "." + user.getImageProfileUser().getExtension());
            if (httpSession.getAttribute("login") != null)
                if (dbService.getUserByLogin((String )httpSession.getAttribute("login")).getLogin().equals(login)) {
                    model.put("errorMessage", httpSession.getAttribute("errorMessage"));
                    httpSession.removeAttribute("errorMessage");
                    return "ownprofile";
                }

            return "profile";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/profile/{login}/changepass", method = RequestMethod.POST)
    public String changePass(HttpSession httpSession,
                           @RequestParam("pass") String pass,
                           HttpServletResponse response,
                           @PathVariable("login") String login,
                           Map<String, Object> model) {

        if (httpSession.getAttribute("login") != null)
            if (httpSession.getAttribute("login").equals(login)) {
                ForumService forumService = ForumServiceImpl.instance();

                if (!forumService.loginValidator(pass)) {
                    httpSession.setAttribute("errorMessage", "Пароль должен быть более пяти и меньше двадцати символов без пробелов.");
                    return "redirect:/profile/{login}";
                }

                DBService dbService = DBServiceImpl.instance();
                User user = dbService.getUserByLogin(login);
                user.setPassword(pass);
                dbService.update(User.class.getName(), user);
                return "redirect:/profile/{login}";
            }

        model.put("errorMessage", "Нет доступа к данным");
        return "errorpage";
    }

}
