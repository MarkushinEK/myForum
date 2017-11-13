package forum.controller;

import forum.dataSet.Traed;
import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CreateThreadController {
    @RequestMapping(value = "/{tag}/create", method = RequestMethod.GET)
    public String initCreateForm(HttpSession httpSession, Map<String, Object> model, @PathVariable("tag") String tag) {
        model.put("tag", tag);
        return "createform";
    }

    @RequestMapping(value = "/{tag}/create", method = RequestMethod.POST)
    public String createThread(HttpSession httpSession, @PathVariable("tag") String tag, @RequestParam("subject") String subject, @RequestParam("comment") String comment) {
        DBService dbService = DBServiceimpl.instance();
        User user = dbService.getUserByLogin(httpSession.getAttribute("login").toString());
        Traed traed = new Traed(subject, comment, user, tag);
        dbService.save(traed);
        return "createform";
    }
}
