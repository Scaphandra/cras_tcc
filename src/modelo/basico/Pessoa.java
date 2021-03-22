package modelo.basico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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

import modelo.dao.BeneficioDAO;
import modelo.dao.PessoaDAO;
import modelo.enumerados.BeneficioTipo;
import modelo.enumerados.Composicao;
import modelo.enumerados.CorRaca;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Genero;
import modelo.enumerados.PessoaEstado;
import modelo.enumerados.Sexo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length=2, discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("P")
@Table(name="pessoas")
public class Pessoa {
	//TODO criar um switch com enum para implementar uma situação estado(state) em que a pessoa é RF, P ou I (inativa)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pessoa")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
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
	
	@Column(name="vinvulo", columnDefinition = "boolean default false")
	private boolean vinculoFormal;
	
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
	
	@Column(name="nomes_beneficios")
	private String nomesBeneficios = "";
	
	@Enumerated(EnumType.STRING)
	private PessoaEstado estado;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean ativo;
	
	@Transient
	boolean pbf = false;
	@Transient
	boolean bpci = false;
	@Transient
	boolean bpcd = false;
	@Transient
	boolean nv = false;
	@Transient
	boolean outro = false;
	
	
//	@OneToMany
//	@JoinTable(name="beneficios_novo",
//	joinColumns = {@JoinColumn (name="nome")}, inverseJoinColumns = {@JoinColumn (name="valor_b")})
//	@MapKey(name="benef")
//	private Map <String, Double> bes = new HashMap<String, Double> ();
	
	public Pessoa() {
		
	}


