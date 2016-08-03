package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import sistema.BaseEntity;

@Entity
public class Tempo implements Serializable,BaseEntity {
	private static final long serialVersionUID = -9194238987566085430L;
	private int id;
	private String hora_minuto;
	private Date tempo;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHora_minuto() {
		return hora_minuto;
	}
	public void setHora_minuto(String hora_minuto) {
		this.hora_minuto = hora_minuto;
	}
	public Date getTempo() {
		return tempo;
	}
	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}
	
	
}
