package forum.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface FileHandlerService {

    public BufferedImage getBufferedImage(String imageName, String extension);

    public void saveImage(long imageId, String extension, MultipartFile data);
}
