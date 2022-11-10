package ahorcado.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

public class RootController implements Initializable{

    @FXML
    private Tab palabrasTab;

    @FXML
    private Tab partidaTab;

    @FXML
    private Tab puntuacionesTab;

    @FXML
    private TabPane view;

    
    public RootController() throws IOException {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.xml"));
    		loader.setController(this);
    		loader.load();
    	
    }
    
    public TabPane getView() {
		return view;
	}
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// TODO Auto-generated method stub
    	
    }
    
}