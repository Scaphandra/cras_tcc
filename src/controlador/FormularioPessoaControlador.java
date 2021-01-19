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

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import gui.util.Constraints;
import gui.util.MaskFieldUtil;
import gui.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Beneficio;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.PessoaDAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.Composicao;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.PessoaEstado;
import modelo.enumerados.Sexo;

public class FormularioPessoaControlador implements Initializable {

	private Pessoa entidade;

	private Familia familia;

	Long id = null;

	private PessoaDAO dao = new PessoaDAO();
	
	private List<Pessoa> lista = dao.obterTodos();

	private boolean pesNova;

	private boolean rf;

	@FXML
	private Label labelAtivo;

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

	@FXML
	private CheckBox homonimo;

	private boolean homo;

	private Composicao compo;

	private CorRaca cor;

	private Sexo sexo;

	private Genero genero;

	private Escolaridade escolaridade;

	private boolean pbf;
	private boolean bpci;
	private boolean bpcd;
	private boolean nv;
	private boolean outro;

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
	private TextField renda;

	// deficiencia.selectdProperty().getValue(); -> retorna um boolean
	@FXML
	private CheckBox deficiencia;

	@FXML
	private CheckBox gestante;

	@FXML
	private CheckBox scfv;

	@FXML
	private CheckBox prioritario;

	@FXML
	private Label responsavel;

	private boolean pri;
	private boolean ges;
	private boolean def;
	private boolean scfvB;
	private boolean resp;

	@FXML
	private ComboBox<Composicao> boxCompo;
	private ObservableList<Composicao> obsCompo;

	@FXML
	private ComboBox<CorRaca> boxCor;
	private ObservableList<CorRaca> obsCor;

	@FXML
	private ComboBox<Sexo> boxSexo;
	private ObservableList<Sexo> obsSexo;

	@FXML
	private ComboBox<Genero> boxGenero;
	private ObservableList<Genero> obsGenero;

	@FXML
	private ComboBox<Escolaridade> boxEscolaridade;
	private ObservableList<Escolaridade> obsEscolaridade;

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

	@FXML
	private Label buscar;

	@FXML
	private Label idPessoa;

	public void setPessoa(Pessoa entidade, boolean b) {
		if (b) {
			this.pesNova = true;
		} else {
			this.pesNova = false;
		}
		this.entidade = entidade;
	}

	public void setFamilia(Familia familia) {

		this.familia = familia;

	}

	@FXML
	private void clicarNome(ActionEvent event) {

	}

	@FXML
	public void clicarSalvar(ActionEvent evento) {

		if (rf && pesNova) {
			Stage parentStage = Util.atual(evento);
			salvarNovaFamilia();
			System.out.println("entrou no resp");
			chamarFormulario(entidade, familia, "/gui/formularioFamilia.fxml", parentStage);
			Util.atual(evento).close();
		} else {
			if (pesNova) {
				salvarPessoa();
				Util.atual(evento).close();
			} else {
				editarPessoa();
				Util.atual(evento).close();
			}
		}

	}

	@FXML
	public void clicarCancelar(ActionEvent evento) {

		Util.atual(evento).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		initializeNodes();

		buscarNomes();

	}

	@FXML
	public void clicarHomonimo() {
		homo = homonimo.selectedProperty().getValue();
	}

	@FXML
	public void clicarBeneficioBpci() {

		bpci = bpci_b.selectedProperty().getValue();
		if (bpci == false) {
			valorBpci.setDisable(true);
		} else {
			valorBpci.setDisable(false);

		}
	}

	@FXML
	public void clicarBeneficioPbf() {
		pbf = (boolean) pbf_b.selectedProperty().getValue();
		if (pbf == false) {
			valorBolsa.setDisable(true);
		} else {
			valorBolsa.setDisable(false);

		}

	}

	@FXML
	public void clicarBeneficioBpcd() {
		bpcd = (boolean) bpcd_b.selectedProperty().getValue();
		if (bpcd == false) {
			valorBpcd.setDisable(true);
		} else {
			valorBpcd.setDisable(false);

		}
	}

	@FXML
	public void clicarBeneficioNv() {
		nv = (boolean) nova_b.selectedProperty().getValue();
		if (nv == false) {
			valorNv.setDisable(true);
		} else {
			valorNv.setDisable(false);

		}
	}

	@FXML
	public void clicarBeneficioOutro() {
		outro = (boolean) outro_b.selectedProperty().getValue();
		if (outro == false) {
			valorOutro.setDisable(true);
		} else {
			valorOutro.setDisable(false);
		}
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
		if (sexo == Sexo.M) {
			gestante.setDisable(true);
		} else {
			gestante.setDisable(false);
		}

	}

