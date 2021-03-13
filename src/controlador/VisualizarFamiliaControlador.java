package controlador;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import aplicacao.App;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.basico.Beneficio;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.EnderecoTipo;

public class VisualizarFamiliaControlador implements Initializable {

	private Pessoa selecionado;
	
    @FXML
    private Label idFamilia;

    @FXML
    private Label dataInclusao;

    @FXML
    private Label labelAtivo;

    @FXML
    private Label rendaRef;

    @FXML
    private Label rendaTotal;

    @FXML
    private Label perCapita;

    @FXML
    private Label mulherChefe;

    @FXML
    private Label beneficios;

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
    private Label totalBeneficios;

    @FXML
    private Label bairro;

    @FXML
    private Label tipoMoradia;

    @FXML
    private Label valorMoradia;

    @FXML
    private Label telefones;
    @FXML
    private Label tel1;
    @FXML
    private Label tel2;
    @FXML
    private Label tel3;

    @FXML
    private Label situacao;

    @FXML
    private Label tecReferencia;

    @FXML
    private Label dataEntradaAcompanhamento;

    @FXML
    private Label perfilCREAS;

    @FXML
    private Label dataUltimoAtendimento;

    @FXML
    private Label ultimaAtualizacaoCad;

    @FXML
    private Label descumprimento;

    @FXML
    private Label endTipo;

    @FXML
    private Label logradouro;

    @FXML
    private Label num;

    @FXML
    private Label complemento;

    @FXML
    private Label cep;

    @FXML
    private Label referencia;

    @FXML
    private Label idPessoa;

    @FXML
    private TableView<Pessoa> tabelaPessoas;

    @FXML
    private TableColumn<Pessoa, String> colunaNome;

    @FXML
    private TableColumn<Pessoa, Long> colId;

    @FXML
    private TableColumn<Pessoa, Integer> colunaIdade;

    @FXML
    private TableColumn<Pessoa, String> colunaParentesco;

    @FXML
    private TableColumn<Pessoa, Double> colunaRenda;

    @FXML
    private TableColumn<Beneficio, String> colunaBeneficio;

    @FXML
    private Label labelMotivoDesligamento;

    @FXML
    private Label labelDataDesligamento;
    
    @FXML
    private Button btOk;
    
