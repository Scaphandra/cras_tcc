package modelo.basico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RE")
public class PesReferencia extends Pessoa{

	
	
	public PesReferencia() {
		
		super.setPesReferencia(true);
	}

	public String toString() {
		return super.getNome();
	}
	
}
