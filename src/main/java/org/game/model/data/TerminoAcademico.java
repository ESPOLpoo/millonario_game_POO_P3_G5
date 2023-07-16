package org.game.model.data;

import java.util.ArrayList;

public class TerminoAcademico {

    //ATRIBUTOS
    private int year;
    private int numeroTermino;
    public static ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();
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


    public static TerminoAcademico getTermino(String termino) {
        for (TerminoAcademico terminoAcademico : terminosAcademicos) {
            if (terminoAcademico.toString().equals(termino)) {
                return terminoAcademico;
            }
        }
        return null;
    }
    public static TerminoAcademico getTermino(int year, int numeroTermino) {
        for (TerminoAcademico terminoAcademico : terminosAcademicos) {
            if (terminoAcademico.getYear() == year && terminoAcademico.getNumeroTermino() == numeroTermino) {
                return terminoAcademico;
            }
        }
        return null;
    }

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
        if (terminosAcademicos.contains(termino)) {
            return;
        }
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
