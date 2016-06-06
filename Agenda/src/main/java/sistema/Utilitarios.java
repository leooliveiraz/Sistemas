package sistema;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Utilitarios {
	public static void msg_erro(String erro){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", erro));
	}
	public static void msg_sucesso(String mensagem){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso: ", mensagem));
	}
	
	public static String gera_senha(){
		String senha = "";
		for(int i = 0 ; i<20 ; i++){
			float numero = (float) Math.random();
			String s = ""+numero;
			s = s.substring(s.length()-1);
			senha=senha+s;
		}
		return senha;
	}
	
	public static String uri(){
		String url = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
		url = url.substring(8, url.length());
		return  url;
	}

	public Utilitarios() {
		// TODO Auto-generated constructor stub
	}

}
