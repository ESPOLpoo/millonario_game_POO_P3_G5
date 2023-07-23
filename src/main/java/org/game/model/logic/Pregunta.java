package org.game.model.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Pregunta implements Serializable {

    //ATRIBUTOS
    private String enunciado;
    private int nivel;
    private String respuestaCorrecta;
    private ArrayList<String> respuestasIncorrectas;

    //CONSTRUCTOR
    public Pregunta(String enunciado, int nivel, String respuestaCorrecta, ArrayList<String> respuestasIncorrectas){
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestasIncorrectas = respuestasIncorrectas;
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

    //SETTERS
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setRespuestaCorrecta(String respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }
    public void setRespuestasIncorrectas(ArrayList<String> respuestasIncorrectas) { this.respuestasIncorrectas = respuestasIncorrectas; }


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
}
