module org.game {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.game to javafx.fxml;
    exports org.game;
    opens org.game.ui to javafx.fxml;
    exports org.game.ui;
}
