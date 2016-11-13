package com.mmugur81.controller;

import com.mmugur81.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;

/**
 * Created by Mugurel on 13.11.2016.
 * Media controller
 */


@Controller
@RequestMapping("/media")
public class MediaController {

    private UploadService uploadService;

    @Autowired
    public MediaController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @ResponseBody
    @RequestMapping(value = "/{target}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> serveFile(
        @PathVariable("target")UploadService.Target target,
        @PathVariable("id") Long id
    ) {
        Resource file = uploadService.retrieveFileAsResource(target, id);
        if (file == null) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

        MediaType mediaType = MediaType.valueOf(new MimetypesFileTypeMap().getContentType(file.getFilename()));

        return ResponseEntity
            .ok()
            .contentType(mediaType)
            .body(file);
    }
}
