package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;

// classe pe trattare dati di tipo double
public class DoubleData implements sourceDataInterface {

    private Double val;

    public  DoubleData(double x){
        val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (Double) val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
