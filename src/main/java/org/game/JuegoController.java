/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.game;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.game.lib.Util;
import org.game.model.logic.Comodin;
import org.game.model.logic.Juego;
import org.game.model.logic.Pregunta;

public class JuegoController {

    @FXML
    private StackPane screen;
    @FXML
    private Label temporizadorLabel;
    @FXML
    private Label preguntaLabel;
    @FXML
    private Label respuestaALabel;
    @FXML
    private Label respuestaCLabel;
    @FXML
    private Label respuestaBLabel;
    @FXML
    private Label respuestaDLabel;
    @FXML
    private ImageView mateApoyoLabel;
    @FXML
    private ImageView cincuentaLabel;
    @FXML
    private ImageView salonLabel;
    @FXML
    private GridPane gridPreguntas;
    private static Boolean correctlyAnswered;
    private static Random random;
    private static Pregunta preguntaActual;
    private boolean mateDisabled = false;
    private boolean cincuentaDisabled = false;
    private boolean salonDisabled = false;
    private Juego juego;
    
    @FXML
    public void initialize() {
        juego = App.JUEGO;
        random = new Random();
        int cantidadPreguntasPorResolver = juego.getPreguntas().size();

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

        Thread mainThread = new Thread(this::play);
        mainThread.start();
    }
    @FXML
    private void returnPrimary(MouseEvent event) {
    }

    @FXML
    public void handleAnswerClick(Event e) {
        Label clickedLabel = (Label) e.getSource();
        String respuesta = clickedLabel.getText();

        if (respuesta.equals(preguntaActual.getRespuestaCorrecta())) {
            correctlyAnswered = true;
            juego.agregarPreguntaContestada(preguntaActual);
        } else {
            correctlyAnswered = false;
        }
    }

    @FXML
    public void handleComodinClick(Event e) {
        ImageView clickedComodin = (ImageView) e.getSource();

        String notAvailable = "Comodin no disponible";
        String content = "Ya has utilizado previamente este comodin";

        double opacity = 0.5;

        if (preguntaActual.getComodin() == null) {
            if (clickedComodin.equals(mateApoyoLabel)) {
                if (!mateDisabled) {
                    preguntaActual.setComodin(Comodin.MATE);
                    Util.showInfo("Comodin compañero de apoyo", "El compañero de apoyo te recomienda la respuesta " + preguntaActual.getRespuestaCorrecta());
                    mateApoyoLabel.setOpacity(opacity);
                } else {
                    Util.showInfo(notAvailable, content);
                }
            } else if (clickedComodin.equals(cincuentaLabel)) {
                if (!cincuentaDisabled) {
                    preguntaActual.setComodin(Comodin.CINCUENTA);
                    Label[] posiblesRespuestas = {respuestaALabel, respuestaBLabel, respuestaCLabel, respuestaDLabel};
                    int eliminadas = 0;
                    for (int i = 0; i < posiblesRespuestas.length && eliminadas < 2; i++) {
                        Label label = posiblesRespuestas[i];
                        if (!label.getText().equals(preguntaActual.getRespuestaCorrecta())) {
                            label.setText("");
                            eliminadas++;
                        }
                    }
                    cincuentaLabel.setOpacity(opacity);
                } else {
                    Util.showInfo(notAvailable, content);
                }
            } else {
                if (!salonDisabled) {
                    preguntaActual.setComodin(Comodin.SALON);
                    Util.showInfo("Comodin salon", "El salon te recomienda la respuesta " + preguntaActual.getRespuestaCorrecta());
                    salonLabel.setOpacity(opacity);
                } else {
                    Util.showInfo(notAvailable, content);
                }
            }
        } else {
            Util.showInfo("Ya has utilizado un comodín", "No se puede utilizar más de un comodín por pregunta");
        }
    }
    
    @FXML
    private void play() {
        juego.setFecha(LocalDate.now());
        int cantidadPreguntaPorResolver = juego.getPreguntas().size();
        int filaPregunta = cantidadPreguntaPorResolver - 1;

        AtomicBoolean correct = new AtomicBoolean(true);
        for (int j = 0; j < cantidadPreguntaPorResolver && correct.get(); j++) {
            correctlyAnswered = null;
            int finalFilaPregunta = filaPregunta;
            int finalJ = j;
            Thread timer = new Thread(() -> {
                Platform.runLater(() -> updateQuestionUi(finalFilaPregunta, juego.getPreguntas().get(finalJ)));
                AtomicInteger timeRemaining = new AtomicInteger(59);
                for (int i = timeRemaining.get(); i > 0; i--) {
                    try {
                        if (correctlyAnswered == null) { // Si no se ha respondido la pregunta
                            Platform.runLater(() -> updateTimerLabel(timeRemaining.getAndDecrement()));
                            Thread.sleep(1000);
                        } else if (correctlyAnswered) { // Sí se respondió correctamente
                            juego.getPreguntasContestadas().add(preguntaActual);
                            juego.setNivelMaximo(finalJ+1);
                            break;
                        } else { // Sí se respondió incorrectamente
                            correct.set(false);
                            Juego.addJuego(juego);
                            Platform.runLater(() -> Util.showInfo("Has perdido el juego", "Suerte para la próxima"));
                            App.JUEGO = new Juego();
                            App.setRoot("primary");
                            break;
                        }
                    } catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    } catch (
                            IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                // Si no se ha respondido la pregunta, se termina el tiempo
                if (correctlyAnswered == null) {
                    Platform.runLater(() -> Util.showInfo("Has perdido el juego", "Se terminó el tiempo, suerte para la próxima"));
                    correct.set(false);
                    Juego.addJuego(juego);
                    try {
                        App.JUEGO = new Juego();
                        App.setRoot("primary");
                    } catch (
                            IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            timer.start();
            try {
                timer.join();
                filaPregunta--;
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (correct.get()) {
            Platform.runLater(() -> {
                Util.showInfo("Has ganado el juego", "Felicidades");

                VBox askForReward = Util.generateModal(screen, null);
                TextField recompensa = Util.addInput(askForReward, "Ingrese su recompensa");
                Button saveReward = Util.generateButton("Guardar");
                saveReward.setOnAction(e -> {
                    juego.setPremio(recompensa.getText());
                    screen.getChildren().remove(askForReward);
                    Juego.addJuego(juego);

                    try {
                        App.JUEGO = new Juego();
                        App.setRoot("primary");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                askForReward.getChildren().add(saveReward);
            });
        }
    }
    public void updateQuestionUi(int filaPregunta, Pregunta pregunta) {
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

        preguntaActual = new Pregunta(pregunta);
    }
    private void updateTimerLabel(int timeRemaining) {
        temporizadorLabel.setText(timeRemaining == 0 ? "0:00" : (timeRemaining > 9 ? "0:" + timeRemaining : "0:0" + timeRemaining));
    }
    
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;}
}
