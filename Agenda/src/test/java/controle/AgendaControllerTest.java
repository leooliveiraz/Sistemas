package controle;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import entidades.Agenda;
import entidades.Local;
import persistencia.AgendaDAO;

public class AgendaControllerTest {
	
	@Test
	public void gera_agendas(){
		AgendaDAO dao = new AgendaDAO();
		List<Agenda> lista_agenda;
		ScheduleModel eventModel;  
		Local local = new Local();
		local.setId(61);
		
		lista_agenda = dao.lista_agendas(local);
		eventModel = new DefaultScheduleModel();
		
		try {
			for(int i = 0;i<lista_agenda.size();i++){
				Calendar dt_retorno = (Calendar) Calendar.getInstance();
				Date dt_inicio_evento = lista_agenda.get(i).getDt_inicial();
				Date hora_inicio = lista_agenda.get(i).getHora_inicial();
				Date hora_fim = lista_agenda.get(i).getHora_final();
				
				dt_retorno.setTime(dt_inicio_evento);
				dt_retorno.set(Calendar.HOUR, hora_inicio.getHours());
				dt_retorno.set(Calendar.MINUTE, hora_inicio.getMinutes());
				dt_retorno.set(Calendar.SECOND, 0);
				
				dt_inicio_evento = dt_retorno.getTime();
				
				eventModel.addEvent(new DefaultScheduleEvent(lista_agenda.get(i).getTitulo(), dt_inicio_evento, new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		   
	}
}
