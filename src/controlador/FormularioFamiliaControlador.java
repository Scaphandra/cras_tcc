package controlador;

import java.io.IOException;
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

import aplicacao.App;
import gui.listeners.DataChangeListener;
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
import modelo.dao.FamiliaDAO;
import modelo.dao.PessoaDAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.EnderecoTipo;
import modelo.enumerados.MoradiaTipo;
import modelo.enumerados.SituacaoFamilia;

public class FormularioFamiliaControlador implements Initializable, DataChangeListener {

	private List<DataChangeListener> listeners = new ArrayList<>();

	private Familia entidade;

	@SuppressWarnings("unused")
	private Long id = null;

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
	private TextField txtRendaPesRef;

	@FXML
	private CheckBox checkDescumprimento;

	private boolean descumprimento;

	@FXML
	private Label lbPBF;

	@FXML
	private Label lbBPCI;

	@FXML
	private Label lbBPCD;

	@FXML
	private Label lbNV;

	@FXML
	private Label lbOutro;

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

	public void inscreverListener(DataChangeListener d) {
		listeners.add(d);
	}

	public void notificarListener() {
		for (DataChangeListener d : listeners) {
			d.onDataChanged();
		}
	}

	public boolean isFamiliaNova() {
		return familiaNova;
	}

	public void setFamiliaNova(boolean familiaNova) {
		this.familiaNova = familiaNova;
	}

	public void setFamilia(Familia familia) {
		daof.abrirTransacao();
		if(familia.getId()==null) {
			this.entidade = familia;
		}else {
			
			this.entidade = daof.obterPorID(familia.getId());
		}
		
		daof.incluir(this.entidade);
		//transacao familiaDAO fechada em salvar
		//daof.fecharTransacao().fechar();

	}

	public Familia getFamilia() {
		return this.entidade;
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

		tecnico = comboTecnico.getSelectionModel().getSelectedItem();
	}

