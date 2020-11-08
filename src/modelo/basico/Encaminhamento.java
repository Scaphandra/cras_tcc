package modelo.basico;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
public class Encaminhamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_encaminhamento;
	
	@ManyToOne
	private Tecnico tecnico_encaminhamento;
	
//	@ManyToOne
//	private Familia familia_encaminhamento;
	
	@Temporal(TemporalType.DATE)
	private Date data_encaminhamento;
	
	@OneToOne (cascade= {CascadeType.PERSIST})
	@JoinColumn(name="id_rede")
	private RedeReferenciada rede_encaminhamento;
	
	public Encaminhamento() {
		
	}

	public Encaminhamento(Tecnico tecnico, Familia familia, Date data, RedeReferenciada rede) {
		super();
		this.tecnico_encaminhamento = tecnico;
	//	this.familia_encaminhamento = familia;
		this.data_encaminhamento = data;
		this.rede_encaminhamento = rede;
	}

	public Long getId_encaminhamento() {
		return id_encaminhamento;
	}

	public void setId_encaminhamento(Long id) {
		this.id_encaminhamento = id;
	}

	public Tecnico getTecnico_encaminhamento() {
		return tecnico_encaminhamento;
	}

	public void setTecnico_encaminhamento(Tecnico tecnico) {
		this.tecnico_encaminhamento = tecnico;
	}

//	public Familia getFamilia_encaminhamento() {
//		return familia_encaminhamento;
//	}
//
//	public void setFamilia_encaminhamento(Familia familia) {
//		this.familia_encaminhamento = familia;
//	}

	public Date getData_encaminhamento() {
		return data_encaminhamento;
	}

	public void setData_encaminhamento(Date data) {
		this.data_encaminhamento = data;
	}

	public RedeReferenciada getRede_encaminhamento() {
		return rede_encaminhamento;
	}

	public void setRede_encaminhamento(RedeReferenciada rede) {
		this.rede_encaminhamento = rede;
	}
//+ familia_encaminhamento.toString()
	@Override
	public String toString() {
		return "Encaminhamento [id_encaminhamento=" + id_encaminhamento + ", tecnico_encaminhamento="
				+ tecnico_encaminhamento.toString() + ", familia_encaminhamento=" 
				+ ", data_encaminhamento=" + data_encaminhamento.toString() + ", rede_encaminhamento=" + rede_encaminhamento.toString() + "]";
	}

	
	
}
