package org.game.model.data;

public class Estudiante {

    private String nombre;
    private String matricula;
    private String correo;

    public Estudiante(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public Estudiante(String nombre, String matricula, String correo) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.correo = correo;
    }

    public String getNombre() { return this.nombre; }
    public String getMatricula() { return matricula; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setCorreo(String correo) { this.correo = correo; }

}