package controle;



import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import entidades.Usuario;
import persistencia.UsuarioDAO;
import sistema.Utilitarios;
@ManagedBean
@ViewScoped
public class UsuarioController {
	private Usuario user = new Usuario();
	private boolean sn_logado;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isSn_logado() {
		return sn_logado;
	}

	public void setSn_logado(boolean sn_logado) {
		this.sn_logado = sn_logado;
	}
	
	public void realiza_login(){
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.valida_login(this.user);
		if(usuario==(null)){
			Utilitarios.msg_erro("Login ou senha inválidos");
		}else{
			this.user = usuario;
			this.sn_logado = true;
		}
	}
	
}
