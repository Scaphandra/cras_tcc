package controlador;

import java.net.URL;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.App;
import gui.util.Util;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.PessoaDAO;
import modelo.enumerados.Composicao;

public class FormularioComposicaoControlador implements Initializable{

	
	private Pessoa pessoa;
	
	@FXML
	private TableView<Pessoa> tabelaComposicao;

	@FXML
	private TableColumn<Pessoa, String> colunaNome;

	@FXML
	private TableColumn <Pessoa, Composicao> colunaComposicao;
	    
	PessoaDAO dao = new PessoaDAO();

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void clicarOk(ActionEvent event) {

		Util.atual(event).close();
		
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void carregarTabela(Familia f, Pessoa p) {
		this.pessoa = p;
		List <Pessoa> lista = dao.obterCondicao("id_familia", f.getId().toString());
		ObservableList obsP = FXCollections.observableArrayList(lista);
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaComposicao.setCellValueFactory(param-> {

			Pessoa pes = (Pessoa) param.getValue();
	
			if(pes.getComposicao()==null) {
				pes.setComposicao(Composicao.N);
			}

			Composicao compo = pes.getComposicao();
			return new SimpleObjectProperty<Composicao>(compo);
		});
		
		dao.abrirTransacao();
		colunaComposicao.setCellFactory((column)->{
			return new TableCell<Pessoa, Composicao>(){
				@Override
				protected void updateItem(Composicao item, boolean empty) {
					super.updateItem(item, empty);
					if(item != null && !empty) {
						
						EnumSet<Composicao> compos = EnumSet.allOf(Composicao.class);
						ObservableList <Composicao> obsCompo = FXCollections.observableArrayList(compos);
						ComboBox<Composicao> combo = new ComboBox<Composicao>(obsCompo);
						Pessoa pessoa = (Pessoa) getTableRow().getItem();
						ObjectProperty obs = new SimpleObjectProperty<Composicao>(pessoa.getComposicao());
						combo.valueProperty().bindBidirectional(obs);
						if(pessoa.getComposicao()==Composicao.RF) {
							combo.setDisable(true);
						}
						combo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
							Composicao c = combo.getSelectionModel().getSelectedItem();
							salvarComposicao(c, pessoa);
						
						});
						
						setGraphic(combo);
						
					}else {
						setText(null);
						setGraphic(null);
					}
				}
			};
		});
		
		dao.fecharTransacao().fechar();


		tabelaComposicao.setEditable(true);
		tabelaComposicao.setItems(obsP);
		tabelaComposicao.setMaxHeight(236);
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaComposicao.prefHeightProperty().bind(cena.heightProperty());

	}
	
	public void salvarComposicao(Composicao c, Pessoa p) {
		PessoaDAO daop = new PessoaDAO();
		daop.abrirTransacao();
		daop.obterPorID(p.getId());
		p.setComposicao(c);
		daop.atualizar(p);
		daop.fecharTransacao().fechar();
	}
	
}
