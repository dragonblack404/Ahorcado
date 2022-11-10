package ahorcado.partidas;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class PartidaControler implements Initializable {
	
	@FXML
	private BorderPane rootBorderPane;

	@FXML
	private HBox gameHbox;

	@FXML
	private TextField datosTextField;

	@FXML
	private Button letraButton;

	@FXML
	private Button resolverButton;

	@FXML
	private VBox gameVbox;

	@FXML
	private ImageView ahorcadoViiew;

	@FXML
	private Label palabraLabel;

	@FXML
	private Label letrasLabael;

	@FXML
	private GridPane infoGridpane;
	
	@FXML
	private Label jugadorLabel;

	@FXML
	private Label pointJugadorLabel;

	@FXML
	private Label vidasLabel;

	private PartidaModel model = new PartidaModel();

	public PartidaControler() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PartidaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		palabraLabel.textProperty().bind(model.ocultaProperty());
		letrasLabael.textProperty().bind(model.falladasLetrasProperty());
		Bindings.bindBidirectional(vidasLabel.textProperty(),model.vidasProperty(),new NumberStringConverter());
		model.textoProperty().bind(datosTextField.textProperty());
		Bindings.bindBidirectional(pointJugadorLabel.textProperty(),model.pointProperty(),new NumberStringConverter());
		ahorcadoViiew.imageProperty().bind(model.imagenProperty());
	
		model.getLista().addAll(new Image(getClass().getResource("/imagenes/9.png").toString()),
				new Image(getClass().getResource("/imagenes/8.png").toString()),
				new Image(getClass().getResource("/imagenes/7.png").toString()),
				new Image(getClass().getResource("/imagenes/6.png").toString()),
				new Image(getClass().getResource("/imagenes/5.png").toString()),
				new Image(getClass().getResource("/imagenes/4.png").toString()),
				new Image(getClass().getResource("/imagenes/3.png").toString()),
				new Image(getClass().getResource("/imagenes/2.png").toString()),
				new Image(getClass().getResource("/imagenes/1.png").toString()));
		model.setVidas(model.getLista().size());							
	}
 
	@FXML
	private void onLetraButtonAction(ActionEvent event) {
		boolean flag=false;
		boolean adivinada=true;
		String palabra="";
		char[] oculta=model.getOculta().toCharArray();
		if(model.getTexto().length()==1 && model.getTexto()!=null) {
			for (int i = 0; i < model.getPalabra().length(); i++) {
				System.out.println(model.getTexto().toUpperCase()+" "+model.getPalabra().charAt(i));
				if(model.getTexto().toUpperCase().charAt(0)==model.getPalabra().charAt(i)) {
					if(oculta[i]=='_')
					model.setPoint(model.getPoint()+1);
					oculta[i]=model.getTexto().charAt(0);
					palabra+=model.getTexto().toUpperCase();
					flag=true;
				} else {palabra+=oculta[i]; adivinada=false; }
			}
			
			model.setOculta(palabra);
			if (flag==false ) {
				if(!model.getFalladasLetras().contains(model.getTexto()))
				model.setFalladasLetras(model.getFalladasLetras()+model.getTexto());
				vida();
				}
			if (adivinada==true) {
				if (model.getPalabrasJuego().size()>0) palabraNext();
			else ganador();}
		} else  if (model.getTexto().length()>0 && model.getTexto().length()<1)pierde();
	}

	

	@FXML
	private void onResolverButtonAction(ActionEvent event) {
		boolean adivinada=true;
		String palabra="";
	
			 if(model.getTexto()!=null && model.getPalabra().length()==model.getTexto().length() ) {
					char[] oculta=model.getOculta().toCharArray();
				 for (int i = 0; i < model.getPalabra().length(); i++) {
						if(model.getTexto().toUpperCase().charAt(i)==model.getPalabra().charAt(i) && oculta[i]=='_') {
							oculta[i]=model.getTexto().charAt(0);
							model.setPoint(model.getPoint()+2);
							palabra+=model.getTexto().toUpperCase();
						} else 
							if(oculta[i]=='_') {
								adivinada=false;
							}	
					}
			 } else adivinada=false;
				if (adivinada==true) {
					if (model.getPalabrasJuego().size()>0) palabraNext();
					else ganador();
				} else pierde();
	}

	private void pierde() {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Ahorcado APP");
		alert.setHeaderText("Vaya parece que no has acertado "+model.getPalabra());
		alert.setContentText("El juez declina tu absolución");
		alert.showAndWait();
		model.setEnJuego(false);
	}

	private void ganador() {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Ahorcado APP");
		alert.setHeaderText("Vaya parece que el juez no tiene más palabras ");
		alert.setContentText("El juez acepta tu absolución. Eres libre.");
		alert.showAndWait();
		model.setEnJuego(false);
		
	}

	public BorderPane getView() {
		return rootBorderPane;
	}

	public PartidaModel getModel() {
		return model;
	}

	// LOGIC
	private String formateaWord(String palabra) {
		String ocultada = "";
		model.setPalabra(palabra);
		char[] caracteres = palabra.toCharArray();
		for (int i = 0; i < caracteres.length; i++) {
			if (caracteres[i] != ' ') {
				ocultada += "_";
			} else
				ocultada += " ";
		}
		return ocultada;
	}

	public void palabraNext() {
		int aleatorio = (int) (Math.random() * model.getPalabrasJuego().size());
		model.setPalabra(model.getPalabrasJuego().get(aleatorio));
		System.out.println(model.getPalabra());
		model.getPalabrasJuego().remove(aleatorio);
		model.setOculta(formateaWord(model.getPalabra()));
		
	}


	private void vida() {
		if (model.getVidas()>0) {
		model.setVidas(model.getVidas()-1);
		model.setImagen(model.getLista().get(model.getVidas()));
		} 
		
		if (model.getVidas()==0)
		pierde();
	}


}
