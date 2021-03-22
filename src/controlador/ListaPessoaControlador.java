package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import aplicacao.App;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.PessoaDAO;
import modelo.enumerados.PessoaEstado;

//observer

public class ListaPessoaControlador implements Initializable{

	private Pessoa pessoa;
	
	private Object valor;
	
	private int idUnidade;
	
	private Long id;
	
	private PessoaDAO dao = new PessoaDAO();
	
	@FXML
	private TextField busca;
	
	
	private ObservableList<Pessoa> obsPessoas = FXCollections.observableArrayList();
	
	private List<Pessoa> lista = dao.obterTodos();
	
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
	private TableColumn <Pessoa, String> colunaAtivo;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private Button inserir;
	
	@FXML
	private Button excluir;
	
	private ObservableList<Pessoa> obsPessoa;
	

	public void setUnidade(int u) {
		this.idUnidade = u;
	}
	
	public int getUnidade() {
		return this.idUnidade;
	}
	
	@FXML
	private void filtrarRegistros() {
		
		busca.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("textfield changed from " + oldValue + " to " + newValue);
		    obsPessoa.clear();
		    if(newValue.length()==0){
		    	lista = dao.obterTodos();
		    	obsPessoa.addAll(lista);
		    }
		    atualizarFiltro(newValue);
		    
		    
		});
}
	
	public void atualizarFiltro(String valor) {
		
		obsPessoas.clear();
		
		if(busca.getText().isEmpty()) {
			
			lista = dao.obterTodos();
			
		}else {
			
			
			lista = dao.obterPrimeiros(valor, "nome");
			
			obsPessoa.addAll(lista);			
		}
		iniciarTabela();

//		obsPessoa = FXCollections.observableArrayList(lista);
//		tabelaPessoa.getItems().setAll(obsPessoas);
//		tabelaPessoa.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void clicarNova(ActionEvent evento) {
		Stage parentStage = Util.atual(evento);
		Pessoa obj = new Pessoa();
		criarFormulario(obj, "/gui/formularioPessoa.fxml", parentStage, true);

		
	}
	@FXML
	public void clicarEditar(ActionEvent evento) {
//		Stage parentStage = Util.atual(evento);
//		
//		Pessoa obj = (Pessoa) valor;
//		System.out.println(obj.getId());
//		pessoa = dao.obterPorID(obj.getId());
//		System.out.println(obj);
//		
//		criarFormulario(obj,"/gui/formularioPessoa.fxml", parentStage, false);
		
	}
	
	@FXML
	public void clicarExcluir(ActionEvent evento) {
		//TODO colocar nesse método a possibilidade de excluir a pessoa da família.	
		
		dao.abrirTransacao();
		Pessoa pessoa = (Pessoa) valor;
		Pessoa pes = dao.obterPorID(pessoa.getId());
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
			dao.remover(pes);
			dao.fecharTransacao();
			dao.fechar();
			carregarPessoas();	
		}
	}
	
	@FXML
	public void clicarAtendimento(ActionEvent evento) {
		
		Stage parent = Util.atual(evento);
		this.pessoa = (Pessoa) valor;
		if(this.pessoa==null) {
			Alerta.showAlert("Nenhuma Pessoa Selecionada", "Selecione uma pessoa", "" , AlertType.WARNING);
		}else {
			
			this.pessoa = dao.obterPorID(this.pessoa.getId());
			chamarAtendimento("/gui/formularioAtendimento.fxml", this.pessoa, parent);
		}
	}
	
	@FXML
	public void clicarAcolhida(ActionEvent evento) {
		
	}
	

	public void setPessoa(Pessoa pessoa) {
				
		this.pessoa = pessoa;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();
		carregarPessoas();
		iniciarTabela();
		filtrarRegistros();
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarTabela() {
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaPessoa.prefHeightProperty().bind(cena.heightProperty());
		tabelaPessoa.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaPessoa.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
		        id = Long.valueOf(selectionModel.getSelectedIndex());//pega o id da TableList
		        System.out.println("Selected Value " + item + " "+ id);
		        Pessoa p = (Pessoa) item;
		        busca.setText(p.getNome());
		        
		        //System.out.println(item.toString().substring(18,19));
		       valor = item;
				
			}
			
		});
	}

	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaIdFamilia.setCellValueFactory(new PropertyValueFactory<>("familia"));
		colunaAtivo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
	}

	public void carregarPessoas() {
		
		obsPessoa = FXCollections.observableArrayList(lista);
		tabelaPessoa.setItems(obsPessoa);
		tabelaPessoa.setMaxHeight(277);
	}
	
	private void criarFormulario(Pessoa obj, String nomeView, Stage parentStage, boolean b) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();	
			FormularioPessoaControlador controlador = loader.getController();
			
			
			controlador.setPessoa(obj, b);
			controlador.setFamilia(obj.getFamilia(), false);
			controlador.preencherPessoa();
			if(obj.getEstado()==PessoaEstado.RF) {
				controlador.identificarRF(true);
			}else {
				controlador.identificarRF(false);
			}
			controlador.prepararPessoa(obj);
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclusão de pessoa");
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
	
	private void chamarAtendimento(String nomeView, Pessoa pessoa, Stage parent) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			FormularioAtendimentoControlador controlador = loader.getController();
			controlador.setPessoa(pessoa);
			controlador.carregarAtendimento();

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclusão de pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parent);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
