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

import modelo.enumerados.SituacaoFamilia;

@Entity
public class Familia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_familia;
	
	@OneToOne(cascade= {CascadeType.PERSIST})
	@JoinColumn(name="id_pessoa")
	private Pessoa referencia_familia;
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@OneToMany(mappedBy= "familia")
	private List <Pessoa> pessoas_familia = new ArrayList<>();
	
	@Embedded
	private Endereco endereco_familia;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="telefones_familia")
	private List <String> telefones_familia = new ArrayList<>();
	
	private String tempoMunicipio;
	
	private SituacaoFamilia situacao_familia;
	
	@OneToMany(mappedBy="familia_atendimento")
	private List<Atendimento> atendimentos_familia = new ArrayList<>();
	
	@OneToMany(mappedBy="familia_encaminhamento")
	private List<Encaminhamento> encaminhamentos_familia = new ArrayList<>();
	
	@Column(columnDefinition = "boolean default false")
	private Boolean acompanhada = this.situacao_familia==SituacaoFamilia.ACOMP?true:false;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean perfilCreas;
	
	@OneToOne (cascade= {CascadeType.PERSIST})
	@JoinColumn(name="id_rede")
	private RedeReferenciada encaminhada_familia;
	
	private double totalRenda;
	
	@ManyToOne
	private Tecnico tecnicoRef_familia;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date atualizacaoCad;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean descumprimento;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean mulherChefe;
	
	@OneToMany(mappedBy="familia_beneficio")
	private List<Beneficio> beneficios_familia = new ArrayList<>();
	
	public Familia() {
		
	}

	public Familia(Pessoa referencia, Date dataEntrada, Pessoa pessoa, Endereco endereco, String telefone,
			String tempoMunicipio, SituacaoFamilia situacao, Atendimento atendimento, Encaminhamento encaminhamento, boolean acompanhada,
			boolean perfilCreas, RedeReferenciada encaminhada, Tecnico tecnicoRef, Date atualizacaoCad,
			boolean descumprimento, boolean mulherChefe, Beneficio beneficio) {
		super();
		this.referencia_familia = referencia;
		this.dataEntrada = dataEntrada;
		this.pessoas_familia.add(pessoa);
		this.endereco_familia = endereco;
		this.telefones_familia.add(telefone);
		this.tempoMunicipio = tempoMunicipio;
		this.situacao_familia = situacao;
		this.atendimentos_familia.add(atendimento);
		this.encaminhamentos_familia.add(encaminhamento);
		this.acompanhada = acompanhada;
		this.perfilCreas = perfilCreas;
		this.encaminhada_familia = encaminhada;
		this.tecnicoRef_familia = tecnicoRef;
		this.atualizacaoCad = atualizacaoCad;
		this.descumprimento = descumprimento;
		this.mulherChefe = mulherChefe;
		this.beneficios_familia.add(beneficio);
	}

	
	
	public Familia(Pessoa referencia, Date dataEntrada, SituacaoFamilia situacao) {
		super();
		this.referencia_familia = referencia;
		this.dataEntrada = dataEntrada;
		this.situacao_familia = situacao;
	}

	public Long getId_familia() {
		return id_familia;
	}

	public void setId_familia(Long id) {
		this.id_familia = id;
	}

	public Pessoa getReferencia_familia() {
		return referencia_familia;
	}

	public void setReferencia_familia(Pessoa referencia) {
		this.referencia_familia = referencia;
	}
	
	
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public List<Pessoa> getPessoas_familia() {
		return pessoas_familia;
	}

	public void setPessoas_familia(Pessoa pessoa) {
		this.pessoas_familia.add(pessoa);
	}

	public Endereco getEndereco_familia() {
		return endereco_familia;
	}

	public void setEndereco_familia(Endereco endereco) {
		this.endereco_familia = endereco;
	}
	
	public List<String> getTelefones_familia() {
		return telefones_familia;
	}

	public void setTelefones_familia(String telefone) {
		this.telefones_familia.add(telefone);
	}

	public String getTempoMunicipio() {
		return tempoMunicipio;
	}

	public void setTempoMunicipio(String tempoMunicipio) {
		this.tempoMunicipio = tempoMunicipio;
	}
	public SituacaoFamilia getSituacao_familia() {
		return situacao_familia;
	}
	
	@Enumerated(EnumType.STRING)
	public void setSituacao_familia(SituacaoFamilia situacao) {
		this.situacao_familia = situacao;
	}

	public List<Encaminhamento> getEncaminhamentos_familia() {
		return encaminhamentos_familia;
	}

	public void setEncaminhamentos_familia(Encaminhamento encaminhamento) {
		this.encaminhamentos_familia.add(encaminhamento);
	}

	public List<Atendimento> getAtendimentos_familia() {
		return atendimentos_familia;
	}

	public void setAtendimentos_familia(Atendimento atendimento) {
		this.atendimentos_familia.add(atendimento);
	}

	public boolean isAcompanhada() {
		return acompanhada;
	}

	public void setAcompanhada(boolean acompanhada) {
		this.acompanhada = acompanhada;
	}

	public boolean isPerfilCreas() {
		return perfilCreas;
	}

	public void setPerfilCreas(boolean perfilCreas) {
		this.perfilCreas = perfilCreas;
	}

	public RedeReferenciada getEncaminhada_familia() {
		return encaminhada_familia;
	}

	public void setEncaminhada_familia(RedeReferenciada encaminhada) {
		this.encaminhada_familia = encaminhada;
	}

	public Tecnico getTecnicoRef_familia() {
		return tecnicoRef_familia;
	}

	public void setTecnicoRef_familia(Tecnico tecnicoRef) {
		this.tecnicoRef_familia = tecnicoRef;
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

	public List<Beneficio> getBeneficios_familia() {
		return beneficios_familia;
	}

	public void setBeneficios_familia(Beneficio beneficio) {
		this.beneficios_familia.add(beneficio);
	}


	public void setTotalRenda() {
	
		for(Pessoa pessoa: this.pessoas_familia)
			this.totalRenda += pessoa.getRenda();
	}

	public double getTotalRenda() {
		return totalRenda;
	}

	@Override
	public String toString() {
		return "Familia [id=" + id_familia + ", referencia=" + referencia_familia.toString() + "]";
	}
	
	
	
}
