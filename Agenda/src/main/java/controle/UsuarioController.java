package controle;




import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import entidades.Usuario;
import persistencia.UsuarioDAO;
import sistema.Utilitarios;
@ManagedBean
@SessionScoped
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
		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = dao.valida_login(this.user);
			if(usuario==(null)){
				Utilitarios.msg_erro("Login ou senha inválidos");
			}else{
				this.user = usuario;
				this.sn_logado = true;
				FacesContext.getCurrentInstance().getAttributes().put("usuario", this.user);
				FacesContext.getCurrentInstance().getExternalContext().redirect("tela_principal.xhtml");	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger log =Logger.getLogger(UsuarioController.class);
			log.error(e.getStackTrace());
		}		
	}
	
	public String recupera_usuario(){
		try {
			Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getAttributes().put("usuario", this.user);
			if(usuario==(null)){
				FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");
				return null;	
			}else{
				UsuarioDAO dao = new UsuarioDAO();
				usuario = dao.valida_login(usuario);
				return usuario.getNome();
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
