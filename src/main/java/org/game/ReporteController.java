package org.game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.game.lib.Util;
import org.game.model.logic.Juego;
import org.game.model.logic.Pregunta;

public class ReporteController {
    @FXML private GridPane gridHistorial;
    @FXML private StackPane screen;
    @FXML
    public void returnPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void initialize() {
        int row = 1;
        for (Juego juego: Juego.getJuegos()) {
            Label fecha = new Label(juego.getFecha().toString());
            Label participante = new Label(juego.getParticipante().getNombre());
            Label nivelMaximo = new Label(String.valueOf(juego.getNivelMaximo()));
            Label tiempo = new Label(String.valueOf(juego.getTiempo()));
            Label preguntasCorrectas = new Label(String.valueOf(juego.getPreguntasContestadas().size() / 2));
            Label comodinesUsados = new Label(String.valueOf(juego.getComodinesUsados().size()));
            Label premio = new Label(juego.getPremio());

            String style = "-fx-font-family: 'JetBrains Mono'; -fx-font-size: 14px;";

            fecha.setTextFill(Color.WHITE);
            participante.setTextFill(Color.WHITE);
            nivelMaximo.setTextFill(Color.WHITE);
            tiempo.setTextFill(Color.WHITE);
            preguntasCorrectas.setTextFill(Color.WHITE);
            comodinesUsados.setTextFill(Color.WHITE);
            premio.setTextFill(Color.WHITE);

            fecha.setStyle(style);
            participante.setStyle(style);
            nivelMaximo.setStyle(style);
            tiempo.setStyle(style);
            preguntasCorrectas.setStyle(style);
            comodinesUsados.setStyle(style);
            premio.setStyle(style);

            Button detalle = Util.generateButton("Ver detalle");
            detalle.setOnMouseClicked(e -> {
                VBox modal = Util.generateModal(screen, detalle, 1000, 1000);
                Util.addShowableInfoToBox(modal, "Fecha", juego.getFecha().toString());
                Util.addShowableInfoToBox(modal, "Participante", juego.getParticipante().getNombre());
                Util.addShowableInfoToBox(modal, "Compañero", juego.getCompañero().getNombre());
                Util.addShowableInfoToBox(modal, "Nivel máximo", String.valueOf(juego.getNivelMaximo()));
                Util.addShowableInfoToBox(modal, "Tiempo", String.valueOf(juego.getTiempo()));
                Util.addShowableInfoToBox(modal, "Premio", juego.getPremio());
                Util.addLabelToBox(modal, "Preguntas del juego");

                GridPane gridPreguntas = new GridPane();

                Label enunciadoLabel = new Label("Enunciado");
                Label nivelLabel = new Label("Nivel");
                Label comodinLabel = new Label("Comodín");

                enunciadoLabel.setTextFill(Color.WHITE);
                nivelLabel.setTextFill(Color.WHITE);
                comodinLabel.setTextFill(Color.WHITE);

                enunciadoLabel.setStyle(style);
                nivelLabel.setStyle(style);
                comodinLabel.setStyle(style);

                gridPreguntas.add(enunciadoLabel, 0, 0);
                gridPreguntas.add(nivelLabel, 1, 0);
                gridPreguntas.add(comodinLabel, 2, 0);

                int rowPreguntas = 1;

                int i = 0;

                for (Pregunta pregunta: juego.getPreguntasContestadas()) {
                    if (i % 2 == 0) {
                        Label enunciado = new Label(pregunta.getEnunciado());
                        Label nivel = new Label(String.valueOf(pregunta.getNivel()));
                        Label comodin = new Label(pregunta.getComodin() == null ? "" : pregunta.getComodin().toString());

                        enunciado.setTextFill(Color.WHITE);
                        nivel.setTextFill(Color.WHITE);
                        comodin.setTextFill(Color.WHITE);

                        enunciado.setStyle(style);
                        nivel.setStyle(style);
                        comodin.setStyle(style);

                        gridPreguntas.add(enunciado, 0, rowPreguntas);
                        gridPreguntas.add(nivel, 1, rowPreguntas);
                        gridPreguntas.add(comodin, 2, rowPreguntas);

                        rowPreguntas++;
                    }
                    i++;
                }

                modal.getChildren().add(gridPreguntas);


            });

            detalle.setStyle("-fx-cursor: hand;");


            gridHistorial.add(fecha, 0, row);
            gridHistorial.add(participante, 1, row);
            gridHistorial.add(nivelMaximo, 2, row);
            gridHistorial.add(tiempo, 3, row);
            gridHistorial.add(preguntasCorrectas, 4, row);
            gridHistorial.add(comodinesUsados, 5, row);
            gridHistorial.add(premio, 6, row);
            gridHistorial.add(detalle, 7, row);
            row++;
        }
    }
}
