package org.game;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import org.game.lib.Util;
import org.game.model.data.Materia;
import org.game.model.data.Paralelo;

public class MateriasParalelosController {

    @FXML private StackPane screen;
    @FXML private GridPane gridMaterias;
    @FXML private GridPane gridParalelos;
    @FXML private Button btnAgregarMateria;
    @FXML private Button btnEditarMateria;
    @FXML private Button btnAgregarParalelo;
    @FXML private Button btnEliminarParalelo;

    @FXML
    public void initialize()  {
        cargarMaterias();
        cargarParalelos();
    }

    @FXML
    void returnPrimary(MouseEvent event) throws IOException {
        App.setRoot("configuracion");
    }

    public void cargarMaterias() {
        int i = 1;
        for (Materia materia: Materia.materias) {
            Util.addCellToGrid(gridMaterias, materia.getCodigo(), 0, i);
            Util.addCellToGrid(gridMaterias, materia.getNombre(), 1, i);
            Util.addCellToGrid(gridMaterias, String.valueOf(materia.getNiveles()), 2, i);
            i++;
        }
    }

    public void cargarParalelos() {
        int i = 1;
        for (Paralelo paralelo: Paralelo.paralelos) {
            Util.addCellToGrid(gridParalelos, paralelo.getMateria().getNombre(), 0, i);
            Util.addCellToGrid(gridParalelos, paralelo.getTermino().toString(), 1, i);
            Util.addCellToGrid(gridParalelos, String.valueOf(paralelo.getNumero()), 2, i);
            i++;
        }
    }

    public void ingresarMateria() {
        Util.setButtonStyleClicked(btnAgregarMateria);
        VBox agregarMateria = Util.generateModal(screen, btnAgregarMateria);

        TextField codigo = Util.addInput(agregarMateria, "Ingrese el código de la materia:");
        TextField nombre = Util.addInput(agregarMateria, "Ingrese el nombre de la materia:");
        TextField niveles = Util.addInput(agregarMateria, "Ingrese el número de niveles:");

        Button agregar = Util.generateButton("Agregar");
        agregar.setOnMouseClicked(e -> {
            if (codigo.getText().isEmpty() || nombre.getText().isEmpty() || niveles.getText().isEmpty()) { // TODO: validar correctamente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al ingresar la materia");
                alert.setContentText("Por favor, ingrese todos los datos");
                alert.showAndWait();
            } else {
                System.out.println("Agregando materia...");
                Util.removeModal(screen, agregarMateria, btnAgregarMateria);
            }

        });
        agregarMateria.getChildren().add(agregar);
    }

    public void editarMateria() {
        Util.setButtonStyleClicked(btnEditarMateria);
        VBox editarMateria = Util.generateModal(screen, btnEditarMateria);

        Util.addLabelToBox(editarMateria, "Selecciona la forma de editar la materia:");
        ChoiceBox editarMateriaOpciones = new ChoiceBox();
        editarMateriaOpciones.getItems().add("Editar usando código");
        editarMateriaOpciones.getItems().add("Editar usando nombre");

        editarMateria.getChildren().add(editarMateriaOpciones);

        VBox div = new VBox();
        editarMateria.getChildren().add(div);

        AtomicReference<TextField> field = new AtomicReference<>(new TextField());

        AtomicReference<TextField> nuevoNombre = new AtomicReference<>(new TextField());
        AtomicReference<TextField> nuevoNumeroNiveles = new AtomicReference<>(new TextField());

        editarMateriaOpciones.setOnAction(e -> {
            div.getChildren().clear();
            if (editarMateriaOpciones.getValue().equals("Editar usando código")) {
                field.set(Util.addInput(div, "Ingrese el código de la materia:"));
            } else {
                field.set(Util.addInput(div, "Ingrese el nombre de la materia:"));
            }
            nuevoNombre.set(Util.addInput(div, "Ingrese el nuevo nombre de la materia:"));
            nuevoNumeroNiveles.set(Util.addInput(div, "Ingrese el nuevo número de niveles:"));
        });

        Button editar = Util.generateButton("Editar");
        editar.setOnMouseClicked(e -> {
            if (editarMateriaOpciones.getValue().equals("Editar usando código")) {
                if (field.get().getText().isEmpty()) { // TODO: validar que el código exista
                    Util.showAlert("Error al editar la materia", "Por favor, ingrese el código de la materia");
                } else {
                    System.out.println("Editando materia...");
                    Util.removeModal(screen, editarMateria, btnEditarMateria);
                }
            } else {
                if (field.get().getText().isEmpty()) { // TODO: validar que el nombre exista
                    Util.showAlert("Error al editar la materia", "Por favor, ingrese el nombre de la materia");
                } else {
                    System.out.println("Editando materia...");
                    Util.removeModal(screen, editarMateria, btnEditarMateria);
                }
            }
        });

        editarMateria.getChildren().add(editar);
    }

