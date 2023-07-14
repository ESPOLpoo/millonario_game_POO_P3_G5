package org.game.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Configuracion {

    private ArrayList<model.Materia> materias;
    private ArrayList<model.TerminoAcademico> terminosAcademicos;

    public Configuracion(){} //Constructor vacío

    public Configuracion(ArrayList<model.Materia> materias, ArrayList<model.TerminoAcademico> terminosAcademicos){
        //Constructor recibe una lista inicial de materias y terminos
        this.materias = materias;
        this.terminosAcademicos = terminosAcademicos;
    }

    public void addTermino(String termino){
        //Añade in termino mediante su cadena año-num
        terminosAcademicos.add(new model.TerminoAcademico(termino));
    }

    public void addTermino(String año, String num){
        //Añade un termino mediante su año y número
        addTermino(año+"-"+num);
    }

    
    public void editarAñoTermino(String termino, String nuevoAño){
        int index = terminosAcademicos.indexOf(new model.TerminoAcademico(termino));

        if (index > 0){
            terminosAcademicos.get(index).setAño(nuevoAño);
        }
    }

    public void editarAñoTermino(String año, String num, String nuevoAño){
        editarAñoTermino(año+"-"+num, nuevoAño);
    }

    public void editarNumTermino(String termino, String nuevoNum){
        int index = terminosAcademicos.indexOf(new model.TerminoAcademico(termino));

        if (index > 0){
            terminosAcademicos.get(index).setNumTermino(nuevoNum);
        }
    }

    public void editarNumTermino(String termino, String nuevoNum){
        editarNumTermino(año+"-"+num, nuevoNum);
    }

    public void ingresarMateria(String codigo, String nombre, String niveles){
        materias.add(new model.Materia(codigo, nombre, niveles));
    }
    
    public void editarMateriaCod(String codigo, String nuevaInfo, String config){
        int index = materias.indexOf(new model.Materia(codigo, "Nada", "Nada"));

        if (index > 0){
            if (config.equals("nombre")){materias.get(index).setNombre(nuevaInfo);}
            if (config.equals("niveles")){materias.get(index).setNiveles(nuevaInfo);}
            
        }
    }

    public void editarMateriaNom(String nombre, String nuevaInfo, String config){
        int index = materias.indexOf(new model.Materia("Nada", nombre, "Nada"));

        if (index > 0){
            if (config.equals("nombre")){materias.get(index).setNombre(nuevaInfo);}
            if (config.equals("niveles")){materias.get(index).setNiveles(nuevaInfo);}
            
        }
    }

    public void agregarParalelo(String nombreMateria, String termino, String num, ArrayList<Participante> estudiantes){
        int index = materias.indexOf(new model.Materia("nada", nombreMateria, "nada"));
        if (index > 0){
            materias.get(index).agregarParalelo(termino, numero, estudiantes)
        }
    }

    public void eliminarParalelo(String termino, String num){
        for (model.Materia materia : materias){
            int index = materia.getParalelo().indexOf(new model.Paralelo(termino, num));
            if (index > 0){
                materia.getParalelo().remove(index);
            }
        }
    }

}
