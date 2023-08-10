package org.game.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML private Button configuracion;
    @FXML private Button nuevoJuego;
    @FXML private Button reporte;
    @FXML private Button salir;

    @FXML void menuConfiguracion() throws IOException {
        App.setRoot("configuracion");
    }

    @FXML void menuNuevoJuego() throws IOException {
        App.setRoot("nuevojuego");
    }

    @FXML void menuReporte() throws IOException {
        App.setRoot("reporte");
    }

    @FXML
    void salir(MouseEvent event) {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }
}
