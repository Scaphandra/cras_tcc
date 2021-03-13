package controlador;

import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import modelo.enumerados.AtendimentoTipo;
import modelo.enumerados.Composicao;
import modelo.enumerados.DemandaAtendimento;

public class FormularioAtendimentoControlador implements Initializable{

	@FXML
    private Label nomePessoa;

    @FXML
    private Label idFamilia;

    @FXML
    private ComboBox <AtendimentoTipo> comboTipo;
    private ObservableList<AtendimentoTipo> obsTipo;
    private boolean tipo;

    @FXML
    private DatePicker data;

    @FXML
    private ComboBox <DemandaAtendimento> comboDemanda;
    private ObservableList<DemandaAtendimento> obsDemanda;
    private boolean demanda;

    @FXML
    private Label nomeFuncionario;
    
    @FXML
    private CheckBox checkRelatorio;
    
    private boolean relatorio;

    @FXML
    public void clicarCancelar(ActionEvent event) {

    }

    @FXML
    public void clicarEncaminhamento(ActionEvent event) {

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
    	tipo = (AtendimentoTipo) comboTipo.setSelectionModel().getSelecitedItem();
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
		// TODO Auto-generated method stub
		
	}

}
