package modelo.enumerados;

public enum NivelAcesso {
	COORDENADOR("Coordenador"), TECNICO("T�cnico"), RECEPCIONISTA("Recepcionista"), SEMACESSO("Sem Acesso");
	
private String descricao;
	
	private NivelAcesso(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
