package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Instituicao;
import entidades.Setor;
import entidades.Usuario;
import sistema.JpaUtil;

public class SetorDAO {
	public Setor inserir(Setor setor){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(setor);	
			return setor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			tx.commit();
			em.close();
		}
	}
	public List<Setor> lista_setor(){
		EntityManager em = JpaUtil.getEntityManager();
		List<Setor> lista;
		Query q = em.createQuery("from Setor");
		
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
	public List<Setor> lista_setor_instituicao(int instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		List<Setor> lista;
		Query q = em.createQuery("from Setor where instituicao = :i");
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

	public List<Setor> lista_setor_instituicao_ativos(int instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		List<Setor> lista;
		Query q = em.createQuery("from Setor where instituicao = :i and sn_ativo = 'S'");
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
