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
        App.setRoot("config_terminos");
    }

    void menuMateriasYParalelos() {
        
    }

    @FXML
    void returnPrimary() throws IOException {
        App.setRoot("primary");
    }

}