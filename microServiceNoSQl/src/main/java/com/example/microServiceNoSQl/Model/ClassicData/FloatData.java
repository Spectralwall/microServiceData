package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;

// classe pe trattare dati di tipo float
public class FloatData implements sourceDataInterface {

    private Float val;

    public FloatData(float x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (float)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
