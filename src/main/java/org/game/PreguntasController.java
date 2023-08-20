package org.game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import org.game.lib.Util;
import org.game.model.data.Materia;
import org.game.model.logic.Pregunta;

public class PreguntasController {
    @FXML private StackPane screen;
    @FXML private GridPane gridPreguntas;
    @FXML private Button btnAgregarPregunta;
    @FXML private Button btnEliminarPregunta;

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
                Util.changeCellSize(gridPreguntas, 0, i, 20, 30);
                Util.addCellToGrid(gridPreguntas, pregunta.getEnunciado(), 1, i);
                Util.addCellToGrid(gridPreguntas, String.valueOf(pregunta.getNivel()), 2, i);
                Util.addCellToGrid(gridPreguntas, pregunta.getRespuestaCorrecta(), 3, i);

                StringBuilder respuestasIncorrectas = new StringBuilder();
                for (String respuestaIncorrecta: pregunta.getRespuestasIncorrectas()) {
                    respuestasIncorrectas.append(respuestaIncorrecta).append(",");
                }

                Util.addCellToGrid(gridPreguntas, respuestasIncorrectas.toString(), 4, i);
                i++;
            }
        }

        ColumnConstraints lastCol = new ColumnConstraints();
        lastCol.setHgrow(Priority.ALWAYS);
        gridPreguntas.getColumnConstraints().addAll(new ColumnConstraints(20), new ColumnConstraints(570), new ColumnConstraints(50), new ColumnConstraints(240), lastCol);
    }

    public void agregarPregunta() {
        Util.setButtonStyleClicked(btnAgregarPregunta);
        VBox agregarPreguntaModal = Util.generateModal(screen, btnAgregarPregunta);


    }

    public void eliminarPregunta() {
        Util.setButtonStyleClicked(btnEliminarPregunta);
        VBox eliminarPreguntaModal = Util.generateModal(screen, btnEliminarPregunta);

        TextField idPregunta = Util.addInput(eliminarPreguntaModal, "ID de la pregunta a eliminar");

        Button eliminar = Util.generateButton("Eliminar");
        eliminar.setOnMouseClicked(e -> {
            int idPreguntaInt;

            try {
                idPreguntaInt = Integer.parseInt(idPregunta.getText());
            } catch (NumberFormatException error) {
                Util.showAlert("Id de pregunta no válido", "El id de la pregunta que desea eliminar no es válido");
                return;
            }

            int rows = gridPreguntas.getRowCount();
            for (int row = 1; row < rows; row++) {
                Label label = (Label) gridPreguntas.getChildren().get(row * 5 );
                int id = Integer.parseInt(label.getText());

                if (id == idPreguntaInt) { // TODO: eliminar pregunta
                    System.out.println("Eliminando pregunta...");
                    Util.removeModal(screen, eliminarPreguntaModal, btnEliminarPregunta);
                    return;
                }
            }
            Util.showAlert("Id de pregunta no encontrado", "El id de la pregunta que desea eliminar no existe");
        });

        eliminarPreguntaModal.getChildren().add(eliminar);
    }
}
