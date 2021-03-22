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

import modelo.enumerados.ReuniaoTipo;

@Entity
@Table(name="reunioes")
public class Reuniao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reuniao")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_reuniao")
	private ReuniaoTipo tipo;
	
	@Column(name="motivo_reuniao", columnDefinition="VARCHAR(200)")
	private String motivo;
	
	@ManyToMany
	@JoinTable(name="funcionarios_reuniao",
	joinColumns= {@JoinColumn(name="id_reuniao")},
	inverseJoinColumns= {@JoinColumn(name="id_funcionario")})
	private List <Funcionario> funcionarios = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_reuniao")
	private Date data;
	
	@Column(columnDefinition="LONGTEXT")
	private String ata;
	
	public Reuniao() {
		
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

	@Enumerated(EnumType.STRING)
	public ReuniaoTipo getTipo() {
		return tipo;
	}

	public void setTipo(ReuniaoTipo tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}


	@Override
	public String toString() {
		return "Reuniao [id_reuniao=" + id + ", tipo_reuniao=" + tipo.toString() + "]";
	}
	
	
}
