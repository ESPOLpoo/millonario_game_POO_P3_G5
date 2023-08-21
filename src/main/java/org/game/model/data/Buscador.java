/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.game.model.data;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class Buscador extends HBox{
    private TextField textoBuscar;
    private Button button;
    private TablaSeleccion tabla;
    
    public Buscador(){}
    
    public Buscador(TablaSeleccion tabla){
        this.tabla = tabla;
        textoBuscar = new TextField();
        button = new Button("Buscar");
        button.setOnAction(eh -> filtrar());
        getChildren().addAll(textoBuscar,button);
    }
    
    public void filtrar(){
        tabla.getVb().getChildren().clear();
        for (int i=0; i<tabla.getTextOption().size();i++){
            System.out.println("a");
            if (tabla.getTextOption().get(i).getText().contains(textoBuscar.getText())){
                HBox hb = new HBox();
                hb.getChildren().addAll(tabla.getTextOption().get(i),tabla.getTexts().get(2*i),tabla.getTexts().get(2*i+1));
                tabla.getVb().getChildren().add(hb);
            }
        }
    }
    
    public void setTabla(TablaSeleccion tabla){this.tabla = tabla;}
}