    public void agregarParalelo() {
        Util.setButtonStyleClicked(btnAgregarParalelo);
        VBox agregarParalelo = Util.generateModal(screen, btnAgregarParalelo);

        TextField materia = Util.addInput(agregarParalelo, "Ingrese el nombre de la materia:");
        TextField termino = Util.addInput(agregarParalelo, "Ingrese el término académico:");
        TextField numero = Util.addInput(agregarParalelo, "Ingrese el número de paralelo:");

        Util.addLabelToBox(agregarParalelo, "Seleccione el archivo con los estudiantes:");
        Button addFile = Util.generateButton("Seleccionar archivo");

        VBox div = new VBox();

        AtomicReference<File> file = new AtomicReference<>(null);

        addFile.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar archivo");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
            );

            file.set(fileChooser.showOpenDialog(null));

            if (file.get() != null) {
                div.getChildren().clear();
                Util.addLabelToBox(div, "Archivo seleccionado: " + file.get().getName());
            }
        });

        agregarParalelo.getChildren().add(addFile);
        agregarParalelo.getChildren().add(div);

        Button agregar = Util.generateButton("Agregar");

        VBox archivoContainer = new VBox();
        agregarParalelo.getChildren().add(archivoContainer);

        AtomicReference<TextField> rutaArchivo = new AtomicReference<>(new TextField());
        agregar.setOnMouseClicked(e -> {
            if (file.get() == null && rutaArchivo.get().getText().isEmpty()) {
                Util.showInfo("No se ha seleccionado un archivo", "No has seleccionado un archivo\nEn tal caso, escribe la ruta del archivo para subirlo posteriormente");
                archivoContainer.getChildren().clear();
                rutaArchivo.set(Util.addInput(archivoContainer, "Ingrese la ruta del archivo:"));
            } else { // TODO: manage materia add
                System.out.println("Agregando paralelo...");
                Util.removeModal(screen, agregarParalelo, btnAgregarParalelo);
            }
        });

        agregarParalelo.getChildren().add(agregar);
    }

    public void eliminarParalelo() {
        Util.setButtonStyleClicked(btnEliminarParalelo);
        VBox eliminarParalelo = Util.generateModal(screen, btnEliminarParalelo);

        TextField materia = Util.addInput(eliminarParalelo, "Ingrese el nombre de la materia:");
        TextField termino = Util.addInput(eliminarParalelo, "Ingrese el término académico:");
        TextField numero = Util.addInput(eliminarParalelo, "Ingrese el número de paralelo:");

        Button eliminar = Util.generateButton("Eliminar");
        eliminar.setOnMouseClicked(e -> {
            if (materia.getText().isEmpty() || termino.getText().isEmpty() || numero.getText().isEmpty()) { // TODO: validar que el paralelo exista
                Util.showAlert("Error al eliminar el paralelo", "Por favor, ingrese todos los datos");
            } else {
                System.out.println("Eliminando paralelo...");
                Util.removeModal(screen, eliminarParalelo, btnEliminarParalelo);
            }
        });

        eliminarParalelo.getChildren().add(eliminar);
    }
}