package org.game.model.data;

import java.util.ArrayList;

public class Paralelo {
    private TerminoAcademico termino;
    private Materia materia;
    private int numero;
    private ArrayList<Estudiante> estudiantes;
    private String rutaArchivoEstudiantes;

    public Paralelo(TerminoAcademico termino, Materia materia, int numero){
        this.termino = termino;
        this.materia = materia;
        this.numero = numero;
    }

    public Paralelo(TerminoAcademico termino, Materia materia, int numero, ArrayList<Estudiante> estudiantes){
        this(termino, materia, numero);
        this.estudiantes = estudiantes;
    }

    public TerminoAcademico getTermino() {
        return termino;
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getRutaArchivoEstudiantes() {
        return rutaArchivoEstudiantes;
    }

    public void setRutaArchivoEstudiantes(String rutaArchivoEstudiantes) {
        this.rutaArchivoEstudiantes = rutaArchivoEstudiantes;
    }

    public String toString(){
        return String.format("Termino: %s - Numero: %s - Estudiantes: %d", termino, numero, estudiantes.size());
    }

    public boolean equals(Object obj){
        if (obj == null){return false;}
        
        Paralelo paralelo= (Paralelo) obj;
        
        if (getClass() == paralelo.getClass() && termino.equals(paralelo.getTermino()) && numero.equals(paralelo.getNumero())){
            return true;
        }
        else{return false;}
    }
}
