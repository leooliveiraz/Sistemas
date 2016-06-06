package controle;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import entidades.Instituicao;
import entidades.Usuario;
import persistencia.InstituicaoDAO;
import persistencia.UsuarioDAO;
import sistema.Utilitarios;

@ManagedBean
@ViewScoped
public class InstituicaoController {
	private Instituicao instituicao_cad = new Instituicao();
	private Usuario usuario_cad = new Usuario();
	private String senha = "";

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario_cad() {
		return usuario_cad;
	}

	public void setUsuario_cad(Usuario usuario_cad) {
		this.usuario_cad = usuario_cad;
	}

	public Instituicao getInstituicao_cad() {
		return instituicao_cad;
	}

	public void setInstituicao_cad(Instituicao instituicao_cad) {
		this.instituicao_cad = instituicao_cad;
	}
	@PostConstruct
	public void inicializar(){
		try{
			this.instituicao_cad= (Instituicao) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("instituicao");
			this.usuario_cad= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("usuario");
			this.senha= (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("senha");
		

			if(this.instituicao_cad == (null)) this.instituicao_cad = new Instituicao();
			if(this.usuario_cad == (null)) this.usuario_cad = new Usuario();
			if(this.instituicao_cad == (null)) this.senha = "";
		}catch(Exception e){
			if(this.instituicao_cad.equals(null)) this.instituicao_cad = new Instituicao();
			if(this.usuario_cad.equals(null)) this.usuario_cad = new Usuario();
			if(this.instituicao_cad.equals(null)) this.senha = "";
		}
	}

	public void cadastra_empresa(){
		try{
			
			//cadastra instituicao gera senha e usuario.
			InstituicaoDAO dao = new InstituicaoDAO();
			this.instituicao_cad = dao.inserir(instituicao_cad);
			this.senha = Utilitarios.gera_senha();
			UsuarioDAO uDao = new UsuarioDAO();
			this.usuario_cad = new Usuario(this.instituicao_cad.getNome(), "MASTER",this.senha , new Date(), this.instituicao_cad, null);
			this.usuario_cad = uDao.inserir(this.usuario_cad);
			Utilitarios.msg_sucesso("Parabéns! Sua empresa foi cadastrada com Sucesso! Seu código é:"+this.instituicao_cad.getId());
			

			//Salva informações na sessão e encaminha para outra pagina
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instituicao", this.instituicao_cad);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("usuario", this.usuario_cad);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("senha", this.senha);

			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro_instituicao_ok.xhtml");
			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getLogger(InstituicaoController.class).error(e.getStackTrace());
		}

	}

	public void encaminha_cadastro(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro_instituicao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getInstance(InstituicaoController.class).fatal(e.getStackTrace());
		}
	}
	
	public void redireciona_inicio(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getInstance(InstituicaoController.class).fatal(e.getStackTrace());
		}
	}

}
