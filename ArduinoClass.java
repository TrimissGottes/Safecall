package com.example.safecallv2;

public class ArduinoClass {
    private String pulso;

    public String getIdArduino() {
        return idArduino;
    }

    public void setIdArduino(String idArduino) {
        this.idArduino = idArduino;
    }

    private String idArduino;
    private String temperatura;
    private String soxigenosan;


    public String getPulso() {
        return pulso;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getSoxigenosan() {
        return soxigenosan;
    }


    public ArduinoClass(String idArduino, String pulso, String temperatura, String soxigenosan){
        this.idArduino = idArduino;
        this.pulso = pulso;
        this.temperatura = temperatura;
        this.soxigenosan = soxigenosan;
    }
    public ArduinoClass(){

    }
}
