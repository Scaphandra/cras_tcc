package aplicacao;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InclusaoPessoaApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		String arquivoCSS = getClass().getResource("../fxml/estilo.css").toExternalForm();
		URL arquivoFXML = getClass().getResource("../fxml/inclusaoPessoa.fxml");
		Pane raiz = FXMLLoader.load(arquivoFXML);
		
		Scene cena = new Scene(raiz);
		cena.getStylesheets().add(arquivoCSS);
		
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Inclusão de Pessoa");
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
		
	

}
