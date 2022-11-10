package ahorcado.puntos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuntosLogic {
	private final static File FICHERO = new File(System.getProperty("user.home"), "puntos.txt");
	
	private static FileReader fr;
	private static BufferedReader br;
	private static FileWriter fw;
	
	public static  void guardar( List<Puntuacion> palabras) throws IOException {
		
		System.out.println("guardando las palabras en el fichero");
	fw=new FileWriter(FICHERO);
			for (int i = 0; i < palabras.size(); i++) {
				fw.write(palabras.get(i).puntos+"-"+palabras.get(i).nombre+"\n");
			}
			fw.close();
		System.out.println("GUARDADO COMPLETADO" );
		
	}

	public static List<Puntuacion> cargar() throws IOException {
		
		System.out.println("cargar las palabras desde el fichero");

	List<Puntuacion> lista=new ArrayList<Puntuacion>();
	if (FICHERO.exists()) {
		fr=new FileReader(FICHERO);
		br=new BufferedReader(fr);
		String linea;
		 String datos[];
		 while ((linea=br.readLine())!=null) {
			 datos=linea.split("-");
			 Puntuacion pt=new Puntuacion();
			 pt.setPuntos(Integer.parseInt(datos[0]));
			 pt.setNombre(datos[1]);
			 lista.add(pt);
		 }
		 return lista;
	}
		return new ArrayList<>();
		
	}

	
}
