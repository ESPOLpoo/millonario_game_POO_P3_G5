package org.game.model.data;

import org.game.App;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import org.game.lib.Util;

public class TerminoAcademico implements Serializable, Comparable<TerminoAcademico>, Extraible{

    //ATRIBUTOS
    private int year;
    private int numeroTermino;
    private static ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();
    public static TerminoAcademico terminoSeleccionado;

    //CONSTRUCTOR
    public TerminoAcademico(int year, int numeroTermino){
        this.year = year;
        this.numeroTermino = numeroTermino;
    }
    
    public TerminoAcademico(String termino) {
        this(Integer.parseInt(termino.split("-")[0]), 
                Integer.parseInt(termino.split("-")[1]));

    }

    //GETTERS
    public int getYear() {
        return year;
    }
    public int getNumeroTermino() {
        return numeroTermino;
    }
    public static ArrayList<TerminoAcademico> getTerminosAcademicos(){
        return terminosAcademicos;
    }
    
    public ArrayList<String>getInfo(){
        ArrayList<String> info = new ArrayList<String>();
        info.add(toString());
        info.add(""+year);
        info.add(""+numeroTermino);
        return info;
    }
    
    public TerminoAcademico getObj(ArrayList<String> info){
        return new TerminoAcademico(info.get(0)+"-"+info.get(1));
    }

    //SETTERS
    public void setYear(int year) { this.year = year; }
    public void setNumeroTermino(int numeroTermino) { this.numeroTermino = numeroTermino; }
    public static void setTerminosAcademicos(ArrayList<TerminoAcademico> t){terminosAcademicos=t;}


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
        if (obj.toString().equals(this.toString())){return true;}
        return false;
    }

    public static void ingresarTermino(int year, int numeroTermino){
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
    
    public void edit(Extraible e)throws ValidacionException{
       TerminoAcademico termino = (TerminoAcademico) e;
       e.validar();
       setYear(termino.getYear());
       setNumeroTermino(termino.getNumeroTermino());
    }
    
    public static void configurarTermino(TerminoAcademico termino) {
        terminoSeleccionado = termino;
    }
    
    public String toString(){
        return this.year + "-" + this.numeroTermino;
    }
    
    public static void loadBase(String ruta) throws IOException{
        terminosAcademicos.clear();
        BufferedReader reader = new BufferedReader(new FileReader(ruta+"terminosAcademicos.txt"));
        String line;
        
        while ((line = reader.readLine())!=null && !line.isEmpty()){
            terminosAcademicos.add(new TerminoAcademico(line));
        }
        Collections.sort(terminosAcademicos);
        Util.updateSer(terminosAcademicos,ruta+"terminos.ser");
    }
    
    public int compareTo(TerminoAcademico t){
        int var;
        if (year>t.getYear()){var=1;}
        else if (year<t.getYear()){var=-1;}
        else{var=0;};
        if (var==0){
            if (numeroTermino > t.getNumeroTermino()){var=1;}
            else{var=-1;}
        }
        return var;
    }
    
    public void validar()throws ValidacionException{
        if (year>2023){
            throw new ValidacionException("Hey viajero del tiemo!. Aún falta un buen rato para el año "+year);}
        else if (terminosAcademicos.contains(this)){
        throw new ValidacionException("Hey! este termino ya existe, debes cambiarlo.");
        }
    }
}
