package com.jsfcourse.login;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jsf.hockshop.dao.UserDAO;
import jsf.hockshop.entities.User;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_MAIN = "/public/userList?faces-redirect=true";
	private static final String PAGE_LOGIN = "/public/login";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String pass;
	private String role;
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role=role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Inject
	UserDAO userDAO;

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();
	
		User user = userDAO.getByLoginAndPassword(login, pass);
		
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub haslo", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		RemoteClient<User> client = new RemoteClient<User>(); 
		client.setDetails(user);
		
		List<String> roles = userDAO.getUserRolesFromDatabase(user);
		
		if (roles != null) {
			for (String role: roles) {
				client.getRoles().add(role);
			}
		}
				
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);

		return PAGE_MAIN;
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.invalidate();
		return PAGE_LOGIN;
	}
	

	
}
