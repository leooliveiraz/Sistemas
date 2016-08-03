package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import entidades.Agenda;
import entidades.Agenda_Usuario;
import entidades.Local;
import entidades.Setor;
import entidades.Tempo;
import entidades.Usuario;
import persistencia.AgendaDAO;
import persistencia.Agenda_UsuarioDAO;
import persistencia.LocalDAO;
import persistencia.SetorDAO;
import persistencia.TempoDAO;
import persistencia.UsuarioDAO;

@ManagedBean
@ViewScoped
public class AgendaController implements Serializable{
	private static final long serialVersionUID = 2014479745596851975L;
	@ManagedProperty(value = "#{usuarioController}")
	private UsuarioController usuarioController;
	private ScheduleModel eventModel;  
	private Local local;
	private List<Local> lista_local;
	
	private List<Agenda> lista_agenda;
	private Agenda age_cad = new Agenda();
	private Agenda age_edit = new Agenda();
	
	private List<Setor> lista_setores,lista_setores_escolhidos;
	private Setor setor = new Setor();

	private List<Usuario> lista_usuarios,lista_usuarios_escolhidos; 
	private Usuario usuario_escolha;	
	
	private Date dt_atual = new Date();
	private	Logger log =Logger.getLogger(this.getClass());

    private ScheduleEvent event = new DefaultScheduleEvent();
	
	public Usuario getUsuario_escolha() {
		return usuario_escolha;
	}
	public void setUsuario_escolha(Usuario usuario_escolha) {
		this.usuario_escolha = usuario_escolha;
	}
	public Agenda getAge_cad() {
		return age_cad;
	}
	public void setAge_cad(Agenda age_cad) {
		this.age_cad = age_cad;
	}
	public ScheduleModel getEventModel() {
		return eventModel;
	}
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public List<Local> getLista_local() {
		return lista_local;
	}
	public void setLista_local(List<Local> lista_local) {
		this.lista_local = lista_local;
	}

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
	
