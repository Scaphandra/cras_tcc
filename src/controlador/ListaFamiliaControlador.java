package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.FamiliaDAO;

//observer

public class ListaFamiliaControlador implements Initializable, DataChangeListener{
	
	

	private Familia familia;
	
	private Object valor;
	
	private boolean familiaNova;
	
	private FamiliaDAO fdao = new FamiliaDAO();
	
	@FXML
	private TableView <Familia> tabelaFamilia;
	
	@FXML 
	private TableColumn<Familia, Long> colunaId;
	
	@FXML 
	private TableColumn<Familia, String> colunaResponsavel;
	
	@FXML 
	private TableColumn<Familia, Integer> colunaNum;
	
	@FXML 
	private TableColumn <Familia, String> colunaAtivo;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private Button inserir;
	
	@FXML
	private Button excluir;
	@FXML
	private Button reativar;
	
	private ObservableList<Familia> obsFamilia;
	
	@FXML
	public void clicarNova(ActionEvent evento) {

		Stage parentStage = Util.atual(evento);
		Pessoa obj = new Pessoa();
		obj.setAtivo(true);
		criarFormularioPessoa(obj, "/gui/formularioPessoa.fxml", parentStage, true);
		
	}
	@FXML
	public void clicarEditar(ActionEvent evento) {
		
		Stage parentStage = Util.atual(evento);
		
		Familia obj = fdao.obterPorID(this.familia.getId());
		this.familiaNova = false;
		
		criarFormularioFamilia(obj,"/gui/formularioFamilia.fxml", parentStage);
	
		
	}
	
	@FXML
	public void clicarReativar(ActionEvent evento) {
		
		Stage parentStage = Util.atual(evento);
		criarFormularioReativar("/gui/reativarFamilia.fxml",parentStage);
	}
	
	@FXML
	public void clicarExcluir(ActionEvent evento) {
		
		fdao.abrirTransacao();
		Familia familia = (Familia) valor;
		Familia obj = fdao.obterPorID(familia.getId());

		System.out.println("clicou excluir");
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Exclusão de pessoa do banco de dados");
		alerta.setHeaderText("Tem certeza que deseja excluir esta pessoa do banco de dados?");
		alerta.setContentText("Escolha o que deseja fazer.");
		ButtonType btCancelar = new ButtonType("Cancelar");
		ButtonType btProsseguir = new ButtonType("Prosseguir");
		
		alerta.getButtonTypes().setAll(btCancelar, btProsseguir);
		Optional <ButtonType> result = alerta.showAndWait();
		
		if(result.get()==btProsseguir) {
			fdao.remover(obj);
			fdao.fecharTransacao();
			fdao.fechar();
			carregarFamilia();
		}
	
		
	}
	
	@FXML
	public void clicarVisualizar(ActionEvent evento) {
		
		Stage parentStage = Util.atual(evento);
		
		criarVisualizar("/gui/visualizarFamilia.fxml",parentStage, familia);
		
	}
	


	public void setFamilia(Familia familia) {
				
		this.familia = familia;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();
		carregarFamilia();
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Familia, Long>("id"));
		colunaResponsavel.setCellValueFactory(new PropertyValueFactory<Familia, String>("pesReferencia"));
		colunaNum.setCellValueFactory(new PropertyValueFactory<Familia, Integer>("numero"));
		colunaAtivo.setCellValueFactory(new PropertyValueFactory<Familia, String>("ativo"));
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaFamilia.prefHeightProperty().bind(cena.heightProperty());
		tabelaFamilia.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaFamilia.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
//		        Long id = Long.valueOf(selectionModel.getSelectedIndex());//pega o id da TableList
		        System.out.println("Selected Value " + item);
		        setFamilia((Familia) item);
		        valor = item;	
		        System.out.println(item);
			}
		});
		
	}
	
	public void carregarFamilia() {
		//CRIA UMA LISTA COM TODAS AS FAMÍLIAS PARA APARECEREM NO TABLEVIEW COM A VARIÁVEL OBSFAMILIA
		List<Familia> pessoas = fdao.obterCondicao("ativo","1");
		obsFamilia = FXCollections.observableArrayList(pessoas);
		tabelaFamilia.setItems(obsFamilia);
		
		
	}
	
	
	private void criarFormularioPessoa(Pessoa obj, String nomeView, Stage parentStage, boolean famNova) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			FormularioPessoaControlador controlador = loader.getController();
			//setando pessoa nova
			//controlador.setPessoa(obj, true);
			controlador.identificarRF(true);
			controlador.prepararPessoa(null);
			controlador.preencherPessoa();
			controlador.setFamiliaNova(famNova);			
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Formulário para Inclusão e Edição de Pessoas");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	private void criarFormularioFamilia(Familia obj, String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			FormularioFamiliaControlador controlador = loader.getController();
			controlador.inscreverListener(this);
			controlador.setFamilia(obj);
			controlador.preencherFamilia();
			controlador.carregarPessoas(obj.getId());
			controlador.setFamiliaNova(this.familiaNova);
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Formulário para Inclusão e Edição de Famílias");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			System.out.println(e.getStackTrace());
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	private void criarFormularioReativar(String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			ReativarFamiliaControlador controlador = loader.getController();
			controlador.inscreverListener(this);
			
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Formulário para Reativação de Famílias");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			System.out.println(e.getStackTrace());
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	private void criarVisualizar(String nomeView, Stage parentStage,Familia familia) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			VisualizarFamiliaControlador controlador = loader.getController();
			controlador.setFamilia(familia);
			controlador.carregarFamilia(familia);
			controlador.carregarPessoas(familia.getId());
			
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Visualização de Famílias");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			System.out.println(e.getStackTrace());
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	@Override
	public void onDataChanged() {
		iniciarComponentes();
//		carregarFamilia();
	
	}

}
