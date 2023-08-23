module com.example.pantalla_game {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.example.pantalla_game to javafx.fxml;
    exports com.example.pantalla_game;
}