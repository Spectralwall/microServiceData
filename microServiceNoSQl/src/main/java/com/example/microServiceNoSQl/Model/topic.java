package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class topic {

    private String nome;

    private LocalDate creationDate;

    private ArrayList<registrazione> listRegistrazioni;

    public topic(String name){
        this.creationDate = LocalDate.now();
        this.nome = name;
        listRegistrazioni = new ArrayList<>();
    }

    public void setListRegistrazioni(ArrayList<registrazione> listRegistrazioni) {
        this.listRegistrazioni = listRegistrazioni;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<registrazione> getListRegistrazioni() {
        return listRegistrazioni;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "nome='" + nome + '\'' +
                ", creationDate=" + creationDate +
                ", listRegistrazioni=" + listRegistrazioni +
                '}';
    }
}
