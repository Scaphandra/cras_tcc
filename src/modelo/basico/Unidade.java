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

@Entity 
public class Unidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unidade")
	private Long id;

	private String idCRAS;
	
	private String nomeCRAS;
	
	private String telCRAS;
	
	@Temporal(TemporalType.DATE)
	private Date dataImplement;
	
	private int capacidade;
	
	private String horarioFuncionamento;
	
	@Embedded
	private Endereco endereco;
	
	@OneToOne
	private Coordenador coordenador;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="abrangencia")
	private List <String> areaAbrangencia = new ArrayList<>(); 
	
	public Unidade() {
		
	}


	public String getIdCRAS() {
		return idCRAS;
	}

	public void setIdCRAS(String idCRAS) {
		this.idCRAS = idCRAS;
	}

	public String getNomeCRAS() {
		return nomeCRAS;
	}

	public void setNomeCRAS(String nome) {
		this.nomeCRAS = nome;
	}
	
	public String getTelCRAS() {
		return telCRAS;
	}
	public void setTelCRAS(String telefone) {
		this.telCRAS = telefone;
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
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco enderecoUnidade) {
		this.endereco = enderecoUnidade;
	}

	public Funcionario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	@Override
	public String toString() {
		return "Unidade [nome_CRAS=" + nomeCRAS + "]";
	}

	public List<String> getAreaAbrangencia() {
		return areaAbrangencia;
	}

	public void setAreaAbrangencia(List<String> areaAbrangencia) {
		this.areaAbrangencia = areaAbrangencia;
	}

	
	
}
