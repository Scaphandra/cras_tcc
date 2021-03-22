package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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

@Entity
@Table(name="acolhidas")
public class Acolhida {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_acolhida")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataPrevista_acolhida")
	private Date dataPrevista;
	
	@OneToMany(mappedBy="acolhida")
	private List <Pessoa> pessoas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="tecnico_acolhida")
	private Tecnico tecnico;
	
	public Acolhida() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoa pessoa) {
		this.pessoas.add(pessoa);
		pessoa.setAcolhida(this);
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public String toString() {
		return "Acolhida [id_acolhida=" + id + ", dataPrevista_adolhida=" + dataPrevista.toString()
				+ ", pes_acolhida=" + pessoas.toString() + ", tecnico_acolhida=" + tecnico.toString() + "]";
	}

	
	
	
}
