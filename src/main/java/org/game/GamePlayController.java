package org.game;

import java.io.IOException;
import java.io.UncheckedIOException;
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

import org.game.lib.Util;
import org.game.model.logic.Comodin;
import org.game.model.logic.Juego;
import org.game.model.logic.Pregunta;

public class GamePlayController {
    @FXML private Label temporizadorLabel;
    @FXML private Label preguntaLabel;
    @FXML private ImageView mateApoyoLabel;
    private boolean mateDisabled = false;
    @FXML private ImageView cincuentaLabel;
    private boolean cincuentaDisabled = false;
    @FXML private ImageView salonLabel;
    private boolean salonDisabled = false;
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

            for (String respuestaIncorrecta : pregunta.getRespuestasIncorrectas()) {
                Label randomLabel = randomLabels.get(random.nextInt(randomLabels.size()));
                randomLabel.setText(respuestaIncorrecta);
                randomLabels.remove(randomLabel);
            }

            preguntaActual = pregunta;

            filaPregunta--;
        }
    }

    public static void setJuego(Juego juego) {
        GamePlayController.juego = juego;
    }

    public void handleAnswerClick(Event e) {
        Label clickedLabel = (Label) e.getSource();
        String respuesta = clickedLabel.getText();

        if (respuesta.equals(preguntaActual.getRespuestaCorrecta())) {
            juego.agregarPreguntaContestada(preguntaActual);
        } else {
            Util.showInfo("Has perdido el juego", "Suerte para la próxima");
        }
    }

    public void handleComodinClick(Event e) {
        ImageView clickedComodin = (ImageView) e.getSource();

        String notAvailable = "Comodin no disponible";
        String content = "Ya has utilizado previamente este comodin";

        Double opacity = 0.8;

        if (preguntaActual.getComodin() == null) {
            if (clickedComodin.equals(mateApoyoLabel)) {
                if (!mateDisabled) {
                    preguntaActual.setComodin(Comodin.MATE);
                    mateApoyoLabel.setOpacity(opacity);
                } else {
                    Util.showInfo(notAvailable, content);
                }
            } else if (clickedComodin.equals(cincuentaLabel)) {
                if (!cincuentaDisabled) {
                    preguntaActual.setComodin(Comodin.CINCUENTA);
                    cincuentaLabel.setOpacity(opacity);
                } else {
                    Util.showInfo(notAvailable, content);
                }
            } else {
                if (!salonDisabled) {
                    preguntaActual.setComodin(Comodin.SALON);
                    salonLabel.setOpacity(opacity);
                } else {
                    Util.showInfo(notAvailable, content);
                }
            }
        } else {
            Util.showInfo("Ya has utilizado un comodín", "No se puede utilizar más de un comodín por pregunta");
        }
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
