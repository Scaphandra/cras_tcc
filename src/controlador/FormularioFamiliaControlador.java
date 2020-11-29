package controlador;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

public class FormularioFamiliaControlador implements Initializable {

	private Familia entidade;
	
	private Long id;

	private Pessoa pessoa;

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
	private TextField txtLogradouro;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtComplemento;

	@FXML
	private ComboBox<String> comboBairro;
	private ObservableList<String> obsBairro;
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
	private ObservableList<Tecnico> obsTecnico;
//	private Map <Long, String> tecs = new HashMap<Long, String>();
//	private String tecNome;
	private Tecnico tecnico;

	@FXML
	private ComboBox<SituacaoFamilia> comboSituacao;
	private ObservableList<SituacaoFamilia> obsSituacao;
	private SituacaoFamilia situacao;

	@FXML
	private TextField txtTel3;

	@FXML
	private TextField txtReferencia;

	@FXML
	private Label idPessoa;

	@FXML
	private TextField txtPerCapita;

	@FXML
	private ComboBox<EnderecoTipo> comboTipoEndereco;
	private ObservableList<EnderecoTipo> obsEnd;
	private EnderecoTipo end;

	@FXML
	private TableView<Pessoa> tabelaPessoas;

	@FXML
	private TableColumn<Pessoa, String> colunaNome;

	@FXML
	private TableColumn<Pessoa, Integer> colunaIdade;

	@FXML
	private TableColumn<Pessoa, String> colunaParentesco;

	@FXML
	private TableColumn<Pessoa, Double> colunaRenda;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btIncluirPessoa;

	@FXML
	private Button btAterarPesReferencia;

	public void setFamilia(Familia familia) {
		this.entidade = familia;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@FXML
	void clicarBoxBairro(ActionEvent event) {
		bairro = (String) comboBairro.getSelectionModel().getSelectedItem();
	}

	@FXML
	void clicarBoxEndereco(ActionEvent event) {
		end = (EnderecoTipo) comboTipoEndereco.getSelectionModel().getSelectedItem();
	}

	@FXML
	void clicarBoxSituacao(ActionEvent event) {
		situacao = comboSituacao.getSelectionModel().getSelectedItem();
	}

	@FXML
	void clicarBoxTecnico(ActionEvent event) {
//		DAO <Tecnico> dao = new DAO<>(Tecnico.class);		
//		tecNome = comboTecnico.getSelectionModel().getSelectedItem();
//		for(Entry<Long, String> registro: tecs.entrySet()){
//			if(registro.getValue() == tecNome) {
//				tecnico = dao.obterPorID(Tecnico.class, registro.getKey());
//			}
//		}
		
		tecnico = comboTecnico.getSelectionModel().getSelectedItem();
		
	}

	@FXML
	void clicarCREAS(ActionEvent event) {

	}

	@FXML
	void clicarCancelar(ActionEvent event) {

	}

	@FXML
	void clicarDescumprimento(ActionEvent event) {

	}

	@FXML
	void clicarMulher(ActionEvent event) {

	}

	@FXML
	public void clicarIncluirPessoa() {

	}

	@FXML
	public void clicarAlterarPesReferencia() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarComboBox();
		//carregarTabela();

	}

	private void carregarTabela() {
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
		colunaParentesco.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("parentesco"));
		DecimalFormat d = new DecimalFormat();
		colunaRenda.setCellValueFactory(new PropertyValueFactory<Pessoa, Double>(d.format("renda")));
		
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
				throw new IllegalStateException("Pessoa não está no banco");
			}else {
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
				for (Beneficio b: entidade.getBeneficios()) {
					if(b.getNome()==BeneficioTipo.PBF) {
						checkPBF.setSelected(true);
					}
					if(b.getNome()==BeneficioTipo.BPCDEF) {
						checkBPCD.setSelected(true);
					}
					if(b.getNome()==BeneficioTipo.BPCI) {
						checkBPCI.setSelected(true);
					}
					if(b.getNome()==BeneficioTipo.NV) {
						checkNV.setSelected(true);
					}
					if(b.getNome()==BeneficioTipo.O) {
						checkOutro.setSelected(true);
					}
				}
				txtTotalBeneficio.setText(entidade.getTotalBeneficio()+"");
			}
	}

	private void carregarComboBox() {
		// LISTA TIPO ENDERECO DIRETO DO BANCO
		EnumSet<EnderecoTipo> ends = EnumSet.allOf(EnderecoTipo.class);
		obsEnd = FXCollections.observableArrayList(ends);
		comboTipoEndereco.setItems(obsEnd);
		comboTipoEndereco.getSelectionModel().selectFirst();
		
		// LISTA ÁREA DE ABRANGÊNCIA DIRETO DO BANCO
//		EntityManagerFactory emf;
//		EntityManager em;
//		emf = Persistence.createEntityManagerFactory("cras_tcc");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
		DAO<Unidade> daoU = new DAO<>(Unidade.class);
		Unidade uni = daoU.obterPorID(Unidade.class, 2L);
		List<String> bairros = uni.getAreaAbrangencia();
		System.out.println(bairros);
		obsBairro = FXCollections.observableArrayList(bairros);
		comboBairro.setItems(obsBairro);
		comboBairro.getSelectionModel().selectFirst();

		// LISTA TÉCNICOS DIRETO DO BANCO
		
		
//		DAO<Tecnico> daoT = new DAO<>(Tecnico.class);
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		String jpql = "select e from Tecnico e";
		
		TypedQuery <Tecnico> query = em.createQuery(jpql, Tecnico.class);
		
		List <Tecnico> tecnicos = query.getResultList();
//		List <Tecnico> ts = daoT.obterTodos();
//		List<String> nomes = new ArrayList<>();
		//colocar um map com chave e valor para pgar o tecinico
//		for(Tecnico t: ts) {
//			tecs.put(t.getId(), t.getNome());
//			nomes.add(t.getNome());
//		}
		//Para pegar a chave no Map é .keySet(), para pegar o valor é .value()
		obsTecnico = FXCollections.observableArrayList(tecnicos);
		comboTecnico.setItems(obsTecnico);
		comboTecnico.getItems().add(null);

		EnumSet<SituacaoFamilia> situacoes = EnumSet.allOf(SituacaoFamilia.class);
		obsSituacao = FXCollections.observableArrayList(situacoes);
		comboSituacao.setItems(obsSituacao);
		comboSituacao.getSelectionModel().selectFirst();

//		em.close();
//		emf.close();
	}
		
}
