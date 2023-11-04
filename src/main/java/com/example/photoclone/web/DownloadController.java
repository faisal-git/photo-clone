package com.example.photoclone.web;

import com.example.photoclone.model.Photo;
import com.example.photoclone.service.PhotoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController("/api/v1")
public class DownloadController {
    private final PhotoService photoService;

    DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Photo photo = photoService.getPhotoWith(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = photo.getData();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition contentDisposition = ContentDisposition
                .builder("inline")
                .filename(photo.getFileName())
                .build();
        httpHeaders.setContentDisposition(contentDisposition);
        return new ResponseEntity<>(data, httpHeaders, HttpStatus.OK);
    }

}
