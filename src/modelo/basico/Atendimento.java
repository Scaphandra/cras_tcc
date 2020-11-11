package modelo.basico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
//	@Column(name="beneficioEventual_atendimento")
//	private BeneficioEventual beneficioEventual;
	
	@ManyToOne
	@JoinColumn(name="funcionario_atendimento")
	private Funcionario funcionario;
	

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
	}

	public AtendimentoTipo getTipo() {
		return tipo;
	}

	public void setTipo_atendimento(AtendimentoTipo tipo) {
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

//	public BeneficioEventual getBeneficioEventual() {
//		return beneficioEventual;
//	}
//
//	public void setBeneficioEventual(BeneficioEventual beneficioEventual) {
//		this.beneficioEventual = beneficioEventual;
//	}

// + beneficioEventual.toString()
	
	
	
	@Override
	public String toString() {
		return "Atendimento [id_atendimento=" + id
				+ ", data_atendimento=" + data.toString() + ",pessoa= "+ pessoa.toString()
				+ ", tipo_atendimento=" + tipo.toString() + ", demanda_atendimento=" + demanda.toString()
				+ "beneficioEventual_atendimento="
				+ ", funcionario_atendimento=" + funcionario.toString() + "]";
	}
	
	
}
