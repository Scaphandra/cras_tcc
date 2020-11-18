package modelo.basico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import modelo.enumerados.Composicao;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length=2, discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("P")
@Table(name="pessoas")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pessoa")
	private Long id;
	
	@Column(name="nome_pessoa")
	private String nome;
	
	@Column(name="cpf_pessoa")
	private String cpf;

	private String rg;

	private String nis;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private int idade;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	private String nomeMae;

	@Enumerated(EnumType.STRING)
	private CorRaca cor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="escolaridade_pessoa")
	private Escolaridade escolaridade;
	
	@Column(precision=2, scale=2)
	private double renda;
	
	private String ocupacao;
	
	@Enumerated(EnumType.STRING)
	private Composicao composicao;
	
	@Column(columnDefinition = "boolean default false")
	private boolean prioritarioSCFV = false;
	
	@OneToMany(mappedBy="pessoa", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List <Beneficio> beneficios = new ArrayList<>();
	
	private double totalBeneficio;

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
	
	@OneToMany(mappedBy="pessoa")
	private List <Encaminhamento> encaminhamentos = new ArrayList<>();
	
	@OneToMany(mappedBy="pessoa")
	private List <Atendimento> atendimentos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="pessoas_acolhida")
	private Acolhida acolhida;
	
	@ManyToOne
	@JoinColumn(name="grupo_pessoas")
	private GrupoSCFV grupo;
	
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


	public void setCpf(String cpf_pes) {
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
		setIdade();
	}
	
	public int getIdade() {
		return idade;
	}

	public void setIdade() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.dataNascimento);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		//o mês entra como um vetor de 12 posições incluindo o 0, por isso precisamos somar com 1
		int mes = cal.get(Calendar.MONTH)+1;
		int ano = cal.get(Calendar.YEAR);
		
		int diaHoje = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int mesHoje = Calendar.getInstance().get(Calendar.MONTH)+1;
		int anoHoje = Calendar.getInstance().get(Calendar.YEAR);
		
		//cálculo da idade
		
		this.idade = anoHoje - ano;
		
		if(mesHoje == mes) {	
			if(diaHoje < dia) {				
				this.idade -= 1;
			}
		}else if(mesHoje < mes) {
			
			this.idade -= 1;
		}
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


	public Composicao getComposicao() {
		return composicao;
	}

	public void setComposicao(Composicao composicao) {
		this.composicao = composicao;
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

	@Transient
	public double getValorBeneficio(BeneficioTipo tipo) {
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

	public Acolhida getAcolhida() {
		return acolhida;
	}


	public void setAcolhida(Acolhida acolhida) {
		this.acolhida = acolhida;
	}

	

	public Familia getFamilia() {
//		Familia f = new Familia();
//		f.setId(0L);
//		if(this.familia == null) {
//			this.familia = f;
//		}
		return familia;
	}


	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	

	@Transient
	public double getTotalBeneficio() {
		totalBeneficio = 0;
		
		if(beneficios != null) {

			for(Beneficio b: getBeneficios()) {
				
				this.totalBeneficio += b.getValor();
			}
		}
		return totalBeneficio;
	}


	public GrupoSCFV getGrupo() {
		return grupo;
	}


	public void setGrupo(GrupoSCFV grupo_pes) {
		this.grupo = grupo_pes;
		this.setNoSCFV(true);
	}


	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}
	//colocado um encaminhamento por vez
	public void setEncaminhamentos(Encaminhamento encaminhamentos) {
		this.encaminhamentos.add(encaminhamentos);
	}
	

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
	}

	

	@Override
	public String toString() {
		return this.nome + " código: "+this.id.toString();
	}

	
}
