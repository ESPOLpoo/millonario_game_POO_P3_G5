package org.game.model;

import java.util.ArrayList;

public class Pregunta {

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

    //GETTERS
    public String getEnunciado() { return enunciado; }
    public int getNivel() { return nivel; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public ArrayList<String> getRespuestasIncorrectas() { return respuestasIncorrectas; }


    //SETTERS
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setRespuestaCorrecta(String respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }
    public void setRespuestasIncorrectas(ArrayList<String> respuestasIncorrectas) { this.respuestasIncorrectas = respuestasIncorrectas; }

}
