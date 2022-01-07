package com.example.microServiceNoSQl.Repo;

import com.example.microServiceNoSQl.Model.Utilities.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DataRepository extends MongoRepository<UserData,String> {
        Optional<UserData> findStudentByIdUser(String idUser);
}

