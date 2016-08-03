package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Agenda implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5974824677207045488L;
	private int id;
	private Instituicao instituicao;
	private Local local;
	private String titulo,ds_agenda;
	private Date dt_inicial,hora_inicial,hora_final,dt_marcacao;
	private Setor setor_solicitante;
	private Usuario usuario_solicitante;
	private List<Setor> setores;
	private boolean sn_concluido;
	
	
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
	@ManyToOne
	@JoinColumn(name="id_local")
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDs_agenda() {
		return ds_agenda;
	}
	public void setDs_agenda(String ds_agenda) {
		this.ds_agenda = ds_agenda;
	}
	@Temporal(TemporalType.DATE)
	public Date getDt_inicial() {
		return dt_inicial;
	}
	public void setDt_inicial(Date dt_inicial) {
		this.dt_inicial = dt_inicial;
	}
	@Temporal(TemporalType.TIME)
	public Date getHora_inicial() {
		return hora_inicial;
	}
	public void setHora_inicial(Date hora_inicial) {
		this.hora_inicial = hora_inicial;
	}
	@Temporal(TemporalType.TIME)
	public Date getHora_final() {
		return hora_final;
	}
	public void setHora_final(Date hora_final) {
		this.hora_final = hora_final;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDt_marcacao() {
		return dt_marcacao;
	}
	public void setDt_marcacao(Date dt_marcacao) {
		this.dt_marcacao = dt_marcacao;
	}
	@ManyToOne
	@JoinColumn(name="id_setor") 
	public Setor getSetor_solicitante() {
		return setor_solicitante;
	}
	public void setSetor_solicitante(Setor setor_solicitante) {
		this.setor_solicitante = setor_solicitante;
	}
	@ManyToOne
	@JoinColumn(name="id_usuario")
	public Usuario getUsuario_solicitante() {
		return usuario_solicitante;
	}
	public void setUsuario_solicitante(Usuario usuario_solicitante) {
		this.usuario_solicitante = usuario_solicitante;
	}
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name="agenda_setores",
	joinColumns=@JoinColumn(name="id_agenda"),
	inverseJoinColumns=@JoinColumn(name="id_setor"))
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	public boolean isSn_concluido() {
		return sn_concluido;
	}
	public void setSn_concluido(boolean sn_concluido) {
		this.sn_concluido = sn_concluido;
	}
	@Override
	public String toString() {
		return "Agenda [id=" + id + ", instituicao=" + instituicao + ", local=" + local + ", titulo=" + titulo
				+ ", ds_agenda=" + ds_agenda + ", dt_inicial=" + dt_inicial + ", hora_inicial=" + hora_inicial
				+ ", hora_final=" + hora_final + ", dt_marcacao=" + dt_marcacao + ", setor_solicitante="
				+ setor_solicitante + ", usuario_solicitante=" + usuario_solicitante + ", setores=" + setores
				+ ", sn_concluido=" + sn_concluido + "]";
	}	
}
