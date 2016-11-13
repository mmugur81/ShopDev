package com.mmugur81.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mugurel on 10.11.2016.
 * Service for handling file uploads (usually images)
 */

public interface UploadService {

    enum Target {
        PRODUCT
    }

    enum Type {
        IMAGE
    }

    boolean uploadFile(MultipartFile uploadedFile, long entityId, Target target, Type fileType);

    /**
     * Target file name format: "{Target}-{id}.{extension}"
     *
     * @param target target type
     * @param entityId id
     * @return String
     */
    String retrieveFileName(Target target, long entityId);

    Resource retrieveFileAsResource(Target target, long entityId);

    default String getFileNameFormat(Target target, long entityId) {
        return target + "-" + entityId;
    }
}
