package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.TallerDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Taller;

public class TallerController implements Initializable{

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private CheckBox cbDisponible;

    @FXML
    private TableColumn<Taller, String> tcCodigo;

    @FXML
    private TableColumn<Taller, Boolean> tcDisponible;

    @FXML
    private TableColumn<Taller, String> tcNombre;

    @FXML
    private TableColumn<Taller, Float> tcPrecio;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TableView<Taller> tvTabla;
    
    //Variables de clase\\
    private TallerDao dao = new TallerDao();

    @FXML
    void actualizarProducto(ActionEvent event) {

    }

    @FXML
    void crearProducto(ActionEvent event) {

    }

    @FXML
    void seleccionarProducto(MouseEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcCodigo.setCellValueFactory(new PropertyValueFactory<Taller, String>("codigo"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<Taller, String>("nombre"));
		tcPrecio.setCellValueFactory(new PropertyValueFactory<Taller, Float>("precio"));
		tcDisponible.setCellValueFactory(new PropertyValueFactory<Taller, Boolean>("disponible"));
		
		btnActualizar.setDisable(true);
		
		ObservableList<Taller>listaTaller = dao.cargarDatos();
		tvTabla.setItems(listaTaller);
		
	}

}
