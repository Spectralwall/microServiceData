package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;

// classe pe trattare dati di tipo Byte
public class ByteData implements sourceDataInterface {

    private Byte val;

    public ByteData(byte x){
        this.val = x;
    }

    @Override
    public Object getData() {
        return val;
    }

    @Override
    public void setData(Object val) {
        this.val = (byte)val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