	public List<Setor> getLista_setores() {
		return lista_setores;
	}
	public void setLista_setores(List<Setor> lista_setores) {
		this.lista_setores = lista_setores;
	}
	public List<Setor> getLista_setores_escolhidos() {
		return lista_setores_escolhidos;
	}
	public void setLista_setores_escolhidos(List<Setor> lista_setores_escolhidos) {
		this.lista_setores_escolhidos = lista_setores_escolhidos;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public List<Usuario> getLista_usuarios() {
		return lista_usuarios;
	}
	public void setLista_usuarios(List<Usuario> lista_usuarios) {
		this.lista_usuarios = lista_usuarios;
	}
	public List<Usuario> getLista_usuarios_escolhidos() {
		return lista_usuarios_escolhidos;
	}
	public void setLista_usuarios_escolhidos(List<Usuario> lista_usuarios_escolhidos) {
		this.lista_usuarios_escolhidos = lista_usuarios_escolhidos;
	}
	
	public List<Agenda> getLista_agenda() {
		return lista_agenda;
	}
	public void setLista_agenda(List<Agenda> lista_agenda) {
		this.lista_agenda = lista_agenda;
	}
	public Agenda getAge_edit() {
		return age_edit;
	}
	public void setAge_edit(Agenda age_edit) {
		this.age_edit = age_edit;
	}
	@PostConstruct
	public void inicializar(){
		gera_lista_local();
		gera_lista_setores();
		this.eventModel = new DefaultScheduleModel();
	}
	public void gera_lista_local(){
		LocalDAO dao = new LocalDAO();
		this.lista_local = dao.lista_ativos(this.usuarioController.getUser().getInstituicao());
	}
	public void gera_lista_setores(){
		SetorDAO dao = new SetorDAO();
		this.lista_setores = dao.lista_setor_instituicao_ativos(this.usuarioController.getUser().getInstituicao());
	}
	
	public void usuarios_por_setor(){
		UsuarioDAO dao = new UsuarioDAO();
		this.lista_usuarios = dao.lista_usuarios_setor(this.usuarioController.getUser().getInstituicao(),this.age_cad.getSetores());
	}
	
	@SuppressWarnings("deprecation")
	public void gera_agendas(){
		AgendaDAO dao = new AgendaDAO();
		this.lista_agenda = dao.lista_agendas(this.local);
		this.eventModel = new DefaultScheduleModel();
		for(int i = 0;i<this.lista_agenda.size();i++){
			Calendar dt_inicio_evento = Calendar.getInstance();
			dt_inicio_evento.setTime(this.lista_agenda.get(i).getDt_inicial());
			dt_inicio_evento.set(Calendar.HOUR,this.lista_agenda.get(i).getHora_inicial().getHours());
			dt_inicio_evento.set(Calendar.MINUTE,this.lista_agenda.get(i).getHora_inicial().getMinutes());
			dt_inicio_evento.set(Calendar.SECOND,0);
			
			Calendar dt_fim_evento = Calendar.getInstance();
			dt_fim_evento.setTime(this.lista_agenda.get(i).getDt_inicial());
			dt_fim_evento.set(Calendar.HOUR,this.lista_agenda.get(i).getHora_final().getHours());
			dt_fim_evento.set(Calendar.MINUTE,this.lista_agenda.get(i).getHora_final().getMinutes());
			dt_fim_evento.set(Calendar.SECOND,0);
			
			this.eventModel.addEvent(new DefaultScheduleEvent(this.lista_agenda.get(i).getTitulo(), dt_inicio_evento.getTime(), dt_fim_evento.getTime()));
		}
		   
	}
	

	@SuppressWarnings("deprecation")
	public void nova_agenda(SelectEvent selectEvent){
		try {
			this.age_cad = new Agenda();
			this.lista_setores_escolhidos = new ArrayList<>();
			Date dt_selecionada = new Date();
			dt_selecionada = (Date) selectEvent.getObject();
			this.age_cad.setLocal(this.local);
			this.age_cad.setUsuario_solicitante(this.usuarioController.getUser());
			this.age_cad.setSetor_solicitante(this.usuarioController.getUser().getSetor());
			this.age_cad.setInstituicao(this.usuarioController.getUser().getInstituicao());
			this.age_cad.setDt_marcacao(this.dt_atual);
			this.age_cad.setDt_inicial((Date) selectEvent.getObject());
			this.age_cad.getDt_inicial().setDate(this.age_cad.getDt_inicial().getDate()+1);

			Date dt_comparacao = this.dt_atual;
			dt_comparacao.setDate(this.dt_atual.getDate());
			dt_comparacao.setDate(dt_comparacao.getDate()-1);
			
			if(dt_selecionada.after(dt_comparacao)){
				RequestContext.getCurrentInstance().execute("PF('sel_data').show();");				
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	public void cadastrar_agenda(){
		if(this.age_cad.getHora_final().after(this.age_cad.getHora_inicial())){
			AgendaDAO dao = new AgendaDAO();
			this.age_cad.setDt_marcacao(new Date());			
			
			this.age_cad = dao.cadastrar_agenda(this.age_cad);
			adicionar_usuario();
			gera_agendas();
			
			this.lista_usuarios = new ArrayList<>();
			RequestContext.getCurrentInstance().execute("PF('sel_data').hide();");	
		}				
	}
	
	public void adicionar_usuario(){
		for(int i = 0 ; i < this.lista_usuarios.size() ; i++){
			if(this.lista_usuarios.get(i).isSn_convidado() == true){
				Agenda_UsuarioDAO dao = new Agenda_UsuarioDAO();
				Agenda_Usuario au = new Agenda_Usuario(0,this.age_cad,this.lista_usuarios.get(i),false,false);
				if(dao.conta_usuarios(au)==0){
					dao.inserir(au);
				}
			}
		}
	}
	
	public void selecionar_evento(SelectEvent selectEvent) {
		
        event = (ScheduleEvent) selectEvent.getObject();
        this.age_edit = new Agenda();
        this.age_edit.setLocal(this.local);
        this.age_edit.setDt_inicial(event.getStartDate());
        this.age_edit.setHora_inicial(event.getStartDate());
        this.age_edit.setHora_final(event.getEndDate());
        this.age_edit.setDs_agenda(event.getTitle());
        
        
        AgendaDAO dao = new AgendaDAO();
        this.age_edit = dao.busca_agenda(this.age_edit);
        
        UsuarioDAO usuario_dao = new UsuarioDAO();
		this.lista_usuarios = usuario_dao.lista_usuarios_setor(this.usuarioController.getUser().getInstituicao(),this.age_edit.getSetores());
		this.lista_usuarios_escolhidos = new ArrayList<>();
		RequestContext.getCurrentInstance().execute("PF('edit_agenda').show();");	
    }
	
}
