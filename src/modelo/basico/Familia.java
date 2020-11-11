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
	
	//private double valorBeneficios;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_pessoa")
	private PesReferencia pesReferencia;
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	//
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
	
	private double totalBeneficio;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	private Tecnico tecnico;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date atualizacaoCad;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean descumprimento;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean mulherChefe;
	
	
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
		
		//valorBeneficios += referencia.getTotal_beneficios();
		
//		for(Beneficio b: referencia.getBeneficios_pes()) {
//			beneficios_familia.add(b);
//		}
	}
	
	
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
		//valorBeneficios += pessoa.getTotal_beneficios();
//		for(Beneficio b: pessoa.getBeneficios_pes()) {
//			beneficios_familia.add(b);
//		}
	
	
//	public double getValorBeneficios() {
//		return valorBeneficios;
//	}
//
//	public void setValorBeneficios(double valorBeneficios) {
//		this.valorBeneficios = valorBeneficios;
//	}

	public List<Pessoa> getPessoas_familia() {
		return pessoas_familia;
	}

	public void setPessoas_familia(List<Pessoa> pessoas_familia) {
		this.pessoas_familia = pessoas_familia;
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

	
	public SituacaoFamilia getSituacao_familia() {
		return situacao_familia;
	}
	
	@Enumerated(EnumType.STRING)
	public void setSituacao_familia(SituacaoFamilia situacao) {
		this.situacao_familia = situacao;
	}

	public EncaminhamentoTipo getEncaminhada() {
		return encaminhada;
	}

	public void setEncaminhada(EncaminhamentoTipo encaminhada) {
		this.encaminhada = encaminhada;
	}

	//
//	public boolean isAcompanhada() {
//		return acompanhada;
//	}
//
//	public void setAcompanhada(boolean acompanhada) {
//		this.acompanhada = acompanhada;
//		if(acompanhada==true) {
//			setSituacao_familia(SituacaoFamilia.ACOMP);
//		}
//	}

	public boolean isPerfilCreas() {
		return perfilCreas;
	}


	public void setPerfilCreas(boolean perfilCreas) {
		this.perfilCreas = perfilCreas;
	}

//	public RedeReferenciada getEncaminhada_familia() {
//		return encaminhada_familia;
//	}
//
//	public void setEncaminhada_familia(RedeReferenciada encaminhada) {
//		this.encaminhada_familia = encaminhada;
//	}
//
//	public Tecnico getTecnicoRef_familia() {
//		return tecnicoRef_familia;
//	}
//
//	public void setTecnicoRef_familia(Tecnico tecnicoRef) {
//		this.tecnicoRef_familia = tecnicoRef;
//	}

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
	
	@Transient
	public int getNumeroPessoas() {
		return this.numero_pessoas;
	}
	


	
//	public void setNumero() {
//		this.numero_pessoas = this.pessoas_familia.size();
//	}

//	@Transient
//	public double getTotalBeneficio() {
//
//		for(Pessoa pes: pessoas_familia) {
//			this.totalBeneficio += pes.getTotalBenef();
//		}
//		
//		return totalBeneficio;
//	}

	@Override
	public String toString() {
		return "Familia [id=" + id + ", referencia=" + pesReferencia.toString() + "]";
	}
	
	
	
}
