package org.game.model.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import org.game.App;

public class Estudiante implements Serializable, Comparable<Estudiante>, Extraible{

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
    
    public Estudiante(String estudiante){
    this(estudiante.split("-")[0], estudiante.split("-")[1], estudiante.split("-")[2]);
    }
    

    public String getNombre() { return this.nombre; }
    public String getMatricula() { return matricula; }
    public String getCorreo() { return correo; }
    public ArrayList<String> getInfo(){
        ArrayList<String> info = new ArrayList<String>();
        info.add(toString());
        info.add(nombre);
        info.add(matricula);
        info.add(correo);
        return info;
    }
    public Estudiante getObj(ArrayList<String> info){
        Estudiante j = null;
        j= new Estudiante(info.get(0),info.get(1),info.get(2));
        return j;
    }
    public void validar()throws ValidacionException{
        if (App.JUEGO.getParalelo().getEstudiantes().contains(this)){throw new ValidacionException("Hey! ya existe un estudiante con este número de matrícula, debes cambiarlo.");}
    }
     public void  edit(Extraible e)throws ValidacionException{
       Estudiante estudiante = (Estudiante) e;
       if (equals(estudiante)){
           setNombre(estudiante.getNombre());
           setCorreo(estudiante.getCorreo());
       }
       else{
       estudiante.validar();
       setNombre(estudiante.getNombre());
       setMatricula(estudiante.getMatricula());
       setCorreo(estudiante.getCorreo());
       }
       
   }

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
        return this.getNombre() + "-" + this.getMatricula();
    }
    
    public int compareTo(Estudiante e){
        int var = this.nombre.compareTo(e.getNombre());
        if (var==0){var = this.matricula.compareTo(e.getMatricula());}
        return var;
    }
}