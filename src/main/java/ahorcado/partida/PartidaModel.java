package ahorcado.partida;

import java.util.Optional;

import ahorcado.AhorcadoApp;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

public class PartidaModel {
	
	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());

	private String nombre;
	private String palabraElegidaBasica;
	private StringProperty palabraElegida = new SimpleStringProperty("");

	private IntegerProperty puntosGanados = new SimpleIntegerProperty();
	private IntegerProperty puntosRestantes = new SimpleIntegerProperty();

	private StringProperty intento = new SimpleStringProperty();
	private StringProperty letrasProbadas = new SimpleStringProperty();
	private StringProperty palabraElegidaEscondida = new SimpleStringProperty("");
	
	private BooleanProperty disableButtons = new SimpleBooleanProperty(false);
	private BooleanProperty gameOver = new SimpleBooleanProperty();
	
	private ObjectProperty<Image> imagen = new SimpleObjectProperty<Image>();
	
	
	private int numFile;
	
	public void cargarDatos() {
		
		setPalabraElegidaBasica();
		setPalabraElegidaEscondida(esconderPalabra());
		setPuntosRestantes(puntosPosibles());
		buildPalabraElegida();
		setLetrasProbadas("");
	}
	
	public void buildPalabraElegida() {
		int i;
		for(i = 0; i < getPalabraElegidaBasica().length()-1; i++) {
			addPalabraElegida(getPalabraElegidaBasica().charAt(i) + " ");
		}
		addPalabraElegida(getPalabraElegidaBasica().charAt(i) + "");
	}

	public void setPalabraElegidaBasica() {
		int random =(int) (Math.random() * palabras.getSize());
		palabraElegidaBasica = palabras.get(random);
	}	
		
	protected String esconderPalabra() {
    	String result = "";
    	for(int i = 0; i < getPalabraElegidaBasica().length()-1; i++) {
    		if(Character.isLetter(getPalabraElegidaBasica().charAt(i))) {
    			result += "_ ";
    		} else if(Character.isSpaceChar(getPalabraElegidaBasica().charAt(i))) {
    			result += "  ";
    		}
    	}
    	result += "_ ";
    	return result;
    }
	
	private int puntosPosibles() {
    	int result = 0;
    	for(int i = 0; i < getPalabraElegidaBasica().length(); i++) {
    		if(Character.isLetter(getPalabraElegidaBasica().toUpperCase().charAt(i))) {
    			result++;
    		}
    	}
    	return result;
    }
	
	protected void comprobarLetra(char intento) {
		if(getPalabraElegida().contains(intento + "")) {
			String aux = "";
			for(int i = 0; i < getPalabraElegida().length(); i++) {
				if(getPalabraElegida().charAt(i) == intento) {
					aux += getPalabraElegida().charAt(i);
					incrementPuntosGanados(1);
					decrementarPuntosRestantes(1);
				} else {
					aux += getPalabraElegidaEscondida().charAt(i);
				}
			}
			setPalabraElegidaEscondida(aux);
			if(getPalabraElegida().equals(getPalabraElegidaEscondida())) {
				addLetrasProbadas(Character.toUpperCase(intento) + " ");
				win();
			}
		}
		else {
			addLetrasProbadas(Character.toUpperCase(intento) + " ");
			fail();
		}
		
	}
	
    protected void fail() {
    	if(getClass().getResource("/images/" + getNumFile() + ".png") != null)
    		setImagen(new Image(getClass().getResource("/images/" + getNumFile() + ".png").toString()));
    	
    	incrementNumFile();
    	if(getClass().getResource("/images/" + getNumFile() + ".png") == null) {
    		loose();
    	}
    	
    }
    
    private void loose() {
    	
    	TextInputDialog dialog = new TextInputDialog();
		dialog.initOwner(AhorcadoApp.primaryStage);
		dialog.setTitle("DERROTA");
		dialog.setHeaderText("Has perdido, danos tu nombre: ");
		dialog.setContentText("Nombre:");
    	
    	Optional<String> nombre = dialog.showAndWait();
    	endGame(nombre);
    }
    
    protected void win() {
    	
    	setPuntosGanados(getPuntosGanados() + getPuntosRestantes());
    	setPalabraElegidaEscondida("");
    	setPalabraElegida("");
    	palabraElegidaBasica = "";
    	
    	cargarDatos();
    	
    }
    
    private void endGame(Optional<String> nombre) {
    	if(nombre.isPresent() && !nombre.get().isBlank()) {
    		setNombre(nombre.get().trim());
	    	setGameOver(true);
    	}
    	setDisableButtons(true);
    }
	
	public final StringProperty intentoProperty() {
		return this.intento;
	}
	public final String getIntento() {
		return this.intentoProperty().get();
	}
	public final void setIntento(final String intento) {
		this.intentoProperty().set(intento);
	}
	
	public final StringProperty letrasProbadasProperty() {
		return this.letrasProbadas;
	}
	public final String getLetrasProbadas() {
		return this.letrasProbadasProperty().get();
	}
	public final void setLetrasProbadas(final String letrasProbadas) {
		this.letrasProbadasProperty().set(letrasProbadas);
	}
	public final void addLetrasProbadas(final String letrasProbadas) {
		letrasProbadasProperty().set(getLetrasProbadas() != null ? getLetrasProbadas() + letrasProbadas:letrasProbadas);
	}
	
	public final IntegerProperty puntosGanadosProperty() {
		return this.puntosGanados;
	}
	public final int getPuntosGanados() {
		return this.puntosGanadosProperty().get();
	}
	public final void setPuntosGanados(final int puntosGanados) {
		this.puntosGanadosProperty().set(puntosGanados);
	}
	public final void incrementPuntosGanados(int puntos) {
		setPuntosGanados(getPuntosGanados() + puntos);
	}
	
	public final BooleanProperty disableButtonsProperty() {
		return this.disableButtons;
	}
	public final boolean isDisableButtons() {
		return this.disableButtonsProperty().get();
	}
	public final void setDisableButtons(final boolean disableButtons) {
		this.disableButtonsProperty().set(disableButtons);
	}
	
	public final BooleanProperty gameOverProperty() {
		return this.gameOver;
	}
	public final boolean isGameOver() {
		return this.gameOverProperty().get();
	}
	public final void setGameOver(final boolean ganado) {
		this.gameOverProperty().set(ganado);
	}
	
	public final ObjectProperty<Image> imagenProperty() {
		return this.imagen;
	}
	public final Image getImagen() {
		return this.imagenProperty().get();
	}
	public final void setImagen(final Image imagen) {
		this.imagenProperty().set(imagen);
	}
	
	public int getNumFile() {
		return numFile;
	}
	public void setNumFile(int numFile) {
		this.numFile = numFile;
	}
	public void incrementNumFile() {
		numFile++;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public final StringProperty palabraElegidaEscondidaProperty() {
		return this.palabraElegidaEscondida;
	}
	public final String getPalabraElegidaEscondida() {
		return this.palabraElegidaEscondidaProperty().get();
	}
	public final void setPalabraElegidaEscondida(final String palabraElegidaEscondida) {
		this.palabraElegidaEscondidaProperty().set(palabraElegidaEscondida);
	}
	

	public final StringProperty palabraElegidaProperty() {
		return this.palabraElegida;
	}
	public final String getPalabraElegida() {
		return this.palabraElegidaProperty().get();
	}
	public final void setPalabraElegida(final String palabraElegida) {
		this.palabraElegidaProperty().set(palabraElegida);
	}
	public final void addPalabraElegida(final String c) {
		setPalabraElegida(getPalabraElegida() + c);
	}
	
	public final ListProperty<String> palabrasProperty() {
		return this.palabras;
	}
	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}
	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
	}
	
	public String getPalabraElegidaBasica() {
		return palabraElegidaBasica;
	}
	
	public final IntegerProperty puntosRestantesProperty() {
		return this.puntosRestantes;
	}
	public final int getPuntosRestantes() {
		return this.puntosRestantesProperty().get();
	}
	public final void setPuntosRestantes(final int puntosRestantes) {
		this.puntosRestantesProperty().set(puntosRestantes);
	}
	public void decrementarPuntosRestantes(int decremento) {
		setPuntosRestantes(getPuntosRestantes() - decremento);
	}
	
}
