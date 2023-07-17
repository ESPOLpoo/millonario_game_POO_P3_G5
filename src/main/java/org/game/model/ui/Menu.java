package org.game.model.ui;

import java.util.ArrayList;
import java.util.Scanner;

import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;
import org.game.model.logic.Juego;
import org.game.model.logic.Pregunta;
import org.game.model.logic.Showable;

public class Menu {
    public static Scanner sc = new Scanner(System.in);
    private ArrayList<String> opciones;
    private String respuesta;
    private ArrayList<Menu> menus = new ArrayList<>();
    private Showable showable;

    public Menu(ArrayList<String> opciones) {
        this.opciones = opciones;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public void setShowable(Showable showable) {
        this.showable = showable;
    }

    public void showMenu() {
        do
        {
            // Mostrar opciones
            System.out.println("Escoge una opción: (escribe el número) ");
            for (String opcion : opciones) {
                System.out.println((opciones.indexOf(opcion) + 1) + ". " + opcion);
            }
            System.out.println((opciones.size() + 1) + ". Salir");

            // Leer respuesta
            respuesta = sc.nextLine();

            // Mostrar menu correspondiente, si tiene submenus
            if (menus.size() != 0) {
                for (Menu menu : menus) {
                    if (respuesta.equals(Integer.toString(menus.indexOf(menu) + 1))) {
                        menu.showMenu();
                    }
                }
            } else {
                // Ejecutar acciones si no tiene submenus
                if (respuesta.equals(Integer.toString(opciones.size() + 1))) {
                    System.out.println("Saliendo...");
                    return;
                }
                switch (this.showable) {
                    case TERMINOACADEMICO -> {
                        /*
                         * 1. Ingresar término
                         * 2. Editar término
                         * 3. Configurar término para el juego
                         * */
                        switch (respuesta) {
                            case "1" -> {
                                System.out.println("Ingrese el año para el nuevo término académico");
                                int year = sc.nextInt();
                                System.out.println("Ingrese el número de término académico");
                                int numero = sc.nextInt();
                                sc.nextLine();
                                TerminoAcademico.ingresarTermino(year, numero);
                            }
                            case "2" -> {
                                System.out.println("Ingrese el año del término a modificar");
                                int year = sc.nextInt();
                                System.out.println("Ingrese el número de término a modificar");
                                int numero = sc.nextInt();
                                System.out.println("Ingrese el año nuevo (0 para no modificar)");
                                int newYear = sc.nextInt();
                                System.out.println("Ingrese el número de término nuevo (0 para no modificar)");
                                int newNumero = sc.nextInt();
                                TerminoAcademico terminoAcademico = TerminoAcademico.getTermino(year, numero);
                                if (terminoAcademico != null){terminoAcademico.editarTermino(newYear == 0 ? null : newYear, newNumero == 0 ? null : newNumero);}
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            case "3" -> {
                                System.out.println("Ingrese el año del término a configurar");
                                int year = sc.nextInt();
                                System.out.println("Ingrese el número de término a configurar");
                                int numero = sc.nextInt();
                                TerminoAcademico terminoAcademico = TerminoAcademico.getTermino(year, numero);
                                if (terminoAcademico != null) {TerminoAcademico.configurarTermino(terminoAcademico);}
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            default -> {
                            }
                        }
                    }
                    case MATERIAPARALELO -> {
                        /*
                         * 1. Ingresar materia
                         * 2. Editar materia
                         * 3. Agregar paralelo
                         * 4. Editar paralelo
                         * */
                        switch (respuesta) {
                            case "1" -> {
                                System.out.println("Ingrese el nombre de la materia");
                                String nombre = sc.nextLine();
                                System.out.println("Ingrese el código de la materia");
                                String codigo = sc.nextLine();
                                System.out.println("Ingrese el número de niveles");
                                int niveles = sc.nextInt();
                                sc.nextLine();
                                Materia.ingresarMateria(nombre, codigo, niveles);
                            }
                            case "2" -> {
                                System.out.println("Ingrese el código de la materia a modificar");
                                String codigo = sc.nextLine();
                                System.out.println("Ingrese el nombre nuevo (0 para no modificar)");
                                String newNombre = sc.nextLine();
                                System.out.println("Ingrese el código nuevo (0 para no modificar)");
                                String newCodigo = sc.nextLine();
                                System.out.println("Ingrese el número de niveles nuevo (0 para no modificar)");
                                int newNiveles = sc.nextInt();
                                sc.nextLine();
                                Materia materia = Materia.getMateria(codigo);
                                if (materia != null) {materia.editarMateria(newNombre.equals("0") ? null : newNombre, newCodigo.equals("0") ? null : newCodigo, newNiveles == 0 ? null : newNiveles);}
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            case "3" -> {
                                System.out.println("Ingrese el código de la materia del nuevo paralelo: ");
                                String codigo = sc.nextLine();
                                System.out.println("Ingrese el término académico del nuevo paralelo: (ej: 2021-1)");
                                String termino = sc.nextLine();
                                System.out.println("Ingrese el número de paralelo");
                                int numero = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Ingrese la ruta del archivo con los estudiantes");
                                String ruta = sc.nextLine();
                                Materia materia = Materia.getMateria(codigo);
                                TerminoAcademico terminoAcademico = TerminoAcademico.getTermino(termino);
                                if (materia != null && terminoAcademico!= null){Paralelo.ingresarParalelo(terminoAcademico, materia, numero, ruta);}
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            case "4" -> {
                                for (Paralelo paralelo : Paralelo.paralelos) {
                                    System.out.println(paralelo);
                                }
                                System.out.println("Ingrese el término académico del paralelo a eliminar: (ej: 2021-1)");
                                String termino = sc.nextLine();
                                TerminoAcademico terminoAcademico = TerminoAcademico.getTermino(termino);
                                System.out.println("Ingrese el código de la materia del paralelo a eliminar");
                                String codigo = sc.nextLine();
                                Materia materia = Materia.getMateria(codigo);
                                System.out.println("Ingrese el número de paralelo a eliminar");
                                int numero = sc.nextInt();
                                sc.nextLine();
                                if (materia != null && terminoAcademico!= null){Paralelo.eliminarParalelo(new Paralelo(terminoAcademico, materia, numero));}
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            default -> {
                            }
                        }
                    }
                    case PREGUNTA -> {
                        /*
                         * 1. Visualizar preguntas
                         * 2. Ingresar pregunta
                         * 2. Eliminar pregunta
                         * */
                        switch (respuesta) {
                            case "1" -> {
                                System.out.println("Ingrese el código de la materia para mostrar sus preguntas");
                                String codigo = sc.nextLine();
                                Materia materia = Materia.getMateria(codigo);
                                if (materia != null){
                                    for (Pregunta pregunta : materia.getPreguntas()) {
                                        System.out.println(pregunta);
                                    }
                                }
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            case "2" -> {
                                System.out.println("Ingrese el código de la materia para ingresar una pregunta");
                                String codigo = sc.nextLine();
                                Materia materia = Materia.getMateria(codigo);
                                if (materia != null){
                                    System.out.println("Para esta materia, existen " + materia.getNiveles() + " niveles");
                                    System.out.println("Ingrese el enunciado de la pregunta");
                                    String enunciado = sc.nextLine();
                                    System.out.println("Ingrese el nivel de la pregunta: (mínimo 1)");
                                    int niveles = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Ingrese la respuesta correcta: ");
                                    String respuestaCorrecta = sc.nextLine();
                                    ArrayList<String> respuestasIncorrectas = new ArrayList<>();
                                    for (int i = 0; i < 3; i++) {
                                        System.out.println("Ingrese una respuesta incorrecta:" + (i) + " de 3");
                                        String respuestaIncorrecta = sc.nextLine();
                                        respuestasIncorrectas.add(respuestaIncorrecta);
                                        }
                                    materia.agregarPregunta(new Pregunta(enunciado, niveles, respuestaCorrecta, respuestasIncorrectas));
                                }
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                                
                            }
                            case "3" -> {
                                System.out.println("Ingrese el código de la materia para eliminar sus preguntas");
                                String codigo = sc.nextLine();
                                Materia materia = Materia.getMateria(codigo);
                                if (materia != null){
                                    for (Pregunta pregunta : materia.getPreguntas()) {
                                        System.out.println(pregunta);
                                    }
                                    System.out.println("Ingrese el enunciado de la pregunta a eliminar");
                                    String enunciado = sc.nextLine();
                                    Pregunta pregunta = materia.getPregunta(enunciado);
                                    materia.eliminarPregunta(pregunta);
                                }
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                                
                            }
                            default -> {
                            }
                        }
                    }
                    case JUEGO -> {
                        Juego juego = new Juego();
                        juego.setTermino(TerminoAcademico.terminoSeleccionado);
                        if (TerminoAcademico.terminoSeleccionado != null){
                            System.out.println("Ingrese el código de la materia para jugar");
                            String codigo = sc.nextLine();
                            Materia materia = Materia.getMateria(codigo);
                            if (materia != null){
                                juego.setMateria(materia);
                                System.out.println("Ingrese el número de paralelo para jugar");
                                int numero = sc.nextInt();
                                sc.nextLine();
                                Paralelo paralelo = Paralelo.getParalelo(TerminoAcademico.terminoSeleccionado, materia, numero);
                                if (paralelo != null){
                                    juego.setParalelo(paralelo);
                                    System.out.println("Ingrese el número de preguntas por nivel");
                                    int preguntasPorNivel = sc.nextInt();
                                    sc.nextLine();
                                    juego.setNumeroPreguntas(preguntasPorNivel);
                                    System.out.println("Ingrese la matrícula del estudiante a jugar (0 para elegir un estudiante aleatorio)");
                                    String matricula = sc.nextLine();
                                    juego.setParticipante(matricula);
                                    System.out.println("Ingrese la matrícula del compañero de apoyo (0 para elegir un estudiante aleatorio)");
                                    String matriculaApoyo = sc.nextLine();
                                    juego.setMateApoyo(matriculaApoyo);
                                    juego.setPreguntasPorResolver();
                                    juego.jugar();
                                }
                                else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                            else {System.out.println("Asegurate de haber ingresado correctamente la información.");}
                            }
                        else {System.out.println("Debes elegir un termino antes de inciar el juego. Ve a configuraciones");}
                        
                        
                    }
                    case REPORTE -> {
                        System.out.println("Ingrese el término académico para generar el reporte: (ej: 2021-1)");
                        String termino = sc.nextLine();
                        System.out.println("Ingrese el código de la materia para generar el reporte");
                        String codigo = sc.nextLine();
                        System.out.println("Ingrese el número de paralelo para generar el reporte");
                        int numero = sc.nextInt();
                        sc.nextLine();
                        Juego.mostrarJuegos(termino, codigo, numero);
                    }
                    default -> {
                    }
                }
            }
        } while (!respuesta.equals(Integer.toString(opciones.size() + 1)));
    }
}
