package org.game;

import java.io.IOException;

import javafx.fxml.FXML;

public class GamePlayController {

    @FXML
    public void returnPrimary() throws IOException {
        App.setRoot("primary");
    }
}
