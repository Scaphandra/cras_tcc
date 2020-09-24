package modelo.enumerados;

public enum Vinculo {
	COMISSIONADO("Comissionado"), CONTRATADO("Contratado"), CONCURSADO("Concursado"), ESTAGIARIO("Estagi�rio"), OUTRO("Outro");
	
	private String descricao;
		
		private Vinculo(String descricao) {
			this.descricao = descricao;
		}
		
		public String toString() {
			return this.descricao;
		}
}
