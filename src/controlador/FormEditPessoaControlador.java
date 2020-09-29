package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.basico.Beneficio;
import modelo.basico.Pessoa;
import modelo.dao.DAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

public class FormEditPessoaControlador implements Initializable{
	
	@FXML
	private Label idPessoa;
	@FXML
	private CheckBox pbf_b;
	
	@FXML
	private CheckBox bpci_b;
	
	@FXML
	private CheckBox bpcd_b;
	
	@FXML
	private CheckBox nova_b;
	
	@FXML
	private CheckBox outro_b;
	
	private CorRaca cor;
	
	private Sexo sexo;
	
	private Genero genero;
	
	private Escolaridade escolaridade;
	
	private boolean pbf;
	private boolean bpci;
	private boolean bpcd;
	private boolean nv;
	private boolean outro;
	
	
	private Pessoa entidade;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField dataNasc;
	
	@FXML
	private TextField nomeMae;
	
	@FXML
	private TextField cpf;
	
	@FXML
	private TextField rg;
	
	@FXML
	private TextField nis;
	
	@FXML
	private TextField ocupacao;
	
	@FXML
	private TextField parentesco;
	
	@FXML
	private TextField renda;
	
	//deficiencia.selectdProperty().getValue(); -> retorna um boolean
	@FXML
	private CheckBox deficiencia;
	
	@FXML
	private CheckBox gestante;
	
	@FXML
	private CheckBox scfv;

	@FXML
	private CheckBox prioritario;
	
	private boolean pri;
	private boolean ges;
	private boolean def;
	private boolean scfvB;
	
	@FXML
	private ComboBox<CorRaca> boxCor;
	private ObservableList<CorRaca> obsCor;

	@FXML
	private ComboBox<Sexo> boxSexo;
	private ObservableList <Sexo> obsSexo;
	
	@FXML
	private ComboBox <Genero> boxGenero;
	private ObservableList <Genero> obsGenero;
	
	@FXML
	private ComboBox <Escolaridade> boxEscolaridade;
	private ObservableList <Escolaridade> obsEscolaridade;
	
	@FXML
    private TextField valorBolsa;

    @FXML
    private TextField valorBpci;

    @FXML
    private TextField valorBpcd;

    @FXML
    private TextField valorNv;

    @FXML
    private TextField valorOutro;
	
	@FXML
	private Button salvar;
	
	@FXML
	private Button cencelar;
	
	
	public void setPessoa(Pessoa entidade) {
		this.entidade = entidade;
	}
	
	@FXML
	public void clicarSalvar() {
		System.out.println("salvar");
		criarListaBeneficio();
		salvarPessoa();
	}

	@FXML
	public void clicarCancelar() {
		
		System.out.println(pbf_b.selectedProperty().getValue());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
			
	}
	
	@FXML
	public void clicarBeneficioBpci() {
		
		bpci = bpci_b.selectedProperty().getValue();
		System.out.println(bpci);
	}
	
	
	@FXML
	public void clicarBeneficioPbf() {
		pbf = (boolean)pbf_b.selectedProperty().getValue();
		
	}
	@FXML
	public void clicarBeneficioBpcd() {
		bpcd = (boolean) bpcd_b.selectedProperty().getValue();
	}
	@FXML
	public void clicarBeneficioNv() {
		nv = (boolean) nova_b.selectedProperty().getValue();
	}
	@FXML
	public void clicarBeneficioOutro() {
		outro = (boolean) outro_b.selectedProperty().getValue();
	}
	@FXML
	public void clicarGestante() {
		ges = gestante.selectedProperty().getValue();
	}
	@FXML
	public void clicarDeficiencia() {
		def = deficiencia.selectedProperty().getValue();
	}
	@FXML
	public void clicarScfv() {
		scfvB = scfv.selectedProperty().getValue();
	}
	@FXML
	public void clicarPrioritario() {
		pri = prioritario.selectedProperty().getValue();
	}
	
	@FXML
	public void clicarBoxCor() {
		 cor = (CorRaca) boxCor.getSelectionModel().getSelectedItem();
		 
	}
	@FXML
	public void clicarBoxSexo() {
		sexo = (Sexo) boxSexo.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	public void clicarBoxGenero() {
		genero = (Genero) boxGenero.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	public void clicarBoxEscolaridade() {
		escolaridade = (Escolaridade) boxEscolaridade.getSelectionModel().getSelectedItem();
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldDouble(renda);
		Constraints.setTextFieldMaxLength(cpf, 11);
		Constraints.setTextFieldMaxLength(nis, 11);
		carregarComboBox();
	}
	
	private void carregarComboBox() {
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
	
	private List<Beneficio> criarListaBeneficio() {
		
		List <Beneficio> beneficios = new ArrayList<>();
		
		if(pbf == true) {
			beneficios.add(new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty()? 0.0:Double.parseDouble(valorBolsa.getText()), null));	
		}else {
			beneficios.add(new Beneficio());
		}
		if(bpci == true) {
			beneficios.add(new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty()? 0.0:Double.parseDouble(valorBpci.getText()), null));	
		}else {
			beneficios.add(new Beneficio());
		
		}
		if(bpcd == true) {
			beneficios.add(new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty()? 0.0:Double.parseDouble(valorBpcd.getText()), null));	
		}else {
			beneficios.add(new Beneficio());
		}
		if(nv == true) {
			beneficios.add(new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty()? 0.0:Double.parseDouble(valorNv.getText()), null));	
		}else {
			beneficios.add(new Beneficio());
			
		}
		if(outro== true) {
			beneficios.add(new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty()? 0.0:Double.parseDouble(valorOutro.getText()), null));	
		}else {
			beneficios.add(new Beneficio());
			
		}
		
		return beneficios;
	}
	
	public void carregarDadosPessoa() {
		if(entidade == null) {
			throw new IllegalStateException("A pessoa não existe no banco de dados");
		}
		nome.setText(entidade.getNome_pes());
		idPessoa.setText(String.valueOf(entidade.getId_pessoa()));
		
	}
	
	public void salvarPessoa() {
		
		List<Beneficio> b = criarListaBeneficio();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = dataNasc.getText();
		Date data = new Date();
		
		
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
		Pessoa p = new Pessoa(nome.getText(), 
				cpf.getText(), 
				rg.getText(), 
				nis.getText(), 
				data, 
				sexo, 
				genero, 
				nomeMae.getText(), 
				cor, escolaridade, 
				Double.parseDouble(renda.getText()), 
				ocupacao.getText(), 
				pri, 
				b, 
				ges, 
				def, 
				scfvB, 
				null, 
				parentesco.getText());
		dao.abrirTransacao();
		dao.incluir(p);
		dao.fecharTransacao();
		dao.fechar();
	}

}
