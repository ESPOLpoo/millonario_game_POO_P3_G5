import java.util.ArrayList;

public class Materia {

    private String codigo;
    private String nombre;
    private String niveles;
    private ArrayList<Paralelo> paralelo;
    private ArrayList<Pregunta> preguntas;

    public Materia(String codigo, String nombre, String niveles){
        this.codigo = codigo;
        this.nombre = nombre;
        this.niveles = niveles;
        paralelo = new ArrayList<Paralelo>();
        preguntas = new ArrayList<Pregunta>();
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getNiveles() { return niveles; }
    public ArrayList<Pregunta> getPreguntas() {return preguntas;}
    public ArrayList<Paralelo> getParalelo() {return paralelo;}


    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNiveles(String niveles) { this.niveles = niveles; }

    public void agregarParalelo(String termino, String num, ArrayList<Participante> estudiantes){
        paralelo.add(new Paralelo(termino, num, estudiantes));
    }

    public void agregarPreguntas(String enunciado, int nivel, String respuestaCorrecta, ArrayList<String> respuestasIncorrectas){
        Pregunta nuevaPregunta = new Pregunta(enunciado,nivel,respuestaCorrecta,respuestasIncorrectas);
        preguntas.add(nuevaPregunta);
    }

    public void eliminarPreguntas(int num){
        preguntas.remove(num-1);
    }

    public String toString(){
        return String.format("Codigo: %s - Nombre: %s - Niveles: %s", codigo, nombre, niveles);
    }

    public boolean equals(Object obj){
        if (obj == null){return false;}
        
        Materia materia= (Materia) obj;
        
        if (getClass() == materia.getClass() && (codigo.equals(materia.getCodigo()) || nombre.equals(materia.getNombre()))){
            return true;
        }
        else{return false;}
    }
}
