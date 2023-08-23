
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
import org.game.model.data.Extraible;
import org.game.model.data.TablaSeleccion;
import org.game.model.data.ValidacionException;
import org.game.model.logic.Pregunta;

public class PreguntaController {

    @FXML
    private FlowPane pane;
    @FXML
    private VBox vb;
    @FXML
    private ScrollPane scrollPanel;
    @FXML
    private VBox vbContainer;
    @FXML
    private TextField inputPregunta;
    @FXML
    private TextField inputRsptCorrecta;
    @FXML
    private TextField inputRsptIncorrecta1;
    @FXML
    private TextField inputRsptIncorrecta2;
    @FXML
    private TextField inputRsptIncorrecta3;
    @FXML
    private Label seleccion;
    private TablaSeleccion tabla;
    private Buscador buscador;
    private Pregunta preguntaSeleccionada;
    @FXML
    private TextField inputNivel;
    @FXML
    private void initialize() throws Exception {
        //Muestra la tabla
        showInfo();
    }

    @FXML
    private void returnConfiguracion(MouseEvent event) throws IOException{
        App.setRoot("configuracion");
    }

    @FXML
    private void updatePreguntas(ActionEvent event) {
        int i = 1;
        try {
            tabla.update();
            //Se verifica que el nuevo termino no esté vació, luego se valida y se agrega
            if (!inputPregunta.getText().isEmpty() && !inputRsptCorrecta.getText().isEmpty() 
                    && !inputRsptIncorrecta1.getText().isEmpty() && !inputRsptIncorrecta2.getText().isEmpty()
                    && !inputRsptIncorrecta3.getText().isEmpty() && !inputNivel.getText().isEmpty()) {
                Pregunta pregunta = new Pregunta(inputPregunta.getText(), inputNivel.getText(), inputRsptCorrecta.getText(),
                inputRsptIncorrecta1.getText(), inputRsptIncorrecta2.getText(), inputRsptIncorrecta3.getText());
                pregunta.validar();
                App.JUEGO.getMateria().getPreguntas().add(pregunta);
                inputPregunta.clear();
                inputRsptCorrecta.clear();
                inputRsptIncorrecta1.clear();
                inputRsptIncorrecta2.clear();
                inputRsptIncorrecta3.clear();
                inputNivel.clear();
            }
            Collections.sort(App.JUEGO.getMateria().getPreguntas());
            if (preguntaSeleccionada!=null){
                int index = App.JUEGO.getMateria().getPreguntas().indexOf(preguntaSeleccionada);
                preguntaSeleccionada.edit(App.JUEGO.getMateria().getPreguntas().get(index));
            }
            
            App.JUEGO.setPreguntas(App.JUEGO.getMateria().getPreguntas());
            
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
        for (Pregunta p : App.JUEGO.getMateria().getPreguntas()) {
            e.add(p);
        }
        String[] tittles = {"Preguntas Disponibles", "Enunciado", "Nivel","Respuesta Correcta", "Incorrecto 1", "Incorrecto 2", "Incorrecto 3"};
        tabla = new TablaSeleccion(tittles, e,300);
        buscador = new Buscador(tabla);
        //Se implementa el evento clicked para seleccionar un termino académico
        for (TextField txt : tabla.getTextOption()) {
            txt.setOnMouseClicked(eh -> {
                seleccion.setText("Materia seleccionada: " + txt.getText());
                Pregunta pregunta = new Pregunta(txt.getText());
                int index = App.JUEGO.getMateria().getPreguntas().indexOf(pregunta);
                preguntaSeleccionada = App.JUEGO.getMateria().getPreguntas().get(index);
            });

        }
        vb.getChildren().add(0,buscador);
        vbContainer.getChildren().add(tabla);
    }

    @FXML
    private void delete(ActionEvent event) throws Exception{
        App.JUEGO.getMateria().getPreguntas().remove(preguntaSeleccionada);
        showInfo();
    }
    
}
