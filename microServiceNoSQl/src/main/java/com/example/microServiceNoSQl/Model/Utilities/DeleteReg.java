package com.example.microServiceNoSQl.Model.Utilities;

public class DeleteReg {

    private String idUser;

    private Long idReg;

    private String topic;


    public String getIdUser() {
        return idUser;
    }

    public Long getIdReg() {
        return idReg;
    }

    public String getTopic() {
        return topic;
    }

    public void setIdReg(Long idReg) {
        this.idReg = idReg;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
