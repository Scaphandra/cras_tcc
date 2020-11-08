package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
@Table(name= "acompanhamento")
public class Acompanhamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_acompanhamento;
	
	@OneToMany(mappedBy="acompanhamento")
	private List<Familia> familias_acompanhamento = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrada_acompanhamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataSaida_acompanhamento;
	
	private String motivoSaida_acompanhamento;
	
	@ManyToOne
	private Tecnico tecnico_acompanhamento;
	
	public Acompanhamento() {
		
	}

	public Acompanhamento(Date dataEntrada, Date dataSaida, String motivoSaida, Tecnico tecnico) {
		super();
//		this.familia_acompanhamento = familia;
		this.dataEntrada_acompanhamento = dataEntrada;
		this.dataSaida_acompanhamento = dataSaida;
		this.motivoSaida_acompanhamento = motivoSaida;
		this.tecnico_acompanhamento = tecnico;
	}

	public Long getId_acompanhamento() {
		return id_acompanhamento;
	}

	public void setId_acompanhamento(Long id) {
		this.id_acompanhamento = id;
	}

//	public Familia getFamilia() {
//		return familia_acompanhamento;
//	}
//
//	public void setFamilia(Familia familia) {
//		this.familia_acompanhamento = familia;
//	}

	public Date getDataEntrada_acompanhamento() {
		return dataEntrada_acompanhamento;
	}

	public void setDataEntrada_acompanhamento(Date dataEntrada) {
		this.dataEntrada_acompanhamento = dataEntrada;
	}

	public Date getDataSaida_acompanhamento() {
		return dataSaida_acompanhamento;
	}

	public void setDataSaida_acompanhamento(Date dataSaida) {
		this.dataSaida_acompanhamento = dataSaida;
	}

	public String getMotivoSaida_acompanhamento() {
		return motivoSaida_acompanhamento;
	}

	public void setMotivoSaida_acompanhamento(String motivoSaida) {
		this.motivoSaida_acompanhamento = motivoSaida;
	}

	public Tecnico getTecnico_acompanhamento() {
		return tecnico_acompanhamento;
	}

	public void setTecnico_acompanhamento(Tecnico tecnico) {
		this.tecnico_acompanhamento = tecnico;
	}

	@Override
	public String toString() {
		return "Acompanhamento [id_acompanhamento=" + id_acompanhamento + ", dataEntrada_acompanhamento="
				+ dataEntrada_acompanhamento.toString() + ", dataSaida_acompanhamento=" + dataSaida_acompanhamento.toString()
				+ ", motivoSaida_acompanhamento=" + motivoSaida_acompanhamento + ", tecnico_acompanhamento="
				+ tecnico_acompanhamento.toString() + "]";
	}

	
}
