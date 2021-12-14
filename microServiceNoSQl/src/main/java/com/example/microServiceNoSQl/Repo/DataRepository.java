package com.example.microServiceNoSQl.Repo;

import com.example.microServiceNoSQl.Model.dataDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<dataDocument,String> {
}

