package controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Setor;
import entidades.Usuario;
import persistencia.SetorDAO;
import persistencia.UsuarioDAO;

@ManagedBean
@ViewScoped
public class SetorController {
	private Setor setor_cad = new Setor(),setor_edit = new Setor();
	private List<Setor> lista,lista_filtrada;
	private List<Usuario> lista_usuarios;
	@ManagedProperty(value = "#{usuarioController}")
	private UsuarioController usuarioController;
	
	public List<Usuario> getLista_usuarios() {
		return lista_usuarios;
	}
	public void setLista_usuarios(List<Usuario> lista_usuarios) {
		this.lista_usuarios = lista_usuarios;
	}
	public UsuarioController getUsuarioController() {
		return usuarioController;
	}
	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}
	public Setor getSetor_cad() {
		return setor_cad;
	}
	public void setSetor_cad(Setor setor_cad) {
		this.setor_cad = setor_cad;
	}
	public Setor getSetor_edit() {
		return setor_edit;
	}
	public void setSetor_edit(Setor setor_edit) {
		this.setor_edit = setor_edit;
	}
	public List<Setor> getLista() {
		return lista;
	}
	public void setLista(List<Setor> lista) {
		this.lista = lista;
	}
	public List<Setor> getLista_filtrada() {
		return lista_filtrada;
	}
	public void setLista_filtrada(List<Setor> lista_filtrada) {
		this.lista_filtrada = lista_filtrada;
	}
	@PostConstruct
	public void inicializa(){
		gera_lista_setores();
	}
	public void gera_lista_setores(){
		SetorDAO dao = new SetorDAO();
		this.lista = dao.lista_setor_instituicao(this.usuarioController.getUser().getInstituicao().getId());
	}

	
}
