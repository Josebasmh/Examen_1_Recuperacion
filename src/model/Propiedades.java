package model;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Clase utilizada para guardar los datos de acceso a la BBDD en un archivo llamado "configuration.properties". 
 */
public class Propiedades {
	private static Properties props = new Properties();
	static {
		try (FileInputStream input = new FileInputStream("configuration.properties")){
			props.load(input);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getValor(String clave) {
		String valor=props.getProperty(clave);
		if (valor!=null) {
			return valor;
		}
		throw new RuntimeException("El fichero de configuracion no existe");
	}
}
