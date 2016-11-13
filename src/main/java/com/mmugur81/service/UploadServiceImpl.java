package com.mmugur81.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mugurel on 10.11.2016.
 * uploadService implementation
 */

@Service
public class UploadServiceImpl implements UploadService {

    private final String uploadDirName = "uploads";

    private String uploadDir;

    public UploadServiceImpl() {
        // Create upload directory if necessary
        uploadDir = System.getProperty("user.dir") + "/" + uploadDirName + "/";
        if (!Files.exists(Paths.get(uploadDir))) {
            try {
                Files.createDirectory(Paths.get(uploadDir));
            }
            catch (IOException e) {
                uploadDir = null;
            }
        }
    }

    @Override
    public boolean uploadFile(MultipartFile uploadedFile, long entityId, Target target, Type fileType) {

        String extension = uploadedFile.getOriginalFilename().split("\\.")[1];
        String newFileName = this.getFileNameFormat(target, entityId) + "." + extension;

        try {
            // Delete old file
            String oldFileName = this.retrieveFileName(target, entityId);
            if (oldFileName != null) {
                new File(uploadDir + oldFileName).delete();
            }

            File file = new File(uploadDir + newFileName);
            uploadedFile.transferTo(file);
        }
        catch (IOException e) {
            // Do some file error handling
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public String retrieveFileName(Target target, long entityId) {
        // Scan for a file matching the correct format
        String fileNamePrefix = this.getFileNameFormat(target, entityId);

        File dir = new File(uploadDir);
        File[] matches = dir.listFiles((file, s) -> s.startsWith(fileNamePrefix+"."));

        if (matches.length > 0) {
            return matches[0].getName();
        }

        return null;
    }

    @Override
    public Resource retrieveFileAsResource(Target target, long entityId) {
        String fileName = retrieveFileName(target, entityId);
        if (fileName == null) {
            return null;
        }

        Resource resource = new FileSystemResource(uploadDir + fileName);
        return (resource.exists() || resource.isReadable()) ? resource : null;
    }
}
