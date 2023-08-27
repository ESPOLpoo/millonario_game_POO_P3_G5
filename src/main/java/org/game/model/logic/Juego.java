package org.game.model.logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

import org.game.model.data.Estudiante;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;
import org.game.model.data.ValidacionException;

public class Juego {
    private TerminoAcademico termino; // 211 Menu.java
    private Materia materia; // 214 Menu.java
    private Paralelo paralelo; // 220 Menu.java
    private int numeroPreguntas; // 224 Menu.java
    private Estudiante participante; // 227 Menu.java
    private Estudiante mateApoyo; // 230 Menu.java
    private String premio;
    private LocalDate fecha;
    private int nivelMaximo;
    private int tiempo;
    private ArrayList<Pregunta> preguntas;
    private ArrayList<Pregunta> preguntasContestadas;
    private ArrayList<Comodin> comodinesUsados;
    private static ArrayList<Juego> juegos = new ArrayList<>();
    

    public Juego() {
        termino = null;
        materia = null;
        paralelo = null;
        participante = null;
        mateApoyo = null;
        preguntas = null;
        preguntasContestadas = new ArrayList<>();
        comodinesUsados = new ArrayList<>();
    }


    public static void addJuego(Juego juego) {
        juegos.add(juego);
    }

    public Estudiante getParticipante() {
        return participante;
    }

    public String getPremio() {
        return premio;
    }
    
    public void setPremio(String premio) {
        this.premio = premio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public int getTiempo() {
        return tiempo;
    }
    
    public TerminoAcademico getTermino(){
        return termino;
    }
    
    public Materia getMateria(){
        return materia;
    }
    
    public Paralelo getParalelo(){
        return paralelo;
    }

    public static ArrayList<Juego> getJuegos(){
        return juegos;
    }

    public Estudiante getCompañero(){
        return mateApoyo;
}
    public ArrayList<Pregunta> getPreguntas(){
        return preguntas;
    }
    public ArrayList<Pregunta> getPreguntasContestadas() {
        return preguntasContestadas;
    }

    public ArrayList<Comodin> getComodinesUsados() {
        return comodinesUsados;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void setPreguntas(ArrayList<Pregunta> p){preguntas=p;}

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = (int) (tiempo / 1000);
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }

    public void agregarPreguntaContestada(Pregunta pregunta) {
        preguntasContestadas.add(pregunta);
    }

    public void agregarComodinUsado(Comodin comodin) {
        comodinesUsados.add(comodin);
    }

    public String toString() {
        return "Juego{" +
                " fecha=" + fecha +
                ", participante=" + participante +
                ", nivelMaximo=" + nivelMaximo +
                ", tiempo=" + tiempo +
                ", preguntasContestadas=" + preguntasContestadas.size() +
                ", comodinesUsados=" + comodinesUsados +
                ", premio='" + premio + '}';
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }


    public void setParticipante(Estudiante e){participante = e;}
    public void setCompañero(Estudiante e){mateApoyo = e;}

    public ArrayList<Pregunta> PreguntasNivel(int n){
        ArrayList<Pregunta> p = new ArrayList<Pregunta>();
        for (Pregunta pregunta: preguntas){
            if (pregunta.getNivel()==n){
                p.add(pregunta);
            }
        }
        return p;
    }
    public void validarPreguntas(int n)throws ValidacionException{
        int maxNivel = preguntas.get(preguntas.size()-1).getNivel();
        ArrayList<Integer> numPreg = new ArrayList<Integer>();
        for (int i=1; i<maxNivel+1; i++){
            numPreg.add(PreguntasNivel(i).size());
        }
        int min = Collections.min(numPreg);
        if (n>min){throw new ValidacionException("No hay preguntas suficientes como para armar un juego de "+n+" preguntas por nivel");}
    }
    
    public void prepararPreguntas(){
        int maxNivel = preguntas.get(preguntas.size()-1).getNivel();
        ArrayList<Pregunta> p = new ArrayList<Pregunta>();
        for (int i=1; i<maxNivel+1; i++){
            ArrayList<Pregunta> pregunta = PreguntasNivel(i);
            for (int j=0; j<this.numeroPreguntas; j++){
                p.add(pregunta.get(j));
            }
        }
        preguntas = p;
    }
   
}
