package com.example.microServiceNoSQl.Repo;

import com.example.microServiceNoSQl.Model.userData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DataRepository extends MongoRepository<userData,String> {
        //Optional<userData> findStudentByIdUser(String idUser);
}

