package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Dialogo {

	private static boolean confirmClose() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Você realmente quer fechar o aplicativo?");
		return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
		}
	private static boolean confirmDeletePes() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Você realmente quer excluir essa pessoa?");
		return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
	}
	private static boolean confirmDeleteFam() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Você realmente quer excluir essa família?");
		return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
	}
}
