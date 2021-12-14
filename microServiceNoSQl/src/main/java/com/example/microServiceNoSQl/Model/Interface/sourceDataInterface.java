package com.example.microServiceNoSQl.Model.Interface;

public interface sourceDataInterface <T> {
    abstract T getData();
    abstract void setData(T val);
}
