package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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

import aplicacao.App;
import gui.util.Alerta;
import gui.util.MaskFieldUtil;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Beneficio;
import modelo.basico.Endereco;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.basico.Tecnico;
import modelo.basico.Unidade;
import modelo.dao.DAO;
import modelo.dao.FamiliaDAO;
import modelo.dao.PessoaDAO;
import modelo.enumerados.EnderecoTipo;
import modelo.enumerados.MoradiaTipo;
import modelo.enumerados.SituacaoFamilia;

public class FormularioFamiliaControlador implements Initializable {

	private Familia entidade;

	@SuppressWarnings("unused")
	private Long id = null;
	
	private int idUnidade;

	private Pessoa pessoa;

	FamiliaDAO daof = new FamiliaDAO();

	PessoaDAO daop = new PessoaDAO();

	private boolean familiaNova;

	private Object selecionado;

	@FXML
	private CheckBox checkPerfilCREAS;

	private boolean creas;

	@FXML
	private CheckBox checkMulherChefe;

	private boolean mulherChefe;

	@FXML
	private Label idFamilia;

	@FXML
	private Label labelAtivo;

	@FXML
	private Label labelMotivoDesligamento;

	@FXML
	private Label labelDataDesligamento;

	@FXML
	private TextField txtDataInclusao;

	@FXML
	private TextField txtDataCad;

	@FXML
	private CheckBox checkDescumprimento;

	private boolean descumprimento;

//	@FXML
//	private Label lbPBF;
//
//	@FXML
//	private Label lbBPCI;
//
//	@FXML
//	private Label lbBPCD;
//
//	@FXML
//	private Label lbNV;
//
//	@FXML
//	private Label lbOutro;
	
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
	private TextField txtTel3;

	@FXML
	private TextField txtCep;

	@FXML
	private TextField txtRenda;

	@FXML
	private ComboBox<Tecnico> comboTecnico;
	private ObservableList<Tecnico> obsTecnico;

	private Tecnico tecnico;

	@FXML
	private ComboBox<SituacaoFamilia> comboSituacao;
	private ObservableList<SituacaoFamilia> obsSituacao;
	private SituacaoFamilia situacao;

	@FXML
	private ComboBox<MoradiaTipo> comboTipoMoradia;
	private ObservableList<MoradiaTipo> obsMoradia;
	private MoradiaTipo moradia;

	@FXML
	private TextField txtAlugada;

	@FXML
	private TextField txtReferencia;

	@FXML
	private Label idPessoa;

	@FXML
	private TextField txtPerCapita;

	@FXML
	private TextField txtDataAcompanhamento;

	@FXML
	private TextField txtDataAtendimento;

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
	private TableColumn<Beneficio, String> colunaBeneficio;

	@FXML
	private TableColumn<Pessoa, Long> colId;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btCriarPessoa;

	@FXML
	private Button btEscolherPessoa;

	@FXML
	private Button btAlterarPesReferencia;
	@FXML
	private Button btEditar;

	@FXML
	private Button btRemover;

	@FXML
	private Button btDesligar;

	public boolean isFamiliaNova() {
		return familiaNova;
	}

	public void setFamiliaNova(boolean familiaNova) {
		
		this.familiaNova = familiaNova;
	}

	public void setFamilia(Familia familia) {
		// FamiliaDAO dao = new FamiliaDAO();
		System.out.println(familia.toString());
		// this.entidade = dao.obterPorID(familia.getId());
		this.entidade = familia;

	}

	public Familia getFamilia() {
		return this.entidade;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;

	}
	
	public void setUnidade(int u) {
		this.idUnidade = u;
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

		tecnico = comboTecnico.getSelectionModel().getSelectedItem();
	}

	@FXML
	void clicarBoxMoradia(ActionEvent event) {

		moradia = (MoradiaTipo) comboTipoMoradia.getSelectionModel().getSelectedItem();

		if (moradia == MoradiaTipo.A || moradia == MoradiaTipo.S) {
			txtAlugada.setDisable(false);
		} else {
			txtAlugada.setText("0");
			txtAlugada.setDisable(true);

		}
	}

