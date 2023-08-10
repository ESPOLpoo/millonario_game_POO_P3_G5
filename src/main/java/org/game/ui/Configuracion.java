package org.game.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Configuracion {
    @FXML private ImageView returnImage;
    @FXML private Button terminosAcademicos;
    @FXML private Button materiasParalelos;
    @FXML private Button preguntas;
    @FXML private GridPane gridOpciones;
    private final String[] opcionesTerminos = {"Añadir termino", "Editar termino", "Elegir termino"};
    private final String[] opcionesMateriasParalelos = {"Ingresar materia", "Editar Materia", "Agregar Paralelo", "Eliminar Paralelo"};
    private VBox vbox;
    @FXML private StackPane stackRoot;

    @FXML
    public void initialize() {
    }

    @FXML
    public void menuTerminosAcademicos() {
        terminosAcademicos.setStyle("-fx-background-color: #FE9C33; -fx-text-fill: #FFFFFF; -fx-pref-width: 450px; -fx-pref-height: 76px;");
        vbox = new VBox();

        for (int i = 0; i < opcionesTerminos.length; i++) {
            VBox canvas = new VBox();
            canvas.setStyle("-fx-background-color: #042C7D;");
            Opcion opcion = new Opcion(opcionesTerminos[i], canvas);
            vbox.getChildren().add(opcion);
        }
        vbox.setSpacing(90);
        gridOpciones.add(vbox, 1, 0);
    }

    @FXML
    void menuMateriasYParalelos() {
        materiasParalelos.setStyle("-fx-background-color: #FE9C33; -fx-text-fill: #FFFFFF; -fx-pref-width: 450px; -fx-pref-height: 76px;");
        vbox = new VBox();
    }

    @FXML
    void returnPrimary() throws IOException {
        App.setRoot("primary");
    }

    public class Opcion extends Button {
        private VBox vboxOpcion;

        public Opcion(String nombre, VBox vboxOpcion) {
            super(nombre);
            this.vboxOpcion = vboxOpcion;
            manageClick();
            buttonStyle("-fx-background-color: #042C7D; -fx-text-fill: #FFFFFF; -fx-pref-width: 450px; -fx-pref-height: 76px; -fx-font-size: 32px; -fx-font-family: 'JetBrains Mono';");

            vboxOpcion.setMaxSize(900, 600);
            vboxOpcion.setAlignment(Pos.CENTER);
        }

        public void manageClick() {
            super.setOnAction(e -> mostrarMenu());
        }

        public void mostrarMenu() {
            stackRoot.getChildren().add(this.vboxOpcion);
        }

        public void buttonStyle(String estilos) {
            super.setStyle(estilos);
        }
    }
}