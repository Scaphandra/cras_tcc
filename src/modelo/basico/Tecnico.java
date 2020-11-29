package modelo.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import modelo.enumerados.Escolaridade;
import modelo.enumerados.NivelAcesso;
import modelo.enumerados.TecnicoReferencia;

@Entity
@DiscriminatorValue("TC")
//cria uma consulta fixa guardada dentro da minha entidade
@NamedQueries(
		@NamedQuery(name="Tecnico.consultarTodos", query = "select e from Tecnico e")
		)
public class Tecnico  extends Funcionario{
	
	@Enumerated(EnumType.STRING)
	private TecnicoReferencia referencia;
	
	@OneToMany(mappedBy="tecnico")
	private List <Familia> acompanhadas_tecnico = new ArrayList<>();
	
	@OneToMany(mappedBy="tecnico")
	private List <Encaminhamento> encaminhamentos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="tecnicos_visita")
	private List <Visita> visitas = new ArrayList<>();
	
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

	
	public List<Familia> getAcompanhadas_tecnico() {
		return acompanhadas_tecnico;
	}

	public void setAcompanhadas_tecnico(Familia acompanhada) {
		this.acompanhadas_tecnico.add(acompanhada);
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(Visita visita) {
		this.visitas.add(visita);
	}

	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}

	//adicionará um por vez
	public void setEncaminhamentos(Encaminhamento encaminhamentos) {
		this.encaminhamentos.add(encaminhamentos);
	}

	@Override
	public String toString() {
		return nome;
	}

	
}
