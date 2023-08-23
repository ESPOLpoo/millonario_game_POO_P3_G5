package com.example.pantalla_game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button btn;

    @FXML
    public void menuGame() throws IOException {
        btn.setOnAction(e -> {
            try {
                HelloApplication.setRoot("game-view");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}