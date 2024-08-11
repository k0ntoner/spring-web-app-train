package com.example.demo.models;

public class Employee {
    private long id;
    private String name;
    public Employee(){

    }
    public Employee(long id, String name){
        this.id=id;
        this.name=name;
    }
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
