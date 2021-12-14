package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;

// classe pe trattare dati di tipo Long
public class LongData implements sourceDataInterface {

    private Long val;

    public LongData(long x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (long)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