	@FXML
	void clicarCREAS(ActionEvent event) {
		creas = checkPerfilCREAS.selectedProperty().getValue();
	}

	@FXML
	void clicarCancelar(ActionEvent event) {

		Util.atual(event).close();
	}

	@FXML
	void clicarDesligar(ActionEvent event) {
		Stage parentStage = Util.atual(event);

		criarFormularioDesligar(this.entidade, "/gui/formDesligamentoFamilia.fxml", parentStage);
	}

	@FXML
	void clicarDescumprimento(ActionEvent event) {
		descumprimento = checkDescumprimento.selectedProperty().getValue();
	}

	@FXML
	void clicarMulher(ActionEvent event) {
		mulherChefe = checkMulherChefe.selectedProperty().getValue();
	}

	@FXML
	public void clicarCriarPessoa(ActionEvent event) {
		Stage parentStage = Util.atual(event);
		criarFormularioPessoaNova("/gui/formularioPessoa.fxml", parentStage);
		carregarPessoas(this.entidade.getId());
		preencherFamilia();
	}

	@FXML
	// do banco
	public void clicarEscolherPessoa(ActionEvent event) {
		Stage parentStage = Util.atual(event);

		criarFormularioPessoaBanco("/gui/escolherListaPessoa.fxml", parentStage);

		carregarPessoas(this.entidade.getId());

		preencherFamilia();
	}

	@FXML
	public void clicarAlterarPesReferencia(ActionEvent event) {
		// TODO
		Stage parentStage = Util.atual(event);

		criarFormularioAlterarRF(entidade, "/gui/mudarReferenciaFamilia.fxml", parentStage);

		carregarPessoas(this.entidade.getId());

		preencherFamilia();

	}

	@FXML
	public void clicarRemover(ActionEvent event) {
//transacao familiaDAO aberta em setFamilia
		FamiliaDAO daof = new FamiliaDAO();
		PessoaDAO daop = new PessoaDAO();
		daop.abrirTransacao();
		daof.abrirTransacao();
		this.pessoa = (Pessoa) selecionado;

		Familia f = daof.obterPorID(entidade.getId());
		pessoa = daop.obterPorID(pessoa.getId());
		for (Pessoa p : f.getPessoas()) {
			if (p.getId().equals(pessoa.getId())) {
				f.excluirPessoa(p);
				break;
			}
		}

		daop.atualizar(pessoa);
		daof.atualizar(f);
		daop.fecharTransacao().fechar();
		daof.fecharTransacao().fechar();

		carregarPessoas(f.getId());
	}

	@FXML
	private void clicarEditar(ActionEvent event) {
		

		Stage parentStage = Util.atual(event);

		Pessoa pes = (Pessoa) selecionado;

		Pessoa p = daop.obterPorID(pes.getId());

		criarFormularioEditarPessoa(p, "/gui/formularioPessoa.fxml", parentStage);

		carregarPessoas(entidade.getId());

	}

