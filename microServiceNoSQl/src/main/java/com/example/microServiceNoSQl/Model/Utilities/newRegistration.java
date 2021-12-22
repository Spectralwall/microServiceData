package com.example.microServiceNoSQl.Model.Utilities;

import com.example.microServiceNoSQl.Model.ClassicData.DoubleData;
import com.example.microServiceNoSQl.Model.ClassicData.IntegerData;
import com.example.microServiceNoSQl.Model.ClassicData.StringData;
import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import com.example.microServiceNoSQl.Model.topic;

import java.util.ArrayList;

public class newRegistration {

    private String userId;

    private String topic;

    ArrayList<String> dataList;

    public String getTopic() {
        return topic;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "newRegistration{" +
                "userId='" + userId + '\'' +
                ", topic='" + topic + '\'' +
                ", dataList=" + dataList +
                '}';
    }

    public ArrayList<sourceDataInterface> trasformData(topic a) {
        ArrayList<dataInfoPair> tmp = a.getNameType();
        ArrayList<sourceDataInterface> result = new ArrayList<>();
        int x=0;

        while(x<tmp.size()){
            switch (tmp.get(x).getData()){
                 case "Text":
                    result.add(new StringData(this.getDataList().get(x)));
                    break;

                case "Integer Number":
                    result.add(new IntegerData(Integer.parseInt(this.getDataList().get(x))));
                    break;

                case "Floating Point Number":
                    result.add(new DoubleData(Double.parseDouble(this.getDataList().get(x))));
                    break;

                case "Date":
                    result.add(new StringData(this.getDataList().get(x)));
                    break;

                case "Hour":
                    result.add(new StringData(this.getDataList().get(x)));
                    break;
                default:
                    break;
            }
            ++x;
        }
        return result;
    }
}