	@FXML
	public void clicarBoxGenero() {
		genero = (Genero) boxGenero.getSelectionModel().getSelectedItem();

	}

	@FXML
	public void clicarBoxEscolaridade() {
		escolaridade = (Escolaridade) boxEscolaridade.getSelectionModel().getSelectedItem();

	}

	@FXML
	public void clicarBoxCompo() {
		compo = (Composicao) boxCompo.getSelectionModel().getSelectedItem();

	}

	private void initializeNodes() {
		if (rf) {
			boxCompo.setDisable(true);
		} else {
			responsavel.setText("");
			boxCompo.setDisable(false);
		}

		if (pesNova) {
			idPessoa.setText("");
		}

		Constraints.setTextFieldMaxLength(this.nis, 11);
		carregarComboBox();
		MaskFieldUtil.cpfField(this.cpf);
		MaskFieldUtil.dateField(this.dataNasc);
		MaskFieldUtil.monetaryField(this.renda);
		MaskFieldUtil.monetaryField(this.valorBolsa);
		MaskFieldUtil.monetaryField(this.valorBpci);
		MaskFieldUtil.monetaryField(this.valorBpcd);
		MaskFieldUtil.monetaryField(this.valorNv);
		MaskFieldUtil.monetaryField(this.valorOutro);
	}

