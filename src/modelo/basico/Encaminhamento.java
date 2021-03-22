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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.enumerados.EncaminhamentoTipo;

@Entity
public class Encaminhamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_encaminhamento")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_encaminhamento")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_encaminhamento")
	private EncaminhamentoTipo tipo;
	
	public Encaminhamento() {
		
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

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
		tecnico.setEncaminhamentos(this);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		pessoa.setEncaminhamentos(this);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public EncaminhamentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(EncaminhamentoTipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Encaminhamento [id=" + id + ", tecnico="
				+ tecnico.toString() + ", familia=" + pessoa.toString()
				+ ", data=" + data.toString() + ", tipo =" + tipo.toString() + "]";
	}

	
	
}
