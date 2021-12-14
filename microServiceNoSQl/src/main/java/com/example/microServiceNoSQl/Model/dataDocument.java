package com.example.microServiceNoSQl.Model;

/*
 * Classe cher sara il documento che viene salvato su mongo DB
 * abbiamo diversi dati
 * un id
 * un id relativo all'utente che ha inserito il topic
 * e la lista di dati
 */

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document
public class dataDocument {

    @Id
    private String id;

    private String idUser;

    private ArrayList<sourceDataInterface> dati;
}
