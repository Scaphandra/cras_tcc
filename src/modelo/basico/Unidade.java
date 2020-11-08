package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity 
public class Unidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_unidade;

	private String id_CRAS;
	
	private String nome_CRAS;
	
	private String tel_CRAS;
	
	@Temporal(TemporalType.DATE)
	private Date dataImplement;
	
	private int capacidade;
	
	private String horarioFuncionamento;
	
	@Embedded
	private Endereco endereco_unidade;
	
	@OneToOne
	private Funcionario coordenador;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="abrangencia")
	private List <String> areaAbrangencia = new ArrayList<>(); 
	
	public Unidade() {
		
	}

	public Unidade(String id_CRAS, String nome, String telefone, Date dataImplement, int capacidade, String horarioFuncionamento,
			Endereco enderecoUnidade, Funcionario coordenador, List<String> abrangencia) {
		super();
		this.id_CRAS = id_CRAS;
		this.nome_CRAS = nome;
		this.tel_CRAS = telefone;
		this.dataImplement = dataImplement;
		this.capacidade = capacidade;
		this.horarioFuncionamento = horarioFuncionamento;
		this.endereco_unidade = enderecoUnidade;
		this.coordenador = coordenador;
		this.areaAbrangencia = abrangencia;
	}

	public String getId_CRAS() {
		return id_CRAS;
	}

	public void setId_CRAS(String id_CRAS) {
		this.id_CRAS = id_CRAS;
	}

	public String getNome_CRAS() {
		return nome_CRAS;
	}

	public void setNome_CRAS(String nome) {
		this.nome_CRAS = nome;
	}
	
	public String getTel_CRAS() {
		return tel_CRAS;
	}
	public void setTel_CRAS(String telefone) {
		this.tel_CRAS = telefone;
	}

	public Date getDataImplement() {
		return dataImplement;
	}

	public void setDataImplement(Date dataImplement) {
		this.dataImplement = dataImplement;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}
	
	public Endereco getEnderecoUnidade() {
		return endereco_unidade;
	}

	public void setEnderecoUnidade(Endereco enderecoUnidade) {
		this.endereco_unidade = enderecoUnidade;
	}

	public Funcionario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Funcionario coordenador) {
		this.coordenador = coordenador;
	}

	@Override
	public String toString() {
		return "Unidade [nome_CRAS=" + nome_CRAS + "]";
	}

	public List<String> getAreaAbrangencia() {
		return areaAbrangencia;
	}

	public void setAreaAbrangencia(List<String> areaAbrangencia) {
		this.areaAbrangencia = areaAbrangencia;
	}

	
	
}
