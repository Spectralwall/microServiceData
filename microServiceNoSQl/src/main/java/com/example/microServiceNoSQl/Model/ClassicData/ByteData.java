package com.example.microServiceNoSQl.Model.ClassicData;


import com.example.microServiceNoSQl.Model.Interface.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ByteData implements SourceDataInterface {

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

    @JsonCreator
    public ByteData(@JsonProperty("val")Byte val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            ByteData castedObj = (ByteData) obj;

            //check val
            if((val != null) && (castedObj.getData() != null)) {
                if(!val.equals(castedObj.getData())) {
                    res = false;
                }
            } else if(!((val == null) && (castedObj.getData() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}
