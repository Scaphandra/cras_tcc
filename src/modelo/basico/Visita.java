package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.enumerados.VisitaTipo;
@Entity
@Table(name="visitas")
public class Visita {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_visita;
	
	
	private VisitaTipo tipo_visita;
	
	@Temporal(TemporalType.DATE)
	private Date data_visita;
	
	private String motivo_visita;
	
	@ManyToMany
	private List <Tecnico> tecnicos_visita = new ArrayList<>();
	

	public Visita() {
		
	}


	public Visita(VisitaTipo tipo, Date data, String motivo, Tecnico tecnico) {
		super();
		this.tipo_visita = tipo;
		this.data_visita = data;
		this.motivo_visita = motivo;
		this.tecnicos_visita.add(tecnico);
	}


	public Long getId_visita() {
		return id_visita;
	}


	public void setId_visita(Long id) {
		this.id_visita = id;
	}

	@Enumerated(EnumType.STRING)
	public VisitaTipo getTipo_visita() {
		return tipo_visita;
	}


	public void setTipo_visita(VisitaTipo tipo) {
		this.tipo_visita = tipo;
	}


	public Date getData_visita() {
		return data_visita;
	}


	public void setData_visita(Date data) {
		this.data_visita = data;
	}


	public String getMotivo_visita() {
		return motivo_visita;
	}


	public void setMotivo_visita(String motivo) {
		this.motivo_visita = motivo;
	}


	public List<Tecnico> getTecnicos_visita() {
		return tecnicos_visita;
	}


	public void setTecnicos_visita(Tecnico tecnico) {
		this.tecnicos_visita.add(tecnico);
	}


	@Override
	public String toString() {
		return "Visita [id_visita=" + id_visita + ", tipo_visita=" + tipo_visita.toString() + "]";
	}
	
	
}
