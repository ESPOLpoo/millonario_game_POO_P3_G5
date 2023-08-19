package org.game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import org.game.model.data.Materia;

public class MateriasParalelosController {

    @FXML private StackPane screen;
    @FXML private VBox materias;
    @FXML private VBox paralelos;
    @FXML private GridPane gridMaterias;
    @FXML private Button btnMateria;

    @FXML
    public void initialize()  {
        cargarMaterias();
    }

    @FXML
    void menuTerminosAcademicos(MouseEvent event) {

    }

    @FXML
    void returnPrimary(MouseEvent event) throws IOException {
        App.setRoot("configuracion");
    }

    public void cargarMaterias() {
        int i = 1;
        for (Materia materia: Materia.materias) {
            Label codigo = new Label(materia.getCodigo());
            codigo.setTextFill(Color.color(1,1,1));
            gridMaterias.add(codigo, 0, i);
            Label nombre = new Label(materia.getNombre());
            nombre.setTextFill(Color.color(1,1,1));
            gridMaterias.add(nombre, 1, i);
            Label niveles = new Label(String.valueOf(materia.getNiveles()));
            niveles.setTextFill(Color.color(1,1,1));
            gridMaterias.add(niveles, 2, i);
            i++;
        }
    }

    public void ingresarMateria() {
        Util.setButtonStyleClicked(btnMateria);
        VBox agregarMateria = new VBox();
        ImageView close = Util.loadIcon("src/main/resources/close.png");

        assert close != null;
        close.setOnMouseClicked(e -> {
            screen.getChildren().remove(agregarMateria);
            Util.setButtonStyleUnclicked(btnMateria);
        });
        close.setStyle("-fx-cursor: hand;");
        agregarMateria.getChildren().add(close);

//        Label codigoLabel = new Label("Ingrese el código de la materia:");
//        codigoLabel.setTextFill(Color.color(1,1,1));
//        agregarMateria.getChildren().add(codigoLabel);
//        TextField codigo = new TextField();
//        agregarMateria.getChildren().add(codigo);
        Util.addInput(agregarMateria, "Ingrese el código de la materia:");

        Label nombreLabel = new Label("Ingrese el nombre de la materia:");
        nombreLabel.setTextFill(Color.color(1,1,1));
        agregarMateria.getChildren().add(nombreLabel);
        TextField nombre = new TextField();
        agregarMateria.getChildren().add(nombre);

        Label nivelesLabel = new Label("Ingrese el número de niveles:");
        nivelesLabel.setTextFill(Color.color(1,1,1));
        agregarMateria.getChildren().add(nivelesLabel);
        TextField niveles = new TextField();
        agregarMateria.getChildren().add(niveles);

        Button agregar = new Button("Agregar");
        agregar.setStyle("-fx-background-color: #FE9C33; -fx-cursor: hand;");
        agregar.setTextFill(Color.color(0, 0, 0));
        agregar.setOnMouseClicked(e -> {
            if (codigo.getText().isEmpty() || nombre.getText().isEmpty() || niveles.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al ingresar la materia");
                alert.setContentText("Por favor, ingrese todos los datos");
                alert.showAndWait();
            } else {
                System.out.println("Agregando materia...");
                screen.getChildren().remove(agregarMateria);
                btnMateria.setStyle("-fx-background-color: #042C7D;");
                btnMateria.setTextFill(Color.color(1, 1, 1));
            }

        });
        agregarMateria.getChildren().add(agregar);

        agregarMateria.setMaxWidth(500);
        agregarMateria.setMaxHeight(500);
        agregarMateria.setStyle("-fx-background-color: #042C7D;");
        screen.getChildren().add(agregarMateria);
    }

    public void editarMateria() {

    }
}