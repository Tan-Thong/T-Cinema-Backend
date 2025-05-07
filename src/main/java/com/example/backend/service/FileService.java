package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    private final Path rootLocation = Paths.get("uploads/");

    public String saveFile(MultipartFile file, String subDir) {
        if (file == null || file.isEmpty())
            return null;
        // Nối đường dẫn thư mục con
        Path dirPath = rootLocation.resolve(subDir);
        try {
            Files.createDirectories(dirPath);

            String fileName = file.getOriginalFilename();
            Path destinationFile = dirPath.resolve(fileName).normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return "uploads/" + subDir + "/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Không thể lưu file: " + file.getOriginalFilename(), e);
        }
    }
}

