package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import lombok.NoArgsConstructor;

// classe pe trattare dati di tipo char
@NoArgsConstructor
public class CharData implements sourceDataInterface {

    private Character val;

    public CharData(char x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (char)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
