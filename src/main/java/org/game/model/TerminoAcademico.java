package org.game.model;

import java.util.ArrayList;

import org.game.model.Materia;

public class TerminoAcademico {

    //ATRIBUTOS
    private int año;
    private int numTermino;
    private ArrayList<Materia> materias;
    private ArrayList<Participante> estudiantes;

    //CONSTRUCTOR
    public TerminoAcademico(int año, int numTermino){
        this.año = año;
        this.numTermino = numTermino;
    }

    //GETTERS
    public int getAño() {
        return año;
    }
    public int getNumTermino() {
        return numTermino;
    }
    public ArrayList<Materia> getMaterias() { return materias; }
    public ArrayList<Participante> getEstudiantes() { return estudiantes; }

    //SETTERS
    public void setAño(int año) { this.año = año; }
    public void setNumTermino(int numTermino) { this.numTermino = numTermino; }

}
