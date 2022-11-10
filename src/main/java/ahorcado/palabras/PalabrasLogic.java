package ahorcado.palabras;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PalabrasLogic {

	
	private static final String FICHERO="palabras.txt";
	
	public static  void guardar( List<String> palabras) throws IOException {
		
		System.out.println("guardando las palabras en el fichero");
		
		File carpeta = new File(System.getProperty("user.home"), ".Ahorcado");
		if (!carpeta.exists()) carpeta.mkdir();
		
		File fichero = new File(carpeta, FICHERO);
				
		Files.write(fichero.toPath(), palabras);
		System.out.println("GUARDADO COMPLETADO" );
		
	}

	public static List<String> cargar() throws IOException {
		
		System.out.println("cargar las palabras desde el fichero");

		File carpeta = new File(System.getProperty("user.home"), ".Ahorcado");
		File fichero = new File(carpeta, FICHERO);
		if (fichero.exists()) {
			System.out.println("CARGADO COMPLETADO");
			return Files.readAllLines(fichero.toPath());
			
		}
		System.out.println("NO HAY DATOS QUE CARGAR");
		return new ArrayList<>();
		
	}

	
}
