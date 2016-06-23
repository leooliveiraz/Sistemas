package controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import entidades.Setor;
import entidades.Usuario;
import persistencia.SetorDAO;
import persistencia.UsuarioDAO;
import sistema.Utilitarios;

@ManagedBean
@ViewScoped
public class Usuario_FormController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 859817978984210970L;
	private Usuario user_cad = new Usuario(),user_edit = new Usuario();
	private List<Usuario> lista,lista_filtrada;
	private List<Setor> lista_setor;
	@ManagedProperty(value = "#{usuarioController}")
	private UsuarioController usuarioController;
	private String senha_confirmacao;
	
	public String getSenha_confirmacao() {
		return senha_confirmacao;
	}
	public void setSenha_confirmacao(String senha_confirmacao) {
		this.senha_confirmacao = senha_confirmacao;
	}
	public UsuarioController getUsuarioController() {
		return usuarioController;
	}
	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}
	public Usuario getUser_cad() {
		return user_cad;
	}
	public void setUser_cad(Usuario user_cad) {
		this.user_cad = user_cad;
	}
	public Usuario getUser_edit() {
		return user_edit;
	}
	public void setUser_edit(Usuario user_edit) {
		this.user_edit = user_edit;
	}
	public List<Usuario> getLista() {
		return lista;
	}
	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	public List<Usuario> getLista_filtrada() {
		return lista_filtrada;
	}
	public void setLista_filtrada(List<Usuario> lista_filtrada) {
		this.lista_filtrada = lista_filtrada;
	}
	public List<Setor> getLista_setor() {
		return lista_setor;
	}
	public void setLista_setor(List<Setor> lista_setor) {
		this.lista_setor = lista_setor;
	}
	@PostConstruct
	public void inicializa(){
		gera_lista();
		gera_lista_setor();
	}
	public void gera_lista(){
		UsuarioDAO dao = new UsuarioDAO();
		this.lista = dao.lista_usuarios_instituicao(this.usuarioController.getUser().getInstituicao());
	}
	public void gera_lista_setor(){
		SetorDAO dao = new SetorDAO();
		this.lista_setor = dao.lista_setor_instituicao_ativos(this.usuarioController.getUser().getInstituicao());
	}
	
	public void novo_usuario(){
		this.user_cad = new Usuario();
		this.senha_confirmacao = "";
	}
	
	public void edita_usuario(){
		this.senha_confirmacao = this.user_edit.getSenha();
	}
	
	public void cadastra_usuario(){
		this.user_cad.setInstituicao(usuarioController.getUser().getInstituicao());
		UsuarioDAO dao = new UsuarioDAO();
		if(this.senha_confirmacao.equals(this.user_cad.getSenha())){
			dao.inserir(this.user_cad);			
			novo_usuario();
			gera_lista();
			RequestContext.getCurrentInstance().execute("PF('cad_us').hide();");
		}else{
			Utilitarios.msg_erro("As senhas informadas não conferem");
		}
	}
	public void altera_usuario(){
		UsuarioDAO dao = new UsuarioDAO();
		if(this.senha_confirmacao.equals(this.user_edit.getSenha())){
			dao.atualizar(this.user_edit)	;	
			novo_usuario();
			gera_lista();
			RequestContext.getCurrentInstance().execute("PF('edit_us').hide();");
		}else{
			Utilitarios.msg_erro("As senhas informadas não conferem");
		}
	}

	public void reseta_usuario(){
		UsuarioDAO dao = new UsuarioDAO();
		if(this.senha_confirmacao.equals(this.user_edit.getSenha())){
			dao.atualizar(this.user_edit);	
			novo_usuario();
			gera_lista();
			RequestContext.getCurrentInstance().execute("PF('reset_us').hide();");
		}else{
			Utilitarios.msg_erro("As senhas informadas não conferem");
		}
	}
	
	
	
}
