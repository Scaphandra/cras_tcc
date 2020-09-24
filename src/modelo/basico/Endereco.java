package modelo.basico;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import modelo.enumerados.EnderecoTipo;

@Embeddable
public class Endereco {

	
	private EnderecoTipo tipo_endereco;
	
	private String logradouro;
	
	@Column(name="end_num")
	private int numero;
	
	private String complemento;
	
	private String bairro;
	
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	private String cep;
	
	public Endereco() {
		
	}

	public Endereco(EnderecoTipo tipo, String logradouro, int numero, String complemento, String bairro, String cep) {
		super();
		this.tipo_endereco = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
	}

	public EnderecoTipo getTipo_endereco() {
		return tipo_endereco;
	}

	public void setTipo_endereco(EnderecoTipo tipo) {
		this.tipo_endereco = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [tipo_endereco=" + tipo_endereco.toString() + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep + "]";
	}
	
	
	
}
