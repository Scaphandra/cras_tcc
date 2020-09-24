package modelo.basico;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import modelo.enumerados.BeneficioTipo;

@Entity
public class Beneficio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_beneficio;
	
	private BeneficioTipo nome_beneficio;
	
	private double valor_beneficio;
	
	@ManyToOne
	private Pessoa pessoa_beneficio;
	
	@ManyToOne
	private Familia familia_beneficio;
	
	public Beneficio() {
		
	}

	public Beneficio(BeneficioTipo nome, double valor, Pessoa pessoa) {
		super();
		this.nome_beneficio = nome;
		this.valor_beneficio = valor;
		this.pessoa_beneficio = pessoa;
	}
	@Enumerated(EnumType.STRING)
	public BeneficioTipo getNome_beneficio() {
		return nome_beneficio;
	}

	public void setNome_beneficio(BeneficioTipo nome) {
		this.nome_beneficio = nome;
	}

	public double getValor_beneficio() {
		return valor_beneficio;
	}

	public void setValor_beneficio(double valor) {
		this.valor_beneficio = valor;
	}

	public Pessoa getPessoa_beneficio() {
		return pessoa_beneficio;
	}

	public void setPessoa_beneficio(Pessoa pessoa_beneficio) {
		this.pessoa_beneficio = pessoa_beneficio;
	}

	public Familia getFamilia_beneficio() {
		return familia_beneficio;
	}

	public void setFamilia_beneficio(Familia familia_beneficio) {
		this.familia_beneficio = familia_beneficio;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	

	
	
}
