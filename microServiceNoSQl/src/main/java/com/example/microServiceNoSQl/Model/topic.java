package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import com.example.microServiceNoSQl.Model.Utilities.dataInfoPair;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class topic {

    @Id
    private Long id;

    private String name;

    private String description;

    private LocalDate creationDate;//data di creazione

    private ArrayList<dataInfoPair> nameType;

    private ArrayList<String> color;

    private ArrayList<registrazione> listRegistrazioni;//lista di future registrazioni

    public topic(String name,String description, ArrayList<String> colors,ArrayList<dataInfoPair> nameType){
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.nameType = nameType;
        this.color = colors;
        listRegistrazioni = new ArrayList<>();
    }

    public void setListRegistrazioni(ArrayList<registrazione> listRegistrazioni) {
        this.listRegistrazioni = listRegistrazioni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNameType(ArrayList<dataInfoPair> nameType) {
        this.nameType = nameType;
    }

    public ArrayList<dataInfoPair> getNameType() {
        return nameType;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<registrazione> getListRegistrazioni() {
        return listRegistrazioni;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", nameType=" + nameType +
                ", color=" + color +
                ", listRegistrazioni=" + listRegistrazioni +
                '}';
    }
}
