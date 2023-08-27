
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
    private VBox vb;
    private HBox tittleBox;
    private Button button;
    private int tamaño;

    public TablaSeleccion(String[] etiquetas, ArrayList<Extraible> info, int ancho){
        this.etiquetas = etiquetas;
        tamaño = ancho;
        this.info = info;
        texts = new ArrayList<TextField>();
        textOption = new ArrayList<TextField>();
        tittleBox = new HBox();
        vb = new VBox();
        getChildren().addAll(tittleBox,vb);
        cargarEtiquetas();
        cargarTabla();
        
    }
    
    private void cargarEtiquetas(){
        HBox hb = new HBox();
        
        for (int i = 0; i<etiquetas.length; i++){
            TextField text = new TextField(etiquetas[i]);
            text.setPrefWidth(tamaño);
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
                infoTabla.setPrefWidth(tamaño);
                
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
                info.add(getTexts().get(n*i+j).getText());
                }
                Extraible obj = this.info.get(i).getObj(info);
                if (!compararComponentes(this.info.get(i).getInfo(), obj.getInfo())) {
                    //En caso de haber uno distinto, se valida y se actualiza
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

    public boolean compararComponentes(ArrayList<String> info1, ArrayList<String> info2){
        boolean valor = true;
        for (int i=0; i<info1.size(); i++){
            if (!info1.get(i).equals(info2.get(i))){valor=false;}
        }
        return valor;
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