    private Familia familia;
    
    
	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		colunaNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		colId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
		colunaParentesco.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("composicao"));
		colunaRenda.setCellValueFactory(new PropertyValueFactory<Pessoa, Double>(("renda")));
		colunaBeneficio.setCellValueFactory(new PropertyValueFactory<Beneficio, String>("nomesBeneficios"));
	
	}
	
	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();//.replace("/", "");
	}
	
	public void carregarFamilia(Familia familia) {

		idFamilia.setText("código da família: " + familia.getId().toString());
		if (!familia.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		dataInclusao.setText("Data de Inclusão: "+ converteData(familia.getDataEntrada()));
		labelAtivo.setText("CADASTRO " +familia.getAtivo());
		referencia.setText("Pessoa de Referência: "+familia.getPesReferencia().toString());
		idPessoa.setText("Código pessoa: "+familia.getPesReferencia().getId());
		endTipo.setText(familia.getEndereco().getTipo_endereco()==null?
				EnderecoTipo.RUA.toString():"Endereço: "+ familia.getEndereco().getTipo_endereco().toString());
		logradouro.setText(familia.getEndereco().getLogradouro()==null?
				"":familia.getEndereco().getLogradouro());
		num.setText(familia.getEndereco().getNumero() == 0?
				"":"Nº: "+Integer.toString(familia.getEndereco().getNumero()));
		complemento.setText(familia.getEndereco().getComplemento()==null?
				"":"Complemento: "+familia.getEndereco().getComplemento());
		cep.setText(familia.getEndereco().getCep()==null?
				"":"CEP: "+ familia.getEndereco().getCep());
		bairro.setText(familia.getEndereco().getBairro()==null?
				"":"Bairro: "+ familia.getEndereco().getBairro());
//		if(familia.getEndereco()==null) {
//			endTipo.setText("Endereço: Não Registrado");
//			logradouro.setText("");
//			num.setText("");
//			complemento.setText("");
//			cep.setText("");
//			bairro.setText("");
//		}if(familia.getEndereco()!=null) {
//			
//		}
		tipoMoradia.setText(familia.getTipoMoradia()== null?
				"Tipo de moradia não informado": "Casa "+ familia.getTipoMoradia().toString());
		valorMoradia.setText(" Valor R$ " + Double.toString(familia.getValorMoradia()));
		tel1.setText(familia.getTelefone().size() < 1 ? "" : (familia.getTelefone().get(0) + ";"));
		tel2.setText(familia.getTelefone().size() < 2 ? "" : (familia.getTelefone().get(1) + ";"));
		tel3.setText(familia.getTelefone().size() < 3 ? "" : familia.getTelefone().get(2));
		situacao.setText(familia.getSituacao()==null?
				"Situação não informada" : "Situação da família: " + familia.getSituacao().toString());
		tecReferencia.setText(familia.getTecnico()==null?
				"Sem técnico de referência": familia.getTecnico().toString());
		dataEntradaAcompanhamento.setText(familia.getAcompanhamento()==null?
				"": "Data de Entrada em Acompanhamento: "+familia.getAcompanhamento().getDataEntrada());
		if(familia.isPerfilCreas()) {
			perfilCREAS.setText("Perfil CREAS");
		}
		if(familia.isMulherChefe()) {
			
			mulherChefe.setText("Mulher Chefe de Família");
		}
		descumprimento.setText(familia.isDescumprimento()?"Em Descumprimento de Condicionalidades":"");
		for (Pessoa p : familia.getPessoas()) {
			if (p.getBeneficios().isEmpty()) {
				lbPBF.setText("");
				lbBPCI.setText("");
				lbBPCD.setText("");
				lbNV.setText("");
				lbOutro.setText("");
			} else {

				for (Beneficio b : p.getBeneficios()) {
					if (b.getNome().equals(BeneficioTipo.PBF)) {
						lbPBF.setText("-> Programa Bolsa Família");
					} else if (b.getNome().equals(BeneficioTipo.BPCDEF)) {
						lbBPCD.setText("-> BPC Pessoa com Deficiência");
					} else if (b.getNome().equals(BeneficioTipo.BPCI)) {
						lbBPCD.setText("-> BPC Idoso");
						;
					} else if (b.getNome().equals(BeneficioTipo.NV)) {
						lbNV.setText("-> Programa Nova Vida");
					} else if (b.getNome().equals(BeneficioTipo.O)){
						lbOutro.setText("-> Outro Benefício");
					}
				}
			}
		}
		DecimalFormat decimal = new DecimalFormat("####,##");
		totalBeneficios.setText("Total R$ " + Double.toString(familia.getTotalBeneficios()));
		rendaRef.setText("Renda da Pessoa de Referência R$ "+Double.toString(familia.getRendaReferencia()));
		rendaTotal.setText("Renda Total R$ " + Double.toString(familia.getTotalRenda()));
		perCapita.setText("Renda Per Capita R$ " + familia.getPercapita());
		labelDataDesligamento.setText(familia.getDataDesligamento()==null?
				"Nunca foi desligada": "Data do Desligamento: "+ familia.getDataDesligamento());
		labelMotivoDesligamento.setText(familia.getMotivoDesligamento()==null?
				"":familia.getMotivoDesligamento());	
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void carregarPessoas(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		String jpql = "select p from Pessoa p where id_familia='" + id +"'";
		TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
		List<Pessoa> pessoas = query.getResultList();
		ObservableList <Pessoa> obsPessoa = FXCollections.observableArrayList(pessoas);
		tabelaPessoas.setItems(obsPessoa);
		tabelaPessoas.setMaxHeight(180);
		
		Stage cena = (Stage) App.getCena().getWindow();
		tabelaPessoas.prefHeightProperty().bind(cena.heightProperty());
		tabelaPessoas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				TableViewSelectionModel selectionModel = tabelaPessoas.getSelectionModel();
				Object item = selectionModel.getSelectedItem();
				System.out.println("Selected Value " + item);
				selecionado = (Pessoa) item;

			}
		});

	}
	
	@FXML
	void clicarOk(ActionEvent event) {
		Util.atual(event).close();
	}
	
	
	@FXML
	void clicarVisualizarPessoa(ActionEvent event) {
		Stage parent = Util.atual(event);
		chamarFormulario(selecionado, "/gui/visualizarPessoa.fxml", parent);
	}
	
	
	private void chamarFormulario(Pessoa p, String caminho, Stage parentStage) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
			Pane pane = loader.load();

			VisualizarPessoaControlador controlador = loader.getController();
			
			controlador.carregarPessoa(selecionado.getId());
			
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
}
