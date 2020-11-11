package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import modelo.enumerados.Escolaridade;
import modelo.enumerados.NivelAcesso;
import modelo.enumerados.TecnicoReferencia;

@Entity
@DiscriminatorValue("TC")
public class Tecnico  extends Funcionario{
	
	@Column(name="referencia_tecnico")
	private TecnicoReferencia referencia;
	
	@OneToMany(mappedBy="tecnico")
	private List <Familia> acompanhadas_tecnico = new ArrayList<>();
	
	@OneToMany(mappedBy="tecnico")
	private List <Familia> encaminhamentos = new ArrayList<>();
	
//	@OneToMany(mappedBy="funcionario_atendimento")
//	private List <Atendimento> atendimentos_tecnico = new ArrayList<>();
//	
//	@ManyToMany(mappedBy="tecnicos_visita")
//	private List <Visita> visitas_tecnico = new ArrayList<>();
	
	public Tecnico() {
		
		super.nivel = NivelAcesso.TECNICO;
		super.escolaridade = Escolaridade.SUPERIOR;
	}


	public TecnicoReferencia getReferencia() {
		return referencia;
	}

	public void setReferencia(TecnicoReferencia referencia) {
		this.referencia = referencia;
	}

	
//	public List<Familia> getAcompanhadas_tecnico() {
//		return acompanhadas_tecnico;
//	}
//
//	public void setAcompanhadas_tecnico(Familia acompanhada) {
//		this.acompanhadas_tecnico.add(acompanhada);
//	}

//	public List<Atendimento> getAtendimentos_tecnico() {
//		return atendimentos_tecnico;
//	}
//
//	public void setAtendimentos_tecnico(Atendimento atendimento) {
//		this.atendimentos_tecnico.add(atendimento);
//	}
//
//	public List<Visita> getVisitas_tecnico() {
//		return visitas_tecnico;
//	}
//
//	public void setVisitas_tecnico(Visita visita) {
//		this.visitas_tecnico.add(visita);
//	}

	public List<Familia> getEncaminhamentos() {
		return encaminhamentos;
	}

	//adicionará um por vez
	public void setEncaminhamentos(Familia encaminhamentos) {
		this.encaminhamentos.add(encaminhamentos);
	}


	@Override
	public String toString() {
		return "Tecnico [id_funcionario=" + id + ", nome_funcionario=" + nome + "]";
	}

	
}
