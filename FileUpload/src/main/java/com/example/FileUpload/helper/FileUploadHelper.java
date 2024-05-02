package com.example.FileUpload.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    //this is static means this give path of this system only
//    public final String upload_dir="/home/admin1/Documents/SpringBootPrograms/FileUpload/src/main/resources/static";

    //for generating path dynamically
    public String upload_dir=new ClassPathResource("static/images/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }


    public boolean uploadFile(MultipartFile multipartFile){
        boolean flag=false;
        try{
//            InputStream inputStream=multipartFile.getInputStream();
//            byte data[]=new byte[inputStream.available()];
//            inputStream.read(data);
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(upload_dir+ File.separator+multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            flag=true;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    return flag;
    }
}
