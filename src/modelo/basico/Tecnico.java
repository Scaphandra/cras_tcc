package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import modelo.enumerados.NivelAcesso;
import modelo.enumerados.TecnicoReferencia;

@Entity

public class Tecnico  extends Funcionario{
	
	private TecnicoReferencia referencia_tecnico;
	
	@OneToMany(mappedBy="tecnicoRef_familia")
	private List <Familia> acompanhadas_tecnico = new ArrayList<>();
	
	@OneToMany(mappedBy="funcionario_atendimento")
	private List <Atendimento> atendimentos_tecnico = new ArrayList<>();
	
	@ManyToMany(mappedBy="tecnicos_visita")
	private List <Visita> visitas_tecnico = new ArrayList<>();
	
	public Tecnico() {
		
	}
	
	

	public Tecnico(String nome, String funcao, String cpf, String senha, NivelAcesso nivel) {
		super(nome, funcao, cpf, senha, nivel);
		// TODO Auto-generated constructor stub
	}



	public Tecnico(TecnicoReferencia referencia, Familia acompanhada,
			Atendimento atendimento, Visita visita) {
		super();
		this.referencia_tecnico = referencia;
		this.acompanhadas_tecnico.add(acompanhada);
		this.atendimentos_tecnico.add(atendimento);
		this.visitas_tecnico.add(visita);
	}

	public TecnicoReferencia getReferencia_tecnico() {
		return referencia_tecnico;
	}

	public void setReferencia_tecnico(TecnicoReferencia referencia) {
		this.referencia_tecnico = referencia;
	}

	public List<Familia> getAcompanhadas_tecnico() {
		return acompanhadas_tecnico;
	}

	public void setAcompanhadas_tecnico(Familia acompanhada) {
		this.acompanhadas_tecnico.add(acompanhada);
	}

	public List<Atendimento> getAtendimentos_tecnico() {
		return atendimentos_tecnico;
	}

	public void setAtendimentos_tecnico(Atendimento atendimento) {
		this.atendimentos_tecnico.add(atendimento);
	}

	public List<Visita> getVisitas_tecnico() {
		return visitas_tecnico;
	}

	public void setVisitas_tecnico(Visita visita) {
		this.visitas_tecnico.add(visita);
	}

	@Override
	public String toString() {
		return "Tecnico [id_funcionario=" + id_funcionario + ", nome_funcionario=" + nome_funcionario + "]";
	}

	
}
