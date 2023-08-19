package org.game;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button salir;

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

    @FXML
    public void initialize() {
    }
}
