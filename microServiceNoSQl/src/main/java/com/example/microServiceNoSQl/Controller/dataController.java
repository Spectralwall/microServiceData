package com.example.microServiceNoSQl.Controller;


import com.example.microServiceNoSQl.Model.Utilities.newTopic;
import com.example.microServiceNoSQl.Model.topic;
import com.example.microServiceNoSQl.Model.userData;
import com.example.microServiceNoSQl.Repo.DataRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * classe che fungera da controller per le chiamate del sito relative hai dati
 */

@RestController
@RequestMapping("/api/v2")
@NoArgsConstructor
public class dataController {

    @Autowired
    DataRepository dataRepository;
    @Autowired
    MongoTemplate mongoTemplate;


    //FUNZIA
    @PostMapping(value = "data")
    public List<userData> all(){
        System.out.println("all user");
        return dataRepository.findAll();
    }

    //FUNZIA
    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newuser")
    public ResponseEntity<String> create(@RequestBody String userId){
        System.out.println("New user --------- document");
        userData newUser = new userData(userId);
        dataRepository.save(newUser);
        return new ResponseEntity<>("document Create", HttpStatus.OK);
    }

    //FUNZIA
    //dato l'id dell'utente ritorna il suo documento
    @PostMapping(value = "/data/document")
    public ResponseEntity<userData> document(@RequestBody String userId){
        System.out.println("Return document");
        Optional<userData> a = dataRepository.findStudentByIdUser(userId);
        return new ResponseEntity<>(a.get(), HttpStatus.OK);
    }


    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newTopic")
    public ResponseEntity<String> newTopic(@RequestBody newTopic val){
        Optional<userData> a = dataRepository.findStudentByIdUser(val.getId());
        userData tmp = a.get();
        tmp.getTopicList().add(new topic(val.getName(), val.getDescription(), val.getColor(), val.getNameType()));
        dataRepository.save(tmp);
        return new ResponseEntity<>("topic add", HttpStatus.OK);
    }
    

}
