package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.basico.Funcionario;

public class MenuAtendimento implements Initializable{
	
	private Funcionario funcionario;
	
	public void setFuncionario(Funcionario f) {
		this.funcionario = f;
	}

	@FXML
	void clicarNovo(ActionEvent event) {

	}

	@FXML
	void clicarVer(ActionEvent event) {

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
