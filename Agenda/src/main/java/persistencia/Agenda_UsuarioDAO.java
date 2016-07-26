package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import entidades.Agenda_Usuario;
import entidades.Instituicao;
import entidades.Setor;
import entidades.Usuario;
import sistema.JpaUtil;

public class Agenda_UsuarioDAO {
	public Agenda_Usuario inserir(Agenda_Usuario agenda_usuario){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try{
			em.persist(agenda_usuario);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
		}		
		return agenda_usuario;
	}
	public Agenda_Usuario atualizar(Agenda_Usuario agenda_usuario){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try{
			em.merge(agenda_usuario);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
		}		
		return agenda_usuario;
	}

	public Long conta_usuarios(Agenda_Usuario agenda_usuario){
		EntityManager em = new JpaUtil().getEntityManager();
		Query query = em.createQuery("select count (*)from Agenda_Usuario where agenda = :agenda and usuario like :user ");
		Usuario user;
		long l =0;
		try {
			query.setParameter("user", agenda_usuario.getUsuario());
			query.setParameter("agenda", agenda_usuario.getAgenda());

			l = (long) query.getSingleResult();
			
			return l;
		} catch (Exception e) {
			Logger log =Logger.getLogger(Agenda_UsuarioDAO.class);
			log.info(e.getStackTrace());
			return (long) 0.0;
		}finally {
			em.close();
		}		
	}
	
	public List<Agenda_Usuario> lista_usuarios(){
		EntityManager em = JpaUtil.getEntityManager();
		List<Agenda_Usuario> lista;
		Query q = em.createQuery("from Agenda_Usuario");
		lista = q.getResultList();
		return lista;
	}
	
	public List<Agenda_Usuario> lista_usuarios_instituicao(Instituicao instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		List<Agenda_Usuario> lista;
		Query q = em.createQuery("from Agenda_Usuario where agenda.instituicao = :i");
		q.setParameter("i", instituicao);
		lista = q.getResultList();
		return lista;
	}
	
	public List<Agenda_Usuario> lista_usuarios_setor(Instituicao instituicao,List<Setor> lista_setor){
		EntityManager em = JpaUtil.getEntityManager();
		List<Agenda_Usuario> lista;
		Query q = em.createQuery("from Agenda_Usuario where agenda.instituicao = :i and  agenda.setor in (:lista"
				+ ") order by setor");
		q.setParameter("i", instituicao);
		q.setParameter("lista", lista_setor);
		lista = q.getResultList();
		return lista;
	}
	
	
	public Agenda_Usuario usuario_id(int id){
		EntityManager em = JpaUtil.getEntityManager();
		try{
			Agenda_Usuario agenda_usuario = em.find(Agenda_Usuario.class, id);
			return agenda_usuario;			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}
	}
}
