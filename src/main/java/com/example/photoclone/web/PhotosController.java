package com.example.photoclone.web;

import com.example.photoclone.model.Photo;
import com.example.photoclone.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotosController {

    private final PhotoService photoService;

    public PhotosController(PhotoService photoService ){
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String entryPoint(){
        return "Hello World";
    }

    @GetMapping("/photos")
    public Iterable<Photo> getPhoto(){
        return photoService.getAllPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhotoWithId(@PathVariable Integer id){
        Photo photo = photoService.getPhotoWith(id);
        if ( photo ==  null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @PostMapping("/photos/add")
    public void createPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        photoService.addPhoto(file);
    }
}
