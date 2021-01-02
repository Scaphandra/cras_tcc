package controlador;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.App;
import gui.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import modelo.dao.PessoaDAO;
import modelo.enumerados.PessoaEstado;

public class EscolherListaPessoaControlador implements Initializable {

	
	private Pessoa pessoa;
	
	private PessoaDAO dao = new PessoaDAO();
	
	private FamiliaDAO daof = new FamiliaDAO();
	
	private Familia familia;
	
	@FXML
	private Button selecionar;
	
	@FXML
	private TextField busca;
	
	@FXML
	private Label idFamilia;
	
	@FXML
	private TableView <Pessoa> tabelaPessoa;
	
	private List<Pessoa> lista;
	
	private ObservableList <Pessoa> obsPessoas;

	@FXML 
	private TableColumn<Pessoa, Long> colunaId;
	
	@FXML 
	private TableColumn<Pessoa, String> colunaNome;
	
	@FXML 
	private TableColumn<Pessoa, Date> colunaData;
	
	@FXML 
	private TableColumn <Pessoa, Integer> colunaIdade;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//idFamilia.setText("Selecione a pessoa para incluir na Família de código: " + familia.getId().toString());
		
		filtrar();
		
		iniciarComponentes();

		iniciarTabela();
		
	}
	
	@FXML
	void clicarSelecionar(ActionEvent event) {
		dao.abrirTransacao();
		daof.abrirTransacao();
		familia = daof.obterPorID(familia.getId());
		pessoa = dao.obterPorID(pessoa.getId());
		pessoa.setAtivo(true);
		pessoa.setPesReferencia(false);
		pessoa.setEstado(PessoaEstado.P);
		familia.setPessoas(pessoa);	
		dao.incluir(pessoa);
		daof.atualizar(familia);
		dao.fecharTransacao().fechar();
		daof.fecharTransacao().fechar();
		
		Util.atual(event).close();
		
	}
	
	
	public void setFamilia(Familia f) {
		
		this.familia = f;
	
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarTabela() {
		lista = dao.obterCondicao("estado","I");
		obsPessoas = FXCollections.observableArrayList(lista);
		tabelaPessoa.setItems(obsPessoas);
		tabelaPessoa.setMaxHeight(180);
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaPessoa.prefHeightProperty().bind(cena.heightProperty());
		tabelaPessoa.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaPessoa.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
		        System.out.println("Selected Value " + item);
		        pessoa = (Pessoa) item;
		        busca.setText(pessoa.getNome());
		        if(busca.getText().equals("")) {
		        	lista = dao.obterCondicao("estado","I");
		        }
		  
		        //System.out.println(item.toString().substring(18,19));
		       //pessoa = p;
				
			}
			
		});
	}
	
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Pessoa, Date>("dataNascimento"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
	}
	
	@FXML
	private void filtrar() {
		
		busca.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("textfield changed from " + oldValue + " to " + newValue);
		    obsPessoas.clear();
		    if(newValue.length()==0){
		    	lista = dao.obterTodos();
		    	obsPessoas.addAll(lista);
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
			
			obsPessoas.addAll(lista);			
		}
		iniciarTabela();
	}

}
