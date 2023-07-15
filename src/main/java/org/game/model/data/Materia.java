package org.game.model.data;

import java.util.ArrayList;
import org.game.model.logic.Pregunta;

public class Materia {
    private String codigo;
    private String nombre;
    private int niveles;
    private ArrayList<Paralelo> paralelos;
    private ArrayList<Pregunta> preguntas;

    public Materia(String codigo, String nombre, int niveles){
        this.codigo = codigo;
        this.nombre = nombre;
        this.niveles = niveles;
        paralelos = new ArrayList<Paralelo>();
        preguntas = new ArrayList<Pregunta>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNiveles() {
        return niveles;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public void editarMateria(String codigo, String nombre, int niveles) {

    }

    public void agregarParalelo(Paralelo paralelo){
        paralelos.add(paralelo);
    }

    public void eliminarParalelo(Paralelo paralelo) {

    }

    public void agregarPregunta(Pregunta pregunta){
        preguntas.add(pregunta);
    }

    public void eliminarPreguntas(Pregunta pregunta){
        preguntas.remove(pregunta);
    }

    public String toString(){
        return String.format("Codigo: %s - Nombre: %s - Niveles: %s", codigo, nombre, niveles);
    }

    public boolean equals(Object obj){
        if (obj == null){return false;}
        
        Materia materia= (Materia) obj;
        
        if (getClass() == materia.getClass() && (codigo.equals(materia.getCodigo()) || nombre.equals(materia.getNombre()))){
            return true;
        }
        else{return false;}
    }
}
