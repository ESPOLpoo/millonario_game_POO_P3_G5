
package org.game.model.data;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;


public class TablaSeleccion extends FlowPane{
    
    private ArrayList<Extraible> info;
    private ArrayList<TextField> texts;
    private ArrayList<TextField> textOption;
    private String[] etiquetas;

    public TablaSeleccion(String[] etiquetas, ArrayList<Extraible> info){
        this.etiquetas = etiquetas;
        this.info = info;
        texts = new ArrayList<TextField>();
        textOption = new ArrayList<TextField>();
        cargarEtiquetas();
        cargarTabla();
        
    }
    
    private void cargarEtiquetas(){
        HBox hb = new HBox();
        
        for (int i = 0; i<3; i++){
            TextField text = new TextField(etiquetas[i]);
            text.setStyle("-fx-font-weight: bold;");
            text.setStyle("-fx-control-inner-background: grey;");
            text.setEditable(false);
            hb.getChildren().add(text);
        }
        getChildren().add(hb);
    }
    
    private void cargarTabla(){
        HBox hb = new HBox();
        for (Extraible t: info){
            hb = new HBox();
            TextField termino = new TextField(t.getInfo().get(0));
            termino.setStyle("-fx-control-inner-background: rgb(100,150,170);");
            termino.setEditable(false);
            TextField año = new TextField(""+t.getInfo().get(1));
            TextField num = new TextField(""+t.getInfo().get(2));
            hb.getChildren().addAll(termino,año,num);
            getChildren().add(hb);
            textOption.add(termino);
            texts.add(año);
            texts.add(num);
            

        }
    }
    
    public ArrayList<TextField> getTexts(){
        return texts;
    }
    
    public ArrayList<TextField> getTextOption(){
        return textOption;
    }
    

}
