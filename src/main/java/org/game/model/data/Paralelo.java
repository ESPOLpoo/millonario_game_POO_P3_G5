package org.game.model.data;

import java.util.ArrayList;

public class Paralelo {
    private TerminoAcademico termino;
    private Materia materia;
    private int numero;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private String rutaArchivoEstudiantes;
    public static ArrayList<Paralelo> paralelos = new ArrayList<>();

    public Paralelo(TerminoAcademico termino, Materia materia, int numero){
        this.termino = termino;
        this.materia = materia;
        this.numero = numero;
    }

    public Paralelo(TerminoAcademico termino, Materia materia, int numero, ArrayList<Estudiante> estudiantes){
        this(termino, materia, numero);
        this.estudiantes = estudiantes;
    }

    public Paralelo(TerminoAcademico termino, Materia materia, int numero, String rutaArchivoEstudiantes) {
        this.termino = termino;
        this.materia = materia;
        this.numero = numero;
        this.rutaArchivoEstudiantes = rutaArchivoEstudiantes;
    }

    public static Paralelo getParalelo(TerminoAcademico terminoSeleccionado, Materia materia, int numero) {
        for (Paralelo paralelo : paralelos) {
            if (paralelo.equals(new Paralelo(terminoSeleccionado, materia, numero))) {
                return paralelo;
            }
        }
        return null;
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

    public Estudiante getEstudiante(String matricula) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getMatricula().equals(matricula)) {
                return estudiante;
            }
        }
        return null;
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

    public static void ingresarParalelo(TerminoAcademico termino, Materia materia, int numero){
        paralelos.add(new Paralelo(termino, materia, numero));
    }

    public static void ingresarParalelo(TerminoAcademico termino, Materia materia, int numero, String rutaArchivoEstudiantes){
        paralelos.add(new Paralelo(termino, materia, numero, rutaArchivoEstudiantes));
    }

    public static void eliminarParalelo(Paralelo paralelo){
        paralelos.remove(paralelo);
    }

    public String toString(){
        return String.format("Termino: %s - Materia: %s - NumeroParalelo: %s ", this.termino, this.materia, this.numero);
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() == this.getClass()) {
            Paralelo paralelo= (Paralelo) obj;
            return this.getTermino().equals(paralelo.getTermino()) && this.getMateria().equals(paralelo.getMateria()) && this.getNumero() == paralelo.getNumero();
        }
        return false;
    }
}
