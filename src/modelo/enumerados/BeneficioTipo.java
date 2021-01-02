package modelo.enumerados;

public enum BeneficioTipo {
	PBF("PBF"), BPCI("BPC Idoso"), BPCDEF("BPC Def."), NV("Nova Vida"), O("Outro");
	
	private String descricao;
	
	private BeneficioTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
