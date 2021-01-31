package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.App;
import gui.listeners.DataChangeListener;
import gui.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.FamiliaDAO;

public class ReativarFamiliaControlador implements Initializable{
	
	private FamiliaDAO daof = new FamiliaDAO();
	
	private List <DataChangeListener> listeners = new ArrayList<>();
	
	private Familia familia;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private TextField busca;
	
	@FXML
	private Label idFamilia;
	
	@FXML
	private TableView <Familia> tabelaFamilia;
	
	private List <Familia> lista;
	
	private ObservableList <Familia> obsFamilias;

	@FXML 
	private TableColumn<Familia, Long> colunaId;
	
	@FXML 
	private TableColumn<Familia, String> colunaNome;
	
	@FXML 
	private TableColumn<Familia, Date> colunaData;
	
	@FXML 
	private TableColumn <Familia, Integer> colunaQuantidade;

	
	public void inscreverListener(DataChangeListener d) {
		listeners.add(d);
	}

	public void notificarListener() {
		for (DataChangeListener d : listeners) {
			d.onDataChanged();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();

		iniciarTabela();
		
	}
	
	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();
	}
	
	@FXML
	void clicarSelecionar(ActionEvent event) {
		//TODO
		
		if(familia.getPesReferencia()==null) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Familia não possui pessoa de referência!");
			alert.setHeaderText("A Família selecionada não pode ser reativada.");
			alert.setContentText("A família selecionada teve a pessoa de referência incluída em outra família,"
					+ " por esse motivo não poderá ser reativada.");
			alert.show();
		}
			
		daof.abrirTransacao();

		familia = daof.obterPorID(familia.getId());

		for(Pessoa p: familia.getPessoas()) {
			p.setAtivo(true);
		}
		familia.setAtivo(true);
		familia.setMotivoDesligamento(familia.getMotivoDesligamento()+ "-> (Família reativada no banco em "
		+ converteData(new Date())+")");
		
		daof.atualizar(familia);
		daof.fecharTransacao().fechar();
		notificarListener();
		Util.atual(event).close();
		
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarTabela() {
		lista = daof.obterCondicao("ativo","0");
		obsFamilias = FXCollections.observableArrayList(lista);
		tabelaFamilia.setItems(obsFamilias);
		tabelaFamilia.setMaxHeight(180);
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaFamilia.prefHeightProperty().bind(cena.heightProperty());
		tabelaFamilia.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaFamilia.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
		        System.out.println("Selected Value " + item);
		        familia = (Familia) item;
		        //busca.setText(familia.toString());
				
			}
			
		});
	}
	
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Familia, Long>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Familia, String>("pesReferencia"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Familia, Date>("dataDesligamento"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<Familia, Integer>("numero"));
	}

	

}
