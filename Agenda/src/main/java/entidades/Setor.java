package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import sistema.BaseEntity;
@Entity
public class Setor implements Serializable,BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2687622266385682064L;
	private int id_setor;
	private String nm_setor;
	private boolean sn_ativo;
	private Instituicao instituicao;
	private Usuario usuario;
	@Id
	@GeneratedValue
	public int getId_setor() {
		return id_setor;
	}
	
	public void setId_setor(int id_setor) {
		this.id_setor = id_setor;
	}
	@Column(length=60,nullable=false)
	public String getNm_setor() {
		return nm_setor;
	}

	public void setNm_setor(String nm_setor) {
		this.nm_setor = nm_setor;
	}
	@Column(nullable = false)
	public boolean isSn_ativo() {
		return sn_ativo;
	}

	public void setSn_ativo(boolean sn_ativo) {
		this.sn_ativo = sn_ativo;
	}
	@ManyToOne
	@JoinColumn(name="id_usuario_responsavel")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@ManyToOne
	@JoinColumn(name="id_instituicao")
	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	

	public Setor(String nm_setor, boolean sn_ativo, Instituicao instituicao, Usuario usuario) {
		super();
		this.nm_setor = nm_setor;
		this.sn_ativo = sn_ativo;
		this.instituicao = instituicao;
		this.usuario = usuario;
	}

	public Setor() {
		
	}

	public Setor(String nm_setor, boolean sn_ativo) {
		super();
		this.nm_setor = nm_setor;
		this.sn_ativo = sn_ativo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_setor;
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
		result = prime * result + ((nm_setor == null) ? 0 : nm_setor.hashCode());
		result = prime * result + (sn_ativo ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		if (id_setor != other.id_setor)
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (nm_setor == null) {
			if (other.nm_setor != null)
				return false;
		} else if (!nm_setor.equals(other.nm_setor))
			return false;
		if (sn_ativo != other.sn_ativo)
			return false;
		
		return true;
	}

	@Override
	@Transient
	public int getId() {
		// TODO Auto-generated method stub
		return this.getId_setor();
	}

}
