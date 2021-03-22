package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.ResourceBundle;

import gui.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import modelo.basico.Pessoa;
import modelo.dao.PessoaDAO;
import modelo.enumerados.AtendimentoTipo;
import modelo.enumerados.DemandaAtendimento;

public class FormularioAtendimentoControlador implements Initializable{

	@FXML
    private Label nomePessoa;

    @FXML
    private Label idFamilia;

    @FXML
    private ComboBox <AtendimentoTipo> comboTipo;
    private ObservableList<AtendimentoTipo> obsTipo;
    private AtendimentoTipo tipo;

    @FXML
    private DatePicker data;

    @FXML
    private ComboBox <DemandaAtendimento> comboDemanda;
    private ObservableList<DemandaAtendimento> obsDemanda;
    private DemandaAtendimento demanda;

    @FXML
    private Label nomeFuncionario;
    
    @FXML
    private CheckBox checkRelatorio;
    
    private boolean relatorio;
    
    private Pessoa pessoa;
    
    public void setPessoa(Pessoa p) {
    	
    	PessoaDAO dao = new PessoaDAO();
    	
    	this.pessoa = dao.obterPorID(p.getId());
    }

    @FXML
    public void clicarCancelar(ActionEvent event) {
    	
    	Util.atual(event).close();
    }

    @FXML
    public void clicarEncaminhamento(ActionEvent event) {
    	System.out.println("Chamar Formulário Encaminhamento com dados da Pessoa e Família");
    }

    @FXML
    public void clicarRelatorio(ActionEvent event) {
    	
    	relatorio = checkRelatorio.selectedProperty().getValue();
    }

    @FXML
    public void clicarSalvar(ActionEvent event) {

    	
    }
    
    @FXML
    public void clicarDemanda() {

    	demanda = (DemandaAtendimento) comboDemanda.getSelectionModel().getSelectedItem();
    	
    }
    
    @FXML
    public void clicarTipo() {
    	
    	tipo = (AtendimentoTipo) comboTipo.getSelectionModel().getSelectedItem();
    }
    
    private void salvarAtendimentoNovo() {
    	
    	
    }
    
    public void carregarAtendimento() {
    	data.setValue(LocalDate.now());
    	nomePessoa.setText(this.pessoa.getNome());
    	idFamilia.setText("Código Familiar: " + this.pessoa.getFamilia().getId());
    }

	
	private void carregarCombos() {
		
	
		EnumSet <DemandaAtendimento> demandas = EnumSet.allOf(DemandaAtendimento.class);
		obsDemanda = FXCollections.observableArrayList(demandas);
		comboDemanda.setItems(obsDemanda);
		
		EnumSet<AtendimentoTipo> atends = EnumSet.allOf(AtendimentoTipo.class);
		obsTipo = FXCollections.observableArrayList(atends);
		comboTipo.setItems(obsTipo);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarCombos();
		
	}

}
