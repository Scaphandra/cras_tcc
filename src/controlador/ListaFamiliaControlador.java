package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.basico.Unidade;
import modelo.dao.FamiliaDAO;

//observer

public class ListaFamiliaControlador implements Initializable{
	

	private Familia familia;
	
	private int unidade;
	
	private Object valor;
	
	private boolean familiaNova;
	
	private FamiliaDAO fdao = new FamiliaDAO();
	
	private List<DataChangeListener> listeners = new ArrayList<>();
	
	@FXML
	private TableView <Familia> tabelaFamilia;
	
	@FXML 
	private TableColumn<Familia, Long> colunaId;
	
	@FXML 
	private TableColumn<Familia, String> colunaResponsavel;
	
	@FXML 
	private TableColumn<Familia, Integer> colunaNum;
	
	@FXML 
	private TableColumn <Familia, Date> colunaData;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private Button inserir;
	
	@FXML
	private Button excluir;
	@FXML
	private Button reativar;
	
	private ObservableList<Familia> obsFamilia;
	
	
	public void setUnidade(int u) {
		this.unidade = u;
	}
	
	public void inscreverListener(DataChangeListener d) {
		listeners.add(d);
	}

	public void notificarListener() {
		for (DataChangeListener d : listeners) {
			d.onDataChanged();
		}
	}
	
	@FXML
	public void clicarNova(ActionEvent evento) {

		Stage parentStage = Util.atual(evento);
		Pessoa obj = new Pessoa();
		obj.setAtivo(true);
		criarFormularioPessoa(obj, "/gui/formularioPessoa.fxml", parentStage, true);
		notificarListener();
		
	}

	@FXML
	
	public void clicarEditar(ActionEvent evento) {
		
		Stage parentStage = Util.atual(evento);
		
		Familia obj = fdao.obterPorID(this.familia.getId());
		this.familiaNova = false;
		
		criarFormularioFamilia(obj,"/gui/formularioFamilia.fxml", parentStage);
		
		notificarListener();
	
		
	}
	
	@FXML
	public void clicarReativar(ActionEvent evento) {
		
		Stage parentStage = Util.atual(evento);
		criarFormularioReativar("/gui/reativarFamilia.fxml",parentStage);
		carregarFamilia();
		notificarListener();
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
	
		carregarFamilia();
		notificarListener();
		
	}
	
	@FXML
	public void clicarVisualizar(ActionEvent evento) {
		
		Stage parentStage = Util.atual(evento);
		
		criarVisualizar("/gui/familiaVisualizar.fxml",parentStage, familia);
		
		
	}
	
	public void setFamilia(Familia familia) {
				
		this.familia = familia;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		iniciarComponentes();
		carregarFamilia();
		
	}
	
	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Familia, Long>("id"));
		colunaResponsavel.setCellValueFactory(new PropertyValueFactory<Familia, String>("pesReferencia"));
		colunaNum.setCellValueFactory(new PropertyValueFactory<Familia, Integer>("numero"));
		//colunaData.setCellValueFactory(new PropertyValueFactory<Familia, Date>("dataEntrada"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Familia, Date>("dataEntrada"));
		
        
        colunaData.setCellFactory( cell -> {          
              
            return new TableCell<Familia, Date>() {
                //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                
                @Override
                protected void updateItem(Date item, boolean empty) {
                   super.updateItem(item, empty);
                   if( !empty ) {
                      setText( converteData(item));
                   }else {
                      setText("");
                      setGraphic(null);
                   }
                }
            };        
         } );
		
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
		List<Familia> pessoas = fdao.obterCondicao2("ativo","1", "id_unidade",Integer.toString(unidade));
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
			controlador.setUnidade(this.unidade);
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
			
			controlador.setUnidade(this.unidade);
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
			
//			ReativarFamiliaControlador controlador = loader.getController();	
			
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
			
			//ListaFamiliaVisualizar controlador = loader.getController();
			
			
			
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


}
