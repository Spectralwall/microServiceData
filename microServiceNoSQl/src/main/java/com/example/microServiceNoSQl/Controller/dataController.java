package com.example.microServiceNoSQl.Controller;


import com.example.microServiceNoSQl.Model.ClassicData.TopicList;
import com.example.microServiceNoSQl.Model.Utilities.*;
import com.example.microServiceNoSQl.Repo.DataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
     * This metod return al UserData in mongo database
     */
    @PostMapping(value = "data")
    public List<UserData> all(){
        System.out.println("all user");
        return dataRepository.findAll();
    }

    /*
     * Create a nre user
     * TODO: fix this method whit RabbitMQ
     */
    @PostMapping(value = "/data/newuser")
    public ResponseEntity<UserData> create(@RequestBody UserData user){
        System.out.println("New user --------- document");
        UserData newUser = new UserData(user.getIdUser());
        dataRepository.save(newUser);

        System.out.println("Return topic for user :" +  user.getIdUser());
        Optional<UserData> a = dataRepository.findStudentByIdUser(user.getIdUser());
        return new ResponseEntity<>(a.get(), HttpStatus.OK);
    }

    /*
     * This metod return al UserData in mongo database
     */
    @PostMapping(value = "/data/document")
    public ResponseEntity<String> document(@RequestBody User user){
        System.out.println("Return document");
        System.out.println(String.valueOf(user.getId()));
        Optional<UserData> a = dataRepository.findStudentByIdUser(String.valueOf(user.getId()));
        UserAndData userAndData = new UserAndData(user,a.get());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(userAndData);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    /*
     * Return the topics for a id from user
     */
    @PostMapping(value = "/data/topics")
    public ResponseEntity<String> topicForUser(@RequestBody UserData user){
        System.out.println("Return topic for user :" +  user.getIdUser());
        Optional<UserData> a = dataRepository.findStudentByIdUser(user.getIdUser());
        System.out.println("topic :" +  user.getIdUser());
        System.out.println(a.get());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(a.get());
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping(value = "/data/deleteTopic")
    public ResponseEntity<String> deleteTopic(@RequestBody DeleteTopic val){
        System.out.println("Delete Topic, name :" +  val.getName() + "for userid :" + val.getId());
        UserData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        for(int a=0; a<tmp.getTopicList().size(); ++a){
            if(tmp.getTopicList().get(a).getName().equals(val.getName())){
                tmp.getTopicList().remove(a);
                break;
            }
        }
        dataRepository.save(tmp);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tmp);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping(value = "/data/deleteReg")
    public ResponseEntity<String> deleteReg(@RequestBody DeleteReg val){
        System.out.println("Delete Registration from topic :" +  val.getTopic() + "for userid :" + val.getIdUser());
        UserData tmp = dataRepository.findStudentByIdUser(val.getIdUser()).get();
        ArrayList<Topic> list = tmp.getTopicList();
        for(int a=0; a<list.size(); ++a){
            if(list.get(a).getName().equals(val.getTopic())){
                ArrayList<Registration> listReg = list.get(a).getListRegistrazioni();
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tmp);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping(value = "/data/sharedTopic")
    public ResponseEntity<String> sharedTopic(){
        System.out.println("return All shared Topic");
        List<UserData> tmp = dataRepository.findAll();
        ArrayList<Topic> val = new ArrayList<>();
        for(int a = 0; a<tmp.size();++a){
            ArrayList<Topic> tmp2 = tmp.get(a).getTopicList();
            for(int b = 0; b<tmp2.size();++b){
                if(tmp2.get(b).getShared()){
                    val.add(tmp2.get(b));
                }
            }
        }

        TopicList sharedTopicList = new TopicList(val);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(sharedTopicList);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping(value = "/data/changSharedTopic")
    public ResponseEntity<String> changeSharedTopic(@RequestBody DeleteTopic val){
        System.out.println("change value shared for topic :" +val.getName() + "for user :" + val.getId());
        UserData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        for(int a=0; a<tmp.getTopicList().size();++a){
            if(tmp.getTopicList().get(a).getName().equals(val.getName())){
                Boolean x = tmp.getTopicList().get(a).getShared();
                tmp.getTopicList().get(a).setShared(!x);
            }
        }
        dataRepository.save(tmp);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tmp);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping(value = "/data/changeNameTopic")
    public ResponseEntity<String> changeNameTopic(@RequestBody DeleteTopic val){
        System.out.println("Change name topic:" +  val.getName()+ "for userid :" + val.getId());
        UserData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        if (Topic .exist(tmp.getTopicList(),val.getNewName())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        for(int a=0; a<tmp.getTopicList().size(); ++a){
            if(tmp.getTopicList().get(a).getName().equals(val.getName())){
                tmp.getTopicList().get(a).setName(val.getNewName());
                break;
            }
        }
        dataRepository.save(tmp);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tmp);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }


    //aggiunge un nuovo documento per un utente
    @PostMapping(value = "/data/newTopic")
    public ResponseEntity<String> newTopic(@RequestBody NewTopic val){
        System.out.println("newTopic------");
        UserData tmp = dataRepository.findStudentByIdUser(val.getId()).get();
        if (Topic.exist(tmp.getTopicList(),val.getName())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        tmp.getTopicList().add(new Topic(val.getName(), val.getDescription(), val.getColor(), val.getNameType(),false));
        dataRepository.save(tmp);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tmp);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PostMapping(value="/data/newRegi")
    public ResponseEntity<String> newRegistratio(@RequestBody NewRegistration val){
        System.out.println("newRegistration------");
        System.out.println(val);
        UserData tmp = dataRepository.findStudentByIdUser(val.getUserId()).get();
        ArrayList<Topic> list = tmp.getTopicList();
        for(int a = 0; a<list.size(); ++a){
            if(list.get(a).getName().equals(val.getTopic())){
                Long spam = Long.valueOf(list.get(a).getNumberRecords()+1);
                Registration x = new Registration(val.trasformData(list.get(a)));
                x.setId(spam);
                list.get(a).getListRegistrazioni().add(x);
                list.get(a).setNumberRecords(spam);
            }
        }
        tmp.setTopicList(list);
        dataRepository.save(tmp);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(tmp);
            System.out.println(jsonString);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }


    @PostMapping(value = "/data/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        System.out.println("Delete data for user :" + user.getEmail());
        UserData tmp = dataRepository.findStudentByIdUser(String.valueOf(user.getId())).get();
        dataRepository.delete(tmp);
        return new ResponseEntity<>("data delete", HttpStatus.OK);
    }

}
