package forum.controller;

import forum.dataSet.Tread;
import forum.service.DBService;
import forum.service.DBServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ThreadController {
    @RequestMapping(value = "/{tag}", method = RequestMethod.GET)
    public String exit(HttpSession httpSession, @PathVariable("tag") String tag, Map<String, Object> model) {
        DBService dbService = DBServiceimpl.instance();
        List<Tread> treads = (List<Tread>) dbService.getListTreadByTag(tag);
        model.put("tag", tag);
        model.put("treads", treads);
        return "thread";
    }
}