	private void carregarComboBox() {

		EnumSet<Composicao> compos = EnumSet.allOf(Composicao.class);
		compos.remove(Composicao.RF);
		obsCompo = FXCollections.observableArrayList(compos);
		boxCompo.setItems(obsCompo);
		// boxCompo.getSelectionModel().selectFirst();

		EnumSet<CorRaca> cores = EnumSet.allOf(CorRaca.class);
		obsCor = FXCollections.observableArrayList(cores);
		boxCor.setItems(obsCor);
		// boxCor.getSelectionModel().selectFirst();

		EnumSet<Sexo> sexos = EnumSet.allOf(Sexo.class);
		obsSexo = FXCollections.observableArrayList(sexos);
		boxSexo.setItems(obsSexo);
		// boxSexo.getSelectionModel().selectFirst();

		EnumSet<Genero> gens = EnumSet.allOf(Genero.class);
		obsGenero = FXCollections.observableArrayList(gens);
		boxGenero.setItems(obsGenero);
		// boxGenero.getSelectionModel().selectFirst();

		EnumSet<Escolaridade> esc = EnumSet.allOf(Escolaridade.class);
		obsEscolaridade = FXCollections.observableArrayList(esc);
		boxEscolaridade.setItems(obsEscolaridade);
		// boxEscolaridade.getSelectionModel().selectFirst();

	}

	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString().replace("/", "");
	}

	public void preencherPessoa() {
		if (entidade == null) {
			throw new IllegalStateException("Pessoa não está no banco");
		}

		this.id = entidade.getId();
		if (!entidade.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		labelAtivo.setText("CADASTRO " + entidade.getAtivo());
		idPessoa.setText(entidade.getId() == null ? "" : "Identificador: " + entidade.getId().toString());
		nome.setText(entidade.getNome());
		homonimo.setSelected(entidade.isHomonimo());
		cpf.setText(entidade.getCpf());
		rg.setText(entidade.getRg());
		nis.setText(entidade.getNis());
		dataNasc.setText(converteData(entidade.getDataNascimento()));
		nomeMae.setText(entidade.getNomeMae());
		renda.setText(String.valueOf(entidade.getRenda() * 10));
		ocupacao.setText(entidade.getOcupacao());

		boxSexo.getSelectionModel().select(entidade.getSexo());
		boxCompo.getSelectionModel().select(entidade.getComposicao());
		boxCor.getSelectionModel().select(entidade.getCor());
		boxGenero.getSelectionModel().select(entidade.getGenero());
		boxEscolaridade.getSelectionModel().select(entidade.getEscolaridade());

		deficiencia.setSelected(entidade.isComDeficiencia());
		gestante.setSelected(entidade.isGestante());
		prioritario.setSelected(entidade.isPrioritarioSCFV());
		scfv.setSelected(entidade.isNoSCFV());
		if (!rf) {
			responsavel.setText("");
		} else {
			responsavel.setText("Pessoa de Referência");
			boxCompo.getSelectionModel().select(Composicao.RF);
			boxCompo.setDisable(true);
		}

		List<Beneficio> bs = entidade.getBeneficios();
		for (Beneficio b : bs) {
			if (b.getNome() == BeneficioTipo.PBF) {
				pbf_b.setSelected(true);
				valorBolsa.setDisable(false);
			}
			if (b.getNome() == BeneficioTipo.BPCDEF) {
				bpcd_b.setSelected(true);
				valorBpcd.setDisable(false);
			}
			if (b.getNome() == BeneficioTipo.BPCI) {

				bpci_b.setSelected(true);
				valorBpci.setDisable(false);
			}
			if (b.getNome() == BeneficioTipo.NV) {

				nova_b.setSelected(true);
				valorNv.setDisable(false);
			}
			if (b.getNome() == BeneficioTipo.O) {

				outro_b.setSelected(true);
				valorOutro.setDisable(true);
			}
		}

		valorBolsa.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.PBF) * 10));
		valorBpci.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.BPCI) * 10));
		valorBpcd.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.BPCDEF) * 10));
		valorNv.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.NV) * 10));
		valorOutro.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.O) * 10));

	}

	public void salvarPessoa() {

		// SALVAR UMA NOVA PESSOA NO BANCO E JÁ SERÁ INSERIDA NA FAMÍLIA

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));

		// CRIANDO LISTA DE BENEFÍCIO

		List<Beneficio> beneficios = new ArrayList<>();

		if (pbf) {
			Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}

		if (bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}

		if (bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}
		if (nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);

		}
		if (outro == true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = dataNasc.getText();
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Pessoa p = new Pessoa();
		this.entidade = p;
		Familia f = this.familia;

		p.setNome(nome.getText());
		p.setHomonimo(homo);
		p.setCpf(cpf.getText().isEmpty() ? "" : cpf.getText());
		p.setRg(rg.getText() == null ? "" : rg.getText());
		p.setNis(nis.getText() == null ? "" : nis.getText());
		p.setNomeMae(nomeMae.getText() == null ? "Não informado" : nomeMae.getText());
		p.setRenda(renda.getText().equals("") ? 0.0
				: Double.parseDouble(renda.getText().replace(".", "").replace(",", ".")));
		p.setOcupacao(ocupacao.getText() == null ? "Não informado" : ocupacao.getText());
		p.setComposicao(compo);
		p.setDataNascimento(data);
		p.setSexo(sexo);
		p.setGenero(genero);
		p.setCor(cor);
		p.setEscolaridade_pes(escolaridade);
		p.setPrioritarioSCFV(pri);
		p.setBeneficios(beneficios);
		p.setComposicao(compo);
		p.setGestante(ges);
		p.setComDeficiencia(def);
		p.setNoSCFV(scfvB);
		p.setEstado(PessoaEstado.P);
		p.setPesReferencia(false);
		p.setFamilia(f);

		em.persist(p);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

	public void editarPessoa() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));
		// CRIANDO LISTA DE BENEFÍCIO

		List<Beneficio> beneficios = new ArrayList<>();

		if (pbf) {
			Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}

		if (bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}

		if (bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}
		if (nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);

		}
		if (outro == true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);
			em.persist(b);
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = dataNasc.getText();
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Pessoa p = em.find(Pessoa.class, entidade.getId());

		Familia f = p.getFamilia();

		p.setNome(nome.getText());
		p.setHomonimo(homo);
		p.setCpf(cpf.getText().isEmpty() ? "" : cpf.getText());
		p.setRg(rg.getText() == "" ? "" : rg.getText());
		p.setNis(nis.getText() == "" ? "" : nis.getText());
		p.setNomeMae(nomeMae.getText() == "" ? "Não informado" : nomeMae.getText());
		p.setRenda(
				renda.getText() == "" ? 0.0 : Double.parseDouble(renda.getText().replace(".", "").replace(",", ".")));
		p.setOcupacao(ocupacao.getText() == "" ? "Não informado" : ocupacao.getText());
		p.setComposicao(compo);
		p.setDataNascimento(data);
		p.setSexo(sexo);
		p.setGenero(genero);
		p.setCor(cor);
		p.setEscolaridade_pes(escolaridade);
		p.setPrioritarioSCFV(pri);
		p.setBeneficios(beneficios);
		p.setGestante(ges);
		p.setComDeficiencia(def);
		p.setNoSCFV(scfvB);
		p.setFamilia(f);

		em.merge(p);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

	public void salvarNovaFamilia() {
		// TODO validar os campos de sexo e genero
		// SALVAR UMA PESSOA DE REFERENCIA CRIANDO COM ELA UMA NOVA FAMÍLIA
		// AS FAMÍLIAS SERÃO RECONHECIDAS PELA PESSOA DE REFERÊNCIA UMA VEZ QUE O ID É
		// IRRELEVANTE NA ROTINA DO PROFISSIONAL

		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));

		List<Beneficio> beneficios = new ArrayList<>();

		PessoaDAO dao = new PessoaDAO();
		dao.abrirTransacao();

		if (pbf) {
			Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);

		}

		if (bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);

		}

		if (bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);

		}
		if (nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);

		}
		if (outro == true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")), null);
			beneficios.add(b);

		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = dataNasc.getText();
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Familia f = familia;
		Pessoa p = entidade;

		p.setNome(nome.getText());
		p.setHomonimo(homo);
		p.setCpf(cpf.getText().isEmpty() ? "" : cpf.getText());
		p.setRg(rg.getText() == null ? "" : rg.getText());
		p.setNis(nis.getText() == null ? "" : nis.getText());
		p.setNomeMae(nomeMae.getText() == null ? "Não informado" : nomeMae.getText());
		p.setRenda(
				renda.getText() == null ? 0.0 : Double.parseDouble(renda.getText().replace(".", "").replace(",", ".")));
		p.setOcupacao(ocupacao.getText() == null ? "Não informado" : ocupacao.getText());
		p.setComposicao(compo);
		p.setDataNascimento(data);
		p.setSexo(sexo);
		p.setGenero(genero);
		p.setCor(cor);
		p.setEscolaridade_pes(escolaridade);
		p.setPrioritarioSCFV(pri);
		p.setBeneficios(beneficios);
		p.setGestante(ges);
		p.setComDeficiencia(def);
		p.setNoSCFV(scfvB);
		p.setPesReferencia(true);
		p.setEstado(PessoaEstado.RF);
		f.setPesReferencia(p);
		setFamilia(f);
		setPessoa(p, true);
		f.setAtivo(true);
		f.setNumero(2);
		dao.incluir(p);
		dao.fecharTransacao().fechar();

	}

	private void chamarFormulario(Pessoa p, Familia f, String caminho, Stage parentStage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Pane pane = loader.load();

			FormularioFamiliaControlador controlador = loader.getController();

			controlador.setFamilia(f);
			controlador.setFamiliaNova(true);
			controlador.setPessoa(p);
			controlador.preencherFamilia();

			Stage avisoCena = new Stage();

			avisoCena.setTitle("Digite os dados para inclusão de pessoa");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepararPessoa(Pessoa p) {
		//PessoaDAO daop = new PessoaDAO();

		// SE NÃO FOR PESSOA NOVA
		if (p != null) {
			if (p.getEstado() == PessoaEstado.RF) {
				boxCompo.getSelectionModel().select(Composicao.RF);
				responsavel.setText("Pessoa de Referência");
			}
			if (p.getEstado() == PessoaEstado.P) {
				responsavel.setText("");
				boxCompo.setDisable(false);
			}

			// SE FOR PESSOA NOVA
		} else {

			//daop.abrirTransacao();

			if (rf) {

				p = new Pessoa();
				p.setAtivo(true);
				p.setPesReferencia(true);
				p.setEstado(PessoaEstado.RF);
				setPessoa(p, true);

				pesNova = true;

				Familia f = new Familia();

				setFamilia(f);

				boxCompo.getSelectionModel().select(Composicao.RF);
				boxCompo.setDisable(true);

			} else {

				p = new Pessoa();
				p.setAtivo(true);
				p.setPesReferencia(false);
				p.setEstado(PessoaEstado.P);
				pesNova = true;

			}

			entidade = p;
			//daop.incluir(p);
			//daop.fecharTransacao().fechar();

		}
		preencherPessoa();

	}

	public void identificarRF(boolean b) {
		if (b) {
			this.rf = true;
		} else {
			this.rf = false;

		}
	}

	@FXML
	private void buscarNomes() {
		nome.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("textfield changed from " + oldValue + " to " + newValue);

			if (newValue == null) {

				buscar.setText("Se salvar pessoa com o mesmo nome de outra deve selecionar a opção Homônimo");

			} else {
				
				lista.clear();
				atualizarFiltro();
				
				System.out.println(buscar.getText());

			}


		});
	}

	public void atualizarFiltro() {


		lista.clear();

		if (!nome.getText().isEmpty()) {
			
			lista = dao.obterPrimeiros(nome.getText(), "nome");
			
			

			buscar.setText("Já existe uma pessoa com o nome: " + lista.get(0).getNome()
					+ " - código identificador: " + lista.get(0).getId().toString());
			
		} else {
			buscar.setText("");
			
			}

	}



}
