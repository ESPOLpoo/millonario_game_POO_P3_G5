package org.game.model.data;

import java.util.ArrayList;

public class TerminoAcademico {

    //ATRIBUTOS
    private int year;
    private int numeroTermino;
    public static ArrayList<TerminoAcademico> terminosAcademicos;
    public static TerminoAcademico terminoSeleccionado;

    //CONSTRUCTOR
    public TerminoAcademico(int year, int numeroTermino){
        this.year = year;
        this.numeroTermino = numeroTermino;
    }

    //GETTERS
    public int getYear() {
        return year;
    }
    public int getNumeroTermino() {
        return numeroTermino;
    }

    //SETTERS
    public void setYear(int year) { this.year = year; }
    public void setNumeroTermino(int numeroTermino) { this.numeroTermino = numeroTermino; }

    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() == this.getClass()) {
            TerminoAcademico termino = (TerminoAcademico) obj;
            return this.getYear() == termino.getYear() && this.getNumeroTermino() == termino.getNumeroTermino();
        }
        return false;
    }

    public static void ingresarTermino(int year, int numeroTermino) {
        TerminoAcademico termino = new TerminoAcademico(year, numeroTermino);
        terminosAcademicos.add(termino);
    }

    public void editarTermino(Integer year, Integer numeroTermino) {
        if (year != null) {
            this.setYear(year);
        }
        if (numeroTermino != null) {
            this.setNumeroTermino(numeroTermino);
        }
    }
    
    public static void configurarTermino(TerminoAcademico termino) {
        terminoSeleccionado = termino;
    }
    
    public String toString(){
        return this.year + "-" + this.numeroTermino;
    }
}
