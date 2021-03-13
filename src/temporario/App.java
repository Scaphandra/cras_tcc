package temporario;
//github.com/Scaphandra/tcc_cras
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application{
	//foi necessário instalar um pluggin para ter a possibilidade de escolher um arquifo
	//fxml na aba help -> eclipse MarcketPlace -> javafx e procurar
	//plugin selecionado foi e(fx)clipse
	
	private static Scene cena;
	

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		String arquivoCSS = getClass().getResource("../gui/estilo.css").toExternalForm();
		URL arquivoFXML = getClass().getResource("../gui/app.fxml");
		FXMLLoader loader = new FXMLLoader(arquivoFXML);
		ScrollPane raiz = loader.load();
		//ScrollPane raiz = loader.load(arquivoFXML);
		
		
//		raiz.setFitToHeight(true);
//		raiz.setFitToWidth(true);
		
		cena = new Scene(raiz);
		cena.getStylesheets().add(arquivoCSS);
		
		
		primaryStage.setResizable(true);
		primaryStage.setTitle("Centro de Referência da Assistência Social");
		//inicializar a tela com cena
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}

	
	
	public static Scene getCena() {
		return cena;
	}


	public static void main(String[] args) {
		launch(args);
	}
	
}
