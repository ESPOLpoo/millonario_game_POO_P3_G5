package org.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import org.game.model.data.TerminoAcademico;
import org.game.lib.Util;
import org.game.model.logic.Juego;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String PATH = "src/main/resources/org/game/files/"; 
    public static boolean SAVE = false;
    public static String ERR_MSG = "Hubo un error inesperado. Tal vez sea una mala entrada de texto o un mal funcionamiento del programa :(";
    public static Juego JUEGO = new Juego();
    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1280, 720);
        stage.setScene(scene);
        if (!SAVE){
            try{TerminoAcademico.loadBase(PATH);}
            catch(IOException e){e.printStackTrace();}
        }
        stage.show();

        System.out.println("Cargando datos...");
        Backend.cargarDatos();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
     //Muestra una alerta
    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información incorrecta");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}