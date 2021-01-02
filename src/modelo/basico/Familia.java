package modelo.basico;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.enumerados.AtendimentoTipo;
import modelo.enumerados.Composicao;
import modelo.enumerados.EncaminhamentoTipo;
import modelo.enumerados.MoradiaTipo;
import modelo.enumerados.PessoaEstado;
import modelo.enumerados.SituacaoFamilia;

@Entity
@Table(name="familia")
public class Familia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_familia")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="id_pessoa_referencia")
	private Pessoa pesReferencia;
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@Temporal(TemporalType.DATE)
	private Date dataCad;
	
	@OneToMany(mappedBy= "familia", fetch=FetchType.LAZY)
	private List <Pessoa> pessoas_familia = new ArrayList<>();
	
	@Column(name="numero_pessoas")
	private Integer numero;
	
	@Embedded
	private Endereco endereco_familia;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name="telefones_familia")
	private List <String> telefones_familia = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private SituacaoFamilia situacao_familia;
		
	@Column(columnDefinition = "boolean default false", name="familia_acompanhamento")
	private boolean acompanhada = this.situacao_familia==SituacaoFamilia.ACOMP?true:false;
	
	@OneToOne
	private Acompanhamento acompanhamento;
	
	@Column(columnDefinition = "boolean default false")
	private boolean perfilCreas;
	
	@Enumerated(EnumType.STRING)
	private EncaminhamentoTipo encaminhada;

	private double totalRenda;

	private double totalBeneficio;
	
	private double percapita;
	
	private double rendaReferencia;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico_referencia")
	private Tecnico tecnico;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date atualizacaoCad;
	
	@Column(columnDefinition = "boolean default false")
	private boolean descumprimento;
	
	@Column(columnDefinition = "boolean default false")
	private boolean mulherChefe;
	
	@OneToMany(mappedBy="familia")
	private List<Beneficio> beneficios = new ArrayList<>();
	
	@OneToMany
	@JoinColumn(name="familia_visitas")
	private List<Visita> visitas;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean ativo;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dataDesligamento;
	
	@Column(name="motivo_desligamento")
	private String motivoDesligamento;
	
	@Enumerated(EnumType.STRING)
	private MoradiaTipo tipoMoradia;

	private double valorMoradia;
	
	public Familia() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPesReferencia() {
		return pesReferencia;
	}

	public void setPesReferencia(Pessoa referencia) {
		this.pesReferencia = referencia;
		if(!pessoas_familia.contains(referencia)) {			
			this.pessoas_familia.add(referencia);
		}
	}
	
	public double getRendaReferencia() {
		return pesReferencia.getRenda();
	}
	
	public void setRendaReferencia() {
		rendaReferencia = pesReferencia.getRenda();
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
		setTotalBeneficios();
		return totalBeneficio;
	}

	public void setTotalBeneficios() {
		double total = 0;
		for(Beneficio b: beneficios) {
			total += b.getValor();
		}
		this.totalBeneficio = total;
	}

	public List<Pessoa> getPessoas() {
		return pessoas_familia;
	}

	public void setPessoas(Pessoa pessoa) {
		this.pessoas_familia.add(pessoa);
		pessoa.setFamilia(this);
	}
	
	public void excluirPessoa(Pessoa pessoa) {
		for(Beneficio b: pessoa.getBeneficios()) {
			this.beneficios.remove(b);
			this.totalBeneficio -= pessoa.getTotalBeneficio();
		}
		this.pessoas_familia.remove(pessoa);
		pessoa.setFamilia(null);
		pessoa.setEstado(PessoaEstado.I);
		pessoa.setComposicao(Composicao.N);
		//TODO isso será possível?
		if(pessoa.getEstado()==PessoaEstado.RF) {
			this.pesReferencia = null;
		}
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

	public void setTelefone(List<String> telefones) {
		
		this.telefones_familia = telefones;
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

	public double getPercapita() {
		return percapita;
	}

	public void setPercapita() {
//		DecimalFormat formato = new DecimalFormat("###.##");      
//		double renda = Double.valueOf(formato.format(this.totalRenda/pessoas_familia.size()));
//		this.percapita = renda;
		this.percapita = totalRenda/pessoas_familia.size();
	}

	public double getTotalRenda() {
		return totalRenda;
	}
	
	public void setTotalRenda() {
		double total = 0;
		for(Pessoa pessoa: this.pessoas_familia) {
			total += pessoa.getRenda();
		}
		this.totalRenda = total;
	}
	
	public Integer getNumero() {
		setNumero();
		return this.numero;
	}
	
	public void setNumero() {		
		this.numero = pessoas_familia.size();
	}
	
	public List<Beneficio> getBeneficios() {
		setBeneficios();
		return beneficios;
	}
	
	public void setBeneficios() {
		List<Beneficio> lista = new ArrayList<>();
		for(Pessoa p: pessoas_familia) {
			for(Beneficio b: p.getBeneficios()) {
				lista.add(b);
			}
		}
		beneficios = lista;
	}

	public double getTotalBeneficio() {
		
		setTotalBeneficio();
		return totalBeneficio;
	}
	
	public void setTotalBeneficio() {
		double total = 0;
		for(Pessoa pes: pessoas_familia) {
			total += pes.getTotalBeneficio();
		}
		this.totalBeneficio = total;
		
	}
	//TODO rever como serão incluídos os encaminhamentos 
	public List<Encaminhamento> getEncaminhamentos(){
		List<Encaminhamento> encaminhamentos = new ArrayList<>();
		for(Pessoa p: this.pessoas_familia) {
			for(Encaminhamento e: p.getEncaminhamentos()) {
				encaminhamentos.add(e);
			}
		}
		return encaminhamentos;
	}
	
	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(Visita visita) {
		this.visitas.add(visita);
	}

	public String getAtivo() {
		if(ativo) {
			return "ATIVO";
		}else {
			return "INATIVO";
		}
	}
	
	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public String getMotivoDesligamento() {
		return motivoDesligamento;
	}

	public void setMotivoDesligamento(String motivoDesligamento) {
		this.motivoDesligamento = motivoDesligamento;
	}
	
	public MoradiaTipo getTipoMoradia() {
		return tipoMoradia;
	}

	public void setTipoMoradia(MoradiaTipo tipoMoradia) {
		this.tipoMoradia = tipoMoradia;
	}
	//TODO testar esse método
	public double getValorMoradia() {
		DecimalFormat d = new DecimalFormat();
		d.format(valorMoradia);
		return valorMoradia;
	}

	public void setValorMoradia(double valorMoradia) {
		this.valorMoradia = valorMoradia;
	}
	
	public Date ultimoAtendimentoCad() {
		List<Atendimento> cad = new ArrayList<>();
		for(Pessoa p: pessoas_familia) {
			if(p.getAtendimentos().size()==0) {
				return null;
			}
			for(Atendimento a: p.getAtendimentos()) {
				if(a.getTipo() == AtendimentoTipo.C) {
					cad.add(a);
				}
			}
		}
		if(cad.isEmpty()) {
			return null;
		}else {
			
			return cad.get(cad.size()-1).getData();
		}
	}
	public Date ultimoAtendimentoTecnico() {
		String sql = "Select max(data_atendimento) from atendimento a";
		
		List<Atendimento> tec = new ArrayList<>();
		
		for(Pessoa p: pessoas_familia) {
			if(p.getAtendimentos().isEmpty()) {
				return null;
			}
			for(Atendimento a: p.getAtendimentos()) {
				if(a.getTipo() == AtendimentoTipo.T) {
					tec.add(a);
				}
			}
		}
		if(tec.isEmpty()) {
			return null;
		}else {
			
			return tec.get(tec.size()-1).getData();
		}
	}

	
	public void apagarFamilia() {

		this.setPesReferencia(null);
		this.setEndereco(null);
		this.setAcompanhamento(null);
		if(!beneficios.isEmpty()) {
			for(Beneficio b: beneficios) {
				beneficios.remove(b);
			}
		}
		if(!visitas.isEmpty()) {
			for(Visita v: visitas) {
				visitas.remove(v);
			}
		}
		
		this.setTecnico(null);
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
