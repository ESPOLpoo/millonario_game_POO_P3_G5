
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
import org.game.model.data.Buscador;
import org.game.model.data.Estudiante;
import org.game.model.data.Extraible;
import org.game.model.data.Paralelo;
import org.game.model.data.TablaSeleccion;
import org.game.model.data.ValidacionException;

public class EstudianteController {

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
    private TablaSeleccion tabla;
    private Buscador buscador;
    private Estudiante estudianteSeleccionado;

    @FXML private void initialize() throws Exception {
        //Muestra la tabla
        showInfo();
        
    }
    
    @FXML
    private void updateEstudiantes(ActionEvent event) {
        int i = 1;
        try {
            
            tabla.update();
            //Se verifica que el nuevo termino no esté vació, luego se valida y se agrega
            if (!inputText.getText().isEmpty()) {
                Estudiante estudiante = new Estudiante(inputText.getText());
                estudiante.validar();
                App.JUEGO.getParalelo().getEstudiantes().add(estudiante);
                inputText.clear();
            }
            if (estudianteSeleccionado!=null){
                int index = App.JUEGO.getParalelo().getEstudiantes().indexOf(estudianteSeleccionado);
                estudianteSeleccionado.setCorreo(App.JUEGO.getParalelo().getEstudiantes().get(index).getCorreo());
            }
            
            Collections.sort(App.JUEGO.getParalelo().getEstudiantes());
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
        for (Estudiante j : App.JUEGO.getParalelo().getEstudiantes()) {
            e.add(j);
        }
        String[] tittles = {"Estudiantes Disponibles", "Nombre", "Matrícula", "Correo"};
        tabla = new TablaSeleccion(tittles, e,150);
        buscador = new Buscador(tabla);
        //Se implementa el evento clicked para seleccionar un termino académico
        for (TextField txt : tabla.getTextOption()) {
            txt.setOnMouseClicked(eh -> {
                seleccion.setText("Estudiante seleccionado: " + txt.getText());
                String[] info = txt.getText().split("-");
                Estudiante estudiante = new Estudiante(info[0],info[1]);
                estudianteSeleccionado = estudiante;
            });

        }
        vb.getChildren().add(0,buscador);
        vbContainer.getChildren().add(tabla);
    }
    
    @FXML
    private void delete(ActionEvent event) throws Exception{
        App.JUEGO.getParalelo().getEstudiantes().remove(estudianteSeleccionado);
        showInfo();
    }

    @FXML
    private void returnParalelo(MouseEvent event) throws IOException {
        try {if (!App.JUEGO.getParalelo().getEstudiantes().contains(App.JUEGO.getParticipante())){
            throw new ValidacionException("Hey no has elegido un estudiante, o tal vez el estudiante que has elegido no existe, vuelve a revisar");}
        else if (!App.JUEGO.getParalelo().getEstudiantes().contains(App.JUEGO.getCompañero())){
        throw new ValidacionException("Hey no has elegido un compañero, o tal vez el compañero que has elegido no existe, vuelve a revisar");}
        else if (App.JUEGO.getParticipante().equals(App.JUEGO.getCompañero())){
        throw new ValidacionException("Hey, has elegido el mismo estudiante y compañero, eso no puede ser posible, vuelve a revisar");}
        App.setRoot("paralelo");
        }
        catch (ValidacionException e) {App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
    }

    @FXML
    private void seleccionarParticipante(ActionEvent event) {
        App.JUEGO.setParticipante(estudianteSeleccionado);
    }

    @FXML
    private void seleccionarCompañero(ActionEvent event) {
        App.JUEGO.setCompañero(estudianteSeleccionado);
    }
    
}
