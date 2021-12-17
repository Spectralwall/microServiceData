package com.example.microServiceNoSQl.Repo;

import com.example.microServiceNoSQl.Model.userData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<userData,String> {
}

