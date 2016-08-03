package entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Agenda_Usuario implements Serializable {
	private static final long serialVersionUID = 7634109222882497033L;
	private int id;
	private Agenda agenda;
	private Usuario usuario;
	private boolean sn_confirmado,sn_participante;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="id_agenda")
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	@ManyToOne
	@JoinColumn(name="id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isSn_confirmado() {
		return sn_confirmado;
	}
	public void setSn_confirmado(boolean sn_confirmado) {
		this.sn_confirmado = sn_confirmado;
	}
	public boolean isSn_participante() {
		return sn_participante;
	}
	public void setSn_participante(boolean sn_participante) {
		this.sn_participante = sn_participante;
	}
	public Agenda_Usuario(int id, Agenda agenda, Usuario usuario, boolean sn_confirmado, boolean sn_participante) {
		super();
		this.id = id;
		this.agenda = agenda;
		this.usuario = usuario;
		this.sn_confirmado = sn_confirmado;
		this.sn_participante = sn_participante;
	}
	public Agenda_Usuario() {
		super();
	}	
}
