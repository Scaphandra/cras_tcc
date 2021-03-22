package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length=5, discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("CRAS")
public class Unidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unidade")
	private int id;
	
	@OneToMany(mappedBy= "unidade", fetch=FetchType.LAZY)
	private List <Familia> familias = new ArrayList<>();
	
	@OneToMany(mappedBy = "unidade", fetch=FetchType.LAZY)
	private List<Pessoa> pessoas = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Acompanhamento> acompanhamentos = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Atendimento> atendimentos = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Funcionario> funcionarios = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Acolhida> acolhidas = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Encaminhamento> encaminhamentos = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Visita> visitas = new ArrayList<>();
	
	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
	private List <Reuniao> reunioes = new ArrayList<>();
	
//	@OneToMany(mappedBy="unidade", fetch=FetchType.LAZY)
//	private List <Agenda> agendas = new ArrayList<>();

	private String idCRAS;
	
	private String nomeCRAS;
	
	private String telCRAS;
	
	@Temporal(TemporalType.DATE)
	private Date dataImplement;
	
	private int capacidade;
	
	private String horarioFuncionamento;
	
	@Embedded
	private Endereco endereco;
	
	@OneToOne
	private Coordenador coordenador;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="abrangencia")
	private List <String> areaAbrangencia = new ArrayList<>(); 
	
	public Unidade() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdCRAS() {
		return idCRAS;
	}

	public void setIdCRAS(String idCRAS) {
		this.idCRAS = idCRAS;
	}

	public String getNomeCRAS() {
		
		return nomeCRAS;
	}

	public void setNomeCRAS(String nome) {
		this.nomeCRAS = nome;
	}
	
	public String getTelCRAS() {
		return telCRAS;
	}
	public void setTelCRAS(String telefone) {
		this.telCRAS = telefone;
	}

	public Date getDataImplement() {
		return dataImplement;
	}

	public void setDataImplement(Date dataImplement) {
		this.dataImplement = dataImplement;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco enderecoUnidade) {
		this.endereco = enderecoUnidade;
	}

	public Funcionario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	@Override
	public String toString() {
		return "Unidade [nome_CRAS=" + nomeCRAS + "]";
	}

	public List<String> getAreaAbrangencia() {
		return areaAbrangencia;
	}

	public void setAreaAbrangencia(List<String> areaAbrangencia) {
		this.areaAbrangencia = areaAbrangencia;
	}


	public List<Familia> getFamilias() {
		return familias;
	}


	public void setFamilia(Familia familia) {
		this.familias.add(familia);
		
	}


	public List<Pessoa> getPessoas() {
		return pessoas;
	}


	public void setPessoa(Pessoa pessoa) {
		if(!pessoas.contains(pessoa)) {
			this.pessoas.add(pessoa);
		}
	}

	public List<Acompanhamento> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void setAcompanhamentos(Acompanhamento acompanhamento) {
		this.acompanhamentos.add(acompanhamento);
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}

	public List<Acolhida> getAcolhidas() {
		return acolhidas;
	}

	public void setAcolhida(Acolhida acolhida) {
		this.acolhidas.add(acolhida);
	}
//
//	public List<Agenda> getAgendas() {
//		return agendas;
//	}
//
//	public void setAgenda(Agenda agenda) {
//		this.agendas.add(agenda);
//	}

	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}

	public void setEncaminhamentos(Encaminhamento encaminhamento) {
		this.encaminhamentos.add(encaminhamento);
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisita(Visita visita) {
		this.visitas.add(visita);
	}

	public List<Reuniao> getReunioes() {
		return reunioes;
	}

	public void setReunioes(Reuniao reuniao) {
		this.reunioes.add(reuniao);
	}
	
	
}
