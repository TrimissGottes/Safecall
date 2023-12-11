package com.example.safecallv4;

public class Usuario {

    private String idUsuario;

        private String rut;
        private String nombreU;
        private String apellidoU;
        private String correo;
        private String contrasena;

        public Usuario(String idUsuario, String rut, String nombreU, String apellidoU, String correo, String contrasena){
            this.idUsuario = idUsuario;
            this.rut = rut;
            this.nombreU = nombreU;
            this.apellidoU = apellidoU;
            this.correo = correo;
            this.contrasena = contrasena;
        }
        public Usuario(){
        }

    public String getIdUsuario(){return idUsuario;}
    public String getRut(){return rut;}
    public String getNombreU(){return nombreU;}
    public String getApellidoU(){return apellidoU;}
    public String getCorreo(){return correo;}
    public String getContrasena(){return contrasena;}


}
