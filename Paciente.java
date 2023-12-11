package com.example.safecallv4;

public class Paciente {
    private String idPaciente;
    private String rut;
    private String nombreP;
    private String apellidoP;
    private String enfermedad;
    private String edad;
    private String medicamento;
    private  String ritmoCardiaco;

    private String temperatura;
    private  String saturacionOxigeno;

    private String genero;

    /*public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    public Paciente(String rut, String nombreP, String apellidoP, String enfermedad, String edad, String genero, String medicamento, String ritmoCardiaco, String temperatura, String saturacionOxigeno){
        this.rut = rut;
        this.nombreP = nombreP;
        this.apellidoP = apellidoP;
        this.enfermedad = enfermedad;
        this.edad = edad;
        this.medicamento = medicamento;
        this.ritmoCardiaco = ritmoCardiaco;
        this.temperatura = temperatura;
        this.saturacionOxigeno = saturacionOxigeno;
        this.genero = genero;
    }
    public Paciente(){
    }*/

    public Paciente(String idPaciente, String rut, String nombreP, String apellidoP, String enfermedad, String edad, String genero, String medicamento, String ritmoCardiaco, String temperatura, String saturacionOxigeno){
        this.idPaciente = idPaciente;
        this.rut = rut;
        this.nombreP = nombreP;
        this.apellidoP = apellidoP;
        this.enfermedad = enfermedad;
        this.edad = edad;
        this.genero = genero;
        this.medicamento = medicamento;
        this.ritmoCardiaco = ritmoCardiaco;
        this.temperatura = temperatura;
        this.saturacionOxigeno = saturacionOxigeno;

    }
    public Paciente(){

    }


    public String getIdPaciente() {
        return idPaciente;
    }
    public String getEnfermedad() {
        return enfermedad;
    }

    public String getGenero(){
        return  genero;
    }

    public String getEdad(){
        return edad;
    }

    public String getMedicamento(){
        return medicamento;
    }

    public String getRitmoCardiaco(){
        return ritmoCardiaco;
    }

    public String getTemperatura(){
        return temperatura;
    }
    public String getSaturacionOxigeno(){
        return saturacionOxigeno;
    }

    public String getRut(){return rut;}
    public String getNombreP(){return nombreP;}
    public String getApellidoP(){return apellidoP;}
}
