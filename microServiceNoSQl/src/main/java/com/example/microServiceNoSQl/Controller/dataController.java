package com.example.microServiceNoSQl.Controller;


import com.example.microServiceNoSQl.Model.Utilities.newTopic;
import com.example.microServiceNoSQl.Model.topic;
import com.example.microServiceNoSQl.Model.userData;
import com.example.microServiceNoSQl.Repo.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * classe che fungera da controller per le chiamate del sito relative hai dati
 */

@RestController
@RequestMapping("/api/v2")
public class dataController {

    @Autowired
    DataRepository dataRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping(value = "data")
    public List<userData> all(){
        return dataRepository.findAll();
    }

    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newUser")
    public ResponseEntity<String> create(@RequestAttribute Long userId){
        System.out.println("New user --> document");
        userData newUser = new userData(userId);
        dataRepository.save(newUser);
        return new ResponseEntity<>("document Create", HttpStatus.OK);
    }

    //dato l'id dell'utente ritorna il suo documento
    @PostMapping(value = "/data/document")
    public ResponseEntity<userData> document(@RequestAttribute Long userId){
        System.out.println("Return document");
        Query query = new Query();
        query.addCriteria(Criteria.where("idUser").is(userId));
        List<userData> tmp = mongoTemplate.find(query,userData.class);
        if(tmp.size() > 1){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        if (tmp.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        userData a = tmp.get(0);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newTopic")
    public ResponseEntity<String> newTopic(@RequestAttribute newTopic val){
        userData tmp = dataRepository.findById(val.getId()).get();
        tmp.getTopicList().add(new topic(val.getName(), val.getDescription(), val.getCreationDate(), val.getColor(), val.getNameType()));
        dataRepository.save(tmp);
        return new ResponseEntity<>("topic add", HttpStatus.OK);
    }
    

}
