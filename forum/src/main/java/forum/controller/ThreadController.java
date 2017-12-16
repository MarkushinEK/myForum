package forum.controller;

import forum.dataSet.Comment;
import forum.dataSet.Tread;
import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceImpl;
import forum.service.ForumService;
import forum.service.ForumServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ThreadController {

    @Value("#{${tagTranslate}}")
    private Map<String,String> tagTranslate;

    @RequestMapping(value = "/{tag}", method = RequestMethod.GET)
    public String getListOfTreads(HttpSession httpSession,
                                  @PathVariable("tag") String tag,
                                  Map<String, Object> model) {

        if (!tagTranslate.containsKey(tag)) {
            model.put("errorMessage", "404 Страница не найдена");
            return "errorpage";
        }

        DBService dbService = DBServiceImpl.instance();
        List<Tread> treads = (List<Tread>) dbService.getListTreadByTag(tag);

        model.put("tag", tag);
        model.put("treads", treads);

        try {
            model.put("tag_transcript", new String(tagTranslate.get(tag).getBytes("ISO-8859-1"), "WINDOWS-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "list_of_treads";
    }

    @RequestMapping(value = "/{tag}/{id}", method = RequestMethod.GET)
    public String getTread(HttpSession httpSession,
                           @PathVariable("tag") String tag,
                           @PathVariable("id") String treadId,
                           Map<String, Object> model) {

        DBService dbService = DBServiceImpl.instance();
        Tread tread = (Tread) dbService.getObjectById(Tread.class.getName(), Long.parseLong(treadId));
        List<Comment> comments = tread.getComments();
        comments.sort((o1, o2)->o1.getDateOfCreateMessage().compareTo(o2.getDateOfCreateMessage()));
        model.put("errorMessage", httpSession.getAttribute("errorMessage"));
        httpSession.removeAttribute("errorMessage");
        model.put("comments", comments);
        model.put("tread", tread);

        return "tread";
    }

    @RequestMapping(value = "/{tag}/{id}", method = RequestMethod.POST)
    public String addComment(HttpSession httpSession,
                             @PathVariable("tag") String tag,
                             @PathVariable("id") String treadId,
                             @RequestParam("message") String message,
                             @RequestParam("g-recaptcha-response") String recaptcha,
                             Map<String, Object> model) {

        ForumService forumService = ForumServiceImpl.instance();

        if (httpSession.getAttribute("login") == null)
            return "redirect:/{tag}/{id}";

        message = message.trim();

        if (!forumService.commentValidator(message)) {
            httpSession.setAttribute("errorMessage", "Максимальная длина сообщения 1500.\n" +
                    "Поле сообщения не должно быть пустым.");
            return "redirect:/{tag}/{id}";
        }

        DBService dbService = DBServiceImpl.instance();
        Tread tread = (Tread) dbService.getObjectById(Tread.class.getName(), Long.parseLong(treadId));

        if (recaptcha.isEmpty()) {
            httpSession.setAttribute("errorMessage", "Капча недействительна");
            return "redirect:/{tag}/{id}";
        }

        User user = dbService.getUserByLogin((String) httpSession.getAttribute("login"));
        Comment comment = new Comment(message, user);

        tread.addComment(comment);
        tread.setDateOfChange(comment.getDateOfCreateMessage());
        dbService.save(comment);
        dbService.update("users", user);
        dbService.update("threads", tread);
        model.put("tread", tread);

        return "redirect:/{tag}/{id}";
    }

}
