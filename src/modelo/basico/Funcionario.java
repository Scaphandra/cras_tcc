package modelo.basico;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import modelo.enumerados.Escolaridade;
import modelo.enumerados.NivelAcesso;
import modelo.enumerados.Vinculo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length=2, discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("FU")
@Table(name="funcionarios")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_funcionario")
	protected Long id;
	
	@Column(name="nome_funcionario")
	protected String nome;
	
	protected String funcao;

	@Column(name="cpf_funcionario")
	protected String cpf;
	
	@Column(length=150)
	private String email;
	
	@Column(nullable=false, length=20)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable= false)
	protected NivelAcesso nivel;
	
	@Enumerated(EnumType.STRING)
	@Column(name="escolaridade_funcionario")
	protected Escolaridade escolaridade;
	
	protected int cargaHoraria;
	
	@Enumerated(EnumType.STRING)
	protected Vinculo vinculo;
	
	@Column(name="endereco_funcionario")
	protected Endereco endereco = new Endereco();
	
	@Column(name="telefone_funcionario")
	protected String telefone;
	
	
	public Funcionario() {
		
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


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public NivelAcesso getNivel() {
		return nivel;
	}


	public void setNivel(NivelAcesso nivel) {
		this.nivel = nivel;
	}


	public Escolaridade getEscolaridade() {
		return escolaridade;
	}


	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}


	public int getCargaHoraria() {
		return cargaHoraria;
	}


	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}


	public Vinculo getVinculo() {
		return vinculo;
	}


	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Funcionario [id_funcionario=" + id + ", nome_funcionario=" + nome + "]";
	}
	
	
}
