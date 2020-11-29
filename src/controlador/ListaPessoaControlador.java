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

public class ListaPessoaControlador implements Initializable, DataChangeListener{

	private Pessoa pessoa;
	
	private Object valor;
	
	private Long id;
	
	@FXML
	private TableView <Pessoa> tabelaPessoa;
	
	@FXML 
	private TableColumn<Pessoa, Long> colunaId;
	
	@FXML 
	private TableColumn<Pessoa, String> colunaNome;
	
	@FXML 
	private TableColumn<Pessoa, Familia> colunaIdFamilia;
	
	@FXML 
	private TableColumn <Pessoa, Pessoa> colunaSelecionar;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private Button inserir;
	
	@FXML
	private Button excluir;
	
	private ObservableList<Pessoa> obsPessoa;
	
	@FXML
	public void clicarNova(ActionEvent evento) {
		Stage parentStage = Util.atual(evento);
		Pessoa obj = new Pessoa();
		obj.setCor(CorRaca.NAODECLARADA);
		obj.setSexo(Sexo.F);
		obj.setGenero(Genero.F);
		criarFormularioAviso(obj, "/gui/formularioPessoa.fxml", parentStage);
		
	}
	@FXML
	public void clicarEditar(ActionEvent evento) {
		Stage parentStage = Util.atual(evento);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		
		Pessoa obj = (Pessoa) valor;
		System.out.println(obj.getId());
		pessoa = em.find(Pessoa.class, obj.getId());
		System.out.println(obj);
		em.close();
		emf.close();
		criarFormularioAviso(obj,"/gui/formularioPessoa.fxml", parentStage);
		
	}
	
	@FXML
	public void clicarExcluir(ActionEvent evento) {
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		
		Pessoa pessoa = (Pessoa) valor;
		Pessoa pes = em.find(Pessoa.class, pessoa.getId());
		System.out.println(pessoa.getId());
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
			em.getTransaction().begin();
			em.remove(pes);
			em.getTransaction().commit();
			em.close();
			emf.close();
			//carregarPessoas();
			onDataChanged();
		
			
		}
		
		
		
	}
	

	public void setPessoa(Pessoa pessoa) {
				
		this.pessoa = pessoa;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaIdFamilia.setCellValueFactory(new PropertyValueFactory<>("familia"));
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaPessoa.prefHeightProperty().bind(cena.heightProperty());
		tabelaPessoa.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaPessoa.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
		        id = Long.valueOf(selectionModel.getSelectedIndex());//pega o id da TableList
		        System.out.println("Selected Value " + item + " "+ id);
		        //System.out.println(item.toString().substring(18,19));
		       valor = item;
				
			}
			
		});
		
	}
	
	
	
	public void carregarPessoas() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "select p from Pessoa p";
		TypedQuery <Pessoa> query = em.createQuery(jpql, Pessoa.class);
		List<Pessoa> pessoas = query.getResultList();
		obsPessoa = FXCollections.observableArrayList(pessoas);
		tabelaPessoa.setItems(obsPessoa);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
	
	private void criarFormularioAviso(Pessoa obj, String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			FormularioPessoaControlador controlador = loader.getController();
			controlador.setPessoa(obj);
			controlador.preencherPessoa();
			controlador.inscreverDataChangeListener(this);
			
			
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

	@Override
	public void onDataChanged() {
		carregarPessoas();
		
	}
}
