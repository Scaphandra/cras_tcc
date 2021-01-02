package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import modelo.dao.PessoaDAO;
import modelo.enumerados.Composicao;
import modelo.enumerados.PessoaEstado;

public class MudarReferenciaFamilia implements Initializable{
	  
	private Familia familia;
	
	private Pessoa pessoa;
	
	private Object valor;
	
	List<Pessoa> lista;
	
	private PessoaDAO dao = new PessoaDAO();
	
	private FamiliaDAO daof = new FamiliaDAO();
	
	@FXML
	private Button btNovaPessoa;

	@FXML
	private Button btSelecionar;

	@FXML
	private TableView<Pessoa> tabelaPessoas ;

	ObservableList <Pessoa> obsPessoas;
	
	@FXML
	private Button btPessoaInativa;
	
	@FXML 
	private TableColumn<Pessoa, String> colunaNome;
	
	@FXML 
	private TableColumn<Pessoa, Integer> colunaIdade;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void setFamilia(Familia f) {
		this.familia = f;
		
	}

	@FXML
	void clicarSelecionar(ActionEvent event) {
		daof.abrirTransacao();
		dao.abrirTransacao();
		
		this.pessoa = (Pessoa) valor;
		
		if(this.pessoa.getEstado()==PessoaEstado.RF) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sele��o de Pessoa de Refer�ncia");
			alert.setHeaderText("Voc� selecionou a pessoa de refer�ncia");
			alert.setContentText(this.pessoa.getNome()+" j� � a Pessoa de Refer�ncia da Fam�lia");
			alert.show();
			
		}else {

			Pessoa antigaRF = dao.obterPorID(familia.getPesReferencia().getId());
			Pessoa novaRF = dao.obterPorID(this.pessoa.getId());			

			antigaRF.setEstado(PessoaEstado.P);
			antigaRF.setComposicao(Composicao.N);
			antigaRF.setPesReferencia(false);
			
			novaRF.setEstado(PessoaEstado.RF);
			novaRF.setPesReferencia(true);
			novaRF.setComposicao(Composicao.RF);
			familia.setTotalRenda();
			familia.setRendaReferencia();
			familia.setPesReferencia(novaRF);
			daof.atualizar(familia);
			
			daof.fecharTransacao().fechar();
			dao.fecharTransacao();
			
			Stage parent = Util.atual(event);
			atualizarComposicao("/gui/atualizarComposicao.fxml", parent);
			Util.atual(event).close();
		}
		
	}

	@FXML
	void clicarNovaPessoa(ActionEvent event) {
		Stage parentStage = Util.atual(event);
		Pessoa obj = new Pessoa();
		formularioPessoa(obj, "/gui/formularioPessoa.fxml", parentStage,true);
		Util.atual(event).close();
	 }

	@FXML
	void clicarPessoaInativa(ActionEvent event) {

	}
	
	@FXML
	void clicarEditarRF(ActionEvent event) {
		
		Stage parent = Util.atual(event);
		
		this.pessoa = (Pessoa) valor;
		
		formularioPessoa(this.pessoa, "/gui/formularioPessoa.fxml", parent, false);
		
		Util.atual(event).close();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void carregarPessoas(Familia familia) {

		this.familia = familia;

		
		lista = dao.obterCondicao("id_familia", familia.getId().toString());
		obsPessoas = FXCollections.observableArrayList(lista);		
		
		tabelaPessoas.setItems(obsPessoas);
		tabelaPessoas.setMaxHeight(200);
		Stage cena = (Stage) App.getCena().getWindow();
		
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		
		tabelaPessoas.prefHeightProperty().bind(cena.heightProperty());
		tabelaPessoas.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
		
		
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) {
			TableViewSelectionModel selectionModel = tabelaPessoas.getSelectionModel();
	        Object item = selectionModel.getSelectedItem();
	        valor = item;
	      
			}
		});
	}

	
	public void formularioPessoa(Pessoa obj, String nomeView, Stage parentStage, boolean pesNova) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();	
			FormularioPessoaControlador controlador = loader.getController();
			
			controlador.setPessoa(obj, pesNova);
			
			if(obj.getEstado()==PessoaEstado.RF) {
				controlador.identificarRF(true);
			}else {
				controlador.identificarRF(false);
			}
			controlador.prepararPessoa(obj);
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclus�o de pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	
	public void atualizarComposicao(String nomeView, Stage parentStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();	
			
			FormularioComposicaoControlador controlador = loader.getController();
			Pessoa pes = (Pessoa) valor;
			
			
			controlador.carregarTabela(familia, pes);
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Atualize a Composi��o Familiar");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
		}catch(IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		
		}
		
	}

}
