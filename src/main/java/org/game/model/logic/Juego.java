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
import org.game.model.ui.Menu;

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
    private Pregunta[] preguntasPorResolver; // 231 Menu.java
    private ArrayList<Comodin> comodinesUsados;
    private static ArrayList<Juego> juegos = new ArrayList<>();
    private static Random random = new Random();

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


    public static void generarReporte() {

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

    public Estudiante getMateApoyo(){
        return mateApoyo;
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

    public Pregunta[] getPreguntasPorResolver() {
        return this.preguntasPorResolver;
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

    public void setParticipante(String matricula) {
        if (matricula.equals("0")) {
            int indice = random.nextInt(paralelo.getEstudiantes().size());
            participante = paralelo.getEstudiantes().get(indice);
        } else {
            this.participante = paralelo.getEstudiante(matricula);
        }
    }
    public void setParticipante(Estudiante e){participante = e;}
    public void setCompañero(Estudiante e){mateApoyo = e;}

    public void setMateApoyo(String matriculaApoyo) {
        if (matriculaApoyo.equals("0")) {
            // elegir un estudiante al azar que no sea el participante
            ArrayList<Estudiante> subEstudiantes = new ArrayList<>(paralelo.getEstudiantes());
            subEstudiantes.remove(participante);
            int indice = random.nextInt(subEstudiantes.size());
            mateApoyo = subEstudiantes.get(indice);
        } else {
            this.mateApoyo = paralelo.getEstudiante(matriculaApoyo);
        }
    }

    public void setPreguntasPorResolver() {
        preguntasPorResolver = new Pregunta[materia.getNiveles() * this.numeroPreguntas]; // array de preguntas con numero de preguntas por nivel
        ArrayList<ArrayList<Pregunta>> preguntasPorNivel = Materia.agruparPorNivel(materia.getPreguntas()); // array de array de preguntas por nivel
        int i = 0;
        for (ArrayList<Pregunta> preguntasNivel : preguntasPorNivel) {
            for (int j = 0; j < numeroPreguntas; j++) {
                Pregunta preguntaRandom = Materia.getRandomPreguntaNivel(preguntasNivel);
                preguntasPorResolver[i] = preguntaRandom;
                preguntasNivel.remove(preguntaRandom);
                i++;
            }
        }
    }
    
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

    public void jugar() {
        boolean correcto = true;
        Comodin[] comodines = Comodin.values();
        ArrayList<String> premios = new ArrayList<>();
        for (int i = 0; i < preguntasPorResolver.length; i++) {
            premios.add("$" + (i + 1) * 100);
        }

        ArrayList<String> respuestasAleatorias = new ArrayList<>();
        boolean usoCincuenta = false;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < preguntasPorResolver.length && correcto; i++) {
            Pregunta preguntaActual = preguntasPorResolver[i];
            if (usoCincuenta) {
                respuestasAleatorias = preguntaActual.agruparAleatoriamente(new ArrayList<>(preguntaActual.getRespuestasIncorrectas().subList(0, 1)));
                usoCincuenta = false;
            } else {
                respuestasAleatorias = preguntaActual.agruparAleatoriamente(preguntaActual.getRespuestasIncorrectas());
            }

            System.out.println(preguntaActual.mostrarPregunta(respuestasAleatorias));
            System.out.println("Premio en juego: " + premios.get(i));
            System.out.println("Ha transcurrido " + (System.currentTimeMillis() - startTime) / 1000 + " segundos");
            String respuesta = Menu.sc.nextLine();
            if (respuesta.equals("*")) { // comodines
                if (comodinesUsados.size() == comodines.length) {
                    System.out.println("Ya no tiene comodines disponibles");
                }
                else {
                    System.out.println("Tiene disponible los siguientes comodines");
                    for (Comodin comodin : comodines) {
                        if (!comodinesUsados.contains(comodin)) {
                            System.out.println(comodin);
                        }
                    }
                    System.out.println("Elija el comodín que desea usar (en mayúsculas)");
                    String comodin = Menu.sc.nextLine();
                    if (comodin.equals(Comodin.CINCUENTA.toString())) {
                        i--;
                        usoCincuenta = true;
                        comodinesUsados.add(Comodin.CINCUENTA);
                    } else if (comodin.equals(Comodin.MATE.toString())) {
                        System.out.println("El estudiante " + mateApoyo.getNombre() + " dice que la respuesta correcta es: " + preguntaActual.getRespuestaCorrecta());
                        i--;
                        comodinesUsados.add(Comodin.MATE);
                    } else if (comodin.equals(Comodin.SALON.toString())) {
                        System.out.println("El salon dice que la respuesta correcta es: " + preguntaActual.getRespuestaCorrecta());
                        i--;
                        comodinesUsados.add(Comodin.SALON);
                    } else {
                        System.out.println("Comodín no valido");
                    }
                }
            }
            else { // respuesta final
                /*
                 * A 0
                 * B 1
                 * C 2
                 * D 3
                 * */
                String[] opciones = { "A", "B", "C", "D" };
                int indice = Arrays.asList(opciones).indexOf(respuesta);
                correcto = preguntaActual.getRespuestaCorrecta().equals(respuestasAleatorias.get(indice).substring(3));
                if (correcto) {
                    preguntasContestadas.add(preguntaActual);
                    premio = premios.get(i);
                    setNivelMaximo(i + 1);
                }
            }
        }

        if (correcto) {
            System.out.println("Felicidades, ha ganado el juego");
            System.out.println("Ingrese el premio final a recibir");
            premio = Menu.sc.nextLine();
        } else {
            System.out.println("Ha perdido el juego");
        }
        setFecha(LocalDate.now());
        setTiempo(System.currentTimeMillis() - startTime);
        juegos.add(this);
    }

    public static void mostrarJuegos(String termino, String codigo, int paralelo) {
        ArrayList<Juego> juegosFiltrados = new ArrayList<>();
        for (Juego juego : juegos) {
            if (juego.termino.equals(TerminoAcademico.getTermino(termino)) && juego.materia.getCodigo().equals(codigo) && juego.paralelo.getNumero() == paralelo) {
                juegosFiltrados.add(juego);
            }
        }

        if (juegosFiltrados.isEmpty()) {
            System.out.println("No hay juegos registrados");
        }
        else {
            for (Juego juego : juegosFiltrados) {
                System.out.println(juego);
            }
        }
    }
}
