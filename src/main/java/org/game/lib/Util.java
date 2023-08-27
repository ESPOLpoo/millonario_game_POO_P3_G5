package org.game.lib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.game.App;
import org.game.model.data.TerminoAcademico;
import org.game.model.data.ValidacionException;

public class Util {
    public static ImageView loadIcon(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            Image image = new Image(fis);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            return imageView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void setButtonStyleUnclicked(Button button) {
        button.setStyle("-fx-background-color: #042C7D;");
        button.setTextFill(Color.color(1, 1, 1));
    }

    public static TextField addInput(VBox box, String label) {
        Util.addLabelToBox(box, label);
        TextField labelField = new TextField();
        box.getChildren().add(labelField);
        return labelField;
    }

    public static VBox generateModal(StackPane screen, Button button) {
        VBox modal = new VBox();
        ImageView close = Util.loadIcon("src/main/resources/close.png");

        assert close != null;
        close.setOnMouseClicked(e -> {
            screen.getChildren().remove(modal);
            Util.setButtonStyleUnclicked(button);
        });
        close.setStyle("-fx-cursor: hand;");
        modal.getChildren().add(close);

        modal.setStyle("-fx-background-color: #042C7D;");
        modal.setMaxWidth(500);
        modal.setMaxHeight(500);

        screen.getChildren().add(modal);

        return modal;
    }

    public static VBox generateModal(StackPane screen, Button button, int width, int height) {
        VBox modal = new VBox();
        ImageView close = Util.loadIcon("src/main/resources/close.png");

        assert close != null;
        close.setOnMouseClicked(e -> {
            screen.getChildren().remove(modal);
            Util.setButtonStyleUnclicked(button);
        });
        close.setStyle("-fx-cursor: hand;");
        modal.getChildren().add(close);

        modal.setStyle("-fx-background-color: #042C7D;");
        modal.setMaxWidth(width);
        modal.setMaxHeight(height);

        screen.getChildren().add(modal);

        return modal;
    }

    public static void addLabelToBox(VBox modal, String label) {
        Label labelTitle = new Label(label);
        labelTitle.setTextFill(Color.color(1,1,1));
        modal.getChildren().add(labelTitle);
    }

    public static Button generateButton(String action) {
        Button button = new Button(action);
        button.setStyle("-fx-background-color: #FE9C33; -fx-cursor: hand;");
        button.setTextFill(Color.color(0, 0, 0));
        return button;
    }

    public static void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Actualiza un archivo serializable
    public static void updateSer(Object obj, String ruta)throws IOException{
        ObjectOutputStream ser = new ObjectOutputStream(new FileOutputStream(ruta));
        ser.writeObject(obj);
        ser.close();
    }
    
    // Busca un archivo serializable
    public static Object getSer(String ruta)throws Exception{
        Object obj=null;
        ObjectInputStream ser2 = new ObjectInputStream(new FileInputStream(ruta));
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
    
    public static ImageView loadView(String ruta, int ancho){
        ImageView image = new ImageView();
        try{
            FileInputStream input = new FileInputStream(ruta);
            Image img = new Image(input, ancho, ancho,false,false);
            image.setImage(img);
        }
        catch(IOException e){e.printStackTrace();}
        return image;
    }

    public static void addShowableInfoToBox(VBox modal, String fecha, String string) {
        Util.addLabelToBox(modal, fecha);
        Util.addLabelToBox(modal, string);
    }
}
