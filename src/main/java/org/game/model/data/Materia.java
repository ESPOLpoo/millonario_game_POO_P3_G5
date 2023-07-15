package org.game.model.data;

import java.util.ArrayList;
import org.game.model.logic.Pregunta;

public class Materia {
    private String codigo;
    private String nombre;
    private int niveles;
    private ArrayList<Paralelo> paralelos;
    private ArrayList<Pregunta> preguntas;
    public static ArrayList<Materia> materias;

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

    public static void ingresarMateria(String codigo, String nombre, int niveles) {
        materias.add(new Materia(codigo, nombre, niveles));
    }

    public void editarMateria(String codigo, String nombre, Integer niveles) {
        if (codigo != null) {
            this.codigo = codigo;
        }
        if (nombre != null) {
            this.nombre = nombre;
        }
        if (niveles != null) {
            this.niveles = niveles;
        }
    }

    public void agregarParalelo(Paralelo paralelo){
        paralelos.add(paralelo);
    }

    public void eliminarParalelo(Paralelo paralelo) {
        paralelos.remove(paralelo);
    }

    public void agregarPregunta(Pregunta pregunta){
        preguntas.add(pregunta);
    }

    public void eliminarPregunta(Pregunta pregunta){
        preguntas.remove(pregunta);
    }

    public String toString(){
        return String.format("Codigo: %s - Nombre: %s - Niveles: %s", codigo, nombre, niveles);
    }

    public boolean equals(Object obj){
        if (obj == null) return false;

        if (obj.getClass() == this.getClass()) {
            Materia materia = (Materia) obj;
            return this.getCodigo().equals(materia.getCodigo());
        }
        return false;
    }
}