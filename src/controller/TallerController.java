package controller;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import dao.TallerDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    	btnActualizar.setDisable(true);
    	try {
    		ObservableList<String>camposNulos = comprobarCampos();
    		String campoPrecio = comprobarPrecio();        	
    		if (!camposNulos.isEmpty() || !campoPrecio.isEmpty()) {
        		Iterator<String> it = camposNulos.iterator();
        		String msg="";
        		while(it.hasNext()) {
        			msg= msg + it.next();
        		}
        		if(!campoPrecio.isEmpty()) {
        			msg += campoPrecio;
        		}
        		throw new NullPointerException(msg);
        	}
        	boolean bdisponible= Boolean.getBoolean(cbDisponible.getText()); 
        	dao.aniadirTaller(new Taller(tfCodigo.getText(), tfNombre.getText(), Float.valueOf(tfPrecio.getText()) , bdisponible));
        	tvTabla.setItems(dao.cargarDatos());
    	}catch (Exception e) {    		
    		ventanaAlerta("E", e.getMessage());
    	}
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
	
	private ObservableList<String> comprobarCampos() {
		ObservableList<String> camposNulos = FXCollections.observableArrayList();
    	if (tfCodigo.getText().length()!=5) {camposNulos.add("El campo Codigo debe contener 5 carácteres.\n");}
    	if (tfNombre.getText().isEmpty()) {camposNulos.add("El campo Nombre está vacío.\n");}
    	if (tfPrecio.getText().isEmpty()) {camposNulos.add("El campo Precio está vacío.\n");}
    	return camposNulos;
	}
	
	private String comprobarPrecio() {
		if (!tfPrecio.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
    		return "El campo Precio debe ser un decimal.\n";
    	}else {
    		return "";
    	}
	}
	
    private void ventanaAlerta(String tipoAlerta, String mensaje) {
		Alert alert = null;
		switch (tipoAlerta) {
			case ("E"):
				alert = new Alert(Alert.AlertType.ERROR);
				break;
			case ("I"):
				alert = new Alert(Alert.AlertType.INFORMATION);
		}
        alert.setContentText(mensaje);
        alert.showAndWait();
	}

}
