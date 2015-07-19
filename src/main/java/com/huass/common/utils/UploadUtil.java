package com.huass.common.utils;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Random;  
import java.util.UUID;
  
import javax.imageio.ImageIO;  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.web.multipart.MultipartFile;  
  
  
public class UploadUtil  
{  
		private String imagePath = "/views/headImg";// 配置图片路径
  
    public void uploadImage1(HttpServletRequest request,MultipartFile file, String getUploadContentType, String getUploadFileName) throws IOException  
    {  
  
        String getImagePath = request.getSession().getServletContext().getRealPath(imagePath);  
  
        File image = new File(getImagePath);  
        if (!image.exists())  
        {  
            image.mkdir();  
        }  
  
        String uploadName = getUploadContentType;  
        System.out.println("图片类型 ------------" + uploadName);  
  
        String lastuploadName = uploadName.substring(uploadName.indexOf("/") + 1, uploadName.length());  
        System.out.println("得到上传文件的后缀名 ------------" + lastuploadName);  
  
        String fileNewName = generateFileName(getUploadFileName);  
        System.out.println("// 得到文件的新名字 ------------" + fileNewName);  
  
        imagePath = imagePath + "/" + fileNewName;  
        System.out.println(" 回图片路径   " + file.getInputStream());  
        System.out.println(" 最后返回图片路径   " + imagePath);  
  
        BufferedImage srcBufferImage = ImageIO.read(file.getInputStream());  
        System.out.println(" w " + srcBufferImage.getWidth() + " h " + srcBufferImage.getHeight());  
        BufferedImage scaledImage;  
        ScaleImage scaleImage = ScaleImage.getInstance();  
        int yw = srcBufferImage.getWidth();  
        int yh = srcBufferImage.getHeight();  
        int w = 10000, h = 10000;  
        if (w > yw && h > yh)  
        {  
            FileOutputStream fos = new FileOutputStream(getImagePath + "/" + fileNewName);  
  
            FileInputStream fis = (FileInputStream) file.getInputStream();  
            byte[] buffer = new byte[1024];  
            int len = 0;  
            while ((len = fis.read(buffer)) > 0)  
            {  
                fos.write(buffer, 0, len);  
            }  
        }  
        else  
        {  
            scaledImage = scaleImage.imageZoomOut(srcBufferImage, w, h);  
            FileOutputStream out = new FileOutputStream(getImagePath + "/" + fileNewName);  
            ImageIO.write(scaledImage, "jpeg", out);  
  
        }  
    }  
  
    private String generateFileName(String fileName)  
    {  
        int position = fileName.lastIndexOf(".");  
        String extension = UUID.randomUUID()+fileName.substring(position);  
        return  extension;  
    }  
  
    public String getImagepath()  
    {  
        return imagePath;  
    }  
  
}  