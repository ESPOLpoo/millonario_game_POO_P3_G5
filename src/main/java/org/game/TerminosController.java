/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.game;

import org.game.model.data.Extraible;
import org.game.model.data.TablaSeleccion;
import org.game.model.data.TerminoAcademico;
import org.game.lib.Util;
import org.game.model.data.ValidacionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.game.model.data.Buscador;

public class TerminosController {
    @FXML
    private VBox vbContainer;
    @FXML
    private TextField inputText;
    @FXML
    private Label seleccion;
    private TablaSeleccion tabla;
    private Buscador buscador;
    private ArrayList<TerminoAcademico> terminos;
    private TerminoAcademico terminoSeleccionado;
    @FXML
    private FlowPane pane;
    @FXML
    private ScrollPane scrollPanel;
    @FXML
    private VBox vb;
    @FXML
    private void initialize() throws Exception {
        //Muestra la tabla
        showInfo();
    }

    @FXML
    private void updateTerminos(ActionEvent event) {
        int i = 1;
        try {
            tabla.update();
            //Se verifica que el nuevo termino no esté vació, luego se valida y se agrega
            if (!inputText.getText().isEmpty()) {
                TerminoAcademico termino = new TerminoAcademico(inputText.getText().replace(" ", ""));
                termino.validar();
                terminos.add(termino);
                inputText.clear();
            }
            Collections.sort(terminos);
            TerminoAcademico.setTerminosAcademicos(terminos);
            App.JUEGO.setTermino(terminoSeleccionado);
            Util.updateSer(terminos, App.PATH + "terminos.ser");
            
            showInfo();}
            catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
            catch (Exception e) {
            App.mostrarAlerta(Alert.AlertType.INFORMATION, App.ERR_MSG);
        }
    }
    //Muestra la tabla
    private void showInfo() throws Exception {
        vb.getChildren().remove(buscador);
        //Limpia el conteneder y recoge los terminos académicos del archivo terminos.ser
        vbContainer.getChildren().clear();
        terminos = (ArrayList<TerminoAcademico>) Util.getSer(App.PATH + "terminos.ser");
        
        //Se crea una matriz de objetos "extraibles" para crear una tabla de seleccion
        ArrayList<Extraible> e = new ArrayList<Extraible>();
        for (TerminoAcademico t : terminos) {
            e.add(t);
        }
        String[] tittles = {"Terminos Disponibles", "Año", "Periodo"};
        tabla = new TablaSeleccion(tittles, e,150);
        buscador = new Buscador(tabla);
        //Se implementa el evento clicked para seleccionar un termino académico
        for (TextField txt : tabla.getTextOption()) {
            txt.setOnMouseClicked(eh -> {
                seleccion.setText("Termino seleccionado: " + txt.getText());
                TerminoAcademico termino = new TerminoAcademico(txt.getText());
                terminoSeleccionado = termino;
            });

        }
        vb.getChildren().add(0,buscador);
        vbContainer.getChildren().add(tabla);
    }

    @FXML
    private void returnConfiguracion(MouseEvent event) throws IOException {
        //Regresa al menú configuración
        try {if (!terminos.contains(App.JUEGO.getTermino())){
            throw new ValidacionException("El termino que has elegido no existe, vuelve a revisar");}
        App.setRoot("configuracion");
        }
        catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
        
    }

    @FXML
    private void aleatorio(ActionEvent event) {
        Random random = new Random();
        int randomIndex = random.nextInt(terminos.size());
        terminoSeleccionado = terminos.get(randomIndex);
        seleccion.setText("Termino seleccionado: " + terminoSeleccionado.toString());
        App.JUEGO.setTermino(terminoSeleccionado);
    }
}
