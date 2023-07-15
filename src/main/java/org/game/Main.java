package org.game;

import java.util.ArrayList;

import org.game.model.data.Materia;
import org.game.model.data.TerminoAcademico;
import org.game.model.logic.Showable;
import org.game.model.ui.Menu;

public class Main {
    static ArrayList<String> opcionesIniciales = new ArrayList<>();
    static ArrayList<String> opcionesConfiguracion = new ArrayList<>();
    static ArrayList<String> opcionesTerminosAcademicos = new ArrayList<>();
    static ArrayList<String> opcionesMateriasParalelos = new ArrayList<>();
    static ArrayList<String> opcionesPreguntas = new ArrayList<>();
    static ArrayList<String> opcionesNuevoJuego = new ArrayList<>();
    static ArrayList<String> opcionesReporte = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatos();
        Menu menuInicial = new Menu(opcionesIniciales); // configuracion, juego, reporte // compuesto
        Menu menuConfiguracion = new Menu(opcionesConfiguracion); // terminos, materias y paralelos, preguntas // compuesto
        Menu menuTerminosAcademicos = new Menu(opcionesTerminosAcademicos); // simple
        Menu menuMateriasParalelos = new Menu(opcionesMateriasParalelos); // simple
        Menu menuPreguntas = new Menu(opcionesPreguntas); // simple
        Menu menuNuevoJuego = new Menu(opcionesNuevoJuego); // simple
        Menu menuNuevoReporte = new Menu(opcionesReporte); // simple

        menuTerminosAcademicos.setShowable(Showable.TERMINOACADEMICO);
        menuMateriasParalelos.setShowable(Showable.MATERIAPARALELO);
        menuPreguntas.setShowable(Showable.PREGUNTA);
        menuNuevoJuego.setShowable(Showable.JUEGO);
        menuNuevoReporte.setShowable(Showable.REPORTE);

        menuConfiguracion.setMenus(new ArrayList<Menu>() {{
            add(menuTerminosAcademicos);
            add(menuMateriasParalelos);
            add(menuPreguntas);
        }});

        menuInicial.setMenus(new ArrayList<Menu>() {{
            add(menuConfiguracion);
            add(menuNuevoJuego);
            add(menuNuevoReporte);
        }});

        menuInicial.showMenu();
    }

    public static void cargarDatos() {
        opcionesIniciales.add("Configuraciones");
        opcionesIniciales.add("Nuevo juego");
        opcionesIniciales.add("Reporte");

        opcionesConfiguracion.add("Administrar términos académicos");
        opcionesConfiguracion.add("Administrar materias y paralelos");
        opcionesConfiguracion.add("Administrar preguntas");

        opcionesTerminosAcademicos.add("Ingresar término");
        opcionesTerminosAcademicos.add("Editar término");
        opcionesTerminosAcademicos.add("Configurar término para el juego");

        opcionesMateriasParalelos.add("Ingresar materia");
        opcionesMateriasParalelos.add("Editar materia");
        opcionesMateriasParalelos.add("Agregar paralelo");
        opcionesMateriasParalelos.add("Eliminar paralelo");

        opcionesPreguntas.add("Visualizar preguntas");
        opcionesPreguntas.add("Agregar pregunta");
        opcionesPreguntas.add("Eliminar pregunta");

        opcionesNuevoJuego.add("Ingresar materia y paralelo");
        opcionesNuevoJuego.add("Ingresar número de preguntas por nivel");
        opcionesNuevoJuego.add("Seleccione participante");
        opcionesNuevoJuego.add("Seleccione compañero de apoyo");

        opcionesReporte.add("Ingresar término académico");
        opcionesReporte.add("Ingresar código de materia");
        opcionesReporte.add("Ingresar paralelo");

        TerminoAcademico.ingresarTermino(2021, 1);
        TerminoAcademico.ingresarTermino(2021, 2);
        TerminoAcademico.ingresarTermino(2022, 1);
        TerminoAcademico.ingresarTermino(2022, 2);
        TerminoAcademico.ingresarTermino(2023, 1);

        Materia materia1 = new Materia("MATG1045", "CÁLCULO DE UNA VARIABLE", 5);
        Materia materia2 = new Materia("MATG1046", "CÁLCULO VECTORIAL", 4);
        Materia materia3 = new Materia("MATG1050", "ECUACIONES DIFERENCIALES", 6);
        Materia materia4 = new Materia("MATG1049", "ÁLGEBRA LINEAL", 3);
        Materia materia5 = new Materia("CCPG1052", "PROGRAMACIÓN ORIENTADA A OBJETOS", 5);


        Materia.ingresarMateria(materia1);
        Materia.ingresarMateria(materia2);
        Materia.ingresarMateria(materia3);
        Materia.ingresarMateria(materia4);
        Materia.ingresarMateria(materia5);

    }
}

/*
* // Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        ArrayList<Participante> p = new ArrayList<Participante>();
        p.add(new Participante("Juan pepe", "100930"));
        p.add(new Participante("Marlon pedro", "202293"));
        p.add(new Participante("Chadi angi", "201034"));

        Configuracion config= new Configuracion();
        config.addTermino("2021", "5");
        config.addTermino("2002-1");

        config.editarAñoTermino("2021", "5", "2000");
        config.editarAñoTermino("2002-1", "1999");

        config.editarNumTermino("2000", "5", "1");
        config.editarNumTermino("1999-1", "9");

        config.ingresarMateria("184U", "Vectorial", "5");
        config.ingresarMateria("2u3", "Algebra", "4");
        config.ingresarMateria("09ko", "Poo", "3");
        config.ingresarMateria("p034", "Ingles", "2");

        config.editarMateriaCod("184U", "Vectu", "nombre");
        config.editarMateriaCod("2u3", "6", "niveles");

        config.editarMateriaNom("Poo", "PooNuevo", "nombre");
        config.editarMateriaNom("Ingles", "1", "niveles");

        config.agregarParalelo("PooNuevo", "2000-1", "5", p);
        config.eliminarParalelo("2000-1", "5");

    }
}
*
* */