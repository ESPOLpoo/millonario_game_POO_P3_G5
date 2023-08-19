package org.game.lib;

import java.io.FileInputStream;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    public static void setButtonStyleClicked(Button button) {
        button.setStyle("-fx-background-color: #FE9C33;");
        button.setTextFill(Color.color(0, 0, 0));
    }

    public static void setButtonStyleUnclicked(Button button) {
        button.setStyle("-fx-background-color: #042C7D;");
        button.setTextFill(Color.color(1, 1, 1));
    }

    public static void addInput(VBox box, String label) {
        Label codigoLabel = new Label(label);
        codigoLabel.setTextFill(Color.color(1,1,1));
        box.getChildren().add(codigoLabel);
        TextField codigo = new TextField();
        box.getChildren().add(codigo);
    }
}
