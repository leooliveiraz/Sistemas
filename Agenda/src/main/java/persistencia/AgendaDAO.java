package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Agenda;
import entidades.Instituicao;
import entidades.Local;
import sistema.JpaUtil;

public class AgendaDAO {
	public Agenda cadastrar_agenda(Agenda agenda){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(agenda);
			tx.commit();
			return agenda;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally {
			em.close();
		}
	}
	
	public Agenda atualizar_agenda(Agenda agenda){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(agenda);
			tx.commit();
			return agenda;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally {
			em.close();
		}
	}
	
	public List<Agenda> lista_agendas(Local local){
		EntityManager em = JpaUtil.getEntityManager();
		List<Agenda> lista = new ArrayList<>();
		try {
			Query q = em.createQuery("from Agenda a where local = :l ");
			q.setParameter("l", local);
			lista = q.getResultList();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}finally {
			em.close();
		}
			
	}
}
