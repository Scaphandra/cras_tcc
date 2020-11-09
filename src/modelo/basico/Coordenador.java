package modelo.basico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import modelo.enumerados.NivelAcesso;

@Entity
@DiscriminatorValue("CO")
public class Coordenador extends Tecnico{

	public Coordenador() {
		super.nivel = NivelAcesso.COORDENADOR;
		super.funcao = "Coordenador";
	}

	
}
