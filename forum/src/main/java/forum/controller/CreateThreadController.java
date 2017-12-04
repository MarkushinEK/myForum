package forum.controller;

import forum.dataSet.Tread;
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
public class CreateThreadController {
    @RequestMapping(value = "/{tag}/create", method = RequestMethod.GET)
    public String initCreateForm(HttpSession httpSession,
                                 Map<String, Object> model,
                                 @PathVariable("tag") String tag) {

        if (httpSession.getAttribute("login") == null) {
          return "redirect:/{tag}";
        }

        model.put("tag", tag);
        model.put("errorMessage", httpSession.getAttribute("errorMessage"));
        httpSession.removeAttribute("errorMessage");

        return "createform";
    }

    @RequestMapping(value = "/{tag}/create", method = RequestMethod.POST)
    public String createThread(HttpSession httpSession,
                               @PathVariable("tag") String tag,
                               @RequestParam("g-recaptcha-response") String recaptcha,
                               @RequestParam("subject") String subject,
                               @RequestParam("comment") String comment) {

        ForumService forumService = ForumServiceImpl.instance();
        comment = comment.trim();
        subject = subject.trim();

        if (httpSession.getAttribute("login") == null) {
            return "redirect:/{tag}";
        }

        if (recaptcha.isEmpty()) {
            httpSession.setAttribute("errorMessage", "Капча недействительна.");
            return "redirect:/{tag}/create";
        }

        if (!forumService.commentValidator(comment)) {
            httpSession.setAttribute("errorMessage", "Максимальная длина сообщения 1500.\n" +
                    "Поле сообщения не должно быть пустым.");
            return "redirect:/{tag}/create";
        }

        if (!forumService.subjectValidator(subject)) {
            httpSession.setAttribute("errorMessage", "Максимальная длина темы 50.\n" +
                    "Поле темы не должно быть пустым.");
        }

        DBService dbService = DBServiceImpl.instance();
        User user = dbService.getUserByLogin(httpSession.getAttribute("login").toString());
        Tread tread = new Tread(subject, comment, user, tag);
        dbService.save(tread);
        dbService.update("users", user);

        return "redirect:/{tag}";
    }

}
