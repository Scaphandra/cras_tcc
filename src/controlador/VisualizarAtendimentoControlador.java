package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import modelo.basico.Atendimento;

public class VisualizarAtendimentoControlador implements Initializable {

	@FXML
	private Label nomePessoa;

	@FXML
	private Label idFamilia;

	@FXML
	private Label demanda;

	@FXML
	private CheckBox relatorio;

	@FXML
	private Label data;

	@FXML
	private Label tipoAtendimento;

	@FXML
	private Label nomeFuncionario;

	@FXML
	private Label encaminhamento;
	
	private Atendimento atendimento;
	
	public void setAtendimento(Atendimento a) {
		this.atendimento = a;
	}

	@FXML
	private void clicarOk(ActionEvent event) {
		Util.atual(event).close();
		
	}
	
	public void carregarAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
		nomePessoa.setText(atendimento.getPessoa().getNome());
		idFamilia.setText("Código Familiar: "+ atendimento.getPessoa().getFamilia().getId());
		data.setText("Data do Atendimento: "+ atendimento.getData().toString());
		tipoAtendimento.setText("Tipo do Atendimento: "+atendimento.getTipo().toString());
		demanda.setText("Demanda: "+atendimento.getDemanda().toString());
		nomeFuncionario.setText(atendimento.getFuncionario().getNome());
		relatorio.setSelected(atendimento.isRelatorio());
		encaminhamento.setText(atendimento.isEncaminhamento()?"Gerou Encaminhamento":"");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		

	}

}
