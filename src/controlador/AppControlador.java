package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import aplicacao.App;
import gui.util.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modelo.basico.Pessoa;

public class AppControlador implements Initializable{

	
	@FXML
	private MenuItem menuPessoa;
	
	@FXML
	private MenuItem menuFamilia;
	
	@FXML
	private MenuItem menuAtendimento;

	@FXML
	private MenuItem menuAjuda;
	@FXML
	private void clicarMenuPessoa() {
		//Utilizando a mesma func�o rodarTela vamos incluir um novo par�metro que � uma fun��o lambda
		rodarTela("../gui/listaPessoa.fxml",(ListaPessoaControlador controlador)-> {
			controlador.setPessoa(new Pessoa());
			controlador.carregarPessoas();
		});
	}
	@FXML
	private void clicarMenuFamilia() {
		System.out.println("familia");
	}
	@FXML
	private void clicarMenuAtendimento() {
		System.out.println("atendimento");
	}
	
	@FXML
	private void clicarMenuSobre() {
		rodarTela("../gui/sobre.fxml", x->{});
		System.out.println("Sobre");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
	}
	
	private synchronized <T> void rodarTela(String arquivoFXML, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(arquivoFXML));
			VBox novoVBox = loader.load();
			
			Scene cena = App.getCena();
			VBox appVBox = (VBox) ((ScrollPane) cena.getRoot()).getContent();
			
			Node appMenu = appVBox.getChildren().get(0);
			appVBox.getChildren().clear();
			appVBox.getChildren().add(appMenu);
			appVBox.getChildren().addAll(novoVBox.getChildren());
			T controlador = loader.getController();
			initializingAction.accept(controlador);
		} catch (IOException e) {
			Alerta.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	/*	private synchronized void rodarTela2(String arquivoFXML) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(arquivoFXML));
				VBox novoVBox = loader.load();
				
				Scene cena = App.getCena();
				VBox appVBox = (VBox) ((ScrollPane) cena.getRoot()).getContent();
				
				Node appMenu = appVBox.getChildren().get(0);
				appVBox.getChildren().clear();
				appVBox.getChildren().add(appMenu);
				appVBox.getChildren().addAll(novoVBox.getChildren());
				
				ListaPessoaControlador controlador = loader.getController();
				controlador.setPessoa(new Pessoa());
				controlador.carregarPessoas();
			} catch (IOException e) {
				Alerta.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
				e.printStackTrace();
			}
		}*/

}