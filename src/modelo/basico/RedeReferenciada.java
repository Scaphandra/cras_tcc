package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.enumerados.RedeTipo;

@Entity
@Table(name="rede_referenciada")
public class RedeReferenciada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_rede;
	
	private String nome_rede;
	
	private String setor;
	
	private RedeTipo natureza;
	
	@Embedded
	private Endereco endereco_rede;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="telefones_familia")
	private List <String> telefones_rede = new ArrayList<>();
	
	@OneToOne(mappedBy="encaminhada_familia")
	private Familia familia_encaminhada;
	
	
	public RedeReferenciada() {
		
	}

	public RedeReferenciada(String nome, String setor, RedeTipo natureza, Endereco endereco, String telefone, Familia familia) {
		super();
		this.nome_rede = nome;
		this.setor = setor;
		this.natureza = natureza;
		this.endereco_rede = endereco;
		this.telefones_rede.add(telefone);
		this.familia_encaminhada = familia;
	}


	public String getNome_rede() {
		return nome_rede;
	}


	public void setNome_rede(String nome) {
		this.nome_rede = nome;
	}


	public String getSetor() {
		return setor;
	}


	public void setSetor(String setor) {
		this.setor = setor;
	}


	public RedeTipo getNatureza() {
		return natureza;
	}


	public void setNatureza(RedeTipo natureza) {
		this.natureza = natureza;
	}


	public Endereco getEndereco_rede() {
		return endereco_rede;
	}


	public void setEndereco_rede(Endereco endereco) {
		this.endereco_rede = endereco;
	}


	public List<String> getTelefones_rede() {
		return telefones_rede;
	}


	public void setTelefones_rede(String telefone) {
		this.telefones_rede.add(telefone);
	}


	public Familia getFamilia_encaminhada() {
		return familia_encaminhada;
	}


	public void setFamilia_encaminhada(Familia familia_encaminhada) {
		this.familia_encaminhada = familia_encaminhada;
	}

	@Override
	public String toString() {
		return "RedeReferenciada [id_rede=" + id_rede + ", nome_rede=" + nome_rede + "]";
	}
	
	
}
