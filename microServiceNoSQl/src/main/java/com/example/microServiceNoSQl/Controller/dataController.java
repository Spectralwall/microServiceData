package com.example.microServiceNoSQl.Controller;


import com.example.microServiceNoSQl.Model.Utilities.deleteReg;
import com.example.microServiceNoSQl.Model.Utilities.deleteTopic;
import com.example.microServiceNoSQl.Model.Utilities.newRegistration;
import com.example.microServiceNoSQl.Model.Utilities.newTopic;
import com.example.microServiceNoSQl.Model.registration;
import com.example.microServiceNoSQl.Model.topic;
import com.example.microServiceNoSQl.Model.userData;
import com.example.microServiceNoSQl.Repo.DataRepository;
import com.google.gson.Gson;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v2")
@NoArgsConstructor
public class dataController {

    @Autowired
    DataRepository dataRepository;
    @Autowired
    MongoTemplate mongoTemplate;


    /*
     * This metod return al userData in mongo database
     */
    @PostMapping(value = "data")
    public List<userData> all(){
        System.out.println("all user");
        return dataRepository.findAll();
    }

    /*
     * Create a nre user
     * TODO: fix this method whit RabbitMQ
     */
    @PostMapping(value = "/data/newuser")
    public ResponseEntity<userData> create(@RequestBody userData user){
        System.out.println("New user --------- document");
        userData newUser = new userData(user.getIdUser());
        dataRepository.save(newUser);

        System.out.println("Return topic for user :" +  user.getIdUser());
        Optional<userData> a = dataRepository.findStudentByIdUser(user.getIdUser());
        return new ResponseEntity<>(a.get(), HttpStatus.OK);
    }

    /*
     * This metod return al userData in mongo database
     */
    @PostMapping(value = "/data/document")
    public ResponseEntity<userData> document(@RequestBody userData user){
        System.out.println("Return document");
        System.out.println(user.getIdUser());
        Optional<userData> a = dataRepository.findStudentByIdUser(user.getIdUser());
        return new ResponseEntity<>(a.get(), HttpStatus.OK);
    }

    /*
     * Return the topics for a id from user
     */
    @PostMapping(value = "/data/topics")
    public ResponseEntity<String> topicForUser(@RequestBody userData user){
        System.out.println("Return topic for user :" +  user.getIdUser());
        Optional<userData> a = dataRepository.findStudentByIdUser(user.getIdUser());
        System.out.println("topic :" +  user.getIdUser());
        Gson gson = new Gson();
        String userJson = gson.toJson(a.get());
        System.out.println(userJson);
        return new ResponseEntity<>(userJson, HttpStatus.OK);
    }

    @PostMapping(value = "/data/deleteTopic")
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

    @PostMapping(value = "/data/deleteReg")
    public ResponseEntity<ArrayList<topic>> deleteReg(@RequestBody deleteReg val){
        System.out.println("Delete Registration from topic :" +  val.getTopic() + "for userid :" + val.getIdUser());
        userData tmp = dataRepository.findStudentByIdUser(val.getIdUser()).get();
        ArrayList<topic> list = tmp.getTopicList();
        for(int a=0; a<list.size(); ++a){
            if(list.get(a).getName().equals(val.getTopic())){
                ArrayList<registration> listReg = list.get(a).getListRegistrazioni();
                for (int x=0; x<listReg.size(); ++x){
                    if(listReg.get(x).getId() == val.getIdReg()){
                        listReg.remove(x);
                        list.get(a).setListRegistrazioni(listReg);
                        break;
                    }
                }
                break;
            }
        }
        tmp.setTopicList(list);
        dataRepository.save(tmp);
        return new ResponseEntity<>(dataRepository.findStudentByIdUser(val.getIdUser()).get().getTopicList(), HttpStatus.OK);
    }

    @PostMapping(value = "/data/sharedTopic")
    public ResponseEntity<ArrayList<topic>> sharedTopic(){
        System.out.println("return All shared Topic");
        List<userData> tmp = dataRepository.findAll();
        ArrayList<topic> val = new ArrayList<>();
        for(int a = 0; a<tmp.size();++a){
            ArrayList<topic> tmp2 = tmp.get(a).getTopicList();
            for(int b = 0; b<tmp2.size();++b){
                if(tmp2.get(b).getShared()){
                    val.add(tmp2.get(b));
                }
            }
        }
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

    @PostMapping(value = "/data/changSharedTopic")
    public ResponseEntity<String> changeSharedTopic(@RequestBody deleteTopic val){
        System.out.println("change value shared for topic :" +val.getName() + "for user :" + val.getId());
        userData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        for(int a=0; a<tmp.getTopicList().size();++a){
            if(tmp.getTopicList().get(a).getName().equals(val.getName())){
                Boolean x = tmp.getTopicList().get(a).getShared();
                tmp.getTopicList().get(a).setShared(!x);
            }
        }
        dataRepository.save(tmp);
        return new ResponseEntity<>("change succes", HttpStatus.OK);
    }

    @PostMapping(value = "/data/changeNameTopic")
    public ResponseEntity<ArrayList<topic>> changeNameTopic(@RequestBody deleteTopic val){
        System.out.println("Change name topic:" +  val.getName()+ "for userid :" + val.getId());
        userData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        if (topic.exist(tmp.getTopicList(),val.getNewName())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
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
    public ResponseEntity<newTopic> newTopic(@RequestBody newTopic val){
        System.out.println("newTopic------");
        userData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        if (topic.exist(tmp.getTopicList(),val.getName())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        tmp.getTopicList().add(new topic(val.getName(), val.getDescription(), val.getColor(), val.getNameType(),false));
        dataRepository.save(tmp);
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

    @PostMapping(value="/data/newRegi")
    public ResponseEntity<newRegistration> newRegistratio(@RequestBody newRegistration val){
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
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

}
