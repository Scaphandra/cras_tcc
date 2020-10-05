package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Dialogo {

	private static boolean confirmClose() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Voc� realmente quer fechar o aplicativo?");
		return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
		}
	private static boolean confirmDeletePes() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Voc� realmente quer excluir essa pessoa?");
		return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
	}
	private static boolean confirmDeleteFam() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Voc� realmente quer excluir essa fam�lia?");
		return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
	}
}
