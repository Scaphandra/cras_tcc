package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.enumerados.FaixaEtaria;
import modelo.enumerados.TecnicoReferencia;

@Entity
@Table(name="grupos_scfv")
public class GrupoSCFV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_grupo")
	private Long id;
	
	@Column(name="nome_grupo")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private FaixaEtaria faixa;
	
	@OneToOne
	@JoinColumn(name="tecnico_grupo")
	private Tecnico tecnico;
	
	@OneToOne
	private Funcionario orientador;
	
	private int quantidadeMax;
	
	@OneToMany(mappedBy="grupo")
	private List<Pessoa> pessoas = new ArrayList<>();
	
	@Column(columnDefinition="TEXT")
	private String observacoes;
	
	public GrupoSCFV() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnicoReferencia) {
		this.tecnico = tecnicoReferencia;
		tecnicoReferencia.setReferencia(TecnicoReferencia.SCFV);
	}

	public Funcionario getOrientador() {
		return orientador;
	}

	public void setOrientador(Funcionario orientador) {
		this.orientador = orientador;
	}

	public int getQuantidadeMax() {
		return quantidadeMax;
	}

	public void setQuantidadeMax(int quantidadeMax) {
		this.quantidadeMax = quantidadeMax;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoa pessoa) {
		this.pessoas.add(pessoa);
		pessoa.setNoSCFV(true);
	}	
	
	public void excluirPessoa(Pessoa pessoa) {
		this.pessoas.remove(pessoa);
		pessoa.setNoSCFV(false);
	}


	public FaixaEtaria getFaixa() {
		return faixa;
	}

	public void setFaixa(FaixaEtaria faixa) {
		this.faixa = faixa;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public String toString() {
		return "GrupoSCFV [id_grupo=" + id + ", nome_grupo=" + nome + "]";
	}
	
	
}
