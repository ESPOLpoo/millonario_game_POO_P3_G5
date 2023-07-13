import java.util.ArrayList;

public class Materia {

    //ATRIBUTOS
    private String codigo;
    private String nombre;
    private int niveles;
    private ArrayList<Paralelo> paralelo;
    private ArrayList<Pregunta> preguntas;

    //CONSTRUCTOR
    public Materia(String codigo, String nombre, int niveles){
        this.codigo = codigo;
        this.nombre = nombre;
        this.niveles = niveles;
    }

    //GETTERS
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getNiveles() { return niveles; }

    //SETTERS
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNiveles(int niveles) { this.niveles = niveles; }

    //MÉTODOS SOBRE LAS PREGUNTAS QUE CONTIENE CADA MATERIA
    public void visualizarPreguntas(){
        int i = 1;
        for(Pregunta preg : preguntas){
            System.out.println(i + ". " + preg.getEnunciado());
            i++;
        }
    }

    public void agregarPreguntas(String enunciado, int nivel, String respuestaCorrecta, ArrayList<String> respuestasIncorrectas){
        Pregunta nuevaPregunta = new Pregunta(enunciado,nivel,respuestaCorrecta,respuestasIncorrectas);
        preguntas.add(nuevaPregunta);
    }

    public void eliminarPreguntas(int num){
        preguntas.remove(num-1);
    }

}