	@FXML
	void clicarBoxMoradia(ActionEvent event) {

		moradia = (MoradiaTipo) comboTipoMoradia.getSelectionModel().getSelectedItem();

		if (moradia == MoradiaTipo.A || moradia == MoradiaTipo.S) {
			txtAlugada.setDisable(false);
		} else {
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

	}

	@FXML
	void clicarDescumprimento(ActionEvent event) {
		creas = checkDescumprimento.selectedProperty().getValue();
	}

	@FXML
	void clicarMulher(ActionEvent event) {
		creas = checkMulherChefe.selectedProperty().getValue();
	}

	@FXML
	public void clicarCriarPessoa(ActionEvent event) {
		Stage parentStage = Util.atual(event);

		criarFormularioPessoaNova("/gui/formularioPessoa.fxml", parentStage);
		carregarPessoas(this.entidade.getId());
	}

	@FXML
	// do banco
	public void clicarEscolherPessoa(ActionEvent event) {
		Stage parentStage = Util.atual(event);
		criarFormularioPessoaBanco("/gui/escolherListaPessoa.fxml", parentStage);
		carregarPessoas(this.entidade.getId());

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
		daop.abrirTransacao();
//		daof.abrirTransacao();
		this.pessoa = (Pessoa) selecionado;
		Pessoa removida = daop.obterPorID(pessoa.getId());
		Familia f = daof.obterPorID(entidade.getId());

		f.excluirPessoa(removida);

		daop.atualizar(pessoa);
		daof.atualizar(f);
		daop.fecharTransacao().fechar();
//		daof.fecharTransacao().fechar();

		carregarPessoas(this.entidade.getId());
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
		notificarListener();
		Util.atual(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarComboBox();
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
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
		MaskFieldUtil.monetaryField(txtRendaPesRef);
		MaskFieldUtil.monetaryField(txtAlugada);
		MaskFieldUtil.foneField(txtTel1);
		MaskFieldUtil.foneField(txtTel2);
		MaskFieldUtil.foneField(txtTel3);
		MaskFieldUtil.cepField(txtCep);

		// preencher família tem que ser chamado antes na contrução, nunca aqui, pois
		// preencherá sem o setFamilia()
		// preencherFamilia();
		// carregarPessoas();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void carregarPessoas(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		String jpql = "select p from Pessoa p where id_familia='" + id + "' and estado!='RF'";
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

		return formatBra.format(dt).toString().replace("/", "");
	}

	public void preencherFamilia() {
	//transacao familiaDAO aberta em setFamilia
//		daof.abrirTransacao();
		this.id = entidade.getId();
		idFamilia.setText("código da família: " + entidade.getId().toString());
		if (!entidade.isAtivo()) {
			btDesligar.setDisable(true);
		}
		txtReferencia.setText(entidade.getPesReferencia() == null ? "" : entidade.getPesReferencia().getNome());
		txtDataInclusao
				.setText(converteData(entidade.getDataEntrada() == null ? (new Date()) : entidade.getDataEntrada()));
		idPessoa.setText("código da pessoa: " + entidade.getPesReferencia().getId());
		comboTipoEndereco.getSelectionModel()
				.select(entidade.getEndereco() == null ? EnderecoTipo.RUA : entidade.getEndereco().getTipo_endereco());
		txtLogradouro.setText(entidade.getEndereco() == null ? "" : entidade.getEndereco().getLogradouro());
		txtNumero.setText(entidade.getEndereco() == null ? "" : "" + entidade.getEndereco().getNumero());
		checkPerfilCREAS.setSelected(entidade.isPerfilCreas() ? true : entidade.isPerfilCreas());
		txtComplemento.setText(entidade.getEndereco() == null ? "" : entidade.getEndereco().getComplemento());
		comboBairro.getSelectionModel()
				.select(entidade.getEndereco() == null ? "" : entidade.getEndereco().getBairro());
		checkMulherChefe.setSelected(entidade.isMulherChefe());
		txtCep.setText(entidade.getEndereco() == null ? "" : entidade.getEndereco().getCep());
		txtTel1.setText(entidade.getTelefone().size() < 1 ? "" : entidade.getTelefone().get(0));
		txtTel2.setText(entidade.getTelefone().size() < 2 ? "" : entidade.getTelefone().get(1));
		txtTel3.setText(entidade.getTelefone().size() < 3 ? "" : entidade.getTelefone().get(2));
		comboTecnico.getSelectionModel().select(entidade.getTecnico());
		comboSituacao.getSelectionModel().select(entidade.getSituacao());
		txtDataCad.setText(entidade.getDataCad() == null ? "" : converteData(entidade.getDataCad()));
		checkDescumprimento.setSelected(entidade.isDescumprimento());
		entidade.setTotalRenda();
		txtRenda.setText(String.valueOf(entidade.getTotalRenda() * 10));
		entidade.setRendaReferencia();
		txtRendaPesRef.setText(String.valueOf(entidade.getRendaReferencia() * 10));

		for (Pessoa p : entidade.getPessoas()) {
			if (p.getBeneficios().isEmpty()) {
				lbPBF.setText("");
				lbBPCI.setText("");
				lbBPCD.setText("");
				lbNV.setText("");
				lbOutro.setText("");
			} else {

				for (Beneficio b : p.getBeneficios()) {
					if (b.getNome() == BeneficioTipo.PBF) {
						lbPBF.setText("-> Programa Bolsa Família");
					} else if (b.getNome() == BeneficioTipo.BPCDEF) {
						lbBPCD.setText("-> BPC Idoso");
					} else if (b.getNome() == BeneficioTipo.BPCI) {
						lbBPCD.setText("-> BPC Pessoa com Deficiência");
						;
					} else if (b.getNome() == BeneficioTipo.NV) {
						lbNV.setText("-> Programa Nova Vida");
					} else if (b.getNome() == BeneficioTipo.O) {
						lbOutro.setText("-> Outro Benefício");
					}
				}
			}
		}
		entidade.setTotalBeneficio();
		txtTotalBeneficio.setText(String.valueOf(entidade.getTotalBeneficio() * 10));
		txtDataAtendimento.setText(
				entidade.ultimoAtendimentoTecnico() == null ? "" : converteData(entidade.ultimoAtendimentoTecnico()));
		txtDataCad
				.setText(entidade.ultimoAtendimentoCad() == null ? "" : converteData(entidade.ultimoAtendimentoCad()));
		entidade.setPercapita();
		txtPerCapita.setText(entidade.getPercapita() * 10 + "");
		if (!entidade.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		labelAtivo.setText("CADASTRO " + entidade.getAtivo());
		// TODO terminar esse método conferindo a consistência do banco com inclusão de
		// dadta do último atendimento
		txtAlugada.setText(String.valueOf(entidade.getValorMoradia() * 10));
		comboTipoMoradia.getSelectionModel().select(entidade.getTipoMoradia());

//		daof.fecharTransacao();
//		daof.fechar();

	}

	private void carregarComboBox() {
		// LISTA TIPO ENDERECO DIRETO DO BANCO
		EnumSet<EnderecoTipo> ends = EnumSet.allOf(EnderecoTipo.class);
		obsEnd = FXCollections.observableArrayList(ends);
		comboTipoEndereco.setItems(obsEnd);
		comboTipoEndereco.getSelectionModel().selectFirst();

		// LISTA ÁREA DE ABRANGÊNCIA DIRETO DO BANCO
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("cras_tcc");
		em = emf.createEntityManager();
		em.getTransaction().begin();

//		DAO<Unidade> daoU = new DAO<>(Unidade.class);
//		Unidade uni = daoU.obterPorID(Unidade.class, 2);
		Unidade uni = em.find(Unidade.class, 2);
		List<String> bairros = uni.getAreaAbrangencia();
		System.out.println(bairros);
		obsBairro = FXCollections.observableArrayList(bairros);
		comboBairro.setItems(obsBairro);
		comboBairro.getSelectionModel().selectFirst();

		// LISTA TÉCNICOS DIRETO DO BANCO

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
//		EntityManager em = emf.createEntityManager();

		String jpql = "select e from Tecnico e";
		TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);
		List<Tecnico> tecnicos = query.getResultList();

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
		//transação de FamiliaDAO aberta em setFamilia
		//daof.abrirTransacao();

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
		f.setValorMoradia(txtAlugada.getText().equals("") ? 0.0 : Double.parseDouble(txtAlugada.getText()));
		f.setTelefone(telefones);
		f.setSituacao(situacao);
		f.setTecnico(tecnico);
		f.setPerfilCreas(creas);
		f.setDescumprimento(descumprimento);
		f.setMulherChefe(mulherChefe);

		daof.incluir(f);
		daof.fecharTransacao().fechar();

	}

	public void criarFormularioPessoaNova(String nomeView, Stage parentStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeView));
			Pane pane = loader.load();

			FormularioPessoaControlador controlador = loader.getController();
			controlador.setFamilia(entidade);
			controlador.setPessoa(pessoa, true);
			controlador.identificarRF(false);
			controlador.prepararPessoa(null);

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Digite os dados para inclusão de pessoa de referência da família");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
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
			controlador.preencherPessoa();

			Stage avisoCena = new Stage();
			avisoCena.setTitle("Formulário para Edição de Pessoa da Família");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
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
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
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

			avisoCena.setTitle("Alteração de Pessoa de Referência Familiar");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			Alerta.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	@Override
	public void onDataChanged() {
		preencherFamilia();

	}

}
