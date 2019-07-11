package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author fabricio
 */
@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile multipartFile){
        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!"png".equals(ext) && !"jpeg".equals(ext)){
            throw new FileException("Only png and jpeg files are permited");
        }
        try {

            BufferedImage img = ImageIO.read(multipartFile.getInputStream());
            if ("png".equals(ext)){
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e){
            throw new FileException("Unable to read file");
        }
    }

    public BufferedImage pngToJpg(BufferedImage image){
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        return jpgImage;
    }


    public InputStream getInputStream(BufferedImage image, String extension){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image,extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        }catch (IOException e){
            throw new FileException("Unable to read file");
        }
    }

//    public BufferedImage cropSquare(BufferedImage sourceImg) {
//        int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
//        return Scalr.crop(
//                sourceImg,
//                (sourceImg.getWidth()/2) - (min/2),
//                (sourceImg.getHeight()/2) - (min/2),
//                min,
//                min);
//    }
//
//    public BufferedImage resize(BufferedImage sourceImg, int size) {
//        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
//    }


}