	@FXML
	private void clicarSalvar(ActionEvent event) {
		
		salvarFamiliaNova();

		Util.atual(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarComboBox();
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
		colunaParentesco.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("composicao"));
		colunaRenda.setCellValueFactory(new PropertyValueFactory<Pessoa, Double>(("renda")));
		colunaBeneficio.setCellValueFactory(new PropertyValueFactory<Beneficio, String>("nomesBeneficios"));

		MaskFieldUtil.dateField(this.txtDataInclusao);
		MaskFieldUtil.dateField(this.txtDataAtendimento);
		MaskFieldUtil.dateField(this.txtDataCad);
		MaskFieldUtil.dateField(this.txtDataAcompanhamento);
		MaskFieldUtil.numericField(txtNumero);
		MaskFieldUtil.monetaryField(txtTotalBeneficio);
		MaskFieldUtil.monetaryField(txtPerCapita);
		MaskFieldUtil.monetaryField(txtRenda);
		MaskFieldUtil.monetaryField(txtAlugada);
		MaskFieldUtil.foneField(txtTel1);
		MaskFieldUtil.foneField(txtTel2);
		MaskFieldUtil.foneField(txtTel3);
		MaskFieldUtil.cepField(txtCep);

		// preencher fam�lia tem que ser chamado antes na contru��o, nunca aqui, pois
		// preencher� sem o setFamilia()
		// preencherFamilia();
		// carregarPessoas();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void carregarPessoas(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		//TODO unidade n�o est� entrando
		String jpql = "select p from Pessoa p where id_familia='" + id + "' and unidade='"+idUnidade+"'";
		TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
		List<Pessoa> pessoas = query.getResultList();
		
		
		
		ObservableList<Pessoa> obsPessoa = FXCollections.observableArrayList(pessoas);
		tabelaPessoas.setItems(obsPessoa);
		tabelaPessoas.setMaxHeight(180);

		Stage cena = (Stage) App.getCena().getWindow();
		tabelaPessoas.prefHeightProperty().bind(cena.heightProperty());
		tabelaPessoas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaPessoas.getSelectionModel();
				Object item = selectionModel.getSelectedItem();
//		        Long id = Long.valueOf(selectionModel.getSelectedIndex());//pega o id da TableList
				System.out.println("Selected Value " + item);
				System.out.println(id);

				selecionado = item;

			}
		});
		

	}

	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();// .replace("/", "");
	}

	public void preencherFamilia() {

		// this.id = entidade.getId();
		
		idFamilia.setText(entidade.getId() == null ? "" : "c�digo da fam�lia: " + getFamilia().getId().toString());
		if (!entidade.isAtivo()) {
			btDesligar.setDisable(true);
		}
		
		txtReferencia.setText(entidade.getPesReferencia() == null ? "" : entidade.getPesReferencia().getNome());
		txtDataInclusao
				.setText(converteData(entidade.getDataEntrada() == null ? (new Date()) : entidade.getDataEntrada()));
		idPessoa.setText("c�digo da pessoa: " + entidade.getPesReferencia().getId());
		comboTipoEndereco.getSelectionModel()
				.select(entidade.getEndereco() == null ? EnderecoTipo.RUA : entidade.getEndereco().getTipo_endereco());
		end = (EnderecoTipo) comboTipoEndereco.getSelectionModel().getSelectedItem();
		txtLogradouro.setText(entidade.getEndereco() == null ? "" : entidade.getEndereco().getLogradouro());
		txtNumero.setText(entidade.getEndereco() == null ? "" : "" + entidade.getEndereco().getNumero());
		
		checkPerfilCREAS.setSelected(entidade.isPerfilCreas() ? true : entidade.isPerfilCreas());
		creas = checkPerfilCREAS.selectedProperty().getValue();
		
		txtComplemento.setText(entidade.getEndereco() == null ? "" : entidade.getEndereco().getComplemento());
		comboBairro.getSelectionModel()
				.select(entidade.getEndereco() == null ? "" : entidade.getEndereco().getBairro());
		bairro = (String) comboBairro.getSelectionModel().getSelectedItem();
		
		checkMulherChefe.setSelected(entidade.isMulherChefe());
		mulherChefe = checkMulherChefe.selectedProperty().getValue();
		
		txtCep.setText(entidade.getEndereco() == null ? "" : entidade.getEndereco().getCep());
		txtTel1.setText(entidade.getTelefone().size() < 1 ? "" : entidade.getTelefone().get(0));
		txtTel2.setText(entidade.getTelefone().size() < 2 ? "" : entidade.getTelefone().get(1));
		txtTel3.setText(entidade.getTelefone().size() < 3 ? "" : entidade.getTelefone().get(2));
		
		comboTecnico.getSelectionModel().select(entidade.getTecnico());
		tecnico = comboTecnico.getSelectionModel().getSelectedItem();
		
		comboSituacao.getSelectionModel().select(entidade.getSituacao());
		situacao = comboSituacao.getSelectionModel().getSelectedItem();
		
		txtDataCad.setText(entidade.getDataCad() == null ? "" : converteData(entidade.getDataCad()));
		
		checkDescumprimento.setSelected(entidade.isDescumprimento());
		descumprimento = checkDescumprimento.selectedProperty().getValue();
		
		entidade.setTotalRenda();
		txtRenda.setText(String.valueOf(entidade.getTotalRenda()*10)+"");
		entidade.setRendaReferencia();

		entidade.setTotalBeneficio();
		txtTotalBeneficio.setText(String.valueOf(entidade.getTotalBeneficio())+"");// * 10));
		//TODO melhorar visualiza��o do �ltimo Atendimento, impactando pessoa velha em fam�lia nova
//		txtDataAtendimento.setText(
//				entidade.ultimoAtendimentoTecnico() == null ? "" : converteData(entidade.ultimoAtendimentoTecnico()));
//		txtDataCad
//				.setText(entidade.ultimoAtendimentoCad() == null ? "" : converteData(entidade.ultimoAtendimentoCad()));
		entidade.setPercapita();
		DecimalFormat d = new DecimalFormat("####,##");
		txtPerCapita.setText(d.format(entidade.getPercapita()*100) + "");
		if (!entidade.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		labelAtivo.setText("CADASTRO " + entidade.getAtivo());
		// TODO terminar esse m�todo conferindo a consist�ncia do banco com inclus�o de
		// data do �ltimo atendimento
		if (entidade.getValorMoradia() != 0) {
			txtAlugada.setDisable(false);
		}
		txtAlugada.setText(String.valueOf(entidade.getValorMoradia() * 10));
		comboTipoMoradia.getSelectionModel().select(entidade.getTipoMoradia());
		moradia = (MoradiaTipo) comboTipoMoradia.getSelectionModel().getSelectedItem();
		
		labelDataDesligamento.setText("Data do Desligamento: " + converteData(entidade.getDataDesligamento()));
		labelMotivoDesligamento.setText("Motivo do Desligamento: " + entidade.getMotivoDesligamento());
//
//		daof.fecharTransacao();
//		daof.fechar();
		carregarPessoas(this.entidade.getId());
	}

	private void carregarComboBox() {
		// LISTA TIPO ENDERECO DIRETO DO BANCO
		EnumSet<EnderecoTipo> ends = EnumSet.allOf(EnderecoTipo.class);
		obsEnd = FXCollections.observableArrayList(ends);
		comboTipoEndereco.setItems(obsEnd);
		comboTipoEndereco.getSelectionModel().selectFirst();

		// LISTA �REA DE ABRANG�NCIA DIRETO DO BANCO
//		EntityManagerFactory emf;
//		EntityManager em;
//		emf = Persistence.createEntityManagerFactory("cras_tcc");
//		em = emf.createEntityManager();
//		em.getTransaction().begin();

		DAO<Unidade> daoU = new DAO<>(Unidade.class);
		Unidade uni = daoU.obterPorID(1);
		// Unidade uni = em.find(Unidade.class, 2);
		List<String> bairros = uni.getAreaAbrangencia();
		System.out.println(bairros);
		obsBairro = FXCollections.observableArrayList(bairros);
		comboBairro.setItems(obsBairro);
		comboBairro.getSelectionModel().selectFirst();

		// LISTA T�CNICOS DIRETO DO BANCO

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
//		EntityManager em = emf.createEntityManager();

		// String jpql = "select e from Tecnico e";
		// TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);
		DAO<Tecnico> daoT = new DAO<>(Tecnico.class);
		List<Tecnico> tecnicos = daoT.obterTodos();

		obsTecnico = FXCollections.observableArrayList(tecnicos);
		comboTecnico.setItems(obsTecnico);
		comboTecnico.getItems().add(null);

		EnumSet<SituacaoFamilia> situacoes = EnumSet.allOf(SituacaoFamilia.class);
		obsSituacao = FXCollections.observableArrayList(situacoes);
		comboSituacao.setItems(obsSituacao);
		comboSituacao.getSelectionModel().selectFirst();

		EnumSet<MoradiaTipo> moradias = EnumSet.allOf(MoradiaTipo.class);
		obsMoradia = FXCollections.observableArrayList(moradias);
		comboTipoMoradia.setItems(obsMoradia);
		comboTipoMoradia.getSelectionModel().selectFirst();

		
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
	}

	public Date preparaData(TextField tx) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String strData = tx.getText();
		Date data = new Date();
		try {
			data = formato.parse(strData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void salvarFamiliaNova() {
		// transa��o de FamiliaDAO aberta em setFamilia
		FamiliaDAO daof = new FamiliaDAO();
		
		daof.abrirTransacao();

		Familia f = daof.obterPorID(this.entidade.getId());

		Endereco e = new Endereco();

		e.setTipo_endereco(end);
		e.setLogradouro(txtLogradouro.getText());
		e.setNumero(txtNumero.getText().equals("") ? 0 : Integer.parseInt(txtNumero.getText()));
		e.setComplemento(txtComplemento.getText());
		e.setCep(txtCep.getText());
		e.setBairro(bairro);

		List<String> telefones = new ArrayList<>();
		telefones.add(txtTel1.getText());
		telefones.add(txtTel2.getText());
		telefones.add(txtTel3.getText());

		f.setDataEntrada(preparaData(txtDataInclusao));
		f.setEndereco(e);
		f.setTipoMoradia(moradia);
//		f.setValorMoradia(txtAlugada.getText().equals("") ? 0 : Double.valueOf(txtAlugada.getText()));
		f.setValorMoradia(txtAlugada.getText().equals("") ? 0
				: Double.parseDouble(txtAlugada.getText().replace(".", "").replace(",", ".")));
		f.setTelefone(telefones);
		f.setSituacao(situacao);
		f.setTecnico(tecnico);
		f.setPerfilCreas(creas);
		f.setDescumprimento(descumprimento);
		f.setMulherChefe(mulherChefe);
		f.setEndereco(e);
		

		daof.incluir(f);
		daof.fecharTransacao().fechar();

	}

	public void criarFormularioPessoaNova(String nomeView, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();

			FormularioPessoaControlador controlador = loader.getController();
			controlador.setFamilia(entidade, false);
			controlador.setPessoa(pessoa, true);
			controlador.identificarRF(false);
			controlador.prepararPessoa(null);

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclus�o de pessoa de refer�ncia da fam�lia");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public void criarFormularioEditarPessoa(Pessoa p, String nomeView, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();

			FormularioPessoaControlador controlador = loader.getController();
			controlador.setPessoa(p, false);
			controlador.identificarRF(p.isPesReferencia());
			controlador.prepararPessoa(p);
			controlador.setFamilia(this.entidade, false);
			controlador.desabilitarBotaoUsar();
			controlador.preencherPessoa();

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Formul�rio para Edi��o de Pessoa da Fam�lia");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public void criarFormularioPessoaBanco(String nomeView, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();

			EscolherListaPessoaControlador controlador = loader.getController();

			controlador.setFamilia(entidade);

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Escolha uma pessoa da lista e clique em selecionar pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public void criarFormularioAlterarRF(Familia familia, String nomeView, Stage parentStage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Stage avisoCena = new Stage();
			Pane pane = loader.load();

			MudarReferenciaFamilia controlador = loader.getController();

			controlador.setFamilia(entidade);
			controlador.carregarPessoas(entidade);

			avisoCena.setTitle("Altera��o de Pessoa de Refer�ncia Familiar");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public void criarFormularioDesligar(Familia familia, String nomeView, Stage parentStage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Stage avisoCena = new Stage();
			Pane pane = loader.load();

			FormDesligamentoFamiliaControlador controlador = loader.getController();

			controlador.setFamilia(entidade);
			controlador.carregarLabel(entidade);

			avisoCena.setTitle("Altera��o de Pessoa de Refer�ncia Familiar");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a p�gina", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
