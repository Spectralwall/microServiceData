package com.example.microServiceNoSQl.Model.Utilities;

public class TypeNameReg<T> {
    private String name;
    private T val;
    public TypeNameReg(String name, T val){
        this.name = name;
        this.val=val;
    }

    public T getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}