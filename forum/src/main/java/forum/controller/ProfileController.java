package forum.controller;

import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            model.put("imageName", user.getImageProfileUser().getId() + "." + user.getImageProfileUser().getExtension());
            if (dbService.getUserByLogin((String )httpSession.getAttribute("login")).getLogin().equals(login))
                return "ownprofile";
            return "profile";
        }

        return "redirect:/";
    }

}
