package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String secondName;
    private Integer passport;
    @ManyToOne
    @JoinColumn(name="positionId")
    private Position position;

    public Employee(){

    }
    public Employee(Long id, String name, String secondName, Integer passport, Position position) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.passport = passport;
        this.position = position;
    }
    
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    public Integer getPassport() {
        return passport;
    }
    public void setPassport(Integer passport) {
        this.passport = passport;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}
