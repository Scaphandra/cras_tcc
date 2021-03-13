package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import gui.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modelo.basico.Beneficio;
import modelo.basico.Pessoa;
import modelo.dao.PessoaDAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.Composicao;

public class VisualizarPessoaControlador implements Initializable{

	 @FXML
	    private Label labelAtivo;

	    @FXML
	    private Label deficiencia;

	    @FXML
	    private Label gestante;

	    @FXML
	    private Label scfv;

	    @FXML
	    private Label prioritario;

	    @FXML
	    private Label valorBolsa;

	    @FXML
	    private Label valorBpci;

	    @FXML
	    private Label valorBpcd;

	    @FXML
	    private Label valorNv;

	    @FXML
	    private Label valorOutro;

	    @FXML
	    private Label pbf_b;

	    @FXML
	    private Label bpci_b;

	    @FXML
	    private Label bpcd_b;

	    @FXML
	    private Label nova_b;

	    @FXML
	    private Label outro_b;

	    @FXML
	    private Label buscar;

	    @FXML
	    private Label idPessoa;
	    
	    @FXML
	    private Label idFamilia;

	    @FXML
	    private Button ok;

	    @FXML
	    private Label nome;

	    @FXML
	    private Label homonimo;

	    @FXML
	    private Label dataNascimento;

	    @FXML
	    private Label nomeMae;

	    @FXML
	    private Label rg;

	    @FXML
	    private Label cpf;

	    @FXML
	    private Label nis;

	    @FXML
	    private Label cor;

	    @FXML
	    private Label sexo;

	    @FXML
	    private Label genero;

	    @FXML
	    private Label ocupacao;

	    @FXML
	    private Label escolaridade;

	    @FXML
	    private Label renda;

	    @FXML
	    private Label composicao;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	void clicarOk(ActionEvent event) {
		
		Util.atual(event).close();
	}
	
	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString();//.replace("/", "");
	}
	
		public void carregarPessoa(Long id) {
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = dao.obterPorID(id);
		
		if (!pessoa.isAtivo()) {
			labelAtivo.setStyle("-fx-text-fill: #ff0000;");
		}
		labelAtivo.setText("CADASTRO " + pessoa.getAtivo());
		idFamilia.setText(pessoa.getFamilia()==null?"Não Pertence a uma Família":"Código da Família: " + pessoa.getFamilia());
		idPessoa.setText(pessoa.getId() == null ? "" : "Código: " + pessoa.getId().toString());
		nome.setText("Nome: " + pessoa.getNome());
		homonimo.setText(pessoa.isHomonimo()==false?" ": "É Homônimo");
		cpf.setText("CPF: "+pessoa.getCpf());
		rg.setText("RG: "+ pessoa.getRg());
		nis.setText("NIS: "+ pessoa.getNis());
		dataNascimento.setText("Data de Nascimento: "+ converteData(pessoa.getDataNascimento()));
		nomeMae.setText(pessoa.getNomeMae()==null?"Nome da mãe não informado":"Nome da Mãe: " + pessoa.getNomeMae());
		renda.setText("Renda Mensal R$: "+String.valueOf(pessoa.getRenda()));
		ocupacao.setText("Ocupação: "+pessoa.getOcupacao());
		cor.setText("Cor/Raça: "+ (pessoa.getCor()==null?"Não informado":pessoa.getCor().toString()));
		sexo.setText("Sexo: "+ (pessoa.getSexo()==null?"Não informado":pessoa.getSexo().toString()));
		genero.setText("Gênero: "+ (pessoa.getGenero()==null?"Não informado":pessoa.getGenero().toString()));
		escolaridade.setText("Escolaridade: "+ (pessoa.getEscolaridade()==null?"Não informado":pessoa.getEscolaridade().toString()));
		composicao.setText("Composição Familiar: "+(pessoa.getComposicao()==null?Composicao.N.toString(): pessoa.getComposicao().toString()));
		deficiencia.setText(pessoa.isComDeficiencia()?"Pessoa com Deficiência":"");
		gestante.setText(pessoa.isGestante()?"Gestante":"");
		scfv.setText(pessoa.isNoSCFV()?"Participante do SCFV":"");
		prioritario.setText(pessoa.isPrioritarioSCFV()?"Prioritário para o SCFV":"");
		for(Beneficio b: pessoa.getBeneficios()) {
			if(b.getNome().equals(BeneficioTipo.PBF)) {
				pbf_b.setText("Programa Bolsa Família");
				valorBolsa.setText("R$: " + b.getValor());
			}
			if(b.getNome().equals(BeneficioTipo.BPCI)) {
				bpci_b.setText("BPC Idoso");
				valorBpci.setText("R$: "+ b.getValor());
			}
			if(b.getNome().equals(BeneficioTipo.BPCDEF)) {
				bpcd_b.setText("BPC Pes. Deficiência");
				valorBpcd.setText("R$: "+b.getValor());
			}
			if(b.getNome().equals(BeneficioTipo.NV)) {
				nova_b.setText("Nova Vida");
				valorNv.setText("R$: "+b.getValor());
			}
			if(b.getNome().equals(BeneficioTipo.O)) {
				outro_b.setText("Outro");
				valorOutro.setText("R$: "+ b.getValor());
			}
			
		}
		
		
	}
}
