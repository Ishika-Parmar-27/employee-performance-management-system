package com.intern.product.controller;

import com.intern.product.dto.ApiResponse;
import com.intern.product.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequestMapping("/files")

public class FileController {

    private FileService fileService;
    public FileController(FileService fileService) {
        this.fileService=fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String filename =
                fileService.uploadFile(file);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Uploaded : " + filename));
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String filename)
            throws Exception {

        Path path =
                fileService.downloadFile(filename);

        Resource resource =
                new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<ApiResponse> deleteFile(
            @PathVariable String filename) {

        String message =
                fileService.deleteFile(filename);

        return ResponseEntity.ok(
                new ApiResponse(message));
    }
}