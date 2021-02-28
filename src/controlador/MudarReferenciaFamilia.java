package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
	
	@FXML 
	private TableColumn<Pessoa, Long> colunaId;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void setFamilia(Familia f) {
		this.familia = f;
		
	}
	
	@FXML
	public void clicarCancelar(ActionEvent event) {
		
		Util.atual(event).close();
	}

	@FXML
	void clicarSelecionar(ActionEvent event) {
		
		this.pessoa = (Pessoa) valor;
		
		if(this.pessoa.getEstado()==PessoaEstado.RF) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Seleção de Pessoa de Referência");
			alert.setHeaderText("Você selecionou a pessoa de referência");
			alert.setContentText(this.pessoa.getNome()+" já é a Pessoa de Referência da Família");
			alert.show();
			
		}else {

			Pessoa antigaRF = dao.obterPorID(familia.getPesReferencia().getId());
			Pessoa novaRF = dao.obterPorID(this.pessoa.getId());			
//
//			antigaRF.setEstado(PessoaEstado.P);
//			antigaRF.setComposicao(Composicao.N);
//			antigaRF.setPesReferencia(false);
//			
//			novaRF.setEstado(PessoaEstado.RF);
//			novaRF.setPesReferencia(true);
//			novaRF.setComposicao(Composicao.RF);
//			familia.setTotalRenda();
//			familia.setPesReferencia(novaRF);
//			familia.setRendaReferencia();
			daof.abrirTransacao();
			dao.abrirTransacao();
			familia.trocarRf(antigaRF, novaRF);
			daof.atualizar(familia);
			
			daof.fecharTransacao().fechar();
			dao.fecharTransacao().fechar();
			
			Stage parent = Util.atual(event);
			atualizarComposicao("/gui/atualizarComposicao.fxml", parent);
			Util.atual(event).close();
		}
		
	}
	


	@FXML
	void clicarNovaPessoa(ActionEvent event) {
		Stage parentStage = Util.atual(event);
//
//		daof.abrirTransacao();
//		daof.obterPorID(familia.getId());
		
		Pessoa obj = new Pessoa();
		
		formularioPessoa(obj, "/gui/formularioPessoa.fxml", parentStage,true, true);
		
		Stage parent = Util.atual(event);
		atualizarComposicao("/gui/atualizarComposicao.fxml", parent);
		Util.atual(event).close();
	 }

	@FXML
	void clicarPessoaInativa(ActionEvent event) {
		Stage parentStage = Util.atual(event);
		criarFormularioPessoaBanco("/gui/escolherListaPessoa.fxml", parentStage);
		Util.atual(event).close();
		
		
	}
	
//	@FXML
//	void clicarEditarRF(ActionEvent event) {
//		
//		Stage parent = Util.atual(event);
//		
//		boolean rf;
//		
//		this.pessoa = (Pessoa) valor;
//		
//		if(this.pessoa.getEstado()==PessoaEstado.RF) {
//			rf = true;
//		}else {
//			rf=false;
//		}
//		
//		formularioPessoa(this.pessoa, "/gui/formularioPessoa.fxml", parent, false, rf);
//		
//		Util.atual(event).close();
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void carregarPessoas(Familia familia) {

		this.familia = familia;

		
		lista = dao.obterCondicao("id_familia", familia.getId().toString());
		obsPessoas = FXCollections.observableArrayList(lista);		
		
		tabelaPessoas.setItems(obsPessoas);
		tabelaPessoas.setMaxHeight(200);
		Stage cena = (Stage) App.getCena().getWindow();
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
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

	
	public void formularioPessoa(Pessoa obj, String nomeView, Stage parentStage, boolean pesNova, boolean rf) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();	
			FormularioPessoaControlador controlador = loader.getController();
			
			controlador.setPessoa(obj, pesNova);
			
			controlador.identificarRF(rf);
			controlador.prepararPessoa(obj);
			controlador.setFamilia(this.familia, false);
			
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
	
	public void atualizarComposicao(String nomeView, Stage parentStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();	
			
			FormularioComposicaoControlador controlador = loader.getController();
			Pessoa pes = (Pessoa) valor;
			
			
			controlador.carregarTabela(familia, pes);
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Atualize a Composição Familiar");
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
	
	public void criarFormularioPessoaBanco(String nomeView, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();

			EscolherListaPessoaControlador controlador = loader.getController();

			controlador.setFamilia(familia);
			controlador.mudarRF(true);

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Escolha uma pessoa da lista e clique em selecionar pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
