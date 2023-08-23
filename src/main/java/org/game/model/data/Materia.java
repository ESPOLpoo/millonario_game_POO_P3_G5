package org.game.model.data;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import org.game.App;
import org.game.lib.Util;
import org.game.model.data.Paralelo;

import org.game.model.logic.Pregunta;

public class Materia implements Serializable, Comparable<Materia>, Extraible{
    private String codigo;
    private String nombre;
    private int niveles;
    private  ArrayList<Paralelo> paralelos;
    private ArrayList<Pregunta> preguntas;
    public static ArrayList<Materia> materias = new ArrayList<>();

    public Materia(String codigo, String nombre, int niveles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.niveles = niveles;
        paralelos = new ArrayList<Paralelo>();
        preguntas = new ArrayList<Pregunta>();
    }
    
    public Materia(String materia){
    this(materia.split("-")[0],materia.split("-")[1],Integer.parseInt(materia.split("-")[2]));
    }

    public Materia() {
        this.nombre = "";
        this.codigo = "";
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

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public ArrayList<Paralelo> getParalelos() {
        return paralelos;
    }
    
    public void setParalelos(ArrayList<Paralelo> p){
        paralelos = p;
    }
    
    public void setPreguntas(ArrayList<Pregunta> p){
        preguntas = p;
    }

    public Pregunta getPregunta(String enunciado) {
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getEnunciado().equals(enunciado)) {
                return pregunta;
            }
        }
        return null;
    }
    
    public static void setMaterias(ArrayList<Materia> m){
        materias = m;
    }
    
    public ArrayList<String> getInfo(){
        ArrayList<String> info = new ArrayList<String>();
        info.add(codigo+"-"+nombre+"-"+niveles);
        info.add(nombre);
        info.add(""+niveles);
        return info;   
    }
    
    public Materia getObj(ArrayList<String> info){
        return new Materia(codigo,info.get(0),Integer.parseInt(info.get(1)));
    }
    
    public void validar()throws ValidacionException{
        if (materias.contains(this)){throw new ValidacionException("Hey! ya existe una materia con ese nombre, debes cambiarla.");}
    }
    
    public void edit(Extraible e)throws ValidacionException{
        Materia m = (Materia)e;
        m.setCodigo(m.getCodigo().toUpperCase());
        m.setNombre(m.getNombre().toUpperCase());
        if(equals(m)){
            setNiveles(m.getNiveles());
        }
        else{
            m.validar();
            setNombre(m.getNombre());
            setNiveles(m.getNiveles());
        }
        
    }

    public static ArrayList<ArrayList<Pregunta>> agruparPorNivel(ArrayList<Pregunta> preguntas) {
        ArrayList<ArrayList<Pregunta>> preguntasPorNivel = new ArrayList<>();
        for (Pregunta pregunta : preguntas) {
            int nivel = pregunta.getNivel();
            if (preguntasPorNivel.size() < nivel) {
                preguntasPorNivel.add(new ArrayList<Pregunta>());
            }
            preguntasPorNivel.get(nivel - 1).add(pregunta);
        }
        return preguntasPorNivel;
    }

    public static Pregunta getRandomPreguntaNivel(ArrayList<Pregunta> preguntas) {
        int random = (int) (Math.random() * preguntas.size());
        return preguntas.get(random);
    }

    public static void ingresarMateria(String codigo, String nombre, int niveles) {
        materias.add(new Materia(codigo, nombre, niveles));
    }

    public static void ingresarMateria(Materia materia) {
        materias.add(materia);
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

    public static Materia getMateria(String codigo) {
        for (Materia materia : materias) {
            if (materia.getCodigo().equals(codigo)) {
                return materia;
            }
        }
        return null;
    }

    public static Materia getMateriaByNombre(String nombre) {
        for (Materia materia : materias) {
            if (materia.getNombre().equals(nombre)) {
                return materia;
            }
        }
        return null;
    }

    public Paralelo getParalelo(TerminoAcademico termino, Materia materia, int numero) {
        for (Paralelo paralelo: paralelos) {
            if (paralelo.equals(new Paralelo(termino, this, numero))) {
                return paralelo;
            }
        }
        return null;
    }
    public void agregarParalelo(Paralelo paralelo) {
        paralelos.add(paralelo);
    }

    public void eliminarParalelo(Paralelo paralelo) {
        paralelos.remove(paralelo);
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
        guardarPreguntaArchivo(pregunta);
    }

    public void guardarPreguntaArchivo(Pregunta pregunta) {
        // CODIGO-MATERIA.csv
        try {
            FileOutputStream fos = new FileOutputStream(Paths.get(".").toAbsolutePath().normalize() + "/src/main/resources/" + this.codigo + ".dat", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pregunta);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void loadBase(String ruta) throws IOException{
        materias.clear();
        BufferedReader reader = new BufferedReader(new FileReader(ruta+"materias.txt"));
        String line;
        Paralelo p = new Paralelo(App.PATH+"estudiantes.txt",0);
        while ((line = reader.readLine())!=null && !line.isEmpty()){
            String[] info = line.split(",");
            Materia materia = new Materia(info[0], info[1], Integer.parseInt(info[2]));
            materia.getParalelos().add(p);
            materia.setPreguntas(Pregunta.preguntasDefecto());
            materias.add(materia);
        }  
        Collections.sort(materias);
        Util.updateSer(materias,ruta+"materias.ser");
    }
    
    

    public void eliminarPregunta(Pregunta pregunta) {
        preguntas.remove(pregunta);
    }

    public String toString() {
        return String.format("Codigo: %s - Nombre: %s - Niveles: %s", codigo, nombre, niveles);
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj.getClass() == this.getClass()) {
            Materia materia = (Materia) obj;
            return this.getNombre().equals(materia.getNombre());
        }
        return false;
    }
    
    public int compareTo(Materia m){
        return nombre.compareTo(m.getNombre());
    }
}
