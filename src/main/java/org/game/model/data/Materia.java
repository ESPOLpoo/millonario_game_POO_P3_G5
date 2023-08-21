package org.game.model.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.game.model.logic.Pregunta;

public class Materia {
    private String codigo;
    private String nombre;
    private int niveles;
    private ArrayList<Paralelo> paralelos;
    private ArrayList<Pregunta> preguntas;
    public static ArrayList<Materia> materias = new ArrayList<>();

    public Materia(String codigo, String nombre, int niveles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.niveles = niveles;
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

    public Pregunta getPregunta(String enunciado) {
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getEnunciado().equals(enunciado)) {
                return pregunta;
            }
        }
        return null;
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
            return this.getCodigo().equals(materia.getCodigo());
        }
        return false;
    }
}
