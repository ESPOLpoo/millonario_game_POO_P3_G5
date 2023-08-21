
package org.game.model.data;

import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.game.App;


public class TablaSeleccion extends FlowPane{
    
    private ArrayList<Extraible> info;
    private ArrayList<TextField> texts;
    private ArrayList<TextField> textOption;
    private String[] etiquetas;
    private ArrayList<String> opciones;
    private HBox buscador;
    private VBox vb;
    private HBox tittleBox;
    private TextField textoBuscar;
    private Button button;

    public TablaSeleccion(String[] etiquetas, ArrayList<Extraible> info){
        this.etiquetas = etiquetas;
        this.info = info;
        texts = new ArrayList<TextField>();
        textOption = new ArrayList<TextField>();
        //buscador = new HBox();
        tittleBox = new HBox();
        vb = new VBox();
        //textoBuscar = new TextField();
        //button = new Button();
        //button.setOnAction(eh -> filtrar());
        //buscador.getChildren().addAll(textoBuscar, button);
        //buscador.setStyle("-fx-padding: 10px 0;");
        getChildren().addAll(tittleBox,vb);
        cargarEtiquetas();
        cargarTabla();
        
    }
    
    private void cargarEtiquetas(){
        HBox hb = new HBox();
        
        for (int i = 0; i<etiquetas.length; i++){
            TextField text = new TextField(etiquetas[i]);
            text.setStyle(";-fx-control-inner-background: grey;-fx-border-color:white;-fx-cursor: pointer;");
            text.setEditable(false);
            hb.getChildren().add(text);
        }
        tittleBox.getChildren().add(hb);
    }
    
    private void cargarTabla(){
        
        for (Extraible t: info){
            HBox hb = new HBox();
            for (int i=0; i<etiquetas.length ; i++){
                TextField infoTabla = new TextField(t.getInfo().get(i));
                if (i==0){
                    infoTabla.setStyle("-fx-control-inner-background: rgb(100,150,170);-fx-border-color:white;-fx-cursor: hand;");
                    infoTabla.setEditable(false);
                    textOption.add(infoTabla);
                }
                else{
                    infoTabla.setStyle("-fx-background-color: #070142; -fx-border-color:white; -fx-text-fill: white");
                    texts.add(infoTabla);
                }
                hb.getChildren().add(infoTabla);
                
            }
    
            vb.getChildren().add(hb);

        }
    }
    
    public void update() {
        int i = 0;
        int n = etiquetas.length-1;
        ArrayList<String> info = null;
        try {
            for (i=0; i<getTextOption().size();i++){
                info = new ArrayList<String>();
                for (int j = 0; j < n; j ++) {
                //Compara los terminos académicos actuales con los anterioresS
                info.add(getTexts().get(2*i+j).getText().replace(" ", ""));
                }
                Extraible obj = this.info.get(0).getObj(info);
                if (!this.info.get(i).equals(obj)) {
                    //En caso de haber uno distinto, se valida y se actualiza
                    obj.validar();
                    this.info.get(i).edit(obj);
                }
            }
        } 
        catch (
                ValidacionException e) {

            if (i < getTextOption().size()) {
                getTexts().get(2*i+1).setStyle("-fx-control-inner-background: red;-fx-border-color:white;");
                getTexts().get(2*i).setStyle("-fx-control-inner-background: red;-fx-border-color:white;");
            }
            App.mostrarAlerta(Alert.AlertType.INFORMATION, e.getMessage());}
       
    
    }
  
    public void filtrar(){
        vb.getChildren().clear();
        
        for (int i=0; i<textOption.size();i++){
            System.out.println("a");
            if (textOption.get(i).getText().contains(textoBuscar.getText())){
                HBox hb = new HBox();
                hb.getChildren().addAll(textOption.get(i),texts.get(2*i),texts.get(2*i+1));
                vb.getChildren().add(hb);
            }
        }
            
    }
    
    public VBox getVb(){
        return vb;
    }
    
    public ArrayList<TextField> getTexts(){
        return texts;
    }
    
    public ArrayList<TextField> getTextOption(){
        return textOption;
    }
    

}
