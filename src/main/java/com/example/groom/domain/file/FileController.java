package com.example.groom.domain.file;


import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class FileController {
    private final FileService fileService;

    @PostMapping()
    public String fileUpload(
            @RequestPart MultipartFile file
    ) throws IOException {

        return fileService.fileUpload(file);

    }

    @GetMapping("/{name}")
    public Resource downloadFile(@PathVariable String name)
            throws FileNotFoundException {

        Resource resource = fileService.loadFile(name);

        return resource;
    }

    @DeleteMapping("/{name}")
    public void removeFile(@PathVariable String name)
            {

        fileService.deleteFile(name);
    }
}
