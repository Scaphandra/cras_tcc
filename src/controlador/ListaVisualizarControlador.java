package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.App;
import gui.listeners.DataChangeListener;
import gui.util.Alerta;
import gui.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.FamiliaDAO;

public class ListaVisualizarControlador implements Initializable{
	
	private FamiliaDAO daof = new FamiliaDAO();
	
	
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
	
	@FXML 
	private TableColumn <Familia, String> colunaAtivo;


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
		
		Stage parentStage = Util.atual(event);
		
		criarFormulario(this.familia, "/gui/visualizarFamilia.fxml",parentStage);
		
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarTabela() {
		
		lista = daof.obterTodos();

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
		       
				
			}
			
		});
	}
	
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Familia, Long>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Familia, String>("pesReferencia"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Familia, Date>("dataEntrada"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<Familia, Integer>("numero"));
		colunaAtivo.setCellValueFactory(new PropertyValueFactory<Familia, String>("ativo"));
	}

	public void criarFormulario(Familia f, String nomeView, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();

			VisualizarFamiliaControlador controlador = loader.getController();
			controlador.setFamilia(f);
			controlador.carregarFamilia(f);
			controlador.carregarPessoas(f.getId());

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclusão de pessoa de referência da família");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
