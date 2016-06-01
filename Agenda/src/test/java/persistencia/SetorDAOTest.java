package persistencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import entidades.Instituicao;
import entidades.Setor;
import entidades.Usuario;
import sistema.JpaUtil;

public class SetorDAOTest {

	@Test
	public void testInserir() {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			Instituicao i = em.find(Instituicao.class, 9);
			Usuario u = em.find(Usuario.class, 13);
			Setor s = new Setor("FATURAMENTO",true,i,u);
			SetorDAO dao = new SetorDAO();
			s = dao.inserir(s);
			s.toString();
		} catch (Exception e) {
			fail("Not yet implemented");
		}finally{
			em.close();
		}
	}

	@Test
	public void testLista_setor() {
		try {
			SetorDAO dao = new SetorDAO();
 			List<Setor> lista = dao.lista_setor();
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

}
