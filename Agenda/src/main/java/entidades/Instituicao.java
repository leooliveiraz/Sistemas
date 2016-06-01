package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Instituicao {
	int id;
	String nome,email;
	boolean sn_ativo;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isSn_ativo() {
		return sn_ativo;
	}
	public void setSn_ativo(boolean sn_ativo) {
		this.sn_ativo = sn_ativo;
	}

	@Column(nullable = false,length=100)	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instituicao() {
		
	}

	public Instituicao(String nome, String email, boolean sn_ativo) {
		super();
		this.nome = nome;
		this.email = email;
		this.sn_ativo = sn_ativo;
	}
	

}
