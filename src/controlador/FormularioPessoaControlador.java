package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import gui.listeners.DataChangeListener;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import modelo.basico.Unidade;
import modelo.dao.PessoaDAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.Composicao;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.PessoaEstado;
import modelo.enumerados.Sexo;

public class FormularioPessoaControlador implements Initializable{
	
	private Pessoa pessoa;

	private Familia familia;
	
	private int idUnidade;
	
	Pessoa pessoaBusca;

	Long id = null;

	private PessoaDAO dao = new PessoaDAO();
	
	private List<Pessoa> lista = dao.obterTodos();

	private boolean pesNova;

	private boolean rf;
	
	private boolean famNova;
	
	@FXML
	private Button botaoUsar;

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

	@FXML 
	private CheckBox checkClt;

	private boolean clt;
	
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
	
	private List <DataChangeListener> listeners = new ArrayList<>();

	public void setPessoa(Pessoa entidade, boolean b) {
		if (b) {
			this.pesNova = true;
		} else {
			this.pesNova = false;
		}
		this.pessoa = entidade;
	
	}

	public void setFamilia(Familia familia, boolean b) {
		if (b) {
			this.famNova = true;
		} else {
			this.famNova = false;
		}

		this.familia = familia;

	}
	
	public void setFamiliaNova(boolean b) {
		if (b) {
			this.famNova = true;
		} else {
			this.famNova = false;
		}
		
	}
	
	public void setUnidade(int idUnidade) {
		this.idUnidade = idUnidade;
	}
	
	public void desabilitarBotaoUsar() {
		botaoUsar.setDisable(true);
	}

	@FXML
	private void clicarNome(ActionEvent event) {

	}
	
