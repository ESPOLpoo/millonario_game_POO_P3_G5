package org.game.model.data;

public class TerminoAcademico {

    //ATRIBUTOS
    private int year;
    private int numeroTermino;

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

    public boolean equals(Object obj){
        if (obj == null) return false;
        
        TerminoAcademico termino = (TerminoAcademico) obj;
        if (getClass() == termino.getClass() && year.equals(termino.getYear()) && numeroTermino.equals(termino.getNumeroTermino())){
            return true;
        }
        else{return false;}
    }

    public String toString(){
        return this.year + "-" + this.numeroTermino;
    }
}
