package modelo.basico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import modelo.enumerados.BeneficioTipo;

@Entity
public class Beneficio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_beneficio;
	
	@Enumerated(EnumType.STRING)
	@Column(name="nome_beneficio")
	private BeneficioTipo nome;
	
	@Column(name="valor_beneficio", precision=2, scale=2)
	private double valor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pessoa_beneficio")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="familia_beneficio")
	private Familia familia;
	
	public Beneficio() {
		
	}

	public Beneficio(BeneficioTipo nome, double valor, Pessoa pessoa) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.pessoa = pessoa;
	}
	
	
	
	public Long getId_beneficio() {
		return id_beneficio;
	}

	public void setId_beneficio(Long id_beneficio) {
		this.id_beneficio = id_beneficio;
	}

	@Enumerated(EnumType.STRING)
	public BeneficioTipo getNome() {
		return nome;
	}

	public void setNome(BeneficioTipo nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa_beneficio) {
		this.pessoa = pessoa_beneficio;
		//this.pessoa.setNomesBeneficios(this.nome.toString());
	}
	

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	@Override
	public String toString() {
		return "Beneficio [id_beneficio=" + id_beneficio + ", nome " + nome + ", pessoa "
				+ pessoa + "]";
	}
	

	
	
	

	
	
}
