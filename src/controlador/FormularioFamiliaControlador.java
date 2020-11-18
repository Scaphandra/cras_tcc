package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import gui.DataChangeListener;
import gui.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.basico.Beneficio;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.basico.Tecnico;
import modelo.basico.Unidade;
import modelo.dao.DAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.EnderecoTipo;
import modelo.enumerados.SituacaoFamilia;

public class FormularioFamiliaControlador implements Initializable{

	private Familia entidade;
	
	Long id = null;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
    private CheckBox checkPerfilCREAS;
	
	private boolean creas;

    @FXML
    private CheckBox checkMulherChefe;
    
    private boolean mulherChefe;

    @FXML
    private Label idFamilia;

    @FXML
    private TextField txtDataInclusao;

    @FXML
    private TextField txtDataCad;

    @FXML
    private CheckBox checkDescumprimento;
    
    private boolean descumprimento;

    @FXML
    private CheckBox checkPBF;
    
    private boolean pbf;

    @FXML
    private CheckBox checkBPCI;
    
    private boolean bpci;

    @FXML
    private CheckBox checkBPCD;
    
    private boolean bpcd;

    @FXML
    private CheckBox checkNV;
    
    private boolean nova;

    @FXML
    private CheckBox checkOutro;
    
    private boolean outro;

    @FXML
    private TextField txtTotalBeneficio;

    @FXML
    private ChoiceBox<EnderecoTipo> comboTipoEndereco;
    private ObservableList<EnderecoTipo> obsEnd;
    private EnderecoTipo end;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtComplemento;
    
    @FXML
    private TextField txtPerCapita;

    @FXML
    private ComboBox<String> comboBairro;
    private ObservableList <String> obsBairro;
    private String bairro;

    @FXML
    private TextField txtTel1;

    @FXML
    private TextField txtTel2;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtRenda;

    @FXML
    private ComboBox<Tecnico> comboTecnico;
    private ObservableList <Tecnico> obsTecnico;
    private Tecnico tecnico;

    @FXML
    private ComboBox<SituacaoFamilia> comboSituacao;
    private ObservableList <SituacaoFamilia> obsSituacao;
    private SituacaoFamilia situacao;

    @FXML
    private TextField txtTel3;

    @FXML
    private TextField txtReferencia;

    @FXML
    private Label idPessoa;

    @FXML
    private TableView<Pessoa> tabelaPessoas;

    @FXML
    private TableColumn<Pessoa, String> colunaNome;

    @FXML
    private TableColumn<Pessoa, Integer> colunaIdade;

    @FXML
    private TableColumn<?, ?> colunaSelecionar;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;
	
	
	public void setFamilia(Familia entidade) {
		this.entidade = entidade;
	}


	@FXML
	public void clicarSalvar(ActionEvent evento) {
		
		if(entidade == null) {
			throw new IllegalStateException("Família não está no banco");
		}
		
		if(id!=null) {
			
//			criarListaBeneficio();
			salvarPessoaEditado();
			notificar();
			Util.atual(evento).close();
			
		}

		if(id==null) {
//		criarListaBeneficio();
//		salvarPessoa();
		notificar();
		Util.atual(evento).close();
		}
		
		
	}

