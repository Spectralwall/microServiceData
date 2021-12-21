package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Utilities.dataInfoPair;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
public class topic {

    @Id
    private Long id;

    private String name;

    private String description;

    private LocalDate creationDate;//data di creazione

    private ArrayList<dataInfoPair> nameType;

    private ArrayList<String> color;

    private ArrayList<registration> listRegistrazioni;//lista di future registrazioni

    private Long numberRecords;

    public topic(String name,String description, ArrayList<String> colors,ArrayList<dataInfoPair> nameType){
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.nameType = nameType;
        this.color = colors;
        listRegistrazioni = new ArrayList<>();
        this.numberRecords = Long.valueOf(0);
    }

    public void setListRegistrazioni(ArrayList<registration> listRegistrazioni) {
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

    public ArrayList<registration> getListRegistrazioni() {
        return listRegistrazioni;
    }

    public String getName() {
        return name;
    }

    public Long getNumberRecords() {
        return numberRecords;
    }

    public void setNumberRecords(Long numberRecords) {
        this.numberRecords = numberRecords;
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
