package modelo.enumerados;

public enum NivelAcesso {
	COORDENADOR("Coordenador"), TECNICO("Técnico"), RECEPCIONISTA("Recepcionista"), 
	SEMACESSO("Sem Acesso"), VIGILANCIA("Vigilância");
	
private String descricao;
	
	private NivelAcesso(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
