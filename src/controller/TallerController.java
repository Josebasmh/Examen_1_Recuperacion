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
import javafx.scene.control.MenuItem;
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

    /**
     * Aarece una ventana auxiliar que muestra la versión y el nombre del alumno
     * @param event
     */
    @FXML
    void acercaDe(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	String mensaje="Version: V1.0\nAutor: Joseba Martinez";
    	alert.setTitle("ACERCA DE");
    	alert.setHeaderText("EXAMEN DE RECU");
    	alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    /**
     * Comprueba que los campos estén correctamente y si es así,modifica el registro seleccionado.
     * @param event
     */
    @FXML
    void actualizarProducto(ActionEvent event) {
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
        	dao.actualizarTaller(new Taller(tfCodigo.getText(), tfNombre.getText(), Float.valueOf(tfPrecio.getText()) , bdisponible));
        	tvTabla.setItems(dao.cargarDatos());
    	}catch (Exception e) {    		
    		ventanaAlerta("E", e.getMessage());
    	}
    	btnActualizar.setDisable(false);
    }

    /**
     * Añade el registro a la BBDD y actualiza la tabla.
     * @param event
     */
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
    	btnCrear.setDisable(false);
    }

	/**
	 * carga en los campos superiores el registro que seleccionamos en la tabla.
	 * @param event
	 */
	@FXML
    void seleccionarProducto(MouseEvent event) {
		btnActualizar.setDisable(false);
		btnCrear.setDisable(true);
    	Taller t =tvTabla.getSelectionModel().getSelectedItem();
    	tfCodigo.setText(t.getCodigo().toString());
    	tfCodigo.setDisable(true);
    	tfNombre.setText(t.getNombre().toString());
    	tfPrecio.setText(t.getPrecio().toString());
    	cbDisponible.setSelected(t.isDisponible());
    }
	
	/**
	 * Modifica los campos superiores al estado inicial.
	 * @param event
	 */
    @FXML
    void limpiar(ActionEvent event) {
    	btnActualizar.setDisable(true);
    	btnCrear.setDisable(false);
    	tfCodigo.setDisable(false);
    	
    	tfCodigo.setText("");
    	tfNombre.setText("");
    	tfPrecio.setText("");
    	cbDisponible.setSelected(false);
    }

    /**
     * Al iniciar la ventana, se sincronizan los campos con la clase y se les insertan 
     * los registros de la BBDD.
     */
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
	
	/**
	 * Comprueba los datos de los campos de la parte superior.
	 * @return mensaje de error.
	 */
	private ObservableList<String> comprobarCampos() {
		ObservableList<String> camposNulos = FXCollections.observableArrayList();
    	if (tfCodigo.getText().length()!=5) {camposNulos.add("El campo Codigo debe contener 5 carácteres.\n");}
    	if (tfNombre.getText().isEmpty()) {camposNulos.add("El campo Nombre está vacío.\n");}
    	if (tfPrecio.getText().isEmpty()) {camposNulos.add("El campo Precio está vacío.\n");}
    	return camposNulos;
	}
	
	/**
	 * Comprueba si el campo precio contiene solo carácteres numéricos.
	 * @return mensaje de error
	 */
	private String comprobarPrecio() {
		if (!tfPrecio.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
    		return "El campo Precio debe ser un decimal.\n";
    	}else {
    		return "";
    	}
	}
	
	/**
	 * Crea ventana auxiliar que puede ser de tipo Error/Información
	 * @param tipoAlerta "E" / "I"
	 * @param mensaje 
	 */
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
