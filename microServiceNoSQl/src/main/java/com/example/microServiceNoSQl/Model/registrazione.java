package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class registrazione {

    private LocalDate creationDate;

    private ArrayList<sourceDataInterface> listaDati;

    public registrazione(ArrayList<sourceDataInterface> val){
        this.creationDate = LocalDate.now();
        this.listaDati = val;
    }

    public void setListaDati(ArrayList<sourceDataInterface> listaDati) {
        this.listaDati = listaDati;
    }

    public ArrayList<sourceDataInterface> getListaDati() {
        return listaDati;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "registrazione{" +
                "creationDate=" + creationDate +
                ", listaDati=" + listaDati +
                '}';
    }
}
