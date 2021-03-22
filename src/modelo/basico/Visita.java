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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.enumerados.VisitaTipo;
@Entity
@Table(name="visitas")
public class Visita {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_visita")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_visita")
	private VisitaTipo tipo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_visita")
	private Date data;
	
	@Column(name="motivo_visita", columnDefinition="VARCHAR(200)")
	private String motivo;
	
	@ManyToMany
	@JoinTable(name="visita_tecnico",
	joinColumns= {@JoinColumn(name="id_visita")},
	inverseJoinColumns= {@JoinColumn(name="id_tecnico")})
	private List <Tecnico> tecnicos_visita = new ArrayList<>();
	
	@ManyToOne
	private Familia familia;

	public Visita() {
		
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

	public VisitaTipo getTipo() {
		return tipo;
	}


	public void setTipo(VisitaTipo tipo) {
		this.tipo = tipo;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public List<Tecnico> getTecnicos() {
		return tecnicos_visita;
	}


	public void setTecnicos(Tecnico tecnico) {
		this.tecnicos_visita.add(tecnico);
		tecnico.setVisitas(this);
	}
	

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
		familia.setVisitas(this);
	}

	@Override
	public String toString() {
		return "Visita [id_visita=" + id + ", tipo_visita=" + tipo.toString() + "]";
	}
	
	
}
