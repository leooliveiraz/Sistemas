package controle;




import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entidades.Instituicao;
import entidades.Usuario;
import persistencia.UsuarioDAO;
import sistema.Utilitarios;

@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6664912381799438035L;
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

	public UsuarioController(){
		this.user.setInstituicao(new Instituicao());
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

	public void realiza_logoff(){
		try {
			FacesContext.getCurrentInstance().getAttributes().clear();
			FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");	
		} catch (Exception e) {
			e.printStackTrace();
			Logger log =Logger.getLogger(UsuarioController.class);
			log.error(e.getStackTrace());
		}		
	}

	public String recupera_usuario(){
		Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") ;
		if(!user.equals(u))
			try {
				Logger log =Logger.getLogger(UsuarioController.class);
				log.error("Usuario Invalido");				
				FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger log =Logger.getLogger(UsuarioController.class);
				log.error(e.getStackTrace());					
			}	
		else{
			return this.user.getNome();
		}
		return null;
	}
}
