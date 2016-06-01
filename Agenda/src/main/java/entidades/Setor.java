package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Setor {
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
	
	
	
	

}
