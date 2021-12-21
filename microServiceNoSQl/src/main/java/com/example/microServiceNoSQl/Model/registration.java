package com.example.microServiceNoSQl.Model;

import com.example.microServiceNoSQl.Model.Interface.sourceDataInterface;
import com.example.microServiceNoSQl.Model.Utilities.dataInfoPair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


@NoArgsConstructor
@Data
public class registration {

    @Id
    private Long id;

    private LocalDate creationDate;

    private ArrayList<sourceDataInterface> typeNameRegistration;

    public registration(ArrayList<sourceDataInterface> val){
        this.creationDate = LocalDate.now();
        this.typeNameRegistration = val;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ArrayList<sourceDataInterface> getTypeNameRegistration() {
        return typeNameRegistration;
    }

    public void setTypeNameRegistration(ArrayList<sourceDataInterface> typeNameRegistration) {
        this.typeNameRegistration = typeNameRegistration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "registration{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", typeNameRegistration=" + typeNameRegistration +
                '}';
    }
}
