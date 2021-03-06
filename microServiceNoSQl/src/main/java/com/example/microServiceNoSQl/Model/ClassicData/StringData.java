package com.example.microServiceNoSQl.Model.ClassicData;

import com.example.microServiceNoSQl.Model.Interface.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringData implements SourceDataInterface {

    // in questo caso non abbiamo un wrapper ma direttamente la classe String
    private String val;

    @JsonCreator
    public StringData(@JsonProperty("val")String x) {
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

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            StringData castedObj = (StringData) obj;

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
