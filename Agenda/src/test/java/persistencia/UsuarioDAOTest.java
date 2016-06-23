package persistencia;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Test;

import entidades.Instituicao;
import entidades.Setor;
import entidades.Usuario;
import sistema.JpaUtil;

public class UsuarioDAOTest {

	@Test
	public void testInserir() {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			Instituicao instituicao = em.find(Instituicao.class, 9);
			Setor setor = em.find(Setor.class, 12);
			Usuario u = new Usuario("ADMINISTRADOR", "ADMIN", "admin", new Date(),instituicao,setor);
			UsuarioDAO dao = new UsuarioDAO();
			u = dao.inserir(u);		
			u.toString();
			em.close();
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testConsulta() {
		try {
			Usuario u = new Usuario();
			u.setLogin("BOLIVEIRA");
			u.setSenha("1515");
			UsuarioDAO dao = new UsuarioDAO();
			u = dao.valida_login(u);		
			u.toString();
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

}
