package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import com.example.microServiceNoSQl.Model.Utilities.dataInfoPair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class registrazione {

    private LocalDate creationDate;

    private ArrayList<dataInfoPair> typeNameRegistration;

    public registrazione(ArrayList<dataInfoPair> val){
        this.creationDate = LocalDate.now();
        this.typeNameRegistration = val;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ArrayList<dataInfoPair> getTypeNameRegistration() {
        return typeNameRegistration;
    }

    public void setTypeNameRegistration(ArrayList<dataInfoPair> typeNameRegistration) {
        this.typeNameRegistration = typeNameRegistration;
    }

    @Override
    public String toString() {
        return "registrazione{" +
                "creationDate=" + creationDate +
                ", listaDati=" + typeNameRegistration +
                '}';
    }
}
