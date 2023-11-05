package com.example.photoclone.repository;

import com.example.photoclone.model.Photo;
import org.springframework.data.repository.CrudRepository;


public interface PhotoRepository extends CrudRepository<Photo,Integer> {
}
