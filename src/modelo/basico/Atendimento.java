package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.enumerados.AtendimentoTipo;
import modelo.enumerados.DemandaAtendimento;

@Entity
@Table(name="atendimentos")
public class Atendimento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_atendimento")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@OneToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_atendimento")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_atendimento")
	private AtendimentoTipo tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="demanda_atendimento")
	private DemandaAtendimento demanda;
	
	@Column(name="relatorio_atendimento")
	private boolean relatorio;
	
	@Column(name="relatorio_encaminhamento", columnDefinition = "boolean default false")
	private boolean encaminhamento;
	
	@Column(name="beneficioEventual_atendimento")
	private BeneficioEventual beneficioEventual;
	
	@ManyToOne
	@JoinColumn(name="funcionario_atendimento")
	private Funcionario funcionario;
	
	private Long idFamilia;
	
//	@Column(name="descricao_atendimento", columnDefinition="VARCHAR(500)")
//	private String descricao;
	
	@ManyToMany
	@JoinTable(name="atendimento_grupo",
	joinColumns= {@JoinColumn(name="id_atendimento")},
	inverseJoinColumns= {@JoinColumn(name="id_pessoa")})
	List <Pessoa> grupo = new ArrayList<>();
	
	public Atendimento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		pessoa.setAtendimentos(this);
	}

	public List<Pessoa> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Pessoa> grupo) {
		this.grupo = grupo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		funcionario.setAtendimentos(this);
	}

	public AtendimentoTipo getTipo() {
		return tipo;
	}

	//adicionar atualização do cad
	public void setTipo(AtendimentoTipo tipo) {
		this.tipo = tipo;

	}

	public DemandaAtendimento getDemanda() {
		return demanda;
	}

	public void setDemanda(DemandaAtendimento demanda) {
		this.demanda = demanda;
	}

	public boolean isRelatorio() {
		return relatorio;
	}

	public void setRelatorio(boolean relatorio) {
		this.relatorio = relatorio;
	}

	public boolean isEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(boolean encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public BeneficioEventual getBeneficioEventual() {
		return beneficioEventual;
	}

	public void setBeneficioEventual(BeneficioEventual beneficioEventual) {
		this.beneficioEventual = beneficioEventual;
	}	
	
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public Long getIdFamilia() {
		
		return this.pessoa.getFamilia().getId();
	}
//	public void setIdFamilia() {
//		
//		this.idFamilia = this.pessoa.getFamilia().getId();
//	}

//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}

	@Override
	public String toString() {
		return tipo.toString();
	}
	
	
}
