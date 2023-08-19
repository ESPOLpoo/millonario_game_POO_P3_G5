package org.game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Configuracion {

    @FXML
    public void menuTerminosAcademicos() throws IOException{
        App.setRoot("terminos");
    }

    @FXML
    void returnPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void menuMateriasYParalelos() throws IOException{
        App.setRoot("materias_paralelos");
    }

}