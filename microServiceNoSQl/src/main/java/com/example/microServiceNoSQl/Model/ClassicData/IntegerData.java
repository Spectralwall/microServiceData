package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import lombok.NoArgsConstructor;

// classe pe trattare dati di tipo integer
@NoArgsConstructor
public class IntegerData implements sourceDataInterface {

    //come attributo abbiamo un Integer ovvero la classe wrapper di int
    //cosi possimo sfruttare tutti i supi metodi
    private Integer val;

    public IntegerData(int x){
        val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (Integer) val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
