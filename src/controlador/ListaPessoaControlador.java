package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import aplicacao.App;
import gui.util.Alerta;
import gui.util.CenaAtual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;

public class ListaPessoaControlador implements Initializable{

	private Pessoa pessoa;
	
	@FXML
	private TableView <Pessoa> tabelaPessoa;
	
	@FXML 
	private TableColumn<Pessoa, Long> colunaId;
	
	@FXML 
	private TableColumn<Pessoa, String> colunaNome;
	
	@FXML 
	private TableColumn<Pessoa, Familia> colunaIdFamilia;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private Button nova;
	
	private ObservableList<Pessoa> obsPessoa;
	
	@FXML
	public void clicarSelecionar() {
		System.out.println("selecionei");
	}
	
	@FXML
	public void clicarNova(ActionEvent evento) {
		Stage parentStage = CenaAtual.atual(evento);
		criarFormularioAviso("/gui/formularioPessoa.fxml", parentStage);
		
	}

	
	
	
	
	public void setPessoa(Pessoa pessoa) {
				
		this.pessoa = pessoa;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();
		
	}

	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id_pessoa"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome_pes"));
		colunaIdFamilia.setCellValueFactory(new PropertyValueFactory<>("familia"));
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaPessoa.prefHeightProperty().bind(cena.heightProperty());
		
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
	}
	
	private void criarFormularioAviso(String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
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
