package gui.util;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//javafx trabalha com palco(tela), cena(conteúdo da tela) e nó(são conteudos da cena - ex.: AnchorPane)
//tooltip

public class Util {

	
	public static Stage atual(ActionEvent evento) {
		
		return (Stage) ((Node) evento.getSource()).getScene().getWindow();
		
	}
	
	public static Integer tentarConverterParaInt(String str) {
		try {
			return Integer.parseInt(str);
		}catch(NumberFormatException e) {
			return null;
		}
	}
	
	public static boolean isVazio(Node ... no) {
		List<Node> camposVazio = new ArrayList<>();
		
		for(Node n: no) {
			if(n instanceof TextField) {//testando se é uma instância de TextField
				//Criar um objeto TextField para o node
				TextField tf = (TextField) n;
				//Capturar o caracter digitado
				tf.textProperty().addListener((observable, ovalue, nvalue)->{
					//criar métodos que mexam nos nós, por exemplo, mudar a cor da borda, mudar texto exibido
					
					//se o campo estiver vazio
					if(tf.getText().trim().equals("")) {
						camposVazio.add(n);
					}
				});
			}
		}
		return camposVazio.isEmpty();
		
	}
	
	
}
