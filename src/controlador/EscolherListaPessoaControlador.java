package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import aplicacao.App;
import gui.util.Util;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
	
	private boolean mudarRF;
	
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


	public void mudarRF(boolean b) {
		this.mudarRF = b;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//idFamilia.setText("Selecione a pessoa para incluir na Família de código: " + familia.getId().toString());
		
		filtrar();
		
		iniciarComponentes();

		iniciarTabela();
		
	}
	
	public Pessoa getPessoa() {
		
		return this.pessoa;
	}
	
	@FXML
	void clicarSelecionar(ActionEvent event) {
		
		if(pessoa.getFamilia()!=null && pessoa.getEstado()==PessoaEstado.P) {
			dao.abrirTransacao();
			daof.abrirTransacao();
			familia = daof.obterPorID(familia.getId());
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Esta pessoa pertence a uma família desligada");
			alert.setHeaderText("A pessoa selecionada será retirada de uma família desligada");
			alert.setContentText("Escolha o que deseja fazer.");
			alert.show();
			ButtonType btCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
			ButtonType btProsseguir = new ButtonType("Prosseguir");
			alert.getButtonTypes().setAll(btCancelar, btProsseguir);
			Optional <ButtonType> result = alert.showAndWait();
			
			if(result.get()==btProsseguir) {
				dao.abrirTransacao();
				daof.abrirTransacao();
				familia = daof.obterPorID(familia.getId());
				pessoa = dao.obterPorID(pessoa.getId());
				Familia f = daof.obterPorID(pessoa.getFamilia());
				f.excluirPessoa(pessoa);
				pessoa.setAtivo(true);
				familia.setPessoas(pessoa);
				if(mudarRF) {
					Pessoa antigaRF = dao.obterPorID(familia.getPesReferencia().getId());
					familia.trocarRf(antigaRF, pessoa);
				}
				daof.atualizar(f);
				daof.atualizar(familia);
				dao.atualizar(pessoa);
				dao.fecharTransacao().fechar();
				daof.fecharTransacao().fechar();
				//Util.atual(event).close();
			}
			
		}
		else if(pessoa.getFamilia()!=null && pessoa.getEstado()==PessoaEstado.RF) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Pessoa de Referência de Família Desligada!");
			alert.setHeaderText("A pessoa selecionada é referência de uma família desligada. "
					+ "\nSe prosseguir, a família a qual a pessoa pertencia \n não poderá mais ser reativada.");
			alert.setContentText("Escolha o que deseja fazer.");
			alert.show();
			ButtonType btCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
			ButtonType btProsseguir = new ButtonType("Prosseguir");
			alert.getButtonTypes().setAll(btCancelar, btProsseguir);
			Optional <ButtonType> result = alert.showAndWait();
			
			if(result.get()==btProsseguir) {
				pessoa = dao.obterPorID(pessoa.getId());
				Familia f = daof.obterPorID(pessoa.getFamilia());
				f.setPesReferencia(null);
				f.excluirPessoa(pessoa);
				pessoa.setAtivo(true);
				pessoa.setPesReferencia(false);
				familia.setPessoas(pessoa);
				if(mudarRF) {
					Pessoa antigaRF = dao.obterPorID(familia.getPesReferencia().getId());
					familia.trocarRf(antigaRF, pessoa);
				}
				daof.atualizar(f);
				daof.atualizar(familia);
				dao.atualizar(pessoa);
				dao.fecharTransacao().fechar();
				daof.fecharTransacao().fechar();
				//Util.atual(event).close();
			}
			
		}else if(pessoa.getFamilia()==null){
			
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
			//Util.atual(event).close();
			
		}
		
		verificarMudarRF();
		
		Util.atual(event).close();
	}

	public void verificarMudarRF() {
		
		if(mudarRF) {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			familia = em.find(Familia.class, familia.getId());
			pessoa = em.find(Pessoa.class, pessoa.getId());
			
			pessoa.setAtivo(true);
			Pessoa antigaRF = em.find(Pessoa.class, familia.getPesReferencia().getId());
			familia.trocarRf(antigaRF, pessoa);
			
			em.merge(pessoa);
			em.merge(antigaRF);
			em.merge(familia);
			em.getTransaction().commit();
			em.close();
			emf.close();
		}
		
	}
	public void setFamilia(Familia f) {
		
		this.familia = f;
	
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void iniciarTabela() {
		lista = dao.obterCondicao("ativo","0");
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
		        	lista = dao.obterCondicao("ativo","0");
		        }
		  
		        //System.out.println(item.toString().substring(18,19));
		       //pessoa = p;
				
			}
			
		});
	}
	
	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();
	}
	
	private void iniciarComponentes() {
		
		colunaId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Pessoa, Date>("dataNascimento"));
		colunaData.setCellFactory( cell -> {          
             
	            return new TableCell<Pessoa, Date>() {
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
