package com.example.pantalla_game;


import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import util.Chronometer;

import java.io.*;
import java.util.ArrayList;

public class GameController {
    @FXML
    private Button Q1;

    @FXML
    private Button Q2;

    @FXML
    private Button Q3;

    @FXML
    private Button Q4;

    @FXML
    private Label question;

    @FXML
    private Label clockSection;

    @FXML
    private HBox mainOrganizator;

    @FXML
    private VBox money;

    @FXML
    private HBox optionSection;

    @FXML
    private VBox optionSectionLeft;

    @FXML
    private VBox optionSectionRight;

    @FXML
    private ImageView questionBackground;

    @FXML
    private StackPane questionBlock;

    @FXML
    private GridPane questionList;

    @FXML
    private HBox questionListSeparator;

    @FXML
    private VBox questionPart;

    @FXML
    private VBox questions;

    @FXML
    private HBox wilcards;

    @FXML
    private Label wildcardLabel;

    @FXML
    private VBox wildcardPart;

    @FXML
    private VBox wildcardPartSeparator;

    @FXML
    private ProgressIndicator chronometer;

    @FXML
    private StackPane pb;

    @FXML
    private VBox quests;

    @FXML private Circle circle;

    private Label lblProgress = new Label();

    private static String PATH = "src/main/resources/";

    public void initialize() throws IOException{
        question.setText("PREGUNTA");

        ArrayList<String> info = readFile(PATH + "files/quests.txt");

        int x = 1;
        for (String i : info){
            if(x%2!=0){
                addCellToGrid(questionList,i,0,x);
            } else {
                addCellToGrid(questionList,i,1,x-1);
            }
            x++;
        }



        chronometer.setProgress(0.55);
        chronometer.setMinSize(190,190);
        chronometer.setStyle("-fx-progress-color:#F2953B;");

        circle.setScaleX(3.2);
        circle.setScaleY(3.2);
        circle.setLayoutY(-2);
        circle.setFill(Color.rgb(41,50,67));

        Chronometer c = new Chronometer(chronometer);
        c.start();
    }

    public static void addCellToGrid(GridPane grid, String text, int col, int row) {
        Label content = new Label(text);
        content.setFont(new Font(15));
        content.setTextFill(Color.rgb(236,150,62));
        grid.add(content, col, row);
        GridPane.setHalignment(content, HPos.CENTER);
    }

    public ArrayList<String> readFile(String path){
        ArrayList<String> info = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))){
            String line;
            while((line = buffer.readLine())!=null){
                String[] quests = line.split("-");
                info.add(quests[0]);
                info.add(quests[1]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return info;
    }

}
