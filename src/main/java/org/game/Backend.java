package org.game;

import java.util.ArrayList;
import java.util.Collections;

import org.game.model.data.Estudiante;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;
import org.game.model.logic.Pregunta;

public class Backend {
    public static void cargarDatos() {
        TerminoAcademico.ingresarTermino(2021, 1);
        TerminoAcademico.ingresarTermino(2021, 2);
        TerminoAcademico.ingresarTermino(2022, 1);
        TerminoAcademico.ingresarTermino(2022, 2);
        TerminoAcademico.ingresarTermino(2023, 1);

        Materia materia1 = new Materia("MATG1045", "CÁLCULO DE UNA VARIABLE", 2);
        Materia materia2 = new Materia("MATG1046", "CÁLCULO VECTORIAL", 2);
        Materia materia3 = new Materia("MATG1050", "ECUACIONES DIFERENCIALES", 1);
        Materia materia4 = new Materia("MATG1049", "ÁLGEBRA LINEAL", 1);
        Materia materia5 = new Materia("CCPG1052", "PROGRAMACIÓN ORIENTADA A OBJETOS", 2);

        Paralelo paraleloCUVP1 = new Paralelo(TerminoAcademico.getTermino("2021-1"), materia1, 1);
        ArrayList<Estudiante> estudiantesCUVP1 = new ArrayList<>();
        estudiantesCUVP1.add(new Estudiante("Juan Pepe", "202207899"));
        estudiantesCUVP1.add(new Estudiante("Marlon Pedro", "202207899"));
        estudiantesCUVP1.add(new Estudiante("Chadi Angi", "202207899"));
        paraleloCUVP1.setEstudiantes(estudiantesCUVP1);

        Paralelo paraleloCUVP2 = new Paralelo(TerminoAcademico.getTermino("2021-2"), materia1, 2);
        ArrayList<Estudiante> estudiantesCUVP2 = new ArrayList<>();
        estudiantesCUVP2.add(new Estudiante("Jorge Romero", "202212589"));
        estudiantesCUVP2.add(new Estudiante("María López", "202212590"));
        estudiantesCUVP2.add(new Estudiante("Pedro Gómez", "202212591"));
        paraleloCUVP2.setEstudiantes(estudiantesCUVP2);

        Paralelo paraleloCUVP3 = new Paralelo(TerminoAcademico.getTermino("2022-1"), materia1, 3);
        ArrayList<Estudiante> estudiantesCUVP3 = new ArrayList<>();
        estudiantesCUVP3.add(new Estudiante("Laura Sánchez", "202345678"));
        estudiantesCUVP3.add(new Estudiante("Pedro Gómez", "202345679"));
        estudiantesCUVP3.add(new Estudiante("María Rodríguez", "202345680"));
        paraleloCUVP3.setEstudiantes(estudiantesCUVP3);

        Paralelo paraleloCUVP4 = new Paralelo(TerminoAcademico.getTermino("2023-1"), materia1, 4);
        ArrayList<Estudiante> estudiantesCUVP4 = new ArrayList<>();
        estudiantesCUVP4.add(new Estudiante("Andrés López", "202399001"));
        estudiantesCUVP4.add(new Estudiante("Carolina Vargas", "202399002"));
        estudiantesCUVP4.add(new Estudiante("Luis Medina", "202399003"));
        paraleloCUVP4.setEstudiantes(estudiantesCUVP4);

        Paralelo paraleloCVP5 = new Paralelo(TerminoAcademico.getTermino("2021-2"), materia2, 5);
        ArrayList<Estudiante> estudiantesCVP5 = new ArrayList<>();
        estudiantesCVP5.add(new Estudiante("Ana Torres", "202222111"));
        estudiantesCVP5.add(new Estudiante("Mario Gutiérrez", "202222112"));
        estudiantesCVP5.add(new Estudiante("Sofía Fernández", "202222113"));
        paraleloCVP5.setEstudiantes(estudiantesCVP5);

        Paralelo paraleloCVP6 = new Paralelo(TerminoAcademico.getTermino("2022-1"), materia2, 6);
        ArrayList<Estudiante> estudiantesCVP6 = new ArrayList<>();
        estudiantesCVP6.add(new Estudiante("Fernando Martínez", "202333444"));
        estudiantesCVP6.add(new Estudiante("Valentina Rojas", "202333445"));
        estudiantesCVP6.add(new Estudiante("Camilo Ruiz", "202333446"));
        paraleloCVP6.setEstudiantes(estudiantesCVP6);

        Paralelo paraleloCVP7 = new Paralelo(TerminoAcademico.getTermino("2023-1"), materia2, 7);
        ArrayList<Estudiante> estudiantesCVP7 = new ArrayList<>();
        estudiantesCVP7.add(new Estudiante("Isabella González", "202488001"));
        estudiantesCVP7.add(new Estudiante("Miguel Silva", "202488002"));
        estudiantesCVP7.add(new Estudiante("Catalina Peña", "202488003"));
        paraleloCVP7.setEstudiantes(estudiantesCVP7);

        Paralelo paraleloEDP8 = new Paralelo(TerminoAcademico.getTermino("2021-2"), materia3, 8);
        ArrayList<Estudiante> estudiantesEDP8 = new ArrayList<>();
        estudiantesEDP8.add(new Estudiante("Daniel Muñoz", "202112233"));
        estudiantesEDP8.add(new Estudiante("Valeria Castro", "202112234"));
        estudiantesEDP8.add(new Estudiante("Gabriel Herrera", "202112235"));
        paraleloEDP8.setEstudiantes(estudiantesEDP8);

        Paralelo paraleloEDP9 = new Paralelo(TerminoAcademico.getTermino("2022-1"), materia3, 9);
        ArrayList<Estudiante> estudiantesEDP9 = new ArrayList<>();
        estudiantesEDP9.add(new Estudiante("Carla Ramírez", "202445566"));
        estudiantesEDP9.add(new Estudiante("Andrés Gómez", "202445567"));
        estudiantesEDP9.add(new Estudiante("Marcela Vargas", "202445568"));
        paraleloEDP9.setEstudiantes(estudiantesEDP9);

        Paralelo paraleloALP10 = new Paralelo(TerminoAcademico.getTermino("2021-2"), materia4, 10);
        ArrayList<Estudiante> estudiantesALP10 = new ArrayList<>();
        estudiantesALP10.add(new Estudiante("Lucas Fernández", "202321654"));
        estudiantesALP10.add(new Estudiante("Antonia López", "202321655"));
        estudiantesALP10.add(new Estudiante("Sebastián Morales", "202321656"));
        paraleloALP10.setEstudiantes(estudiantesALP10);

        Paralelo paraleloALP11 = new Paralelo(TerminoAcademico.getTermino("2023-1"), materia4, 11);
        ArrayList<Estudiante> estudiantesALP11 = new ArrayList<>();
        estudiantesALP11.add(new Estudiante("Daniela Silva", "202366677"));
        estudiantesALP11.add(new Estudiante("Diego Pérez", "202366678"));
        estudiantesALP11.add(new Estudiante("Mariana Rojas", "202366679"));
        paraleloALP11.setEstudiantes(estudiantesALP11);

        Paralelo paraleloPOOP12 = new Paralelo(TerminoAcademico.getTermino("2021-2"), materia5, 12);
        ArrayList<Estudiante> estudiantesPOOP12 = new ArrayList<>();
        estudiantesPOOP12.add(new Estudiante("Alejandro Torres", "202398765"));
        estudiantesPOOP12.add(new Estudiante("Valentina González", "202398766"));
        estudiantesPOOP12.add(new Estudiante("Gabriela Martínez", "202398767"));
        paraleloPOOP12.setEstudiantes(estudiantesPOOP12);

        Paralelo paraleloPOOP13 = new Paralelo(TerminoAcademico.getTermino("2022-1"), materia5, 13);
        ArrayList<Estudiante> estudiantesPOOP13 = new ArrayList<>();
        estudiantesPOOP13.add(new Estudiante("Francisco Rojas", "202476543"));
        estudiantesPOOP13.add(new Estudiante("María Vargas", "202476544"));
        estudiantesPOOP13.add(new Estudiante("Matías Soto", "202476545"));
        paraleloPOOP13.setEstudiantes(estudiantesPOOP13);

        Paralelo paraleloPOOP14 = new Paralelo(TerminoAcademico.getTermino("2023-1"), materia5, 14);
        ArrayList<Estudiante> estudiantesPOOP14 = new ArrayList<>();
        estudiantesPOOP14.add(new Estudiante("José Pérez", "202512345"));
        estudiantesPOOP14.add(new Estudiante("Sara Rodríguez", "202512346"));
        estudiantesPOOP14.add(new Estudiante("Andrea Morales", "202512347"));
        paraleloPOOP14.setEstudiantes(estudiantesPOOP14);

        Paralelo paraleloPOO3 = new Paralelo(TerminoAcademico.getTermino("2023-1"), materia5, 3);
        ArrayList<Estudiante> estudiantesPOO3 = new ArrayList<>();
        estudiantesPOO3.add(new Estudiante("ACELDO TORRES MARIA GRAZIA", "202500001"));
        estudiantesPOO3.add(new Estudiante("AGUILAR TINOCO JEAN CARLOS", "202500002"));
        estudiantesPOO3.add(new Estudiante("AMORETTI SANCHEZ JUAN CARLOS", "202500003"));
        estudiantesPOO3.add(new Estudiante("ANDRADE VELASCO ANGELLO BERNIE", "202500004"));
        estudiantesPOO3.add(new Estudiante("ARAUJO ORTEGA DIEGO ENZO JAVIER", "202500005"));
        estudiantesPOO3.add(new Estudiante("AZU PERLAZA NICOLE FERNANDA", "202500006"));
        estudiantesPOO3.add(new Estudiante("BALDEON BAQUE IVAN GONZALO", "202500007"));
        estudiantesPOO3.add(new Estudiante("BARBERAN GALLARDO MELISSA ESTEFANIA", "202500008"));
        estudiantesPOO3.add(new Estudiante("BASILIO ACEBO DANIELA MILENA", "202500009"));
        estudiantesPOO3.add(new Estudiante("BORBOR GUTIERREZ VICTOR DANIEL", "202500010"));
        estudiantesPOO3.add(new Estudiante("CABRERA VIVANCO ALVARO DAVID", "202500011"));
        estudiantesPOO3.add(new Estudiante("CARRILLO BASTIDAS GLADYS ELIANA", "202500012"));
        estudiantesPOO3.add(new Estudiante("CORDERO CALLES RONALD ELIAS", "202500013"));
        estudiantesPOO3.add(new Estudiante("ESPINOZA PINELA ANGELO ALEXANDER", "202500014"));
        estudiantesPOO3.add(new Estudiante("GONZABAY ESPIN DOUGLAS VICENTE", "202500015"));
        estudiantesPOO3.add(new Estudiante("GUAMAN QUIJIJE RONALD STEVEN", "202500016"));
        estudiantesPOO3.add(new Estudiante("HERRERA LEON ANTHONY ARTURO", "202500017"));
        estudiantesPOO3.add(new Estudiante("LINO INDACOCHEA STEVEN MOISES", "202500018"));
        estudiantesPOO3.add(new Estudiante("LORENZO LOPEZ ERICK GABRIEL", "202500019"));
        estudiantesPOO3.add(new Estudiante("MACIAS ARTURO LEONARDO DAVID", "202500020"));
        estudiantesPOO3.add(new Estudiante("MAZA PUNINE ISSAC ALEXANDER", "202500021"));
        estudiantesPOO3.add(new Estudiante("MEJIA PARRA KEVIN SANTIAGO", "202500022"));
        estudiantesPOO3.add(new Estudiante("NAVARRETE CASTILLO ANTHONY JOSUE", "202500023"));
        estudiantesPOO3.add(new Estudiante("POVEDA QUIMIZ MICHAEL CRESCENCIO", "202500024"));
        estudiantesPOO3.add(new Estudiante("RIVAS ABAD BRAULIO DE JESUS", "202500025"));
        estudiantesPOO3.add(new Estudiante("RIVAS PINCAY EMMANUEL GERARDO", "202500026"));
        estudiantesPOO3.add(new Estudiante("ROMERO ALMEIDA EMILIO JOSE", "202500027"));
        estudiantesPOO3.add(new Estudiante("SANTANDER LOPEZ EDU ISRAEL", "202500028"));
        estudiantesPOO3.add(new Estudiante("SIAVICHAY VASQUEZ ADRIAN RICARDO", "202500029"));
        estudiantesPOO3.add(new Estudiante("SUAREZ MENDIETA GARY STEVEN", "202500030"));
        estudiantesPOO3.add(new Estudiante("SUAREZ VALDIVIESO JOSE JULIO", "202500031"));
        estudiantesPOO3.add(new Estudiante("VARGAS ISA GENESIS DAYANNA", "202500032"));
        estudiantesPOO3.add(new Estudiante("VILLAMAGUA ESCUDERO JUAN MATEO", "202500033"));
        estudiantesPOO3.add(new Estudiante("ZAMORA CEDEÑO JORDY STEVEN", "202500034"));
        estudiantesPOO3.add(new Estudiante("ZARUMA GAME JOSHUA ANDRES", "202500035"));
        paraleloPOO3.setEstudiantes(estudiantesPOO3);

        Collections.addAll(Paralelo.paralelos, paraleloCUVP1, paraleloCUVP2, paraleloCUVP3, paraleloCUVP4, paraleloCVP5, paraleloCVP6, paraleloCVP7, paraleloEDP8, paraleloEDP9, paraleloALP10, paraleloALP11, paraleloPOOP12, paraleloPOOP13, paraleloPOOP14, paraleloPOO3);

        materia1.agregarParalelo(paraleloCUVP1);
        materia1.agregarParalelo(paraleloCUVP2);
        materia1.agregarParalelo(paraleloCUVP3);
        materia1.agregarParalelo(paraleloCUVP4);
        materia2.agregarParalelo(paraleloCVP5);
        materia2.agregarParalelo(paraleloCVP6);
        materia2.agregarParalelo(paraleloCVP7);
        materia3.agregarParalelo(paraleloEDP8);
        materia3.agregarParalelo(paraleloEDP9);
        materia4.agregarParalelo(paraleloALP10);
        materia4.agregarParalelo(paraleloALP11);
        materia5.agregarParalelo(paraleloPOOP12);
        materia5.agregarParalelo(paraleloPOOP13);
        materia5.agregarParalelo(paraleloPOOP14);

        ArrayList<String> ri1 = new ArrayList<>();
        ri1.add("x^2");
        ri1.add("x^3");
        ri1.add("x^4");
        materia1.agregarPregunta(new Pregunta("¿Cuál es la derivada de x^2?", 1, "2x", ri1));

        ArrayList<String> ri8 = new ArrayList<>();
        ri8.add("Albert Einstein");
        ri8.add("Stephen Hawking");
        ri8.add("Nikola Tesla");
        materia1.agregarPregunta(new Pregunta("¿Quién inventó el Cálculo?", 1, "Isaac Newton", ri8));

        ArrayList<String> ri2 = new ArrayList<>();
        ri2.add("15x^3");
        ri2.add("15x^4");
        ri2.add("15x^5");
        materia1.agregarPregunta(new Pregunta("¿Cuál es la derivada de 5x^3?", 2, "15x^2", ri2));

        ArrayList<String> ri9 = new ArrayList<>();
        ri9.add("La pendiente de la recta tangente");
        ri9.add("La velocidad instantánea");
        ri9.add("La distancia recorrida");
        materia1.agregarPregunta(new Pregunta("¿Qué es la integral?", 2, "El área bajo la curva", ri9));

        ArrayList<String> ri3 = new ArrayList<>();
        ri3.add("Funciones escalares");
        ri3.add("Funciones lineales");
        ri3.add("Funciones cuadráticas");
        materia2.agregarPregunta(new Pregunta("¿Cómo se llaman las funciones con 2 o más componentes de llegada", 1, "Funciones vectoriales", ri3));

        ArrayList<String> ri4 = new ArrayList<>();
        ri4.add("(5, 5, 5)");
        ri4.add("(1, 2, 3)");
        ri4.add("(1, 3, 2)");
        materia2.agregarPregunta(new Pregunta("¿Cuál es el vector normal a la recta (1, 2, 3) + t(1, 1, 1)?", 2, "(1, 1, 1)", ri4));

        ArrayList<String> ri5 = new ArrayList<>();
        ri5.add("Una ecuación sin derivadas");
        ri5.add("Una ecuación con integrales");
        ri5.add("Una ecuación con raíces");
        materia3.agregarPregunta(new Pregunta("¿Qué es una ecuación diferencial", 1, "Una ecuación con derivadas", ri5));

        ArrayList<String> ri6 = new ArrayList<>();
        ri6.add("Una función");
        ri6.add("Una función que cumple con la propiedad de linealidad y no homogeneidad");
        ri6.add("Una función que cumple con la propiedad de no linealidad y homogeneidad");
        materia4.agregarPregunta(new Pregunta("¿Qué es una transformación lineal", 1, "Una función que cumple con la propiedad de linealidad y homogeneidad", ri6));

        // P1 Nivel 1
        ArrayList<String> ri7 = new ArrayList<>();
        ri7.add("Un lenguaje de programación");
        ri7.add("Un lenguaje de programación funcional");
        ri7.add("Un lenguaje de programación multiparadigma");
        materia5.agregarPregunta(new Pregunta("¿Qué es Java?", 1, "Un lenguaje de programación orientado a objetos", ri7));

        // P2 Nivel 1
        ArrayList<String> ri10 = new ArrayList<>();
        ri10.add("Una clase");
        ri10.add("Un objeto");
        ri10.add("Un método");
        materia5.agregarPregunta(new Pregunta("¿Qué es un objeto?", 1, "Una instancia de una clase", ri10));

        // P3 Nivel 1
        ArrayList<String> ri11 = new ArrayList<>();
        ri11.add("Variable global");
        ri11.add("Variable de instancia");
        ri11.add("Variable estática");
        materia5.agregarPregunta(new Pregunta("¿Cuál es el alcance de una variable declarada dentro de un método en Java?", 1, "Variable local", ri11));

        // P1 Nivel 2
        ArrayList<String> ri21 = new ArrayList<>();
        ri21.add("Abstracción");
        ri21.add("Encapsulación");
        ri21.add("Herencia");
        materia5.agregarPregunta(new Pregunta("¿Cuáles son los cuatro pilares de la programación orientada a objetos en Java?", 2, "Todas las anteriores", ri21));

        // P2 Nivel 2
        ArrayList<String> ri22 = new ArrayList<>();
        ri22.add("public y private");
        ri22.add("protected y default");
        ri22.add("abstract y synchronized");
        materia5.agregarPregunta(new Pregunta("¿Cuáles son los modificadores de acceso en Java?", 2, "Todas las anteriores", ri22));

        // P3 Nivel 2
        ArrayList<String> ri23 = new ArrayList<>();
        ri23.add("ArrayList");
        ri23.add("LinkedList");
        ri23.add("HashSet");
        materia5.agregarPregunta(new Pregunta("¿Cuáles son algunas implementaciones de la interfaz Set en Java?", 2, "Todas las anteriores", ri23));

        Materia.ingresarMateria(materia1);
        Materia.ingresarMateria(materia2);
        Materia.ingresarMateria(materia3);
        Materia.ingresarMateria(materia4);
        Materia.ingresarMateria(materia5);
    }
}