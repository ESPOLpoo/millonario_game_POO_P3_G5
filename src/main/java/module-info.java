module org.game {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.game to javafx.fxml;
    exports org.game;
}
