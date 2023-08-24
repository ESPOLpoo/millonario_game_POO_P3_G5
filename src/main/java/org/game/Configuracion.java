package org.game;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.game.model.data.Estudiante;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;
import org.game.model.data.TerminoAcademico;
import org.game.model.data.ValidacionException;
import org.game.model.logic.Pregunta;

public class Configuracion {

    @FXML
    private Label terminoLabel;
    @FXML
    private Label materiaLabel;
    @FXML
    private Label paraleloLabel;
    @FXML
    private Label estudianteLabel;
    @FXML
    private Label compañeroLabel;
    @FXML
    private Label preguntaLabel;
    private TerminoAcademico termino;
    private Materia materia;
    @FXML
    public void initialize(){
        termino = App.JUEGO.getTermino();
        materia = App.JUEGO.getMateria();
        Paralelo paralelo = App.JUEGO.getParalelo();
        Estudiante estudiante = App.JUEGO.getParticipante();
        Estudiante compañero = App.JUEGO.getCompañero();
        ArrayList<Pregunta> preguntas = App.JUEGO.getPreguntas();
        if (termino==null){terminoLabel.setText("Termino seleccionado: Ninguno");}
        else{terminoLabel.setText("Termino seleccionado: "+ termino);}
        if (materia==null){materiaLabel.setText("Materia seleccionada: Ninguno");}
        else{materiaLabel.setText("Materia seleccionada: "+materia.getNombre());}
        if(paralelo==null){paraleloLabel.setText("Paralelo seleccionado: Ninguno");}
        else{paraleloLabel.setText("Paralelo seleccionado: "+paralelo.getNumero());}
        if (estudiante==null){estudianteLabel.setText("Estudiante seleccionado: Ninguno");}
        else{estudianteLabel.setText("Estudiante seleccionado: "+estudiante.getNombre());}
        if(compañero==null){compañeroLabel.setText("Compañero seleccionado: Ninguno");}
        else{compañeroLabel.setText("Compañero seleccionado: "+compañero.getNombre());}
        if(preguntas==null){preguntaLabel.setText("Preguntas seleccionadas: Ningunas");}
        else{preguntaLabel.setText("Preguntas seleccionadas: "+preguntas.size());}
    }
    

    @FXML
    void returnPrimary() throws IOException {
        App.setRoot("primary");
    }


    @FXML
    public void menuPreguntas() throws IOException, ValidacionException{
        try {
         if (App.JUEGO.getMateria() == null){throw new ValidacionException("Antes debes elegir una materia");}
         else{App.setRoot("pregunta");}
        }
        catch(ValidacionException e){App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
    }

    @FXML
    private void menuMateriasYParalelos(MouseEvent event) throws IOException{
        System.out.println("a");
        App.setRoot("materias");
        System.out.println("b");

    }

    @FXML
    private void menuTerminosAcademicos(MouseEvent event) throws IOException{
        App.setRoot("terminos");
    }

}