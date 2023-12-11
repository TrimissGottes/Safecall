package com.example.safecallv4;

public class Persona {
    private String rut;
    private String nombre;
    private String apellido;

    private String id;


    public Persona(){

    }

    public Persona(String rut, String nombre, String apellido, String id){
        this.nombre = nombre;
        this.rut = rut;
        this.apellido = apellido;
        this.id = id;
    }

    public String getRut(){
        return rut;
    }

    public void setRut(String rut){
        this.rut = rut;
    }

    public  String getNombre(){
        return nombre;
    }

    public  void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getId(){
        return id;
    }

    public  void setId(String id){
        this.id = id;
    }
}


