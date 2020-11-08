package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
@Table(name="acolhidas")
public class Acolhida {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_acolhida;
	
	@Temporal(TemporalType.DATE)
	private Date dataPrevista_adolhida;
	
	@OneToMany(mappedBy="acolhida_pes")
	private List <Pessoa> pes_acolhida = new ArrayList<>();
	
	@ManyToOne
	private Tecnico tecnico_acolhida;
	
	public Acolhida() {
		
	}

	public Acolhida(Date dataPrevista, Pessoa pessoas, Tecnico tecnico) {
		super();
		this.dataPrevista_adolhida = dataPrevista;
		this.pes_acolhida.add(pessoas);
		this.tecnico_acolhida = tecnico;
	}

	public Long getId_acolhida() {
		return id_acolhida;
	}

	public void setId_acolhida(Long id) {
		this.id_acolhida = id;
	}

	public Date getDataPrevista_acolhida() {
		return dataPrevista_adolhida;
	}

	public void setDataPrevista_acolhida(Date dataPrevista) {
		this.dataPrevista_adolhida = dataPrevista;
	}

	public List<Pessoa> getPes_acolhida() {
		return pes_acolhida;
	}

	public void setPes_acolhida(Pessoa pessoa) {
		this.pes_acolhida.add(pessoa);
	}

	public Tecnico getTecnico_acolhida() {
		return tecnico_acolhida;
	}

	public void setTecnico_acolhida(Tecnico tecnico) {
		this.tecnico_acolhida = tecnico;
	}

	@Override
	public String toString() {
		return "Acolhida [id_acolhida=" + id_acolhida + ", dataPrevista_adolhida=" + dataPrevista_adolhida.toString()
				+ ", pes_acolhida=" + pes_acolhida.toString() + ", tecnico_acolhida=" + tecnico_acolhida.toString() + "]";
	}

	
	
	
}
