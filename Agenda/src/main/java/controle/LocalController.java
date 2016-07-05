package controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import entidades.Local;
import persistencia.LocalDAO;

@ManagedBean
@ViewScoped
public class LocalController implements Serializable {

	private static final long serialVersionUID = -3685679888633265453L;
	private Date dt_atual = new Date();
	private boolean sn_edit = true ;
	private List<Local> lista;
	private Local local_cad = new Local(), local_edit = new Local();
	@ManagedProperty(value = "#{usuarioController}")
	private UsuarioController usuarioController;
	
	
	public UsuarioController getUsuarioController() {
		return usuarioController;
	}
	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}
	public Date getDt_atual() {
		return dt_atual;
	}
	public void setDt_atual(Date dt_atual) {
		this.dt_atual = dt_atual;
	}
	public Local getLocal_cad() {
		return local_cad;
	}
	public void setLocal_cad(Local local_cad) {
		this.local_cad = local_cad;
	}
	public Local getLocal_edit() {
		return local_edit;
	}
	public void setLocal_edit(Local local_edit) {
		this.local_edit = local_edit;
	}
	public List<Local> getLista() {
		return lista;
	}
	public void setLista(List<Local> lista) {
		this.lista = lista;
	}
	
	@PostConstruct
	public void inicializar(){
		gera_lista();
	}

	public void gera_lista(){
		LocalDAO dao = new LocalDAO();
		this.lista  = dao.lista(this.usuarioController.getUser().getInstituicao());
	}
	
	public void novo_local(){
		this.local_cad = new Local();
		this.local_cad.setDt_criacao(new Date());
	}

	public void cadastrar_local(){
		LocalDAO dao = new LocalDAO();
		this.local_cad.setSn_ativo(true);
		this.local_cad.setInstituicao(this.usuarioController.getUser().getInstituicao());
		dao.inserir(this.local_cad);
		gera_lista();
		RequestContext.getCurrentInstance().execute("PF('cad_locais').hide();");

	}
	
	public void novo_atualizar(){
		this.sn_edit = this.local_edit.isSn_ativo();
	}
	
	public void atualizar_local(){
		LocalDAO dao = new LocalDAO();
		if (this.sn_edit ==true && this.local_edit.isSn_ativo()==true) {
			this.local_edit.setDt_inativacao(null);
			dao.atualizar(this.local_edit);	
		}if (this.sn_edit ==true && this.local_edit.isSn_ativo()==false) {
			this.local_edit.setDt_inativacao(new Date());
			dao.atualizar(this.local_edit);	
		}if (this.sn_edit ==false && this.local_edit.isSn_ativo()==false) {
			dao.atualizar(this.local_edit);	
		}if (this.sn_edit ==false && this.local_edit.isSn_ativo()==true) {
			this.local_edit.setDt_inativacao(null);
			dao.atualizar(this.local_edit);	
		}
		gera_lista();
		RequestContext.getCurrentInstance().execute("PF('edit_locais').hide();");

	}
	
}
