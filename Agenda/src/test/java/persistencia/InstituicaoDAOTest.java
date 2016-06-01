package persistencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entidades.Instituicao;

public class InstituicaoDAOTest {

	@Test
	public void testInserir() {
		try {
			Instituicao i = new Instituicao("LEO","LEONARDO@OUTLOOK.COM",true);
			InstituicaoDAO dao = new InstituicaoDAO();
			i = dao.inserir(i);
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

	@Test
	public void testLista_instituicoes() {
		try {
			InstituicaoDAO dao = new InstituicaoDAO();
			List<Instituicao> lista = dao.lista_instituicoes();
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

}
