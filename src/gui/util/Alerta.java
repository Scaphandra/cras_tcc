package gui.util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class Alerta {
	
	
	public static void showAlert(String titulo, String cabecalho, String conteudo, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.show();
		
	}
	
	public static void campoVazio(Node ... no) {
		
		for(Node n: no) {
			if(n instanceof TextField) {
				TextField txt = (TextField) n;
				if(txt.getText().trim().equals("")) {
					Alerta.showAlert("Erro", "Campo Vazio", "Preencha o campo obrigatório", AlertType.ERROR);
				}
			}
		}
	}
}
