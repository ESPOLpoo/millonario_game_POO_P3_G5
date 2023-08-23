/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.game.model.data;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.game.App;
import org.game.lib.Util;


public class Buscador extends HBox{
    private TextField textoBuscar;
    private Button button;
    private TablaSeleccion tabla;
    
   
    public Buscador(TablaSeleccion tabla){
        this.tabla = tabla;
        textoBuscar = new TextField();
        textoBuscar.setStyle("-fx-border-color:white;");
        ImageView image = Util.loadView(App.PATH+"lupa.png",27);
        image.setStyle("-fx-cursor: hand;");
        image.setOnMouseClicked(eh -> filtrar());
        setStyle("-fx-padding: 10px 0;");
        getChildren().addAll(textoBuscar,image);
    }
    
  
    public void filtrar(){
        tabla.getVb().getChildren().clear();
        for (int i=0; i<tabla.getTextOption().size();i++){
            if (tabla.getTextOption().get(i).getText().contains(textoBuscar.getText())){
                HBox hb = new HBox();
                hb.getChildren().addAll(tabla.getTextOption().get(i),tabla.getTexts().get(2*i),tabla.getTexts().get(2*i+1));
                tabla.getVb().getChildren().add(hb);
            }
        }
    }
    
    public void setTabla(TablaSeleccion tabla){
        this.tabla = tabla;
    
    }
}
