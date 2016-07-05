package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sistema.BaseEntity;

@Entity
public class Local implements Serializable,BaseEntity {

	private static final long serialVersionUID = -8977170025832105192L;
	private int id;
	private Instituicao instituicao;
	private Date dt_criacao,dt_inativacao;
	private String ds_local;
	private boolean sn_ativo;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="id_instituicao")
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDt_inativacao() {
		return dt_inativacao;
	}
	
	public void setDt_inativacao(Date dt_inativacao) {
		this.dt_inativacao = dt_inativacao;
	}
	public String getDs_local() {
		return ds_local;
	}
	public void setDs_local(String ds_local) {
		this.ds_local = ds_local;
	}
	public boolean isSn_ativo() {
		return sn_ativo;
	}
	public void setSn_ativo(boolean sn_ativo) {
		this.sn_ativo = sn_ativo;
	}
	public Local(){
		
	}
	public Local(Instituicao instituicao, Date dt_criacao, Date dt_inativacao, String ds_local, boolean sn_ativo) {
		super();
		this.instituicao = instituicao;
		this.dt_criacao = dt_criacao;
		this.dt_inativacao = dt_inativacao;
		this.ds_local = ds_local;
		this.sn_ativo = sn_ativo;
	}
	public Local(int id, Instituicao instituicao, Date dt_criacao, Date dt_inativacao, String ds_local,
			boolean sn_ativo) {
		super();
		this.id = id;
		this.instituicao = instituicao;
		this.dt_criacao = dt_criacao;
		this.dt_inativacao = dt_inativacao;
		this.ds_local = ds_local;
		this.sn_ativo = sn_ativo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ds_local == null) ? 0 : ds_local.hashCode());
		result = prime * result + ((dt_criacao == null) ? 0 : dt_criacao.hashCode());
		result = prime * result + ((dt_inativacao == null) ? 0 : dt_inativacao.hashCode());
		result = prime * result + id;
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
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
		Local other = (Local) obj;
		if (ds_local == null) {
			if (other.ds_local != null)
				return false;
		} else if (!ds_local.equals(other.ds_local))
			return false;
		if (dt_criacao == null) {
			if (other.dt_criacao != null)
				return false;
		} else if (!dt_criacao.equals(other.dt_criacao))
			return false;
		if (dt_inativacao == null) {
			if (other.dt_inativacao != null)
				return false;
		} else if (!dt_inativacao.equals(other.dt_inativacao))
			return false;
		if (id != other.id)
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (sn_ativo != other.sn_ativo)
			return false;
		return true;
	}
	
}
