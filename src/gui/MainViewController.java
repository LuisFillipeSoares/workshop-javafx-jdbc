package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadview("/gui/DepartmentList.fxml"); //carregando o public void initialize
	}
	
	@FXML
	public void onMenuItemAboutAction() { 
		loadview("/gui/About.fxml"); //carregando o public void initialize
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	private synchronized void loadview (String absoluteName) { //nome da tela
		//synchronized é utilizado para que não interropa o funcionamento da tela
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); //para carregar uma tela, colocando o nome que vai vim
			VBox newVBox = loader.load(); //para carregar uma nova tela
			
			Scene mainScene = Main.getMainScene(); //buscando o metodo publicado da classe main application
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //pegando a referencia do vbox da janela principal MainView
			/*o getRoot pega o primeiro elemento da view, no caso o ScrollPane. Coloco o (ScrollPane) para o copilador entender que estou pegando ele
			o getContent pega o primeiro content, no caso o vboc. Coloco o (Vbox) para o copilador entende rque tem que pegar o vbox
			*/
			
			Node mainMenu = mainVBox.getChildren().get(0); //aqui preservo o menuBar, referencia pra ele .get(0)
			//Node é o ponto de conexão com outra tela
			
			mainVBox.getChildren().clear(); //limpo os childrens
			mainVBox.getChildren().add(mainMenu); //adiciono o main menu que referencia o menuBar
			mainVBox.getChildren().addAll(newVBox); //aqi adiciono os filhos do newVbox, que vão ser o do nova tela "absoluteName"
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR); //exceção caso o valor absoluto não seja reconhecido
		}
	}
}

