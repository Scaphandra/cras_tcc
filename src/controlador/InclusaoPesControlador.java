package controlador;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.basico.Pessoa;
import modelo.dao.DAO;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

public class InclusaoPesControlador implements Initializable{
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtDn;
	
	@FXML
	private TextField txtNomeMae;
	
	@FXML
	private TextField txtCpf;
	
	@FXML
	private TextField txtRg;
	
	@FXML
	private TextField txtNis;
	
	@FXML
	private TextField txtOcupacao;
	
	@FXML
	private TextField txtRenda;
	
	@FXML
	private ComboBox <CorRaca> boxCor;
	
	ObservableList <CorRaca> obsCor;
	
	private CorRaca cor;
	
	@FXML
	private ComboBox <Sexo> boxSexo;
	
	ObservableList <Sexo> obsSexo;
	
	private Sexo sexo;
	
	@FXML
	private ComboBox <Genero> boxGenero;
	
	ObservableList <Genero> obsGenero;
	
	private Genero genero;
	
	@FXML
	private ComboBox <Escolaridade> boxEscolaridade;
	
	ObservableList <Escolaridade> obsEscolaridade;
	
	private Escolaridade escolaridade;
	
	
	@FXML
	private Button salvar;

	@FXML
	private Button cancelar;
	
	@FXML
	private CheckBox scfv = new CheckBox("Prioritario SCFV");
	@FXML
	private CheckBox gestante  = new CheckBox("Gestante");
	@FXML
	private CheckBox responsavel = new CheckBox("Responsável Familiar");
	@FXML
	private CheckBox deficiencia = new CheckBox("Pessoa com Deficiência");

	@FXML
	public void clicarCor() {
		
		cor = boxCor.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	public void clicarSexo() {
		
		sexo = boxSexo.getSelectionModel().getSelectedItem();
	
	}
	@FXML
	public void clicarGenero() {
		
		genero = boxGenero.getSelectionModel().getSelectedItem();
		
	
	}
	@FXML
	public void clicarEscolaridade() {
		
		escolaridade = boxEscolaridade.getSelectionModel().getSelectedItem();
	
	}
	
	@FXML
	public void clicarSalvar() throws ParseException {
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		data = formato.parse(txtDn.getText());
		DAO <Pessoa> dao = new DAO(Pessoa.class);
		Pessoa p = new Pessoa();
		p.setNome_pes(txtNome.getText());
		p.setDataNascimento(data);
		p.setCpf_pes(txtCpf.getText());
		p.setRg(txtRg.getText());
		p.setNis(txtNis.getText());
		p.setSexo(sexo);
		p.setGenero(genero);
		p.setEscolaridade_pes(escolaridade);
		p.setCor(cor);
		p.setNomeMae(txtNomeMae.getText());
		p.setRenda(Double.parseDouble(txtRenda.getText()));
		p.setOcupacao(txtOcupacao.getText());
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		EnumSet<CorRaca> cores = EnumSet.allOf(CorRaca.class);
		obsCor = FXCollections.observableArrayList(cores);
		boxCor.setItems(obsCor);
		
		EnumSet<Sexo> sexos = EnumSet.allOf(Sexo.class);
		obsSexo = FXCollections.observableArrayList(sexos);
		boxSexo.setItems(obsSexo);
		
		EnumSet<Genero> gens = EnumSet.allOf(Genero.class);
		obsGenero = FXCollections.observableArrayList(gens);
		boxGenero.setItems(obsGenero);
		
		EnumSet<Escolaridade> esc = EnumSet.allOf(Escolaridade.class);
		obsEscolaridade = FXCollections.observableArrayList(esc);
		boxEscolaridade.setItems(obsEscolaridade);
		
		
		
	}
	
	
}
