package com.example.microServiceNoSQl.Controller;


import com.example.microServiceNoSQl.Model.Utilities.deleteTopic;
import com.example.microServiceNoSQl.Model.Utilities.newRegistration;
import com.example.microServiceNoSQl.Model.Utilities.newTopic;
import com.example.microServiceNoSQl.Model.registration;
import com.example.microServiceNoSQl.Model.topic;
import com.example.microServiceNoSQl.Model.userData;
import com.example.microServiceNoSQl.Repo.DataRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
@NoArgsConstructor
public class dataController {

    @Autowired
    DataRepository dataRepository;
    @Autowired
    MongoTemplate mongoTemplate;


    @PostMapping(value = "data")
    public List<userData> all(){
        System.out.println("all user");
        return dataRepository.findAll();
    }

    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newuser")
    public ResponseEntity<String> create(@RequestBody String userId){
        System.out.println("New user --------- document");
        userData newUser = new userData(userId);
        dataRepository.save(newUser);
        return new ResponseEntity<>("document Create", HttpStatus.OK);
    }

    //dato l'id dell'utente ritorna il suo documento
    @PostMapping(value = "/data/document")
    public ResponseEntity<userData> document(@RequestBody String userId){
        System.out.println("Return document");
        System.out.println(userId);
        Optional<userData> a = dataRepository.findStudentByIdUser(userId);
        return new ResponseEntity<>(a.get(), HttpStatus.OK);
    }

    //dato l'id dell'utente ritorna il tutti i suoi topic
    @PostMapping(value = "/data/topics")
    public ResponseEntity<ArrayList<topic>> topicForUser(@RequestBody String userId){
        System.out.println("Return topic for user :" +  userId);
        Optional<userData> a = dataRepository.findStudentByIdUser(userId);
        return new ResponseEntity<>(a.get().getTopicList(), HttpStatus.OK);
    }

    @PostMapping(value = "/data/deledeTopic")
    public ResponseEntity<String> deleteTopic(@RequestBody deleteTopic val){
        System.out.println("Delete Topic, name :" +  val.getName() + "for userid :" + val.getId());
        userData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        for(int a=0; a<tmp.getTopicList().size(); ++a){
            if(tmp.getTopicList().get(a).getName().equals(val.getName())){
                tmp.getTopicList().remove(a);
                break;
            }
        }
        dataRepository.save(tmp);
        return new ResponseEntity<>("topic delete", HttpStatus.OK);
    }

    @PostMapping(value = "/data/changeNameTopic")
    public ResponseEntity<ArrayList<topic>> changeNameTopic(@RequestBody deleteTopic val){
        System.out.println("Change name topic:" +  val.getName()+ "for userid :" + val.getId());
        userData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        for(int a=0; a<tmp.getTopicList().size(); ++a){
            if(tmp.getTopicList().get(a).getName().equals(val.getName())){
                tmp.getTopicList().get(a).setName(val.getNewName());
                break;
            }
        }
        dataRepository.save(tmp);
        return new ResponseEntity<>(dataRepository.findStudentByIdUser(val.getId()).get().getTopicList(), HttpStatus.OK);
    }


    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newTopic")
    public ResponseEntity<String> newTopic(@RequestBody newTopic val){
        System.out.println("newTopic------");
        userData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        tmp.getTopicList().add(new topic(val.getName(), val.getDescription(), val.getColor(), val.getNameType()));
        dataRepository.save(tmp);
        return new ResponseEntity<>("topic add", HttpStatus.OK);
    }

    @PostMapping(value="/data/newRegi")
    public ResponseEntity<ArrayList<topic>> newRegistratio(@RequestBody newRegistration val){
        System.out.println("newRegistration------");
        System.out.println(val);
        userData tmp = dataRepository.findStudentByIdUser(val.getUserId()).get();
        ArrayList<topic> list = tmp.getTopicList();
        for(int a = 0; a<list.size(); ++a){
            if(list.get(a).getName().equals(val.getTopic())){

                Long spam = Long.valueOf(list.get(a).getNumberRecords()+1);
                registration x = new registration(val.trasformData(list.get(a)));
                x.setId(spam);
                list.get(a).getListRegistrazioni().add(x);
                list.get(a).setNumberRecords(spam);
            }
        }
        tmp.setTopicList(list);
        dataRepository.save(tmp);
        return new ResponseEntity<>(dataRepository.findStudentByIdUser(val.getUserId()).get().getTopicList(), HttpStatus.OK);
    }

}
