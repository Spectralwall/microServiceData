package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import com.example.microServiceNoSQl.Model.Utilities.dataInfoPair;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
public class registrazione {

    private LocalDate creationDate;

    private ArrayList<sourceDataInterface> typeNameRegistration;

    public registrazione(ArrayList<sourceDataInterface> val){
        this.creationDate = LocalDate.now();
        this.typeNameRegistration = val;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ArrayList<sourceDataInterface> getTypeNameRegistration() {
        return typeNameRegistration;
    }

    public void setTypeNameRegistration(ArrayList<sourceDataInterface> typeNameRegistration) {
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
