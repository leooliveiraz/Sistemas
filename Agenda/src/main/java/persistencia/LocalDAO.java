package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Instituicao;
import entidades.Local;
import sistema.JpaUtil;

public class LocalDAO {
	public Local inserir(Local local){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(local);
			tx.commit();
			return local;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally {
			em.close();
		}
	}
	
	public Local atualizar(Local local){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.merge(local);
			tx.commit();
			return local;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally {
			em.close();
		}
	}

	public List<Local> lista(Instituicao instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		List<Local> lista;
		Query q = em.createQuery("from Local where instituicao = :i ");
		q.setParameter("i", instituicao);
		try {
			lista = q.getResultList();			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}
	}


	public List<Local> lista_ativos(Instituicao instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		List<Local> lista;
		Query q = em.createQuery("from Local where instituicao = :i and sn_ativo = 1 ");
		q.setParameter("i", instituicao);
		try {
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







