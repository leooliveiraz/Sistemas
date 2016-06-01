package sistema;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utilitarios {
	public static void msg_erro(String erro){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", erro));
	}
	public Utilitarios() {
		// TODO Auto-generated constructor stub
	}

}
