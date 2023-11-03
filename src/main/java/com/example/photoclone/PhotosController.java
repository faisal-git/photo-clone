package com.example.photoclone;

import com.example.photoclone.model.Photo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotosController {

    private HashMap<String,Photo> db = new HashMap<>(){{
        put("1",new Photo("id","photo.jpeg"));
    }};

    //private List<Photo> db = List.of(new Photo("id","photo.jpeg"));

    @GetMapping("/")
    public String entryPoint(){
        return "Hello World";
    }

    @GetMapping("/photos")
    public List<Photo> getPhoto(){
        return db.values().stream().toList();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhotoWithId(@PathVariable String id){
        Photo photo = db.get(id);
        if ( photo ==  null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @PostMapping("/photos/add")
    public Photo createPhoto(@RequestBody @Valid Photo photo){
        String id = UUID.randomUUID().toString();
        photo.setId(id);
        db.put(id,photo);
        return photo;
    }
}
