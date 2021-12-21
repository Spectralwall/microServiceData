package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BooleanData implements sourceDataInterface {

    private Boolean val;

    public BooleanData(boolean x){
        val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (Boolean)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
