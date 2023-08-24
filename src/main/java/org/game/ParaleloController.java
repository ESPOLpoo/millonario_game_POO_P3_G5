/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.game.model.data.Buscador;
import org.game.model.data.Extraible;
import org.game.model.data.Paralelo;
import org.game.model.data.TablaSeleccion;
import org.game.model.data.ValidacionException;

/**
 *
 * @author user
 */
public class ParaleloController {

    @FXML
    private FlowPane pane;
    @FXML
    private VBox vb;
    @FXML
    private ScrollPane scrollPanel;
    @FXML
    private VBox vbContainer;
    @FXML
    private TextField inputText;
    @FXML
    private TextField inputNumero;
    @FXML
    private Label seleccion;
    private TablaSeleccion tabla;
    private Buscador buscador;
    private Paralelo paraleloSeleccionado;
    @FXML
    private void initialize() throws Exception {
        //Muestra la tabla
        showInfo();
        
    }
    
    @FXML
    private void returnMateria(MouseEvent event)throws IOException {
        try {if (!App.JUEGO.getMateria().getParalelos().contains(App.JUEGO.getParalelo())){
            throw new ValidacionException("Hey no has elegido un paralelo, o tal vez el paralelo que has elegido no existe, vuelve a revisar");}
        else if (!App.JUEGO.getParalelo().getEstudiantes().contains(App.JUEGO.getParticipante())){
            throw new ValidacionException("Hey no has elegido un estudiante, o tal vez el estudiante que has elegido no existe, vuelve a revisar");}
        else if (!App.JUEGO.getParalelo().getEstudiantes().contains(App.JUEGO.getCompañero())){
        throw new ValidacionException("Hey no has elegido un compañero, o tal vez el compañero que has elegido no existe, vuelve a revisar");}
        App.setRoot("materias");
        }
        catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
    }

    @FXML
    private void updateParalelos(ActionEvent event) {
        int i = 1;
        try {
            tabla.update();
            //Se verifica que el nuevo termino no esté vació, luego se valida y se agrega
            if (!inputText.getText().isEmpty() && !inputNumero.getText().isEmpty()) {
                Paralelo paralelo = new Paralelo(inputText.getText(),Integer.parseInt(inputNumero.getText()));
                paralelo.validar();
                App.JUEGO.getMateria().getParalelos().add(paralelo);
                inputText.clear();
                inputNumero.clear();
            }
            
            Collections.sort(App.JUEGO.getMateria().getParalelos());
            App.JUEGO.setParalelo(paraleloSeleccionado);
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

        //Se crea una matriz de objetos "extraibles" para crear una tabla de seleccion
        ArrayList<Extraible> e = new ArrayList<Extraible>();
        for (Paralelo p : App.JUEGO.getMateria().getParalelos()) {
            e.add(p);
        }
        String[] tittles = {"Paralelos Disponibles", "Num estudiantes", "Num paralelo"};
        tabla = new TablaSeleccion(tittles, e,150);
        buscador = new Buscador(tabla);
        //Se implementa el evento clicked para seleccionar un termino académico
        for (TextField txt : tabla.getTextOption()) {
            txt.setOnMouseClicked(eh -> {
                seleccion.setText("Paralelo seleccionado: " + txt.getText());
                Paralelo paralelo = App.JUEGO.getMateria().getParalelos().get(Integer.parseInt(txt.getText().split(" ")[1]));
                paraleloSeleccionado = paralelo;
            });

        }
        vb.getChildren().add(0,buscador);
        vbContainer.getChildren().add(tabla);
    }

    @FXML
    private void delete(ActionEvent event) throws Exception {
        App.JUEGO.getMateria().getParalelos().remove(paraleloSeleccionado);
        showInfo();
    }
    
    @FXML
    private void estudiantesMenu(ActionEvent event) throws Exception {
        try {if (!App.JUEGO.getMateria().getParalelos().contains(App.JUEGO.getParalelo())){
            throw new ValidacionException("Hey no has elegido un paralelo, o tal vez el paralelo que has elegido no existe, vuelve a revisar");}
            
        App.setRoot("estudiantes");
        }
        catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
    }

    @FXML
    private void aleatorio(ActionEvent event) {
        Random random = new Random();
        int randomIndex = random.nextInt(App.JUEGO.getMateria().getParalelos().size());
        paraleloSeleccionado = App.JUEGO.getMateria().getParalelos().get(randomIndex);
        seleccion.setText("Paralelo seleccionado: " + paraleloSeleccionado.getInfo().get(0));
        App.JUEGO.setParalelo(paraleloSeleccionado);
    }
}
