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

    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() == this.getClass()) {
            Estudiante estudiante = (Estudiante) obj;
            return this.getMatricula().equals(estudiante.getMatricula());
        }
        return false;
    }

    public String toString() {
        return this.getNombre() + " " + this.getMatricula();
    }
}