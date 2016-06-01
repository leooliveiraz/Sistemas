package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Instituicao;
import entidades.Usuario;
import sistema.JpaUtil;

public class InstituicaoDAO {
	public Instituicao inserir(Instituicao instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(instituicao);	
			return instituicao;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			tx.commit();
			em.close();
		}
	}
	public List<Instituicao> lista_instituicoes(){
		EntityManager em = JpaUtil.getEntityManager();
		List<Instituicao> lista;
		Query q = em.createQuery("from Instituicao");
		
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
