package org.game.model.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import org.game.App;

public class Paralelo implements Serializable, Extraible, Comparable<Paralelo>{
    private TerminoAcademico termino;
    private Materia materia;
    private int numero;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private String rutaArchivoEstudiantes;
    public static ArrayList<Paralelo> paralelos = new ArrayList<>();

    public Paralelo(String rutaEstudiantes, int numero)throws IOException{
        rutaArchivoEstudiantes = rutaEstudiantes;
        this.numero = numero;
        loadEstudiantes();
    }
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
        this.agregarEstudiantes();
    }
    
    public void loadEstudiantes()throws IOException{
        estudiantes.clear();
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivoEstudiantes));
        String line;
        while ((line = reader.readLine())!=null && !line.isEmpty()){
            String[] info = line.split(",");
            estudiantes.add(new Estudiante(info[0], info[1], info[2]));
        }
        Collections.sort(estudiantes);
    }
    public void agregarEstudiantes() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        String ruta = Paths.get(".").toAbsolutePath().normalize() + "/src/main/resources/" + this.rutaArchivoEstudiantes;
        try {
            FileReader reader = new FileReader(ruta);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Estudiante estudiante = new Estudiante(data[0], data[1]);
                estudiantes.add(estudiante);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.setEstudiantes(estudiantes);
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
    
    public ArrayList<String> getInfo(){
        ArrayList<String> info = new ArrayList<String>();
        info.add("Paralelo "+numero);
        info.add(""+estudiantes.size());
        info.add(""+numero);
        return info;   
    }
    
    public Paralelo getObj(ArrayList<String> info){
        Paralelo p = null;
        try{p= new Paralelo(rutaArchivoEstudiantes, Integer.parseInt(info.get(1)));}
        catch(IOException e){e.printStackTrace();}
        return p;
    }
    
    public void validar()throws ValidacionException{
        if (App.JUEGO.getMateria().getParalelos().contains(this)){throw new ValidacionException("Hey! ya existe un paralelo con este numero, debes cambiarlo.");}
    }
    
   public void  edit(Extraible e)throws ValidacionException{
       Paralelo paralelo = (Paralelo) e;
       paralelo.validar();
       setRutaArchivoEstudiantes(paralelo.getRutaArchivoEstudiantes());
       setNumero(paralelo.getNumero());
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
        return String.format("Termino: %s - Materia: %s - NumeroParalelo: %s - Estudiantes: %s", this.termino, this.materia, this.numero, this.estudiantes);
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (obj.getClass() == this.getClass()) {
            Paralelo paralelo= (Paralelo) obj;
            return  this.getNumero() == paralelo.getNumero();
        }
        return false;
    }
    
    public int compareTo(Paralelo p){
        if (numero >p.getNumero()){return 1;}
        else if (numero <p.getNumero()){return -1;}
        else{return 0;}
    }
}
