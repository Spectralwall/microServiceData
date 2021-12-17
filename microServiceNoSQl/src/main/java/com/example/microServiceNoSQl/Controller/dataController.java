package com.example.microServiceNoSQl.Controller;


import com.example.microServiceNoSQl.Model.userData;
import com.example.microServiceNoSQl.Repo.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    MongoTemplate mongoTemplate;

    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newUser")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestAttribute Long userId){
        userData newUser = new userData(userId);
        dataRepository.save(newUser);
    }

    //dato l'id dell'utente ritorna il suo documento
    @GetMapping(value = "/data/document")
    @ResponseStatus(HttpStatus.OK)
    public userData document(@RequestAttribute Long userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("idUser").is(userId));
        List<userData> tmp = mongoTemplate.find(query,userData.class);
        if(tmp.size() > 1){
            throw new IllegalStateException("many userData for this id");
        }
        if (tmp.isEmpty()){
            throw new IllegalStateException("no userData whit this id");
        }
        userData a = tmp.get(0);
        return dataRepository.findById(a.getId()).get();
    }

    //aggiunge un nuovo documento per un utente
    /*
    @PostMapping(value = "/data/newTopic")
    @ResponseStatus(HttpStatus.OK)
    public void newTopic(@RequestAttribute String id){
        userData tmp = dataRepository.findById(id).get();

    }
    
     */
}
