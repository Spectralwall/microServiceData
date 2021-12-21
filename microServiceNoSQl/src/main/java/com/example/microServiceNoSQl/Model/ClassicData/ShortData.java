package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import lombok.NoArgsConstructor;

// classe pe trattare dati di tipo float
@NoArgsConstructor
public class ShortData implements sourceDataInterface {

    private Short val;

    public ShortData(short x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (short)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