	public void excluirBanco() {
		
		PessoaDAO dao = new PessoaDAO();
		dao.abrirTransacao();
		Pessoa pes = dao.obterPorID(this.getId());
		dao.removerPorID(pes.getId());
		dao.fecharTransacao().fechar();
		
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
	
	

	public Unidade getUnidade() {
		return unidade;
	}


	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
		this.unidade.setPessoa(this);
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
	
	//OPÇÃO PESSOA NOVA
	public void setBeneficios(List<Beneficio> beneficios) {
		
		this.beneficios = beneficios;
		
		if(this.beneficios!=null) {
			
			for (Beneficio b : this.beneficios) {
				
				if (b.getNome().equals(BeneficioTipo.PBF)) {
					pbf = true;
				}
				else if(b.getNome().equals(BeneficioTipo.BPCI)){
					bpci = true;
				}
				else if(b.getNome().equals(BeneficioTipo.BPCDEF)){
					bpcd = true;
				}
				else if(b.getNome().equals(BeneficioTipo.NV)){
					nv = true;
				}
				else if(b.getNome().equals(BeneficioTipo.O)){
					outro = true;
				}
			}
		}
		
		setNomesBeneficios(pbf, bpci, bpcd, nv, outro);
	}
	//SOBRESCRIÇÃO DO MÉTODO SETBENEFICIOS PARA PODER GERAR LISTA COM VALOR ATUALIZADO E BENEFÍCIO EXCLUÍDO SAIR DO BANCO
	//OPÇÃO EDITAR PESSOA JÁ CRIADA
	
	private void checarBeneficios() {
		
		for (Beneficio b : this.beneficios) {
			if (b.getNome().equals(BeneficioTipo.PBF)) {
				pbf = true;
			}
			else if(b.getNome().equals(BeneficioTipo.BPCI)) {
				bpci = true;
			}
			else if(b.getNome().equals(BeneficioTipo.BPCDEF)) {
				bpcd = true;
			}
			else if(b.getNome().equals(BeneficioTipo.NV)) {
				nv = true;
			}
			else if(b.getNome().equals(BeneficioTipo.O)) {
				outro = true;
			}
		}
	}
	
	public void setBeneficios(Map <String, Double> nova, boolean p, boolean b1, boolean b2, boolean n, boolean o, EntityManager em) {
		
		//TODO -> está salvando o beneficio duas vezes e não está excluindo.
		
		List<Beneficio> bs = new ArrayList<>();
		
		Beneficio novo = new Beneficio();

		if (this.beneficios != null) {
			checarBeneficios();
			for (Beneficio b : this.beneficios) {
				if (pbf && p) {
					System.err.print("PBF --------->Alterar");
					b = em.find(Beneficio.class, b.getId_beneficio());
					b.setValor(nova.get("PBF") == null ? 0 : nova.get("PBF"));
					bs.add(b);
					em.merge(b);
				}
				if (pbf && !p) {
					pbf = false;
					System.err.print("PBF --------->Excluir\n");
					novo = em.find(Beneficio.class, b.getId_beneficio());
					em.remove(novo);
				}

				if (bpci && b1) {
					b = em.find(Beneficio.class,b.getId_beneficio());
					b.setValor(nova.get("BPCI") == null ? 0 : nova.get("BPCI"));
					bs.add(b);
					em.merge(b);
				}
				if (bpci && !b1) {
					bpci = false;
					novo = em.find(Beneficio.class,b.getId_beneficio());
					em.remove(novo);
				}

				if (bpcd && b2) {
					b = em.find(Beneficio.class,b.getId_beneficio());
					b.setValor(nova.get("BPCDEF") == null ? 0 : nova.get("BPCDEF"));
					bs.add(b);
					em.merge(b);
				}
				if (bpcd && !b2) {
					bpcd = false;
					novo = em.find(Beneficio.class,b.getId_beneficio());
					em.remove(novo);
				}

				if (nv && n) {
					b = em.find(Beneficio.class,b.getId_beneficio());
					b.setValor(nova.get("NV") == null ? 0 : nova.get("NV"));
					bs.add(b);
					em.merge(b);
				}
				if (nv && !n) {
					nv = false;
					novo = em.find(Beneficio.class,b.getId_beneficio());
					em.remove(novo);
				}

				if (outro && o) {
					b = em.find(Beneficio.class,b.getId_beneficio());
					b.setValor(nova.get("O") == null ? 0 : nova.get("O"));
					bs.add(b);
					em.merge(b);
				}
				if (outro && !o) {
					outro = false;
					novo = em.find(Beneficio.class,b.getId_beneficio());
					em.remove(novo);
				}
			}
		}
		if (!pbf && p) {
			System.err.print("PBF --------->Novo");
			pbf = true;
			novo.setValor(nova.get("PBF"));
			novo.setNome(BeneficioTipo.PBF);
			novo.setPessoa(this);
			em.persist(novo);
			bs.add(novo);

		}
		if (!bpci && b1) {
			bpci = true;
			novo.setValor(nova.get("BPCI"));
			novo.setNome(BeneficioTipo.BPCI);
			novo.setPessoa(this);
			em.persist(novo);
			bs.add(novo);

		}
		if (!bpcd && b2) {
			bpcd = true;
			novo.setValor(nova.get("BPCDEF"));
			novo.setNome(BeneficioTipo.BPCDEF);
			novo.setPessoa(this);
			em.persist(novo);
			bs.add(novo);

		}
		if (!nv && n) {
			nv = true;
			novo.setValor(nova.get("NV"));
			novo.setNome(BeneficioTipo.NV);
			novo.setPessoa(this);
			em.persist(novo);
			bs.add(novo);
		}
		if (!outro && o) {
			outro = true;
			novo.setValor(nova.get("O"));
			novo.setNome(BeneficioTipo.O);
			novo.setPessoa(this);
			em.persist(novo);
			bs.add(novo);
		}

		this.beneficios = bs;
		
		setNomesBeneficios(pbf, bpci, bpcd, nv, outro);

	}

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
		return familia;
	}


	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	public void sairFamilia() {
		if(familia != null) {
			setFamilia(null);
			setAtivo(false);
			setComposicao(Composicao.N);

		}
		
	}
	
	public void apagarPessoa() {
		setFamilia(null);
		setBeneficios(null);
		setEncaminhamentos(null);
		setAtendimentos(null);
		setAcolhida(null);
		
	}

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

	public PessoaEstado getEstado() {
		return estado;
	}

	public void setEstado(PessoaEstado estado) {
		this.estado = estado;
	}

	public String getNomesBeneficios() {
		
		return this.nomesBeneficios;
	}

	public void setNomesBeneficios(boolean p, boolean b1, boolean b2, boolean n, boolean o) {
		nomesBeneficios = "";
		
		if(p) {
			this.nomesBeneficios += " "+ BeneficioTipo.PBF + " ";
		}
		else if(b1) {
			this.nomesBeneficios += " "+ BeneficioTipo.BPCI + " ";
		}
		else if(b2) {
			this.nomesBeneficios += " "+ BeneficioTipo.BPCDEF + " ";
		}
		else if(n) {
			this.nomesBeneficios += " "+ BeneficioTipo.NV + " ";
		}
		else if(o) {
			this.nomesBeneficios += " "+ BeneficioTipo.O + " ";
		}
	}
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getAtivo() {
		if(this.ativo) {
			return "ATIVO";
		}else {
			return "INATIVO";
		}
		
	}

	public boolean isVinculoFormal() {
		return vinculoFormal;
	}


	public void setVinculoFormal(boolean vinculoFormal) {
		this.vinculoFormal = vinculoFormal;
	}


	@Override
	public String toString() {
		return nome;
	}
	
}
