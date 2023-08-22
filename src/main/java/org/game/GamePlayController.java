package org.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import org.game.model.logic.Comodin;
import org.game.model.logic.Juego;
import org.game.model.logic.Pregunta;

public class GamePlayController {
    @FXML private Label temporizadorLabel;
    @FXML private Label preguntaLabel;
    @FXML private ImageView mateApoyoLabel;
    @FXML private ImageView cincuentaLabel;
    @FXML private ImageView salonLabel;
    @FXML private Label respuestaALabel;
    @FXML private Label respuestaBLabel;
    @FXML private Label respuestaCLabel;
    @FXML private Label respuestaDLabel;

    @FXML private GridPane gridPreguntas;

    private static Juego juego;
    private static Pregunta preguntaActual;

    private static Random random;

    @FXML
    public void returnPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void initialize() {
        random = new Random();
        System.out.println("GamePlayController initialized" + juego);
        boolean correcto = true;
        String premio = "";

        int cantidadPreguntasPorResolver = juego.getPreguntasPorResolver().length;


        for (int i = 0; i < cantidadPreguntasPorResolver; i++) {
            Label numeroPregunta = new Label(String.valueOf((cantidadPreguntasPorResolver - i)));
            Label valorPregunta = new Label(String.valueOf((cantidadPreguntasPorResolver - i) * 100));

            numeroPregunta.setTextFill(Color.color(0.5, 0.5, 0.5));
            valorPregunta.setTextFill(Color.color(1, 1, 1));

            String fontSize = "-fx-font-size: 20px;";
            numeroPregunta.setStyle(fontSize);
            valorPregunta.setStyle(fontSize);

            numeroPregunta.setPadding(new Insets(5));

            gridPreguntas.add(numeroPregunta, 0, i);
            gridPreguntas.add(valorPregunta, 1, i);
        }

        int filaPregunta = cantidadPreguntasPorResolver - 1;
        for (Pregunta pregunta : juego.getPreguntasPorResolver()) {
            Label labelNaranja = (Label) getNodeFromGridPane(gridPreguntas, 1, filaPregunta);
            assert labelNaranja != null;
            labelNaranja.setTextFill(Color.color(1, 0.61, 0.2));

            preguntaLabel.setText(pregunta.getEnunciado());

            ArrayList<Label> randomLabels = new ArrayList<>();
            randomLabels.add(respuestaALabel);
            randomLabels.add(respuestaBLabel);
            randomLabels.add(respuestaCLabel);
            randomLabels.add(respuestaDLabel);

            Label randomLabelCorrect = randomLabels.get(random.nextInt(randomLabels.size()));
            randomLabelCorrect.setText(pregunta.getRespuestaCorrecta());
            randomLabels.remove(randomLabelCorrect);

            for (String respuestaIncorrecta: pregunta.getRespuestasIncorrectas()) {
                Label randomLabel = randomLabels.get(random.nextInt(randomLabels.size()));
                randomLabel.setText(respuestaIncorrecta);
                randomLabels.remove(randomLabel);
            }

            filaPregunta--;
        }
    }

    public static void setJuego(Juego juego) {
        GamePlayController.juego = juego;
    }

    public void handleAnswerClick(Event e) {

    }

    public void handleComodinClick(Event e) {

    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
