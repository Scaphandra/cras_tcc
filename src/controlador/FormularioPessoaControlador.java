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
import modelo.dao.FamiliaDAO;
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

	Long id = null;

	private PessoaDAO dao = new PessoaDAO();
	
	private List<Pessoa> lista = dao.obterTodos();

	private boolean pesNova;

	private boolean rf;
	
	private boolean famNova;

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
//	private boolean resp;

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
	

	@FXML
	private void clicarNome(ActionEvent event) {

	}

	@FXML
	public void clicarSalvar(ActionEvent evento) {

		if (rf && pesNova && famNova) {
			Stage parentStage = Util.atual(evento);
			salvarNovaFamilia();
			chamarFormulario(pessoa, familia, "/gui/formularioFamilia.fxml", parentStage, true);
			Util.atual(evento).close();
			
		}if(!rf) {
			if (pesNova) {
				salvarPessoa();
				
				Util.atual(evento).close();
			} else {
				editarPessoa();
				
				Util.atual(evento).close();
			}
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
		if(rf && !pesNova && !famNova) {
			editarPessoa();
			Util.atual(evento).close();
		}

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

		bpci = bpci_b.selectedProperty().getValue();
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

		//this.id = entidade.getId();
		System.out.println(pessoa.toString());
		//nullPointer 
		if (!pessoa.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		labelAtivo.setText("CADASTRO " + pessoa.getAtivo());
		idPessoa.setText(pessoa.getId() == null ? "" : "Identificador: " + pessoa.getId().toString());
		nome.setText(pessoa.getNome());
		homonimo.setSelected(pessoa.isHomonimo());
		cpf.setText(pessoa.getCpf());
		rg.setText(pessoa.getRg());
		nis.setText(pessoa.getNis());
		dataNasc.setText(converteData(pessoa.getDataNascimento()));
		nomeMae.setText(pessoa.getNomeMae());
		renda.setText(String.valueOf(pessoa.getRenda()));// * 10));
		ocupacao.setText(pessoa.getOcupacao());

		boxSexo.getSelectionModel().select(pessoa.getSexo());
		boxCompo.getSelectionModel().select(pessoa.getComposicao());
		boxCor.getSelectionModel().select(pessoa.getCor());
		boxGenero.getSelectionModel().select(pessoa.getGenero());
		boxEscolaridade.getSelectionModel().select(pessoa.getEscolaridade());

		deficiencia.setSelected(pessoa.isComDeficiencia());
		gestante.setSelected(pessoa.isGestante());
		prioritario.setSelected(pessoa.isPrioritarioSCFV());
		scfv.setSelected(pessoa.isNoSCFV());
		if (!rf) {
			responsavel.setText("");
		} else {
			responsavel.setText("Pessoa de Referência");
			boxCompo.getSelectionModel().select(Composicao.RF);
			boxCompo.setDisable(true);
		}

		List<Beneficio> bs = pessoa.getBeneficios();
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

		valorBolsa.setText(String.valueOf(pessoa.getValorBeneficio(BeneficioTipo.PBF)));// * 10));
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
		this.pessoa = p;
		Familia f = em.find(Familia.class, this.familia.getId());

		//p.setAtivo(true);

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
		p.setEstado(PessoaEstado.P);
		p.setPesReferencia(false);
		p.setFamilia(f);
		f.setNumero(f.getPessoas().size());

		em.persist(p);
		em.merge(f);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}
	//VERIFICADOR DE LISTA DE BENEFÍCIO ANTIGA, PASSA A LISTA ANTIGA E O BENEFÍCIO QUE QUER VERIFICAR
	private Long verificarLista(List<Beneficio> lista, String nome) {
		
		Long id = null;
		
		for(Beneficio b : lista) {
			
			if(nome.equals(b.getNome())) {
				id = b.getId_beneficio();
			}else {
				
				id = null;
			}			
		}
		return id;
	}

	public void editarPessoa() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Pessoa p = em.find(Pessoa.class, pessoa.getId());

		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(nome, Validator.createEmptyValidator("Campo Obrigatório"));
		validarTxt.registerValidator(dataNasc, Validator.createEmptyValidator("Campo Obrigatório"));
		// CRIANDO LISTA DE BENEFÍCIO

		List<Beneficio> beneficios = new ArrayList<>();
		List<Beneficio> checar = p.getBeneficios();

		if (pbf) {
			//SE A LISTA DE BENEFÍCIOS JÁ TEM O BENEFÍCIO PBF
			if(verificarLista(checar, BeneficioTipo.PBF.toString())!=null) {
				Beneficio b = em.find(Beneficio.class, verificarLista(checar, BeneficioTipo.PBF.toString()));
				b.setValor(valorBolsa.getText().isEmpty() ? 0.0
						: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")));
				em.merge(b);
				beneficios.add(b);
				//SE O BENEFÍCIO NÃO ESTÁ NA LISTA
			}else {
				Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
						: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), null);
				beneficios.add(b);
				em.persist(b);
			}
//			Beneficio b = new Beneficio(BeneficioTipo.PBF, valorBolsa.getText().isEmpty() ? 0.0
//					: Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")), null);
//			for(Beneficio be :checar) {
//				if(be.equals(b)) {
//					beneficios.add(b);
//					b = em.find(Beneficio.class, be.getId_beneficio());
//					em.merge(b);
//				}else {			
//					beneficios.add(b);
//					em.persist(b);
//				}
//			}
			
		}else {
			if(verificarLista(checar, BeneficioTipo.PBF.toString())!=null) {
				Beneficio b = em.find(Beneficio.class, verificarLista(checar, BeneficioTipo.PBF.toString()));
				b.setValor(Double.parseDouble(valorBolsa.getText().replace(".", "").replace(",", ".")));
				em.remove(b);
			}
		}

		if (bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpci.getText().replace(".", "").replace(",", ".")), null);
			for(Beneficio be :checar) {
				
				if(be.equals(b)) {
					beneficios.add(b);
					b = em.find(Beneficio.class, be.getId_beneficio());
					em.merge(b);
				}else {			
					beneficios.add(b);
					em.persist(b);
				}
			}

		}

		if (bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorBpcd.getText().replace(".", "").replace(",", ".")), null);
			for(Beneficio be :checar) {
				
				if(be.equals(b)) {
					beneficios.add(b);
					b = em.find(Beneficio.class, be.getId_beneficio());
					em.merge(b);
				}else {			
					beneficios.add(b);
					em.persist(b);
				}
			}
			
		}
		if (nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorNv.getText().replace(".", "").replace(",", ".")), null);
			for(Beneficio be :checar) {
				
				if(be.equals(b)) {
					beneficios.add(b);
					b = em.find(Beneficio.class, be.getId_beneficio());
					em.merge(b);
				}else {			
					beneficios.add(b);
					em.persist(b);
				}
			}
			

		}
		if (outro == true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText().isEmpty() ? 0.0
					: Double.parseDouble(valorOutro.getText().replace(".", "").replace(",", ".")), null);
			for(Beneficio be :checar) {
				
				if(be.equals(b)) {
					beneficios.add(b);
					b = em.find(Beneficio.class, be.getId_beneficio());
					em.merge(b);
				}else {			
					beneficios.add(b);
					em.persist(b);
				}
			}
			
		}
		if(!outro && !nv && !bpcd && !bpci && !pbf && !checar.isEmpty()) {
			
			for(Beneficio b : checar) {
				b = em.find(Beneficio.class, b.getId_beneficio());
				em.remove(b);
			}
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
		p.setHomonimo(homo);
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
		FamiliaDAO daof = new FamiliaDAO();
		daof.abrirTransacao();
		Familia f = familia;
		Pessoa p = pessoa;

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
		p.setHomonimo(homo);
		p.setPesReferencia(true);
		p.setEstado(PessoaEstado.RF);
		f.setPesReferencia(p);
		setFamilia(f, true);
		setPessoa(p, true);
		f.setAtivo(true);
		f.setNumero(f.getPessoas().size());
		daof.atualizar(f);
		dao.atualizar(p);
		dao.fecharTransacao().fechar();
		daof.fecharTransacao().fechar();
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
			//System.out.println(entidade.toString());

			if (rf) {
				
				this.pessoa = new Pessoa();
				this.pessoa.setAtivo(true);
				//this.pessoa.setPesReferencia(true);
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

				buscar.setText("Pessoas com o mesmo nome devem ser marcadas como Homônimo");

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
