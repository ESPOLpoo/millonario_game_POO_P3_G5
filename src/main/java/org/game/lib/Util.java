package org.game.lib;

import java.io.FileInputStream;

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

    public static void addCellToGrid(GridPane grid, String text, int col, int row) {
        Label content = new Label(text);
        content.setTextFill(Color.color(1,1,1));
        grid.add(content, col, row);
    }

    public static void removeModal(StackPane screen, VBox modal, Button button) {
        screen.getChildren().remove(modal);
        Util.setButtonStyleUnclicked(button);
    }
}
