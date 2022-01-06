package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// classe pe trattare dati di tipo char
@NoArgsConstructor
public class StringData implements sourceDataInterface, Serializable {

    // in questo caso non abbiamo un wrapper ma direttamente la classe String
    private String val;

    public StringData(String x){
        this.val = x;
    }

    @Override
    public Object getData(){
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (String)val;
    }

    @Override
    public String toString() {
        return val;
    }
}
