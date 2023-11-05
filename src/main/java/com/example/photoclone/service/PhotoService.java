package com.example.photoclone.service;

import com.example.photoclone.model.Photo;
import com.example.photoclone.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    PhotoService(PhotoRepository photoRepository){
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoWith(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void addPhoto(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        photo.setContentType(file.getContentType());
        photoRepository.save(photo);
    }
}
