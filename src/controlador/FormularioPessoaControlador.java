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

import gui.DataChangeListener;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Beneficio;
import modelo.basico.Familia;
import modelo.basico.PesReferencia;
import modelo.basico.Pessoa;
import modelo.dao.DAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.Composicao;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

public class FormularioPessoaControlador implements Initializable{

	private Pessoa entidade;
	
	private Familia familia;
	
	Long id = null;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	//deficiencia.selectdProperty().getValue(); -> retorna um boolean
	@FXML
	private CheckBox deficiencia;
	
	@FXML
	private CheckBox gestante;
	
	@FXML
	private CheckBox scfv;

	@FXML
	private CheckBox prioritario;
	
	@FXML
	private CheckBox responsavel;
	
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
	
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}


	@FXML
	public void clicarSalvar(ActionEvent evento) {

		criarListaBeneficio();
		
		if(entidade == null) {
			throw new IllegalStateException("Pessoa não está no banco");
		}

		else {
			salvarPessoa();
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
		
			
	}
	
	@FXML
	public void clicarHomonimo() {
		homo = homonimo.selectedProperty().getValue();
	}
	
	@FXML
	public void clicarBeneficioBpci() {
		
		bpci = bpci_b.selectedProperty().getValue();
		if(bpci == false) {
			valorBpci.setDisable(true);
		}else {
			valorBpci.setDisable(false);
			
		}
	}
	
	
	@FXML
	public void clicarBeneficioPbf() {
		pbf = (boolean)pbf_b.selectedProperty().getValue();
		if(pbf == false){
			valorBolsa.setDisable(true);
		}else {
			valorBolsa.setDisable(false);
			
		}
		
	}
	@FXML
	public void clicarBeneficioBpcd() {
		bpcd = (boolean) bpcd_b.selectedProperty().getValue();
		if(bpcd == false) {
			valorBpcd.setDisable(true);
		}else {
			valorBpcd.setDisable(false);
			
		}
	}
	@FXML
	public void clicarBeneficioNv() {
		nv = (boolean) nova_b.selectedProperty().getValue();
		if(nv == false) {
			valorNv.setDisable(true);
		}else {
			valorNv.setDisable(false);
			
		}
	}
	@FXML
	public void clicarBeneficioOutro() {
		outro = (boolean) outro_b.selectedProperty().getValue();
		if(outro == false) {
			valorOutro.setDisable(true);
		}else {
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
	public void clicarResponsavel() {
		resp = responsavel.selectedProperty().getValue();
		if(resp) {
			
			boxCompo.setDisable(true);
		}else {
			boxCompo.setDisable(false);
			
		}
		
	}
	
	@FXML
	public void clicarBoxCor() {
		 cor = (CorRaca) boxCor.getSelectionModel().getSelectedItem();
		 
	}
	
	@FXML
	public void clicarBoxSexo() {
	
		sexo = (Sexo) boxSexo.getSelectionModel().getSelectedItem();
		if(sexo == Sexo.M) {
			gestante.setDisable(true);
		}else {
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
		obsCompo = FXCollections.observableArrayList(compos);
		boxCompo.setItems(obsCompo);
		boxCompo.getSelectionModel().selectFirst();
		
		EnumSet<CorRaca> cores = EnumSet.allOf(CorRaca.class);
		obsCor = FXCollections.observableArrayList(cores);
		boxCor.setItems(obsCor);
		boxCor.getSelectionModel().selectFirst();
		
		
		EnumSet<Sexo> sexos = EnumSet.allOf(Sexo.class);
		obsSexo = FXCollections.observableArrayList(sexos);
		boxSexo.setItems(obsSexo);
		boxSexo.getSelectionModel().selectFirst();
		
		EnumSet<Genero> gens = EnumSet.allOf(Genero.class);
		obsGenero = FXCollections.observableArrayList(gens);
		boxGenero.setItems(obsGenero);
		boxGenero.getSelectionModel().selectFirst();
		
		EnumSet<Escolaridade> esc = EnumSet.allOf(Escolaridade.class);
		obsEscolaridade = FXCollections.observableArrayList(esc);
		boxEscolaridade.setItems(obsEscolaridade);
		boxEscolaridade.getSelectionModel().selectFirst();
		

	}
	
	private List<Beneficio> criarListaBeneficio() {
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("cras_tcc");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		List <Beneficio> beneficios = new ArrayList<>();
		
		if(pbf == true) {
			Beneficio b = new Beneficio(BeneficioTipo.PBF,valorBolsa.getText()
					.isEmpty()? 0.0:Double.parseDouble(valorBolsa.getText()
							.replace(".","").replace(",",".")), null);	
			beneficios.add(b);
			em.persist(b);
		}else {
			beneficios.add(new Beneficio());
		}
		if(bpci == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCI, valorBpci.getText()
					.isEmpty()? 0.0:Double.parseDouble(valorBpci.getText()
							.replace(".","").replace(",",".")), null);	
			beneficios.add(b);
			em.persist(b);
		}else {
			beneficios.add(new Beneficio());
		
		}
		if(bpcd == true) {
			Beneficio b = new Beneficio(BeneficioTipo.BPCDEF, valorBpcd.getText()
					.isEmpty()? 0.0:Double.parseDouble(valorBpcd.getText()
							.replace(".","").replace(",",".")), null);	
			beneficios.add(b);
			em.persist(b);
		}else {
			beneficios.add(new Beneficio());
		}
		if(nv == true) {
			Beneficio b = new Beneficio(BeneficioTipo.NV, valorNv.getText()
					.isEmpty()? 0.0:Double.parseDouble(valorNv.getText()
							.replace(".","").replace(",",".")), null);	
			beneficios.add(b);
			em.persist(b);
		}else {
			beneficios.add(new Beneficio());
			
		}
		if(outro== true) {
			Beneficio b = new Beneficio(BeneficioTipo.O, valorOutro.getText()
					.isEmpty()? 0.0:Double.parseDouble(valorOutro.getText()
							.replace(".","").replace(",",".")), null);	
			beneficios.add(b);
			em.persist(b);
		}else {
			beneficios.add(new Beneficio());
			
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
		return beneficios;
	}
	
	protected String converteData(Date dt) {
		if(dt==null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");
		
		return formatBra.format(dt).toString().replace("/","");
	}
	
	public void preencherPessoa() {
		if(entidade == null) {
			throw new IllegalStateException("Pessoa não está no banco");
		}
		
		this.id = entidade.getId();
		nome.setText(entidade.getNome());
		homonimo.setSelected(entidade.isHomonimo()?true:entidade.isHomonimo());
		cpf.setText(entidade.getCpf());
		rg.setText(entidade.getRg());
		nis.setText(entidade.getNis());
		dataNasc.setText(converteData(entidade.getDataNascimento()));
		nomeMae.setText(entidade.getNomeMae());
		renda.setText(String.valueOf(entidade.getRenda()*10));
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
		if(entidade.isPesReferencia()) {
			responsavel.setSelected(true);
			responsavel.setDisable(true);
		}else {
			
			responsavel.setSelected(entidade.isPesReferencia());
		}
		
		pbf_b.setSelected(pbf);
		bpci_b.setSelected(bpci);
		bpcd_b.setSelected(bpcd);
		nova_b.setSelected(nv);
		outro_b.setSelected(outro);
		
		valorBolsa.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.PBF)*10));
		valorBpci.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.BPCI)*10));
		valorBpcd.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.BPCDEF)*10));
		valorNv.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.NV)*10));
		valorOutro.setText(String.valueOf(entidade.getValorBeneficio(BeneficioTipo.O)*10));
		
	}
	
	
	public void salvarPessoa() {
		
		
		
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
//PRIMEIRA ETAPA DA CRIAÇÃO DA FAMÍLIA -> SÓ CRIAMOS NOVA FAMÍLIA DEPOIS DA CRIAÇÃO DE UM RESPONSÁVEL FAMILIAR
		if(resp) {
			DAO <Familia> daoFamilia = new DAO<>(Familia.class);
			DAO <Pessoa> daoPessoa = new DAO<>(Pessoa.class);
			
			daoPessoa.abrirTransacao();
			PesReferencia p = new PesReferencia();			
			criarPessoa(p);
			daoFamilia.abrirTransacao();
			Familia f = new Familia();
			f.setPesReferencia(p);
			f.setNumero(1);
			daoPessoa.incluir(p).fecharTransacao().fechar();			
			daoFamilia.incluir(f).fecharTransacao().fechar();
			Stage parentStage = new Stage();
			chamarFormulario(p, f, "/gui/formularioFamilia.fxml", parentStage);
			
		}
//SALVAR UMA PESSOA QUE JÁ EXISTE NO BANCO, NÃO CRIAREMOS NOVA PESSOA NO BANCO MAS FAREMOS UM MARGE		
		else if(id != null) {
			//IREMOS CONSIDERAR QUE A PESSOA EDITADA JÁ TEM UMA FAMÍLIA
			DAO <Pessoa> dao = new DAO<>(Pessoa.class);
			DAO <Familia> daoF = new DAO<>(Familia.class);
			
			dao.abrirTransacao();
			
			Pessoa p = dao.obterPorID(Pessoa.class, id);
			
			if(p instanceof PesReferencia) {
			//SE A PESSOA EDITADA NÃO FOR UMA PESSOA DE REFERÊNCIA E RESPONSÁVEL FAMILIAR ESTIVER MARCADO
				if(!p.isPesReferencia() && resp) {
			//CRIAMOS UMA NOVA PESSOA DE REFERÊNCIA
					PesReferencia ref = new PesReferencia();
			//ATUALIZAMOS AS INFORMAÇÕES PARA A PESSOA DE REFERÊNCIA QUE CRIAMOS
					ref = (PesReferencia) p;
					daoF.abrirTransacao();
					Familia f = daoF.obterPorID(Familia.class, p.getFamilia().getId());
					f.setPesReferencia(ref);
					daoF.atualizar(f);
					daoF.fecharTransacao();
					daoF.fechar();	
			//RETIRAMOS A PESSOA ANTIGA DA FAMÍLIA -> ESSA AÇÃO É PARA O BANCO, POIS NO BANCO SERÁ UMA PESSOA NOVA
			//SE TRANSFORMARMOS UMA PESSOA EM PESREFERENCIA NO CÓDIGO NÃO ACONTECE NO BANCO
					p.sairFamilia();
			//APAGAMOS A PESSOA DO BANCO, POIS AGORA ESTARÁ COMO PESREFERENCIA EM REF
					p.apagarPessoa();
					dao.remover(p);
			//INCLUIMOS REF NO BANCO COM TODOS OS RELACIONAMENTOS DE P
					criarPessoa(ref);
					dao.incluir(ref);
					dao.fecharTransacao().fechar();
					setPessoa(ref);
					setFamilia(f);
				}
			}
			
			criarPessoa(p);
			dao.incluir(p).fecharTransacao().fechar();
			
		}else {
			
			DAO <Pessoa> dao = new DAO<>(Pessoa.class);
			dao.abrirTransacao();
			Pessoa p = new Pessoa();
			criarPessoa(p);
			dao.incluir(p).fecharTransacao().fechar();
			
		}
	}
	
	public void criarPessoa(Pessoa p) {
		
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
		
		p.setNome(nome.getText());
		p.setHomonimo(homo);
		p.setCpf(cpf.getText().isEmpty()?"":cpf.getText());
		p.setRg(rg.getText()==null?"":rg.getText());
		p.setNis(nis.getText()==null?"":nis.getText());
		p.setNomeMae(nomeMae.getText()==null?"Não informado":nomeMae.getText());
		p.setRenda(renda.getText()==null?0.0:Double.parseDouble(renda.getText().replace(".","").replace(",",".")));
		p.setOcupacao(ocupacao.getText()==null?"Não informado":ocupacao.getText());
		p.setComposicao(compo);
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
		p.setPesReferencia(resp);
	}
	
	private void chamarFormulario(Pessoa p, Familia f, String caminho, Stage parentStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Pane pane = loader.load();
			FormularioFamiliaControlador controlador = loader.getController();
			controlador.preencherFamilia();
			controlador.setFamilia(f);
			controlador.setPessoa(p);
			
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
	
	public void desabilitarResponsavel() {
		responsavel.setSelected(false);
		responsavel.setDisable(true);
	}
	
	public void habilitarResponsavel() {
		
		responsavel.setSelected(true);
		responsavel.setDisable(true);

	}


}
