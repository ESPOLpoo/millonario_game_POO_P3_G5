/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.game.model.data;

import org.game.App;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class Util {
    
    // Actualiza un archivo serializable
    public static void updateSer(Object obj, String ruta)throws IOException{
        ObjectOutputStream ser = new ObjectOutputStream(new FileOutputStream(ruta));
        ser.writeObject(obj);
        ser.close();
    }
    
    // Busca un archivo serializable
    public static Object getSer(String ruta)throws Exception{
        Object obj=null;
        ObjectInputStream ser2 = new ObjectInputStream(new FileInputStream(App.PATH+"terminos.ser"));
        obj = ser2.readObject();
        ser2.close();

        return obj;
    }
    
    // Valida el año de un termino y que no esté repetido
    public static void validarTermino(ArrayList<TerminoAcademico> terminos, TerminoAcademico termino) throws ValidacionException{
        if (termino.getYear()>2023){
        throw new ValidacionException("Hey viajero del tiemo!. Aún falta un buen rato para el año "+termino.getYear());
        }
        else if (terminos.contains(termino)){
        throw new ValidacionException("Hey! este termino ya existe, debes cambiarlo.");
        }
    }
    
    
}
