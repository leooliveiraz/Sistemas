package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Instituicao;
import entidades.Tempo;
import sistema.JpaUtil;

public class TempoDAO {
	public List<Tempo> lista(Instituicao instituicao){
		EntityManager em = JpaUtil.getEntityManager();
		List<Tempo> lista = new ArrayList<>();
		try{
			Query q = em.createQuery("from Tempo");
			lista = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return lista;
		
	}
}
