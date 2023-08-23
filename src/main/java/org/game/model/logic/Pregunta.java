package org.game.model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import org.game.App;
import org.game.model.data.Extraible;
import org.game.model.data.ValidacionException;

public class Pregunta implements Serializable, Comparable<Pregunta>, Extraible {

    //ATRIBUTOS
    private String enunciado;
    private int nivel;
    private String respuestaCorrecta;
    private ArrayList<String> respuestasIncorrectas;
    private Comodin comodin;

    //CONSTRUCTOR
    public Pregunta(String enunciado, int nivel, String respuestaCorrecta, ArrayList<String> respuestasIncorrectas){
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestasIncorrectas = respuestasIncorrectas;
    }
    public Pregunta(String enunciado, String nivel, String correcto, String incorrecto1, String incorrecto2, String incorrecto3){
        this.enunciado = enunciado;
        this.nivel = Integer.parseInt(nivel);
        respuestaCorrecta = correcto;
        respuestasIncorrectas = new ArrayList<String>();
        respuestasIncorrectas.add(incorrecto1);
        respuestasIncorrectas.add(incorrecto2);
        respuestasIncorrectas.add(incorrecto3);
    }
    
    public Pregunta(String enunciado){
    this.enunciado = enunciado;
    }
    public String toString() {
        return "Pregunta [enunciado=" + enunciado + ", nivel=" + nivel + ", respuestaCorrecta=" + respuestaCorrecta
                + ", respuestasIncorrectas=" + respuestasIncorrectas + "]";
    }

    public String mostrarPregunta(ArrayList<String> respuestasAleatorias) {
        String pregunta = "";
        pregunta += "Enunciado: " + enunciado + "\n";
        pregunta += "Nivel: " + nivel + "\n";
        for (String respuesta : respuestasAleatorias) {
            pregunta += respuesta + "\n";
        }
        pregunta += "Si desea usar el comodin, ingrese el caracter '*'\n";
        return pregunta;
    }

    public ArrayList<String> agruparAleatoriamente(ArrayList<String> respuestasIncorrectas) {
        String[] opciones = { "A", "B", "C", "D" };
        ArrayList<String> respuestas = new ArrayList<>(respuestasIncorrectas);
        respuestas.add(respuestaCorrecta);
        Collections.shuffle(respuestas);
        ArrayList<String> respuestasAleatorias = new ArrayList<>();
        int i = 0;
        for (String respuesta : respuestas) {
            respuestasAleatorias.add(opciones[i] + ". " + respuesta);
            i++;
        }
        return respuestasAleatorias;
    }

    //GETTERS
    public String getEnunciado() { return enunciado; }
    public int getNivel() { return nivel; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public ArrayList<String> getRespuestasIncorrectas() { return respuestasIncorrectas; }

    public boolean esCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }
    public Comodin getComodin() {
        return this.comodin;
    }

    //SETTERS
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setRespuestaCorrecta(String respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }
    public void setRespuestasIncorrectas(ArrayList<String> respuestasIncorrectas) { this.respuestasIncorrectas = respuestasIncorrectas; }
    public void setComodin(Comodin comodin) {
        this.comodin = comodin;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            Pregunta pregunta = (Pregunta) obj;
            return pregunta.getEnunciado().equals(this.enunciado);
        }
        return false;
    }
    
    public static ArrayList<Pregunta> preguntasDefecto()throws IOException{
        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        BufferedReader reader = new BufferedReader(new FileReader(App.PATH+"preguntas.txt"));
        String line;
        while ((line = reader.readLine())!=null && !line.isEmpty()){
            String[] info = line.split(",");
            Pregunta pregunta = new Pregunta(info[0], info[1], info[2], info[3], info[4], info[5]);
            preguntas.add(pregunta);
        }
        Collections.sort(preguntas);
        return preguntas;
    }
    
    public int compareTo(Pregunta p){
        int valor = 0;
        if (nivel > p.getNivel()){valor=1;}
        else if (nivel < p.getNivel()){valor=-1;}
        return valor;
    }
    public ArrayList<String> getInfo(){
        ArrayList<String> info = new ArrayList<String>();
        info.add(enunciado);
        info.add(enunciado);
        info.add(""+nivel);
        info.add(respuestaCorrecta);
        info.add(respuestasIncorrectas.get(0));
        info.add(respuestasIncorrectas.get(1));
        info.add(respuestasIncorrectas.get(2));
        return info;
    }
    
    public Pregunta getObj(ArrayList<String> info){
        return new Pregunta(info.get(0),info.get(1),info.get(2),info.get(3),info.get(4),info.get(5));
    }
    
    public void validar()throws ValidacionException{
        if (nivel<=0){throw new ValidacionException("No existe el nivel "+nivel);}
        if (App.JUEGO.getMateria().getPreguntas().contains(this)){throw new ValidacionException("Hey! ya existe una pregunta como esta, debes cambiarla.");}
    }
    
    public void edit(Extraible e)throws ValidacionException{
        Pregunta p = (Pregunta)e;
        if(equals(p)){
            setNivel(p.getNivel());
            setRespuestaCorrecta(p.getRespuestaCorrecta());
            setRespuestasIncorrectas(p.getRespuestasIncorrectas());
        }
        else{
            p.validar();
            setEnunciado(p.getEnunciado());
            setNivel(p.getNivel());
            setRespuestaCorrecta(p.getRespuestaCorrecta());
            setRespuestasIncorrectas(p.getRespuestasIncorrectas());
        }
    }
}
