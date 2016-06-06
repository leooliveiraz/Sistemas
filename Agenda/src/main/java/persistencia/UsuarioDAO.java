package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import entidades.Usuario;
import sistema.JpaUtil;

public class UsuarioDAO {
	public Usuario inserir(Usuario usuario){
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try{
			em.persist(usuario);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
		}		
		return usuario;
	}

	public Usuario valida_login(Usuario usuario){
		EntityManager em = new JpaUtil().getEntityManager();
		Query query = em.createQuery("from Usuario where instituicao = :ins and login like :user and senha = sha2( :pass , 512 )");
		Usuario user;
		List<Usuario> l;
		try {
			query.setParameter("user", usuario.getLogin());
			query.setParameter("pass", usuario.getSenha());
			query.setParameter("ins", usuario.getInstituicao());

			l = query.getResultList();
			user = l.get(0);
			return user;
		} catch (Exception e) {
			Logger log =Logger.getLogger(UsuarioDAO.class);
			log.info(e.getStackTrace());
			return null;
		}finally {
			em.close();
		}		
	}
	
	public List<Usuario> lista_usuarios(){
		EntityManager em = JpaUtil.getEntityManager();
		List<Usuario> lista;
		Query q = em.createQuery("from Usuario");
		lista = q.getResultList();
		return lista;
	}
}
