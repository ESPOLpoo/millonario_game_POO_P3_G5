package org.game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import org.game.lib.Util;
import org.game.model.data.Materia;
import org.game.model.logic.Pregunta;

public class PreguntasController {
    @FXML private StackPane screen;
    @FXML private GridPane gridPreguntas;

    @FXML
    void returnPrimary() throws IOException {
        App.setRoot("configuracion");
    }

    @FXML
    public void initialize() {
        cargarPreguntas();
    }

    public void cargarPreguntas() {
        int i = 1;
        for (Materia materia: Materia.materias) {
            for (Pregunta pregunta: materia.getPreguntas()) {
                Util.addCellToGrid(gridPreguntas, String.valueOf(i), 0, i);
                Util.addCellToGrid(gridPreguntas, pregunta.getEnunciado(), 1, i);
                Util.addCellToGrid(gridPreguntas, String.valueOf(pregunta.getNivel()), 2, i);
                Util.addCellToGrid(gridPreguntas, pregunta.getRespuestaCorrecta(), 3, i);

                StringBuilder respuestasIncorrectas = new StringBuilder();
                for (String respuestaIncorrecta: pregunta.getRespuestasIncorrectas()) {
                    respuestasIncorrectas.append(respuestaIncorrecta).append("\n");
                }

                Util.addCellToGrid(gridPreguntas, respuestasIncorrectas.toString(), 4, i);
                i++;
            }
        }
    }
}
