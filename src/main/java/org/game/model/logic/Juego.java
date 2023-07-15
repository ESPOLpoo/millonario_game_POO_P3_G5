package org.game.model.logic;

import java.time.LocalDate;
import java.util.ArrayList;

import org.game.model.data.Estudiante;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;

public class Juego {
    private TerminoAcademico termino;
    private Materia materia;
    private Paralelo paralelo;
    private int numeroPreguntas;
    private Estudiante participante;
    private Estudiante mateApoyo;
    private String premio;
    private LocalDate fecha;
    private int nivelMaximo;
    private int tiempo;
    private ArrayList<Pregunta> preguntasContestadas;
    private ArrayList<Pregunta> preguntasPorResolver;
    private ArrayList<Comodin> comodinesUsados;
    private static ArrayList<Juego> juegos;

    public Juego(TerminoAcademico termino, Materia materia, Paralelo paralelo, int numeroPreguntas) {
        this.termino = termino;
        this.materia = materia;
        this.paralelo = paralelo;
        this.numeroPreguntas = numeroPreguntas;
    }

    public Juego(TerminoAcademico termino, Materia materia, Paralelo paralelo, int numeroPreguntas, Estudiante participante, Estudiante mateApoyo) {
        this.termino = termino;
        this.materia = materia;
        this.paralelo = paralelo;
        this.numeroPreguntas = numeroPreguntas;
        this.participante = participante;
        this.mateApoyo = mateApoyo;
    }

    public void descartarRespuestasIncorrectas() {

    }

    public void consultaMate() {

    }

    public void consultaSalon() {

    }

    public static void generarReporte() {

    }

    public Estudiante getParticipante() {
        return participante;
    }

    public String getPremio() {
        return premio;
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

    public ArrayList<Pregunta> getPreguntasContestadas() {
        return preguntasContestadas;
    }

    public ArrayList<Comodin> getComodinesUsados() {
        return comodinesUsados;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void agregarPreguntaContestada(Pregunta pregunta) {
        preguntasContestadas.add(pregunta);
    }

    public void agregarComodinUsado(Comodin comodin) {
        comodinesUsados.add(comodin);
    }

    public String toString() {
        return "";
    }
}
