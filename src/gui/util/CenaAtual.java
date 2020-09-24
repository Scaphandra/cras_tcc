package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CenaAtual {
	
	public static Stage atual(ActionEvent evento) {
		return (Stage) ((Node) evento.getSource()).getScene().getWindow();
		
	}
	
	
}
