package org.game;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.game.lib.Util;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;
import org.game.model.logic.Juego;

public class Nuevojuego {
    @FXML
    private VBox gameData;

    @FXML
    public void initialize() {
        // testing
        TerminoAcademico.configurarTermino(new TerminoAcademico(2023, 1));

        try {
            Util.showInfo("Termino académico seleccionado", TerminoAcademico.terminoSeleccionado.toString());
            getData();
        } catch (
                Exception e) {
            Util.showAlert("No se ha seleccionado un término académico", "Por favor, seleccione un término académico");
        }
    }

    @FXML
    public void returnPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void getData() {
        Util.addLabelToBox(gameData, "Selecciona la materia del juego");
        ChoiceBox<String> materias = new ChoiceBox<>();
        for (Materia materia : Materia.materias) {
            materias.getItems().add(materia.getNombre());
        }

        gameData.getChildren().add(materias);

        AtomicReference<Materia> materia = new AtomicReference<>();

        ChoiceBox<String> paralelos = new ChoiceBox<>();

        materias.setOnAction(e -> {
            materia.set(Materia.getMateriaByNombre(materias.getValue()));

            paralelos.getItems().clear();

            for (Paralelo paralelo: materia.get().getParalelos()) {
                if (paralelo.getTermino().equals(TerminoAcademico.terminoSeleccionado)) {
                    paralelos.getItems().add(String.valueOf(paralelo.getNumero()));
                }
            }
        });

        Util.addLabelToBox(gameData, "Selecciona el paralelo del juego");
        gameData.getChildren().add(paralelos);

        TextField preguntasPorNivel = Util.addInput(gameData, "Ingresa el número de preguntas por nivel");

        TextField matriculaEstudiante = Util.addInput(gameData, "Ingresa la matrícula del estudiante");
        CheckBox estudianteAleatorio = new CheckBox("Estudiante aleatorio");
        estudianteAleatorio.setTextFill(Color.color(1, 1, 1));
        gameData.getChildren().add(estudianteAleatorio);

        TextField matriculaCompanero = Util.addInput(gameData, "Ingresa la matrícula del compañero de apoyo");
        CheckBox companeroAleatorio = new CheckBox("Compañero de apoyo aleatorio");
        companeroAleatorio.setTextFill(Color.color(1, 1, 1));
        gameData.getChildren().add(companeroAleatorio);

        Button button = Util.generateButton("Iniciar juego");
        button.setOnMouseClicked(e -> {
            try {
                Juego juego = new Juego();
                juego.setTermino(TerminoAcademico.terminoSeleccionado);
                Materia materiaSeleccionada = materia.get();
                juego.setMateria(materiaSeleccionada);
                juego.setParalelo(Paralelo.getParalelo(TerminoAcademico.terminoSeleccionado, materiaSeleccionada, Integer.parseInt(paralelos.getValue())));
                juego.setNumeroPreguntas(Integer.parseInt(preguntasPorNivel.getText()));

                if (juego.getNumeroPreguntas() != Integer.parseInt(preguntasPorNivel.getText())) {
                    Util.showInfo("Se eligió automáticamente una cantidad distinta de preguntas", "Se eligieron " + juego.getNumeroPreguntas() + " preguntas");
                }

                juego.setParticipante(estudianteAleatorio.isSelected() ? "0" : matriculaEstudiante.getText());
                juego.setMateApoyo(companeroAleatorio.isSelected() ? "0" : matriculaCompanero.getText());

                System.out.println("Juego creado: " + juego);

                App.setRoot("gameplay");
            } catch (Exception exception) {
                Util.showAlert("Los datos son incorrectos", "Un dato ingresado no es correcto " + exception.getMessage());
            }
        });

        gameData.getChildren().add(button);
    }
}