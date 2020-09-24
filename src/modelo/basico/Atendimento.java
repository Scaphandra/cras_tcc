package modelo.basico;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private Long id_atendimento;
	
	@ManyToOne
	private Familia familia_atendimento;
	
	@ManyToOne
	private Pessoa pessoa_atendimento;
	
	@Temporal(TemporalType.DATE)
	private Date data_atendimento;
	
	private AtendimentoTipo tipo_atendimento;
	
	private DemandaAtendimento demanda_atendimento;
	
	private boolean relatorio;
	
	private BeneficioEventual beneficioEventual_atendimento;
	
	@ManyToOne
	private Funcionario funcionario_atendimento;
	

	public Atendimento() {
		
	}


	public Atendimento(Familia familia, Pessoa pessoa, Date data, AtendimentoTipo tipo, DemandaAtendimento demanda,
			boolean relatorio, BeneficioEventual beneficioEventual, Funcionario funcionario) {
		super();
		this.familia_atendimento = familia;
		this.pessoa_atendimento = pessoa;
		this.data_atendimento = data;
		this.tipo_atendimento = tipo;
		this.demanda_atendimento = demanda;
		this.relatorio = relatorio;
		this.beneficioEventual_atendimento = beneficioEventual;
		this.funcionario_atendimento = funcionario;
	}



	public Long getId_atendimento() {
		return id_atendimento;
	}

	public void setId_atendimento(Long id) {
		this.id_atendimento = id;
	}

	public Familia getFamilia_atendimento() {
		return familia_atendimento;
	}

	public void setFamilia_atendimento(Familia familia) {
		this.familia_atendimento = familia;
	}

	public Pessoa getPessoa_atendimento() {
		return pessoa_atendimento;
	}

	public void setPessoa_atendimento(Pessoa pessoa) {
		this.pessoa_atendimento = pessoa;
	}

	public Date getData_atendimento() {
		return data_atendimento;
	}

	public void setData_atendimento(Date data) {
		this.data_atendimento = data;
	}
	
	public Funcionario getFuncionario_atendimento() {
		return funcionario_atendimento;
	}

	public void setFuncionario_atendimento(Funcionario funcionario) {
		this.funcionario_atendimento = funcionario;
	}

	public AtendimentoTipo getTipo_atendimento() {
		return tipo_atendimento;
	}

	public void setTipo_atendimento(AtendimentoTipo tipo) {
		this.tipo_atendimento = tipo;
	}

	public DemandaAtendimento getDemanda_atendimento() {
		return demanda_atendimento;
	}

	public void setDemanda_atendimento(DemandaAtendimento demanda) {
		this.demanda_atendimento = demanda;
	}

	public boolean isRelatorio() {
		return relatorio;
	}

	public void setRelatorio(boolean relatorio) {
		this.relatorio = relatorio;
	}

	public BeneficioEventual getBeneficioEventual_atendimento() {
		return beneficioEventual_atendimento;
	}

	public void setBeneficioEventual_atendimento(BeneficioEventual beneficioEventual) {
		this.beneficioEventual_atendimento = beneficioEventual;
	}


	@Override
	public String toString() {
		return "Atendimento [id_atendimento=" + id_atendimento + ", familia_atendimento=" + familia_atendimento.toString()
				+ ", pessoa_atendimento=" + pessoa_atendimento.toString() + ", data_atendimento=" + data_atendimento.toString()
				+ ", tipo_atendimento=" + tipo_atendimento.toString() + ", demanda_atendimento=" + demanda_atendimento.toString()
				+ "beneficioEventual_atendimento=" + beneficioEventual_atendimento.toString()
				+ ", funcionario_atendimento=" + funcionario_atendimento.toString() + "]";
	}
	
	
}