	public void notificar() {//na classe que emite evento
		for(DataChangeListener listener: dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}
	public void inscreverDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
		
	

	@FXML
	public void clicarCancelar(ActionEvent evento) {
		
		Util.atual(evento).close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
		carregarTabela();
		ListaPessoaControlador l = new ListaPessoaControlador();
		l.carregarPessoas();
			
	}
	
	@FXML
	public void clicarHomonimo() {
		creas = checkPerfilCREAS.selectedProperty().getValue();
	}
	

	@FXML
	public void clicarCREAS() {
		creas = checkPerfilCREAS.selectedProperty().getValue();
	}

	
	@FXML
	public void clicarBoxEndereco() {
		 end = (EnderecoTipo) comboTipoEndereco.getSelectionModel().getSelectedItem();
		 
	}
	@FXML
	public void clicarBoxBairro() {
		
		bairro = (String) comboBairro.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	public void clicarBoxTecnico() {
		
		tecnico = comboTecnico.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	public void clicarBoxSituacao() {
		situacao = comboSituacao.getSelectionModel().getSelectedItem();
		
	}
	
	private void initializeNodes() {
//		Constraints.setTextFieldMaxLength(this.nis, 11);
		carregarComboBox();
		
		
//		MaskFieldUtil.cpfField(this.cpf);
//		MaskFieldUtil.dateField(this.dataNasc);
//		MaskFieldUtil.monetaryField(this.renda);
//		MaskFieldUtil.monetaryField(this.valorBolsa);
//		MaskFieldUtil.monetaryField(this.valorBpci);
//		MaskFieldUtil.monetaryField(this.valorBpcd);
//		MaskFieldUtil.monetaryField(this.valorNv);
//		MaskFieldUtil.monetaryField(this.valorOutro);
	}
	
	private void carregarTabela() {
		
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
		//colunaSelecionar.setCellValueFactory(new PropertyValueFactory<>("familia"));
	}
	
	private void carregarComboBox() {
		//lista do tipo de endereço
		EnumSet<EnderecoTipo> ends = EnumSet.allOf(EnderecoTipo.class);
		obsEnd = FXCollections.observableArrayList(ends);
		comboTipoEndereco.setItems(obsEnd);
		comboTipoEndereco.getSelectionModel().selectFirst();
		//lista da área de abrangência cadastrada na unidade
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("cras_tcc");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Unidade uni = em.find(Unidade.class, 1L);
		List <String> bairros = uni.getAreaAbrangencia();
		obsBairro = FXCollections.observableArrayList(bairros);
		comboBairro.setItems(obsBairro);
		comboBairro.getSelectionModel().selectFirst();
		
		//lista de técnicos cadastrados no banco de dados
		
		String jpql = "select e from Tecnico e";
		TypedQuery<Tecnico> query = em.createQuery(jpql,Tecnico.class);
		List <Tecnico> tecs = query.getResultList();
		obsTecnico = FXCollections.observableArrayList(tecs);
		comboTecnico.setItems(obsTecnico);
		
		EnumSet<SituacaoFamilia> situacoes = EnumSet.allOf(SituacaoFamilia.class);
		obsSituacao = FXCollections.observableArrayList(situacoes);
		comboSituacao.setItems(obsSituacao);
		comboSituacao.getSelectionModel().selectFirst();
		
		em.close();
		emf.close();
	}
	

	
	protected String converteData(Date dt) {
		if(dt==null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");
		
		return formatBra.format(dt).toString().replace("/","");
	}
	
	public void preencherFamilia() {
		if(entidade == null) {
			throw new IllegalStateException("Família não está no banco");
		}
		
		this.id = entidade.getId();
		txtReferencia.setText(entidade.getPesReferencia().getNome());
		idFamilia.setText("código: " + entidade.getId().toString());
		txtDataInclusao.setText(converteData(entidade.getDataEntrada()));
		idPessoa.setText("código da pessoa: " + entidade.getPesReferencia().getId());
		comboTipoEndereco.getSelectionModel().select(entidade.getEndereco().getTipo_endereco());
		txtLogradouro.setText(entidade.getEndereco().getLogradouro());
		txtNumero.setText(""+entidade.getEndereco().getNumero());
		checkPerfilCREAS.setSelected(entidade.isPerfilCreas());
		txtComplemento.setText(entidade.getEndereco().getComplemento());
		comboBairro.getSelectionModel().select(entidade.getEndereco().getBairro());
		checkMulherChefe.setSelected(entidade.isMulherChefe());
		txtCep.setText(entidade.getEndereco().getCep());
		txtTel1.setText(entidade.getTelefone().get(0));
		txtTel2.setText(entidade.getTelefone().get(1));
		txtTel3.setText(entidade.getTelefone().get(2));
		comboTecnico.getSelectionModel().select(entidade.getTecnico());
		comboSituacao.getSelectionModel().select(entidade.getSituacao());
		txtDataCad.setText(converteData(entidade.getDataCad()));
		checkDescumprimento.setSelected(entidade.isDescumprimento());
		txtRenda.setText(entidade.getTotalRenda()+"");
		
//		
//		valorBolsa.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.PBF)*10));
//		valorBpci.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.BPCI)*10));
//		valorBpcd.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.BPCDEF)*10));
//		valorNv.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.NV)*10));
//		valorOutro.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.O)*10));
		
	}
	
	
	public void salvarFamilia() {
		
		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(txtLogradouro, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(txtNumero, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(txtDataInclusao, Validator.createEmptyValidator("Campo Obrigatório"));
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = txtDataInclusao.getText();
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		DAO<Pessoa> dao = new DAO<>(Pessoa.class);
//		dao.abrirTransacao();
		
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("cras_tcc");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Familia f = new Familia();
		
		f.setDataEntrada(data);
		f.setPesReferencia())
		p.setCpf(cpf.getText().isEmpty()?"":cpf.getText());
		p.setRg(rg.getText()==null?"":rg.getText());
		p.setNis(nis.getText()==null?"":nis.getText());
		p.setNomeMae(nomeMae.getText()==null?"Não informado":nomeMae.getText());
		p.setRenda(renda.getText()==null?0.0:Double.parseDouble(renda.getText().replace(".","").replace(",",".")));
		p.setOcupacao(ocupacao.getText()==null?"Não informado":ocupacao.getText());
		p.setParentesco(parentesco.getText()==null?"Não informado":parentesco.getText());
		p.setDataNascimento(data);
		p.setSexo(sexo); 
		p.setGenero(genero);
		p.setCor(cor);
		p.setEscolaridade_pes(escolaridade); 
		p.setPrioritarioSCFV(pri);
		p.setBeneficios(b);
		p.setGestante(ges); 
		p.setComDeficiencia(def); 
		p.setNoSCFV(scfvB); 
			
//		em.persist(p);
//		em.getTransaction().commit();
//		em.close();
		
		dao.incluirAtualizar(p);
		dao.fecharTransacao();
		dao.fechar();
		notificar();
	}
	public void salvarPessoaEditado() {
		
		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));
		
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
		
		dao.abrirTransacao();
		
		
//		EntityManagerFactory emf;
//		EntityManager em;
//		emf = Persistence.createEntityManagerFactory("cras_tcc");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
		
		//Pessoa p = em.find(Pessoa.class, id);
		Pessoa p = dao.obterPorID(Pessoa.class, id);
		
		
		p.setNome(nome.getText());
		p.setHomonimo(homo);
		p.setCpf(cpf.getText().isEmpty()?"":cpf.getText());
		p.setRg(rg.getText()==null?"":rg.getText());
		p.setNis(nis.getText()==null?"":nis.getText());
		p.setNomeMae(nomeMae.getText()==null?"Não informado":nomeMae.getText());
		p.setRenda(renda.getText()==null?0.0:Double.parseDouble(renda.getText().replace(".","").replace(",",".")));
		p.setOcupacao(ocupacao.getText()==null?"Não informado":ocupacao.getText());
		p.setParentesco(parentesco.getText()==null?"Não informado":parentesco.getText());
		p.setDataNascimento(data);
		p.setSexo(sexo); 
		p.setGenero(genero);
		p.setCor(cor);
		p.setEscolaridade_pes(escolaridade); 
		p.setPrioritarioSCFV(pri);
		p.setBeneficios(b);
		p.setGestante(ges); 
		p.setComDeficiencia(def); 
		p.setNoSCFV(scfvB); 
		
		
//		em.merge(p);
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
		dao.atualizar(p);
		dao.fecharTransacao();
		dao.fechar();
		notificar();
	}
	


}
