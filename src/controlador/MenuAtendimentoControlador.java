package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import aplicacao.App;
import gui.util.Alerta;
import gui.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Funcionario;

public class MenuAtendimentoControlador implements Initializable{
	
	private Funcionario funcionario;
	
	public void setFuncionario(Funcionario f) {
		this.funcionario = f;
	}

	@FXML
	void clicarNovo(ActionEvent event) {

		Stage parent = Util.atual(event);
		chamarFormulario("/gui/formularioAtendimento.fxml", parent);
	}

	@FXML
	void clicarVer(ActionEvent event) {
		rodarTela("/gui/listaAtendimento.fxml", (ListaAtendimentoControlador  controlador)-> {
			controlador.carregarTabela();
			
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
	
	private void chamarFormulario(String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			FormularioAtendimentoControlador controlador = loader.getController();			
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Formulário para Inclusão e Edição de Famílias");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			System.out.println(e.getStackTrace());
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
