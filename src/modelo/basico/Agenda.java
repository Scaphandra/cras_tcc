package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//Classe ainda é bean, será incluída após melhorias no sistema
public class Agenda {

	
	private Long id;
	
//	@ManyToOne
//	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	private Tecnico tecnico;
	
	private List<Familia> familias = new ArrayList<>();	
	
	private Date previsao;
	
	private int tamanho;
	
	private boolean adiado;
	
	private int limite;
	
	public Agenda() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Unidade getUnidade() {
		return unidade;
	}



	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}



	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(Familia familia) {
		if(limite>0) {
			this.familias.add(familia);
			limite -= 1;
		}
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
		this.limite = tamanho;
	}
	
	
}
