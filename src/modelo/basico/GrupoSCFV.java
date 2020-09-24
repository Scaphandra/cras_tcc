package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="grupos_scfv")
public class GrupoSCFV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_grupo;
	
	private String nome_grupo;
	
	@OneToOne
	private Tecnico tecnicoReferencia_grupo;
	
	@OneToOne
	private Funcionario orientador;
	
	private int quantidadeMax;
	
	@OneToMany(mappedBy="grupo_pes")
	private List<Pessoa> pessoas_grupo = new ArrayList<>();
	
	public GrupoSCFV() {
		
	}

	public GrupoSCFV(String nome, Tecnico tecnicoReferencia, Funcionario orientador, int quantidadeMax,
			Pessoa pessoa) {
		super();
		this.nome_grupo = nome;
		this.tecnicoReferencia_grupo = tecnicoReferencia;
		this.orientador = orientador;
		this.quantidadeMax = quantidadeMax;
		this.pessoas_grupo.add(pessoa);
	}

	public Long getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Long id) {
		this.id_grupo = id;
	}

	public String getNome_grupo() {
		return nome_grupo;
	}

	public void setNome_grupo(String nome) {
		this.nome_grupo = nome;
	}

	public Tecnico getTecnicoReferencia_grupo() {
		return tecnicoReferencia_grupo;
	}

	public void setTecnicoReferencia_grupo(Tecnico tecnicoReferencia) {
		this.tecnicoReferencia_grupo = tecnicoReferencia;
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

	public List<Pessoa> getPessoas_grupo() {
		return pessoas_grupo;
	}

	public void setPessoas_grupo(Pessoa pessoa) {
		this.pessoas_grupo.add(pessoa);
	}

	@Override
	public String toString() {
		return "GrupoSCFV [id_grupo=" + id_grupo + ", nome_grupo=" + nome_grupo + "]";
	}
	
	
}
