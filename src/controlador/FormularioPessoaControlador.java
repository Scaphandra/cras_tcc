package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FormularioPessoaControlador implements Initializable{

	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField dataNasc;
	
	@FXML
	private TextField nomeMae;
	
	@FXML
	private TextField cpf;
	
	@FXML
	private TextField rg;
	
	@FXML
	private TextField nis;
	
	@FXML
	private TextField ocupacao;
	
	@FXML
	private TextField parentesco;
	
	@FXML
	private TextField renda;
	
	@FXML
	private CheckBox deficiencia;
	
	@FXML
	private CheckBox gestante;
	
	@FXML
	private CheckBox scfv;
	
	@FXML
	private CheckBox responsavel;
	
	@FXML
	private ComboBox boxCor;
	
	@FXML
	private ComboBox boxSexo;
	
	@FXML
	private ComboBox boxGenero;
	
	@FXML
	private ComboBox boxEscolaridade;
	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cencelar;
	
	@FXML
	public void clicarSalvar() {
		System.out.println("salvar");
	}

	@FXML
	public void clicarCancelar() {
		
		System.out.println("cancelar");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
		
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldDouble(renda);
		Constraints.setTextFieldMaxLength(cpf, 11);
		Constraints.setTextFieldMaxLength(nis, 11);
	}

}
