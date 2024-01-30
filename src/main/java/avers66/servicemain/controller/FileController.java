package avers66.servicemain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * FileController
 *
 * @Author Tretyakov Alexandr
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> fileUpload(@RequestPart MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("File is empty");
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            log.info("file content: {}", content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("file uploaded successfully");

    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String fileName) {
        String filePath = "files/" + fileName;
        Resource fileResource = new ClassPathResource(filePath);
        if (!fileResource.exists()) return ResponseEntity.notFound().build();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.setContentType(MediaType.TEXT_PLAIN);
        return ResponseEntity.ok().headers(headers).body(fileResource);

    }


}
