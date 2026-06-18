package com.intern.product.service;

import com.intern.product.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new FileStorageException("File is empty");
        }

        String contentType = file.getContentType();

        if (!(contentType.equals("image/jpeg")
                || contentType.equals("image/png")
                || contentType.equals("application/pdf"))) {

            throw new FileStorageException(
                    "Only JPG, PNG and PDF files are allowed");
        }

        try {

            Path path = Paths.get(uploadDir);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath =
                    path.resolve(file.getOriginalFilename());

            Files.copy(file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            return file.getOriginalFilename();

        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to upload file");
        }
    }

    public Path downloadFile(String filename) {

        Path path = Paths.get(uploadDir)
                .resolve(filename);

        if (!Files.exists(path)) {
            throw new FileStorageException(
                    "File not found");
        }

        return path;
    }

    public String deleteFile(String filename) {

        try {

            Path path = Paths.get(uploadDir)
                    .resolve(filename);

            if (!Files.exists(path)) {
                throw new FileStorageException(
                        "File not found");
            }

            Files.delete(path);

            return "File deleted successfully";

        } catch (IOException e) {

            throw new FileStorageException(
                    "Error deleting file");
        }
    }
}