	@FXML
	public void clicarUsarBanco(ActionEvent event) {
		
		if (pessoaBusca == null || pessoaBusca.getNome()==null || nome.getText().isEmpty()) {

			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Não houve busca");
			alerta.setHeaderText("Digite um nome no campo 'nome'");
			alerta.show();
		}
		if (pessoaBusca.getUnidade() != null) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Pessoa cadastrada");
			alerta.setHeaderText("Essa pessoa ainda está cadastrada na unidade "
			+ pessoaBusca.getUnidade().getNomeCRAS());
			alerta.show();

		}
		if(pessoaBusca.getUnidade()==null) {
			if(famNova) {
				this.pessoaBusca.setComposicao(Composicao.RF);
			}
			this.pessoa = pessoaBusca;
			pesNova = false;
			//this.pessoa.setFamilia(familia);
			//clicarSalvar(event);
			preencherPessoa();
		}

	}

	@FXML
	public void clicarSalvar(ActionEvent evento) {
		//Quando criamos uma nova família
		if ((rf && pesNova && famNova)||(rf && !pesNova && famNova)) {
			Stage parentStage = Util.atual(evento);
			salvarNovaFamilia();
			chamarFormulario(pessoa, familia, "/gui/formularioFamilia.fxml", parentStage, true);
			Util.atual(evento).close();
		//Quando não é um RF
		}if(!rf) {
			if (pesNova) {
				salvarPessoa();
				
				Util.atual(evento).close();
			} else {
				
				editarPessoa();
				
				Util.atual(evento).close();
			}
		//Quando o Cadastro é chamado de Alterar Pessoa de Referência -> RF é pessoa nova
		}if(rf && pesNova && !famNova) {
			
			salvarPessoa();

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			familia = em.find(Familia.class, familia.getId());

			System.out.println(pessoa.toString());
		
			Pessoa pNova = em.find(Pessoa.class, pessoa.getId());
			Pessoa pAntiga = em.find(Pessoa.class, familia.getPesReferencia().getId());
			familia.trocarRf(pAntiga, pNova);
			pNova.setAtivo(true);

			em.merge(familia);
			em.merge(pNova);
			em.merge(pAntiga);
			em.getTransaction().commit();
			em.close();
			emf.close();
			Util.atual(evento).close();
		}
		//Quando vamos editar um RF
		if(rf && !pesNova && !famNova) {
			editarPessoa();
			Util.atual(evento).close();
		}
//		//Quando Usamos uma pessoa já cadastrada para iniciar uma nova Família (bt -> Usar Pessoa do Banco)
//		if(rf && !pesNova && famNova) {
//			
//		}

	}

	@FXML
	public void clicarCancelar(ActionEvent evento) {
//		if(pessoa.getNome()==null||pessoa.getAtivo()==null || pessoa.getId()!=null) {			
//			pessoa.excluirBanco();
//		}

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

		bpci = (boolean) bpci_b.selectedProperty().getValue();
		if (bpci == false) {
			valorBpci.setText("0.00");
			valorBpci.setDisable(true);
		} else {
			valorBpci.setDisable(false);

		}
	}

	@FXML
	public void clicarBeneficioPbf() {
		pbf = (boolean) pbf_b.selectedProperty().getValue();
		if (pbf == false) {
			valorBolsa.setText("0.00");
			valorBolsa.setDisable(true);
		} else {
			valorBolsa.setDisable(false);

		}

	}

	@FXML
	public void clicarBeneficioBpcd() {
		bpcd = (boolean) bpcd_b.selectedProperty().getValue();
		if (bpcd == false) {
			valorBpcd.setText("0.00");
			valorBpcd.setDisable(true);
		} else {
			valorBpcd.setDisable(false);

		}
	}

	@FXML
	public void clicarBeneficioNv() {
		nv = (boolean) nova_b.selectedProperty().getValue();
		if (nv == false) {
			valorNv.setText("0.00");
			valorNv.setDisable(true);
		} else {
			valorNv.setDisable(false);

		}
	}

	@FXML
	public void clicarBeneficioOutro() {
		outro = (boolean) outro_b.selectedProperty().getValue();
		if (outro == false) {
			valorOutro.setText("0.00");
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
	public void clicarCLT() {
		
		clt = checkClt.selectedProperty().getValue();
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
		if (pessoa == null) {
			throw new IllegalStateException("Pessoa não está no banco");
		}
		//TODO setar unidade aqui 
		if (!pessoa.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		labelAtivo.setText("CADASTRO " + pessoa.getAtivo());
		idPessoa.setText(pessoa.getId() == null ? "" : "Identificador: " + pessoa.getId().toString());
		nome.setText(pessoa.getNome());
		homonimo.setSelected(pessoa.isHomonimo());
		homo = homonimo.selectedProperty().getValue();
		
		cpf.setText(pessoa.getCpf());
		rg.setText(pessoa.getRg());
		nis.setText(pessoa.getNis());
		dataNasc.setText(converteData(pessoa.getDataNascimento()));
		nomeMae.setText(pessoa.getNomeMae());
		renda.setText(String.valueOf(pessoa.getRenda()));
		ocupacao.setText(pessoa.getOcupacao());
		checkClt.setSelected(pessoa.isVinculoFormal());
		clt = checkClt.selectedProperty().getValue();
		boxGenero.getSelectionModel().select(pessoa.getGenero());
		genero = (Genero) boxGenero.getSelectionModel().getSelectedItem();

		boxSexo.getSelectionModel().select(pessoa.getSexo());
		sexo = (Sexo) boxSexo.getSelectionModel().getSelectedItem();
		if (sexo == Sexo.M) {
			gestante.setDisable(true);
		} else {
			gestante.setDisable(false);
		}

		
		boxCompo.getSelectionModel().select(pessoa.getComposicao());
		boxCor.getSelectionModel().select(pessoa.getCor());
		cor = (CorRaca) boxCor.getSelectionModel().getSelectedItem();
		
		boxEscolaridade.getSelectionModel().select(pessoa.getEscolaridade());
		escolaridade = (Escolaridade) boxEscolaridade.getSelectionModel().getSelectedItem();

		deficiencia.setSelected(pessoa.isComDeficiencia());
		def = deficiencia.selectedProperty().getValue();
		
		gestante.setSelected(pessoa.isGestante());
		ges = gestante.selectedProperty().getValue();
		
		prioritario.setSelected(pessoa.isPrioritarioSCFV());
		pri = prioritario.selectedProperty().getValue();
		
		scfv.setSelected(pessoa.isNoSCFV());
		scfvB = scfv.selectedProperty().getValue();
		
		if (!rf) {
			responsavel.setText("");
		} else {
			responsavel.setText("Pessoa de Referência");
			boxCompo.getSelectionModel().select(Composicao.RF);
			boxCompo.setDisable(true);
		}
		compo = (Composicao) boxCompo.getSelectionModel().getSelectedItem();
		
		List <Beneficio> bs = pessoa.getBeneficios();
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
		bpci = (boolean) bpci_b.selectedProperty().getValue();
		pbf = (boolean) pbf_b.selectedProperty().getValue();
		bpcd = (boolean) bpcd_b.selectedProperty().getValue();
		nv = (boolean) nova_b.selectedProperty().getValue();
		outro = (boolean) outro_b.selectedProperty().getValue();

		valorBolsa.setText(String.valueOf(pessoa.getValorBeneficio(BeneficioTipo.PBF) * 10));
		valorBpci.setText(String.valueOf(pessoa.getValorBeneficio(BeneficioTipo.BPCI)));
		valorBpcd.setText(String.valueOf(pessoa.getValorBeneficio(BeneficioTipo.BPCDEF)));
		valorNv.setText(String.valueOf(pessoa.getValorBeneficio(BeneficioTipo.NV)));
		valorOutro.setText(String.valueOf(pessoa.getValorBeneficio(BeneficioTipo.O)));

	}

	public void salvarPessoa() {

		// SALVAR UMA NOVA PESSOA NO BANCO E JÁ SERÁ INSERIDA NA FAMÍLIA

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));
		Pessoa p = new Pessoa();
		this.pessoa = p;

		// CRIANDO LISTA DE BENEFÍCIO

		List<Beneficio> beneficios = new ArrayList<>();

		if (pbf) {
			Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
		}

		if (bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
		}

		if (bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
		}
		if (nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);

		}
		if (outro == true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")), p);
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

		Familia f = em.find(Familia.class, this.familia.getId());
		Unidade u = em.find(Unidade.class, familia.getUnidade().getId());

		//p.setAtivo(true);
		p.setAtivo(true);
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
		p.setHomonimo(homo);
		p.setVinculoFormal(clt);
		p.setEstado(PessoaEstado.P);
		//TODO retirar pesReferencia
		p.setPesReferencia(false);
		p.setFamilia(f);
		f.setNumero(f.getPessoas().size());
		p.setUnidade(u);
		
		em.persist(p);
		em.merge(f);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

	public void editarPessoa() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
//		PessoaDAO daop = new PessoaDAO();
//		FamiliaDAO daof = new FamiliaDAO();
//		BeneficioDAO daob = new BeneficioDAO();
//		daop.abrirTransacao();
//		daof.abrirTransacao();
//		daob.abrirTransacao();
		
//		Pessoa p = daop.obterPorID(pessoa.getId());
		Pessoa p = em.find(Pessoa.class, pessoa.getId());

		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));
		
		// CRIANDO LISTA DE BENEFÍCIO

		Map <String, Double> beneficios = new HashMap<>();
		

		if (pbf) {
			beneficios.put("PBF", Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", "."))/10);

		}

		if (bpci) {
			beneficios.put("BPCI", Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")));
		}
		
		if (bpcd) {
			beneficios.put("BPCDEF", Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")));
		}

		if (nv) {
			beneficios.put("NV", Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")));

		}
		if (outro) {
			beneficios.put("O", Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")));
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

		Familia f = em.find(Familia.class, familia.getId());
		Unidade u = em.find(Unidade.class, familia.getUnidade().getId());
		
		System.out.println(u.getNomeCRAS()); 
		p.setAtivo(true);
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
		p.setBeneficios(beneficios, pbf, bpci, bpcd, nv, outro, em);
		p.setGestante(ges);
		p.setComDeficiencia(def);
		p.setNoSCFV(scfvB);
		p.setHomonimo(homo);
		p.setVinculoFormal(clt);
		p.setFamilia(f);
		f.setUnidade(u);
		p.setUnidade(u);
		u.setPessoa(p);

		em.merge(p);
		em.merge(f);
		em.getTransaction().commit();
		em.close();
		emf.close();
		

	}

	public void salvarNovaFamilia() {
		// TODO validar os campos de sexo e genero
		// SALVAR UMA PESSOA DE REFERENCIA CRIANDO COM ELA UMA NOVA FAMÍLIA
		// AS FAMÍLIAS SERÃO RECONHECIDAS PELA PESSOA DE REFERÊNCIA UMA VEZ QUE O ID É
		// IRRELEVANTE NA ROTINA DO PROFISSIONAL

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));

		Pessoa p = new Pessoa();
		Familia f = new Familia();
		Unidade u = em.find(Unidade.class, idUnidade);
		if(pessoa.getId()!=null) {
			p = em.find(Pessoa.class, pessoa.getId());
			
		}

		List<Beneficio> beneficios = new ArrayList<>();
		
		if (pbf) {
			Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
		}
		
		if (bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
		}
		
		if (bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
		}
		if (nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")), p);
			beneficios.add(b);
			em.persist(b);
			
		}
		if (outro == true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")), p);
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

		p.setAtivo(true);
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
		p.setGestante(ges);
		p.setComDeficiencia(def);
		p.setNoSCFV(scfvB);
		p.setHomonimo(homo);
		p.setVinculoFormal(clt);
		p.setPesReferencia(true);
		p.setEstado(PessoaEstado.RF);
		f.setPesReferencia(p);
		setFamilia(f, true);
		setPessoa(p, true);
		f.setAtivo(true);
		f.setNumero(f.getPessoas().size());
		p.setBeneficios(beneficios);
		f.setUnidade(u);
		p.setUnidade(u);
		em.persist(f);
		if(pesNova) {
			em.persist(p);
		}else {
			em.merge(p);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
		familia = f;

	}

	private void chamarFormulario(Pessoa p, Familia f, String caminho, Stage parentStage,boolean familiaNova) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Pane pane = loader.load();

			FormularioFamiliaControlador controlador = loader.getController();
			
			controlador.setFamilia(f);
			controlador.setFamiliaNova(familiaNova);
			controlador.setPessoa(p);
			controlador.preencherFamilia();
			controlador.setUnidade(this.idUnidade);
			
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
	
	private Pessoa chamarEscolherBanco(String caminho, Stage parentStage) {
		Pessoa p = new Pessoa();
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Pane pane = loader.load();

			EscolherListaPessoaControlador controlador = loader.getController();
				
			
			Stage avisoCena = new Stage();
			avisoCena.setTitle("Escolha Pessoa do Sistema");
			avisoCena.setScene(new Scene(pane));
			avisoCena.setResizable(false);
			avisoCena.initOwner(parentStage);
			avisoCena.initModality(Modality.WINDOW_MODAL);
			avisoCena.showAndWait();
			
			p =  controlador.getPessoa();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public void prepararPessoa(Pessoa p) {
			
		this.pessoa = p;
		// SE NÃO FOR PESSOA NOVA
		if (p != null) {
			System.out.println("entidade não é null");
			if (this.pessoa.getEstado() == PessoaEstado.RF) {
				boxCompo.getSelectionModel().select(Composicao.RF);
				responsavel.setText("Pessoa de Referência");
				this.pessoa.setAtivo(true);
			}
			if (this.pessoa.getEstado() == PessoaEstado.P) {
				responsavel.setText("");
				boxCompo.setDisable(false);
				this.pessoa.setAtivo(true);
			}

			// SE FOR PESSOA NOVA
		} else {

			if (rf) {
				
				this.pessoa = new Pessoa();
				this.pessoa.setAtivo(true);
				this.pessoa.setEstado(PessoaEstado.RF);
				setPessoa(this.pessoa, true);

				pesNova = true;

				Familia f = new Familia();

				setFamilia(f, famNova);

				boxCompo.getSelectionModel().select(Composicao.RF);
				boxCompo.setDisable(true);

			} else {

				this.pessoa = new Pessoa();
				this.pessoa.setAtivo(true);
				this.pessoa.setPesReferencia(false);
				this.pessoa.setEstado(PessoaEstado.P);
				pesNova = true;

			}


		}
		this.pessoa.setAtivo(true);
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
				lista.clear();
				buscar.setText("Pessoas com o mesmo nome devem ser marcadas como Homônimo");

			} else {
				if(newValue.length()>=5) {
					atualizarFiltro();
					lista.clear();
					
					System.out.println(buscar.getText());
				}
				else {
					lista.clear();
				}
				

			}


		});
	}

	public void atualizarFiltro() {


		lista.clear();

		if (!nome.getText().isEmpty()) {
			
			lista = dao.obterPrimeiros(nome.getText(), "nome");
			
//			if(lista.size() <= nome.getText().length()) {
//				
//				lista.clear();
//				return;
//			}
			buscar.setText("Já existe uma pessoa com o nome: " + (lista.get(0).getNome()).toUpperCase()
					+ " - código identificador: " + lista.get(0).getId().toString() 
					+ (lista.get(0).getUnidade()==null?
							" 'Sem unidade'":"-> Unidade: "+lista.get(0).getUnidade().getNomeCRAS()));
			pessoaBusca =  lista.get(0);
			
		} else {
			buscar.setText("");
			
			}

	}



}
