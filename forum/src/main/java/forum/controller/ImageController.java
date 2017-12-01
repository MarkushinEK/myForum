package forum.controller;

import forum.dataSet.ImageProfileUser;
import forum.dataSet.User;
import forum.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

@Controller
public class ImageController {

    @RequestMapping(value = "/image/{imageName}.{extension}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void returnImage(HttpServletResponse response,
                            @PathVariable("imageName") String imageName,
                            @PathVariable("extension") String extension) {

        try(OutputStream out = response.getOutputStream()) {
            FileHandlerService fileHandlerService = FileHandlerServiceImpl.instance();
            BufferedImage bufImg = fileHandlerService.getBufferedImage(imageName, extension);
            ImageIO.write(bufImg, extension, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/image/add", method = RequestMethod.POST)
    public String addImage(HttpSession httpSession,
                           @RequestParam("data") MultipartFile data,
                           Map<String, Object> model) {
        DBService dbService = DBServiceImpl.instance();

        User user = dbService.getUserByLogin((String) httpSession.getAttribute("login"));
        String extension = data.getOriginalFilename().substring(data.getOriginalFilename().lastIndexOf(".")+1);

        if(!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp"))) {
            httpSession.setAttribute("errorMessage", "Недопустимый формат данных");
            return "redirect:/profile/" + user.getLogin();
        }

        if (data.getSize()>2000000) {
            httpSession.setAttribute("errorMessage", "Файл превышает допустимый размер");
            return "redirect:/profile/" + user.getLogin();
        }

        String fileName = data.getOriginalFilename();
        ImageProfileUser imageProfileUser = new ImageProfileUser(data.getOriginalFilename());
        dbService.save(imageProfileUser);
        user.setImageProfileUser(imageProfileUser);
        dbService.update(User.class.getName(), user);

        FileHandlerService fileHandlerService = FileHandlerServiceImpl.instance();

        imageProfileUser = user.getImageProfileUser();
        fileHandlerService.saveImage(imageProfileUser.getId(), imageProfileUser.getExtension(), data);

        model.put("login", user.getLogin());
        model.put("image", user.getImageProfileUser().getId() + "." + user.getImageProfileUser().getExtension());
        return "redirect:/profile/" + user.getLogin();
    }
}
