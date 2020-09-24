package modelo.enumerados;

public enum BeneficioTipo {
	PBF("PBF"), BPCI("BPCIdoso"), BPCDEF("BPCDeficiente"), NV("Nova Vida"), O("Outro");
	
	private String descricao;
	
	private BeneficioTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
