package com.example.groom.entity;

import com.example.groom.entity.common.OwnEntity;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@NoArgsConstructor
public class Attachment extends OwnEntity {

    @Column
    private String ext;

    @Column
    private String originalName;

    @Column
    private String uuidFileName;

    @Column
    private String url;

    @Builder
    protected Attachment(String ext, String originalName, String uuidFileName, String url) {
        this.ext = ext;
        this.originalName = originalName;
        this.uuidFileName = uuidFileName;
        this.url = url;
    }

    static public Attachment of(String uploadDir, MultipartFile multipartFile){
        UUID uuid = UUID.randomUUID();
        List<String> fileName = Arrays.stream(multipartFile.getOriginalFilename().split("\\.")).toList();
        System.out.println(fileName);
        System.out.println(multipartFile.getOriginalFilename());
        String ext = fileName.get(fileName.size() - 1);
        String originalName = multipartFile.getOriginalFilename();
        String uuidFileName = uuid.toString() + "."+ext;
        Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(uuidFileName));
        return Attachment.builder()
                .ext(ext)
                .originalName(originalName)
                .uuidFileName(uuidFileName)
                .url(copyOfLocation.toString())
                .build();
    }
}
