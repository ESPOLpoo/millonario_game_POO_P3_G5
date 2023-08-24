
package org.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.game.lib.Util;
import org.game.model.data.Buscador;
import org.game.model.data.Extraible;
import org.game.model.data.Materia;
import org.game.model.data.TablaSeleccion;
import org.game.model.data.TerminoAcademico;
import org.game.model.data.ValidacionException;


public class MateriasController {

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
    private Label seleccion;
    private ArrayList<Materia> materias;
    private TablaSeleccion tabla;
    private Buscador buscador;
    private Materia materiaSeleccionada = new Materia();
    
    @FXML
    private void initialize() throws Exception {
        //Muestra la tabla
        showInfo();
    }
    
    @FXML
    private void returnConfiguracion(MouseEvent event) throws IOException{  

        try {if (!materias.contains(App.JUEGO.getMateria())){
            throw new ValidacionException("Hey no has elegido una materias, o tal vez la materia que has elegido no existe, vuelve a revisar");}
        else if (!App.JUEGO.getMateria().getParalelos().contains(App.JUEGO.getParalelo())){
            throw new ValidacionException("Hey no has elegido un paralelo, o tal vez el paralelo que has elegido no existe, vuelve a revisar");
        }
            
        App.setRoot("configuracion");
        }
        catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
    }

    @FXML
    private void updateMaterias(ActionEvent event) {
        int i = 1;
        try {
            tabla.update();
            //Se verifica que el nuevo termino no esté vació, luego se valida y se agrega
            if (!inputText.getText().isEmpty()) {
                Materia materia = new Materia(inputText.getText().toUpperCase());
                materia.validar();
                materias.add(materia);
                inputText.clear();
            }
            int index = materias.indexOf(materiaSeleccionada);
            if (index>=0){
                
                materiaSeleccionada.setParalelos(materias.get(index).getParalelos());
                materiaSeleccionada.setPreguntas(materias.get(index).getPreguntas());
            }
            Collections.sort(materias);
            Materia.setMaterias(materias);
            Util.updateSer(materias, App.PATH + "materias.ser");

            App.JUEGO.setMateria(materiaSeleccionada);
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
        
        materias = (ArrayList<Materia>) Util.getSer(App.PATH + "materias.ser");
        
        //Se crea una matriz de objetos "extraibles" para crear una tabla de seleccion
        ArrayList<Extraible> e = new ArrayList<Extraible>();
        for (Materia m : materias) {
            e.add(m);
        }
        String[] tittles = {"Materias Disponibles", "Nombre", "Niveles"};
        tabla = new TablaSeleccion(tittles, e,300);
        buscador = new Buscador(tabla);
        //Se implementa el evento clicked para seleccionar un termino académico
        for (TextField txt : tabla.getTextOption()) {
            txt.setOnMouseClicked(eh -> {
                seleccion.setText("Materia seleccionada: " + txt.getText());
                Materia materia = new Materia(txt.getText());
                materiaSeleccionada = materia;
            });

        }
        vb.getChildren().add(0,buscador);
        vbContainer.getChildren().add(tabla);
    }

    @FXML
    private void paraleloMenu(ActionEvent event)throws IOException {
        try {if (!materias.contains(App.JUEGO.getMateria())){
            throw new ValidacionException("Hey no has elegido una materias, o tal vez la materia que has elegido no existe, vuelve a revisar");}
        App.setRoot("paralelo");
        }
        catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
    }
    
}
