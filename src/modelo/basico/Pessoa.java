package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length=2, discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("PE")
@Table(name="pessoas")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pessoa")
	private Long id;
	
	
//	@ManyToOne
//	@Column(name="grupo_pessoa")
//	private GrupoSCFV grupo;
	
	@Column(name="nome_pessoa")
	private String nome;
	
	@Column(name="cpf_pessoa")
	private String cpf;

	private String rg;

	private String nis;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private Sexo sexo;

	private Genero genero;

	private String nomeMae;

	private CorRaca cor;
	
	@Column(name="escolaridade_pessoa")
	private Escolaridade escolaridade;
	
	private double renda;
	
	private String ocupacao;
	
	private String parentesco;
	
	@Column(columnDefinition = "boolean default false")
	private boolean prioritarioSCFV = false;
	
	@OneToMany(mappedBy="pessoa", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List <Beneficio> beneficios = new ArrayList<>();
	
	private double totalBenef;

	@Column(columnDefinition = "boolean default false")
	private boolean gestante = false;
	
	@Column(columnDefinition = "boolean default false")
	private boolean homonimo = false;

	@Column(columnDefinition = "boolean default false")
	private boolean comDeficiencia = false;
	
	@Column(columnDefinition = "boolean default false")
	private boolean noSCFV = false;

	@Column(columnDefinition = "boolean default false", name="pessoa_referencia")
	private boolean pesReferencia = false;
	
//	@ManyToOne
//	private Acolhida acolhida_pes;
	//
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_familia")
	private Familia familia;
	
	public Pessoa() {
		
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id_pessoa) {
		this.id = id_pessoa;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	public boolean isHomonimo() {
		return homonimo;
	}


	public void setHomonimo(boolean homonimo) {
		this.homonimo = homonimo;
	}
	
	


	public boolean isPesReferencia() {
		return pesReferencia;
	}

	public void setPesReferencia(boolean pesReferencia) {
		this.pesReferencia = pesReferencia;
	}

	public String getCpf() {
		return cpf;
	}


	public void setCpf_pes(String cpf_pes) {
		this.cpf = cpf_pes;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getNis() {
		return nis;
	}


	public void setNis(String nis) {
		this.nis = nis;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public String getNomeMae() {
		return nomeMae;
	}


	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}


	public CorRaca getCor() {
		return cor;
	}


	public void setCor(CorRaca cor) {
		this.cor = cor;
	}


	public Escolaridade getEscolaridade() {
		return escolaridade;
	}


	public void setEscolaridade_pes(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}


	public double getRenda() {
		return renda;
	}


	public void setRenda(double renda) {
		this.renda = renda;
	}


	public String getOcupacao() {
		return ocupacao;
	}


	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}


	public boolean isPrioritarioSCFV() {
		return prioritarioSCFV;
	}


	public void setPrioritarioSCFV(boolean prioritarioSCFV) {
		this.prioritarioSCFV = prioritarioSCFV;
	}


	public List<Beneficio> getBeneficios() {
		
		return beneficios;
	}


	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios = beneficios;
		
	}

	public double retornarValorBeneficio(BeneficioTipo tipo) {
		for(Beneficio b: getBeneficios()) {
			if(b.getNome() == tipo) {
				return b.getValor();
			}
		}
		return 0.0;
	}

	public boolean isGestante() {
		return gestante;
	}


	public void setGestante(boolean gestante) {
		this.gestante = gestante;
	}


	public boolean isComDeficiencia() {
		return comDeficiencia;
	}


	public void setComDeficiencia(boolean comDeficiencia) {
		this.comDeficiencia = comDeficiencia;
	}


	public boolean isNoSCFV() {
		return noSCFV;
	}

public void setNoSCFV(boolean noSCFV) {
	this.noSCFV = noSCFV;
}
//	public void setNoSCFV(boolean noSCFV) {
//		if (!noSCFV) {
//			this.grupo = null;
//		}
//		this.noSCFV = noSCFV;
//	}
//

	


//	public Acolhida getAcolhida_pes() {
//		return acolhida_pes;
//	}
//
//
//	public void setAcolhida_pes(Acolhida acolhida) {
//		this.acolhida_pes = acolhida;
//	}

	

	public Familia getFamilia() {
		return familia;
	}


	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	

	@Transient
	public double getTotalBenef() {
		
		for(Beneficio b: getBeneficios()) {
			
			this.totalBenef += b.getValor();
		}
		
		return totalBenef;
	}


//	public GrupoSCFV getGrupo_pes() {
//		return grupo_pes;
//	}
//
//
//	public void setGrupo_pes(GrupoSCFV grupo_pes) {
//		this.grupo_pes = grupo_pes;
//	}


	



	public String getParentesco() {
		return parentesco;
	}


	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	


	@Override
	public String toString() {
		return "Pessoa [id_pessoa=" + id + ", nome_pes=" + nome + "]";
	}

	
}
