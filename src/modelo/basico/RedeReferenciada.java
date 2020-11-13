package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import modelo.enumerados.RedeTipo;

@Entity
@Table(name="rede_referenciada")
public class RedeReferenciada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rede")
	private Long id;
	
	@Column(name="nome_rede")
	private String nome;
	
	private String setor;
	
	@Enumerated(EnumType.STRING)
	private RedeTipo natureza;
	
	@Embedded
	private Endereco endereco_rede;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="telefones_familia")
	private List <String> telefones = new ArrayList<>();
	
	public RedeReferenciada() {
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
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


	public List<String> getTelefones() {
		return telefones;
	}


	public void setTelefones(String telefone) {
		this.telefones.add(telefone);
	}


	@Override
	public String toString() {
		return "RedeReferenciada [id_rede=" + id + ", nome_rede=" + nome + "]";
	}
	
	
}
