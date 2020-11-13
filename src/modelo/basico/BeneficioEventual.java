package modelo.basico;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import modelo.enumerados.BEventualTipo;

@Embeddable
public class BeneficioEventual {
	
	@Enumerated(EnumType.STRING)
	private BEventualTipo tipo_beneficioE;
	
	public BeneficioEventual() {
		
	}

	public BeneficioEventual(BEventualTipo tipo) {
		super();
		this.tipo_beneficioE = tipo;
	}

	public BEventualTipo getTipo_beneficioE() {
		return tipo_beneficioE;
	}

	public void setTipo_beneficioE(BEventualTipo tipo) {
		this.tipo_beneficioE = tipo;
	}

	@Override
	public String toString() {
		
		return "Benefício Eventual: " + tipo_beneficioE.toString();
	}

	
	
}
