package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Atendimento;
import modelo.basico.Funcionario;
import modelo.basico.Pessoa;
import modelo.dao.AtendimentoDAO;

public class ListaAtendimentoControlador implements Initializable{


    @FXML
    private TableView<Atendimento> tabelaAtendimentos;
    
    private ObservableList<Atendimento> obsAtendimentos;

    @FXML
    private TableColumn<Atendimento, Long> colunaId;

    @FXML
    private TableColumn<Atendimento, Date> colunaData;

    @FXML
    private TableColumn<Atendimento, Pessoa> colunaResponsavel;

    @FXML
    private TableColumn<Atendimento, Funcionario> colunaFuncionario;
    
    @FXML
    private TableColumn<Atendimento, Long> colunaFamilia;
    
    private Atendimento atendimento;
    
    private Object valor;
    
    

    public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	@FXML
    void clicarVisualizar(ActionEvent event) {
		
		Stage parent = Util.atual(event);
		visualizar(atendimento.getId(), "/gui/visualizarAtendimento.fxml", parent);
    }

    protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colunaId.setCellValueFactory(new PropertyValueFactory<Atendimento, Long>("id"));
		colunaData.setCellValueFactory(new PropertyValueFactory<Atendimento, Date>("data"));
		colunaResponsavel.setCellValueFactory(new PropertyValueFactory<Atendimento, Pessoa>("pessoa"));
		colunaFuncionario.setCellValueFactory(new PropertyValueFactory<Atendimento, Funcionario>("funcionario"));
		colunaFamilia.setCellValueFactory(new PropertyValueFactory<Atendimento, Long>("idFamilia"));
		colunaData.setCellFactory( cell -> {          
            
            return new TableCell<Atendimento, Date>() {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void carregarTabela() {
		AtendimentoDAO dao = new AtendimentoDAO();
		List<Atendimento> lista = dao.obterPorOrdem("data");
		obsAtendimentos = FXCollections.observableArrayList(lista);
		tabelaAtendimentos.setItems(obsAtendimentos);
		
		Stage cena = (Stage) App.getCena().getWindow();
		
		tabelaAtendimentos.prefHeightProperty().bind(cena.heightProperty());
		tabelaAtendimentos.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaAtendimentos.getSelectionModel();
		        Object item = selectionModel.getSelectedItem();
		        System.out.println("Selected Value " + item);
		        setAtendimento((Atendimento) item);
		        valor = item;	
		        System.out.println(item);
			}
		});
		
	}
	
	private void visualizar(Long id, String nomeView, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();
			
			VisualizarAtendimentoControlador controlador = loader.getController();
			AtendimentoDAO dao = new AtendimentoDAO();
			Atendimento atendimento = dao.obterPorID(id);
			controlador.carregarAtendimento(atendimento);
			
			
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

}
