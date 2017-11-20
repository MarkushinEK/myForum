package forum.controller;

import forum.dataSet.Comment;
import forum.dataSet.Tread;
import forum.dataSet.User;
import forum.service.DBService;
import forum.service.DBServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ThreadController {
    @RequestMapping(value = "/{tag}", method = RequestMethod.GET)
    public String getListOfTreads(HttpSession httpSession,
                                  @PathVariable("tag") String tag,
                                  Map<String, Object> model) {

        DBService dbService = DBServiceimpl.instance();
        List<Tread> treads = (List<Tread>) dbService.getListTreadByTag(tag);
        model.put("tag", tag);
        model.put("treads", treads);

        return "list_of_treads";
    }

    @RequestMapping(value = "/{tag}/{id}", method = RequestMethod.GET)
    public String getTread(HttpSession httpSession,
                           @PathVariable("tag") String tag,
                           @PathVariable("id") String treadId,
                           Map<String, Object> model) {

        DBService dbService = DBServiceimpl.instance();
        Tread tread = (Tread) dbService.getObjectById(Tread.class.getName(), Long.parseLong(treadId));
        List<Comment> comments = tread.getComments();
        model.put("comments", tread.getComments());
        model.put("tread", tread);

        return "tread";
    }

    @RequestMapping(value = "/{tag}/{id}", method = RequestMethod.POST)
    public String addComment(HttpSession httpSession,
                             @PathVariable("tag") String tag,
                             @PathVariable("id") String treadId,
                             @RequestParam("message") String message,
                             Map<String, Object> model) {

        DBService dbService = DBServiceimpl.instance();
        Tread tread = (Tread) dbService.getObjectById(Tread.class.getName(), Long.parseLong(treadId));
        User user = dbService.getUserByLogin((String) httpSession.getAttribute("login"));
        Comment comment = new Comment(message, user);

        tread.addComment(comment);
        tread.setDateOfChange(comment.getDateOfCreateMessage());
        dbService.save(comment);
        dbService.update("threads", tread);
        model.put("tread", tread);

        return "redirect:/{tag}/{id}";
    }

}
