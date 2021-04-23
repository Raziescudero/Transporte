package com.example.SWAutobus;
//DTO
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Viajes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String viaje;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getViaje() {
        return viaje;
    }
    public void setViaje(String viaje) {
        this.viaje = viaje;
    }
    
}
