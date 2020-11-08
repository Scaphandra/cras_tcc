package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import modelo.enumerados.Escolaridade;
import modelo.enumerados.NivelAcesso;
import modelo.enumerados.Vinculo;

//@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id_funcionario;
	
	protected String nome_funcionario;
	
	protected String funcao;

	protected String cpf_funcionario;
	
	@Column(length=150)
	private String email;
	
	@Column(nullable=false, length=20)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable= false)
	protected NivelAcesso nivel;
	
	@Enumerated(EnumType.STRING)
	protected Escolaridade escolaridade_funcionario;
	
	protected int cargaHoraria;
	
	@Enumerated(EnumType.STRING)
	protected Vinculo vinculo;
	
	@ManyToMany
	private List <Reuniao> reunioes_funcionario = new ArrayList<>();
	
	
	protected Endereco enderecos_funcionario = new Endereco();
	
	
	protected String telefones_funcionario;
	
	
	public Funcionario() {
		
	}

	public Funcionario(String nome, String funcao, String cpf, String senha, NivelAcesso nivel) {
		super();
		this.nome_funcionario = nome;
		this.funcao = funcao;
		this.cpf_funcionario = cpf;
		this.senha = senha;
		this.nivel = nivel;
	}


	public Funcionario(String nome, String funcao,String cpf,String email, String senha, NivelAcesso nivel, Escolaridade escolaridade, int cargaHoraria, Vinculo vinculo,
			Endereco endereco, String telefone, Reuniao reuniao) {
		super();
		this.nome_funcionario = nome;
		this.funcao = funcao;
		this.cpf_funcionario = cpf;
		this.email = email;
		this.senha = senha;
		this.nivel = nivel;
		this.escolaridade_funcionario = escolaridade;
		this.cargaHoraria = cargaHoraria;
		this.vinculo = vinculo;
		this.enderecos_funcionario = endereco;
		this.telefones_funcionario = telefone;
		this.reunioes_funcionario.add(reuniao);
	}


	public Long getId_funcionario() {
		return id_funcionario;
	}


	public void setId_funcionario(Long id) {
		this.id_funcionario = id;
	}


	public List<Reuniao> getReunioes_funcionario() {
		return reunioes_funcionario;
	}

	public void setReunioes_funcionario(Reuniao reuniao) {
		this.reunioes_funcionario.add(reuniao);
	}

	public String getNome_funcionario() {
		return nome_funcionario;
	}


	public void setNome_funcionario(String nome) {
		this.nome_funcionario = nome;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	public String getCpf_funcionario() {
		return cpf_funcionario;
	}


	public void setCpf_funcionario(String cpf) {
		this.cpf_funcionario = cpf;
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


	public Escolaridade getEscolaridade_funcionario() {
		return escolaridade_funcionario;
	}


	public void setEscolaridade_funcionario(Escolaridade escolaridade) {
		this.escolaridade_funcionario = escolaridade;
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
	public Endereco getEndereco_funcionario() {
		return enderecos_funcionario;
	}


	public void setEndereco_funcionario(Endereco endereco) {
		this.enderecos_funcionario = endereco;
	}

	public String getTelefone_funcionario() {
		return telefones_funcionario;
	}


	public void setTelefone_funcionario(String telefone) {
		this.telefones_funcionario = telefone;
	}

	@Override
	public String toString() {
		return "Funcionario [id_funcionario=" + id_funcionario + ", nome_funcionario=" + nome_funcionario + "]";
	}
	
	
}
