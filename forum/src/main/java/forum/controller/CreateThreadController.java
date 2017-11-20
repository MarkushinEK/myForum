package forum.controller;

import forum.dataSet.Tread;
import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CreateThreadController {
    @RequestMapping(value = "/{tag}/create", method = RequestMethod.GET)
    public String initCreateForm(HttpSession httpSession,
                                 Map<String, Object> model,
                                 @PathVariable("tag") String tag) {

        if (httpSession.getAttribute("login") == null) {
          return "redirect:/{tag}";
        }
        model.put("tag", tag);

        return "createform";
    }

    @RequestMapping(value = "/{tag}/create", method = RequestMethod.POST)
    public String createThread(HttpSession httpSession,
                               @PathVariable("tag") String tag,
                               @RequestParam("subject") String subject,
                               @RequestParam("comment") String comment) {

        if (httpSession.getAttribute("login") == null) {
            return "redirect:/{tag}";
        }
        DBService dbService = DBServiceimpl.instance();
        User user = dbService.getUserByLogin(httpSession.getAttribute("login").toString());
        Tread tread = new Tread(subject, comment, user, tag);
        dbService.save(tread);

        return "redirect:/{tag}";
    }

}
