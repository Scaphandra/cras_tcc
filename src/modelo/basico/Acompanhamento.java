package modelo.basico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "acompanhamento")
public class Acompanhamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_acompanhamento")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="familia_acompanhamento")
	private Familia familia;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_entrada_acom")
	private Date dataEntrada;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_saida_acom")
	private Date dataSaida;
	
	@Column(name="motivo_saida_acom")
	private String motivoSaida;
	
	@OneToOne
	@JoinColumn(name="tecnico_acompanhamento")
	private Tecnico tecnico;
	
	public Acompanhamento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getMotivoSaida() {
		return motivoSaida;
	}

	public void setMotivoSaida(String motivoSaida) {
		this.motivoSaida = motivoSaida;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public String toString() {
		return "Acompanhamento [id_acompanhamento=" + id + ", dataEntrada_acompanhamento="
				+ dataEntrada.toString() + ", dataSaida_acompanhamento=" + dataSaida.toString()
				+ ", motivoSaida_acompanhamento=" + motivoSaida + ", tecnico_acompanhamento="
				+ tecnico.toString() + "]";
	}

	
}
