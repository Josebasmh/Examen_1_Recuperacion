package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TallerController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private CheckBox cbDisponible;

    @FXML
    private TableColumn<?, ?> tcCodigo;

    @FXML
    private TableColumn<?, ?> tcDisponible;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcPrecio;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TableView<?> tvTabla;

    @FXML
    void actualizarProducto(ActionEvent event) {

    }

    @FXML
    void crearProducto(ActionEvent event) {

    }

    @FXML
    void seleccionarProducto(MouseEvent event) {

    }

}
