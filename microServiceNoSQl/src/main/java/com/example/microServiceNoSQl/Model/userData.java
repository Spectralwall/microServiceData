package com.example.microServiceNoSQl.Model;

/*
 * Classe cher sara il documento che viene salvato su mongo DB
 * abbiamo diversi dati
 * un id
 * un id relativo all'utente
 * e la lista dei topic
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document
public class userData {

    @Id
    private String id;

    private Long idUser;

    private ArrayList<topic> topicList;

    public userData(Long Userid){
        this.idUser = Userid;
        this.topicList = new ArrayList<>();
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setTopicList(ArrayList<topic> topicList) {
        this.topicList = topicList;
    }

    public String getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public ArrayList<topic> getTopicList() {
        return topicList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", idUser=" + idUser.toString() +
                ", topicList =" + topicList +
                '}';
    }


}

