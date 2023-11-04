package com.example.photoclone.service;

import com.example.photoclone.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {

    private HashMap<String, Photo> db = new HashMap<>(){{
        put("1",new Photo("1","photo.jpeg"));
    }};

    public List<Photo> getAllPhotos() {
        return db.values().stream().toList();
    }

    public Photo getPhotoWith(String id) {
        return db.get(id);
    }

    public void addPhoto(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        db.put(photo.getId(), photo);
    }
}
