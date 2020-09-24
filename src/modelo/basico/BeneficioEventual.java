package modelo.basico;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import modelo.enumerados.BEventualTipo;

@Embeddable
public class BeneficioEventual {
	
	private BEventualTipo tipo_beneficioE;
	
	public BeneficioEventual() {
		
	}

	public BeneficioEventual(BEventualTipo tipo) {
		super();
		this.tipo_beneficioE = tipo;
	}

	@Enumerated(EnumType.STRING)
	public BEventualTipo getTipo_beneficioE() {
		return tipo_beneficioE;
	}

	public void setTipo_beneficioE(BEventualTipo tipo) {
		this.tipo_beneficioE = tipo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
}
