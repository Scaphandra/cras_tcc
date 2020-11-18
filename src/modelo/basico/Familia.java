package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import modelo.enumerados.EncaminhamentoTipo;
import modelo.enumerados.SituacaoFamilia;

@Entity
public class Familia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_familia")
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_pessoa_referencia")
	private PesReferencia pesReferencia;
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@Temporal(TemporalType.DATE)
	private Date dataCad;
	
	@OneToMany(mappedBy= "familia", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List <Pessoa> pessoas_familia = new ArrayList<>();
	
	private int numero_pessoas;
	
	@Embedded
	private Endereco endereco_familia;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="telefones_familia")
	private List <String> telefones_familia = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private SituacaoFamilia situacao_familia;
		
	@Column(columnDefinition = "boolean default false", name="familia_acompanhamento")
	private Boolean acompanhada = this.situacao_familia==SituacaoFamilia.ACOMP?true:false;
	
	@OneToOne
	private Acompanhamento acompanhamento;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean perfilCreas;
	
	@Enumerated(EnumType.STRING)
	private EncaminhamentoTipo encaminhada;

	private double totalRenda;
	
	@Transient
	private double totalBeneficio;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico_referencia")
	private Tecnico tecnico;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date atualizacaoCad;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean descumprimento;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean mulherChefe;
	
	@Transient
	private List<Beneficio> beneficios = new ArrayList<>();
	
	@OneToOne
	private Visita visita;
	
	
	public Familia() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PesReferencia getPesReferencia() {
		return pesReferencia;
	}

	public void setPesReferencia(PesReferencia referencia) {
		this.pesReferencia = referencia;
		this.pessoas_familia.add(referencia);
		referencia.setFamilia(this);
		
		totalBeneficio += referencia.getTotalBeneficio();
		
		for(Beneficio b: referencia.getBeneficios()) {
			beneficios.add(b);
		}
	}
	
	
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	
	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public double getTotalBeneficios() {
		return totalBeneficio;
	}

	public void setTotalBeneficios(double valorBeneficios) {
		this.totalBeneficio = valorBeneficios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas_familia;
	}

	public void setPessoas(List<Pessoa> pessoas_familia) {
		this.pessoas_familia = pessoas_familia;
		for(Pessoa p: this.pessoas_familia) {
			p.setFamilia(this);
			for(Beneficio b: p.getBeneficios()) {
				this.beneficios.add(b);
			}
		}
	}
	
	public void excluirPessoa(Pessoa pessoa) {
		if(pessoa instanceof PesReferencia) {
			this.pesReferencia = null;
		}
		for(Beneficio b: pessoa.getBeneficios()) {
			this.beneficios.remove(b);
			this.totalBeneficio -= pessoa.getTotalBeneficio();
		}
		this.pessoas_familia.remove(pessoa);
		pessoa.setFamilia(null);
	}

	public Endereco getEndereco() {
		return endereco_familia;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco_familia = endereco;
	}
	
	public List<String> getTelefone() {
		return telefones_familia;
	}

	public void setTelefone(String telefone) {
		this.telefones_familia.add(telefone);
	}

	
	public SituacaoFamilia getSituacao() {
		return situacao_familia;
	}
	
	@Enumerated(EnumType.STRING)
	public void setSituacao(SituacaoFamilia situacao) {
		this.situacao_familia = situacao;
	}

	public EncaminhamentoTipo getEncaminhada() {
		return encaminhada;
	}

	public void setEncaminhada(EncaminhamentoTipo encaminhada) {
		this.encaminhada = encaminhada;
	}

	
	public boolean isAcompanhada() {
		return acompanhada;
	}

	public void setAcompanhada(boolean acompanhada) {
		this.acompanhada = acompanhada;
		if(acompanhada==true) {
			setSituacao(SituacaoFamilia.ACOMP);
		}
	}

	public Acompanhamento getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(Acompanhamento acompanhamento) {
		setTecnico(acompanhamento.getTecnico());
		this.acompanhamento = acompanhamento;
	}

	public boolean isPerfilCreas() {
		return perfilCreas;
	}


	public void setPerfilCreas(boolean perfilCreas) {
		this.perfilCreas = perfilCreas;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnicoRef) {
		this.tecnico = tecnicoRef;
	}

	public Date getAtualizacaoCad() {
		return atualizacaoCad;
	}

	public void setAtualizacaoCad(Date atualizacaoCad) {
		this.atualizacaoCad = atualizacaoCad;
	}

	public boolean isDescumprimento() {
		return descumprimento;
	}

	public void setDescumprimento(boolean descumprimento) {
		this.descumprimento = descumprimento;
	}

	public boolean isMulherChefe() {
		return mulherChefe;
	}

	public void setMulherChefe(boolean mulherChefe) {
		this.mulherChefe = mulherChefe;
	}


	public void setTotalRenda() {
	
		for(Pessoa pessoa: this.pessoas_familia)
			this.totalRenda += pessoa.getRenda();
	}
	
	public double perCapta() {
		return this.totalRenda/pessoas_familia.size();
	}

	@Transient
	public double getTotalRenda() {
		return totalRenda;
	}
	
	public int getNumeroPessoas() {
		return this.numero_pessoas;
	}
	
	public void setNumero() {
		this.numero_pessoas = this.pessoas_familia.size();
	}
	
	public List<Beneficio> getBeneficios() {
		return beneficios;
	}

	@Transient
	public double getTotalBeneficio() {

		for(Pessoa pes: pessoas_familia) {
			this.totalBeneficio += pes.getTotalBeneficio();
		}
		
		return totalBeneficio;
	}
	@Transient
	public List<Encaminhamento> getEncaminhamentos(){
		List<Encaminhamento> encaminhamentos = new ArrayList<>();
		for(Pessoa p: this.pessoas_familia) {
			for(Encaminhamento e: p.getEncaminhamentos()) {
				encaminhamentos.add(e);
			}
		}
		return encaminhamentos;
	}
	

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	@Override
	public String toString() {
		
		if(this.pesReferencia == null) {
			PesReferencia p = new PesReferencia();
			p.setNome("Não possui");
			this.pesReferencia = p;
		}
		return pesReferencia.getNome();
	}
	
	
	
}
