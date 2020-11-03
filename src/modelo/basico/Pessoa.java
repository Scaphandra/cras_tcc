package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_pessoa;
	
	private double total_beneficios;
	
	@ManyToOne
	private GrupoSCFV grupo_pes;
	
	@NotNull
	private String nome_pes;
	
	@Column(name="cpf_pes")
	private String cpf_pes;
	
	@Column(name="rg")
	private String rg;
	
	@Column(name="nis")
	private String nis;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name="sexo")
	private Sexo sexo;
	
	@Column(name="genero")
	private Genero genero;
	
	@Column(name="nomeMae")
	private String nomeMae;
	
	@Column(name="cor")
	private CorRaca cor;
	
	private Escolaridade escolaridade_pes;
	
	private double renda;
	
	private String ocupacao;
	
	private String parentesco;
	
	@Column(columnDefinition = "boolean default false")
	private boolean prioritarioSCFV = false;
	
	@OneToMany(mappedBy="pessoa_beneficio", cascade=CascadeType.ALL)
	private List <Beneficio> beneficios_pes = new ArrayList<>();
	
	@Column(columnDefinition = "boolean default false")
	private boolean gestante = false;
	
	@Column(columnDefinition = "boolean default false")
	private boolean homonimo = false;

	@Column(columnDefinition = "boolean default false")
	private boolean comDeficiencia = false;
	
	@Column(columnDefinition = "boolean default false")
	private boolean noSCFV = false;
	
	@ManyToOne
	private Acolhida acolhida_pes;
	
	@ManyToOne
	@JoinColumn(name="id_pessoaFamilia")
	private Familia familia;
	
	public Pessoa() {
		
	}


	public Pessoa(String nome, boolean homonimo, String cpf_pes, String rg, String nis, Date dataNascimento, Sexo sexo, Genero genero,
			String nomeMae, CorRaca cor, Escolaridade escolaridade_pes, double renda, String ocupacao,
			boolean prioritarioSCFV, List<Beneficio> beneficios, boolean gestante, boolean comDeficiencia,
			boolean noSCFV,Familia familia, String parentesco) {
		super();
		this.nome_pes = nome;
		this.homonimo = homonimo;
		this.cpf_pes = cpf_pes;
		this.rg = rg;
		this.nis = nis;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.genero = genero;
		this.nomeMae = nomeMae;
		this.cor = cor;
		this.escolaridade_pes = escolaridade_pes;
		this.renda = renda;
		this.ocupacao = ocupacao;
		this.prioritarioSCFV = prioritarioSCFV;
		this.beneficios_pes = beneficios;
		this.gestante = gestante;
		this.comDeficiencia = comDeficiencia;
		this.noSCFV = noSCFV;
		this.familia = familia;
		this.parentesco = parentesco;
	}


	public Pessoa(String nome, Date dataNascimento, Sexo sexo, Genero genero, String nomeMae, CorRaca cor) {
		super();
		this.nome_pes = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.genero = genero;
		this.nomeMae = nomeMae;
		this.cor = cor;
	}


	public Long getId_pessoa() {
		return id_pessoa;
	}


	public void setId_pessoa(Long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}


	public String getNome_pes() {
		return nome_pes;
	}


	public void setNome_pes(String nome) {
		this.nome_pes = nome;
	}

	
	
	public boolean isHomonimo() {
		return homonimo;
	}


	public void setHomonimo(boolean homonimo) {
		this.homonimo = homonimo;
	}


	public String getCpf_pes() {
		return cpf_pes;
	}


	public void setCpf_pes(String cpf_pes) {
		this.cpf_pes = cpf_pes;
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


	public Escolaridade getEscolaridade_pes() {
		return escolaridade_pes;
	}


	public void setEscolaridade_pes(Escolaridade escolaridade_pes) {
		this.escolaridade_pes = escolaridade_pes;
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
		return beneficios_pes;
	}


	public void setBeneficios(List<Beneficio> beneficios) {
		this.beneficios_pes = beneficios;
		for(Beneficio b: beneficios) {
			
			this.total_beneficios += b.getValor_beneficio();
		}
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
		if (!noSCFV) {
			this.grupo_pes = null;
		}
		this.noSCFV = noSCFV;
	}


	public double getTotal_beneficios_pes() {
		return total_beneficios;
	}
	
	


	public void setBeneficios_pes(Beneficio beneficios_pes) {
		this.beneficios_pes.add(beneficios_pes);
	}


	public Acolhida getAcolhida_pes() {
		return acolhida_pes;
	}


	public void setAcolhida_pes(Acolhida acolhida) {
		this.acolhida_pes = acolhida;
	}

	

	public Familia getFamilia() {
		return familia;
	}


	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	

	public double getTotal_beneficios() {
		return total_beneficios;
	}


	public void setTotal_beneficios(double total_beneficios) {
		this.total_beneficios = total_beneficios;
	}


	public GrupoSCFV getGrupo_pes() {
		return grupo_pes;
	}


	public void setGrupo_pes(GrupoSCFV grupo_pes) {
		this.grupo_pes = grupo_pes;
	}


	public List<Beneficio> getBeneficios_pes() {
		
		return beneficios_pes;
	}
	
	public double retornarValorBeneficio(BeneficioTipo tipo) {
		for(Beneficio b: beneficios_pes) {
			if(b.getNome_beneficio() == tipo) {
				return b.getValor_beneficio();
			}
		}
		return 0.0;
	}


	public void setBeneficios_pes(List<Beneficio> beneficios_pes) {
		
		this.beneficios_pes = beneficios_pes;
	}


	public String getParentesco() {
		return parentesco;
	}


	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	


	@Override
	public String toString() {
		return "Pessoa [id_pessoa=" + id_pessoa + ", nome_pes=" + nome_pes + "]";
	}

	
}
