package com.example.groom.domain.file;

import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.entity.Attachment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    @Value("${upload-path}")
    private String uploadDir;

    public String  fileUpload(MultipartFile multipartFile) throws IOException {
        // File.seperator 는 OS종속적이다.
        // Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제한다
        Attachment attachment = Attachment.of(uploadDir, multipartFile);
        File file = new File(uploadDir + attachment.getUuidFileName());
        try {
            final String imagePath = "src/main/resources/"; //path
            FileOutputStream output = new FileOutputStream(imagePath+attachment.getUuidFileName());
            output.write(multipartFile.getBytes());
            fileRepository.save(attachment);
            return attachment.getUuidFileName();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAILED);
        }

    }

    public Resource loadFile(String name) throws FileNotFoundException {
        Attachment attachment = this.fileRepository.findByUuidFileName(name).orElseThrow(()-> new CustomException(ErrorCode.FILE_UPLOAD_FAILED));
        try {
            Path file = Path.of(this.uploadDir).resolve(attachment.getUuidFileName()).normalize();
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new FileNotFoundException("Could not find file");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file");
        }

    }

    public void deleteFile(String name) {
        Attachment attachment = this.fileRepository.findByUuidFileName(name).orElseThrow(()-> new CustomException(ErrorCode.FILE_UPLOAD_FAILED));
        File file = new File(attachment.getUrl());
        if( file.delete() ){
            System.out.println(file.getName()+" 삭제성공");
        }else{
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAILED);
        }
        this.fileRepository.delete(attachment);
    }
}