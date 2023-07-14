package org.game.model;

import java.util.ArrayList;

public class Paralelo {

    //ATRIBUTOS
    private model.TerminoAcademico termino;
    private int numero;
    private ArrayList<Participante> estudiantes;

    //CONSTRUCTOR
    public Paralelo(model.TerminoAcademico termino, int numero, ArrayList<Participante> estudiantes){
        this.termino = termino;
        this.numero = numero;
        this.estudiantes = estudiantes;
    }

    //GETTERS
    public model.TerminoAcademico getTermino() { return termino; }
    public int getNumero() { return numero; }
    public ArrayList<Participante> getEstudiantes() { return estudiantes; }

    //SETTERS
    public void setTermino(model.TerminoAcademico termino) { this.termino = termino; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setEstudiantes(ArrayList<Participante> estudiantes) { this.estudiantes = estudiantes; }

}
