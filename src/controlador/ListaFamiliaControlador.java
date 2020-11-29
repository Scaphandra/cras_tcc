package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import aplicacao.App;
import gui.DataChangeListener;
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
import javafx.scene.Parent;
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
import modelo.enumerados.CorRaca;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

//observer

public class ListaFamiliaControlador implements Initializable, DataChangeListener{

	private Familia familia;
	
	private Object valor;
	
	@FXML
	private TableView <Familia> tabelaFamilia;
	
	@FXML 
	private TableColumn<Familia, Long> colunaId;
	
	@FXML 
	private TableColumn<Pessoa, String> colunaResponsavel;
	
	@FXML 
	private TableColumn<Familia, Integer> colunaNum;
	
	@FXML 
	private TableColumn <Familia, Familia> colunaSelecionar;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private Button inserir;
	
	@FXML
	private Button excluir;
	
	private ObservableList<Familia> obsFamilia;
	
	@FXML
	public void clicarNova(ActionEvent evento) {
//		try {
//			Parent raiz = FXMLLoader.load(getClass().getResource("../gui/formularioFamilia.fxml"));
//			Stage stage = new Stage();
//			Scene cena = new Scene(raiz);
//			stage.setScene(cena);			
//			stage.show();
//			
//		}catch(IOException e) {
//			e.printStackTrace();		}
		Stage parentStage = Util.atual(evento);
		Pessoa obj = new Pessoa();
		obj.setCor(CorRaca.NAODECLARADA);
		obj.setSexo(Sexo.F);
		obj.setGenero(Genero.F);
		
		criarFormularioPessoa(obj, "/gui/formularioPessoa.fxml", parentStage);
		
	}
	@FXML
	public void clicarEditar(ActionEvent evento) {
		Stage parentStage = Util.atual(evento);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		
		Familia obj = (Familia) valor;
		familia = em.find(Familia.class, obj.getId());
		System.out.println(obj);
		criarFormularioFamilia(obj,"/gui/formularioFamilia.fxml", parentStage);
		
		em.close();
		emf.close();
		
	}
	
	@FXML
	public void clicarExcluir(ActionEvent evento) {
		Stage parentStage = Util.atual(evento);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		
		Familia obj = (Familia) valor;
		familia = em.find(Familia.class, obj.getId());
		em.getTransaction().begin();
		System.out.println(obj);
		
		
		
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
			em.remove(familia);
			em.getTransaction().commit();
			em.close();
			emf.close();
			carregarFamilia();
		}
		
		
		
	}
	

	public void setFamilia(Familia familia) {
				
		this.familia = familia;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Familia, Long>("id"));
		colunaResponsavel.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("pesReferencia"));
		colunaNum.setCellValueFactory(new PropertyValueFactory<Familia, Integer>("numero"));
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaFamilia.prefHeightProperty().bind(cena.heightProperty());
		tabelaFamilia.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaFamilia.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
		        int id = selectionModel.getSelectedIndex();//pega o id da TableList, mas está em inteiro, tem que fazer conversão para Long se quiser usar
		        System.out.println("Selected Value " + item + " "+ id);
		        valor = item;
				
			}
			
		});
		
	}
	
	public void carregarFamilia() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "select p from Familia p";
		TypedQuery <Familia> query = em.createQuery(jpql, Familia.class);
		List<Familia> pessoas = query.getResultList();
		obsFamilia = FXCollections.observableArrayList(pessoas);
		tabelaFamilia.setItems(obsFamilia);
	}
	
	

	@Override
	public void onDataChanged() {
		carregarFamilia();
		
	}
	
	private void criarFormularioPessoa(Pessoa obj, String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			FormularioPessoaControlador controlador = loader.getController();
			controlador.setPessoa(obj);
			controlador.preencherPessoa();
			controlador.inscreverDataChangeListener(this);
			controlador.habilitarResponsavel();
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclusão de pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			
		}
	}
	private void criarFormularioFamilia(Familia obj, String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			FormularioFamiliaControlador controlador = loader.getController();
//			controlador.setFamilia(obj);
//			controlador.preencherPessoa();
//			controlador.inscreverDataChangeListener(this);
//			controlador.habilitarResponsavel();
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclusão de pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			
		}
	}
}
