package org.game;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.game.model.data.Estudiante;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;
import org.game.model.data.ValidacionException;
import org.game.model.logic.Pregunta;

public class PrimaryController {

    @FXML
    private Button salir;

    @FXML void menuConfiguracion() throws IOException {
        App.setRoot("configuracion");
    }

    @FXML void menuNuevoJuego() throws IOException {
        try{
        TerminoAcademico termino = App.JUEGO.getTermino();
        Materia materia = App.JUEGO.getMateria();

        if (termino==null){throw new ValidacionException("Aún no has seleccionado un término");}
        if (materia==null){throw new ValidacionException("Aún no has seleccionado una materia");}
        if (App.JUEGO.getNumeroPreguntas()==0){throw new ValidacionException("Aún no has seleccionado un numero de preguntas por nivel");}
        App.setRoot("juego");
        }
        
        catch(ValidacionException e){App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
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
