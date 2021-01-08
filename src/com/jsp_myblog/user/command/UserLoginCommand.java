package com.jsp_myblog.user.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.jsp_myblog.user.dao.UserDao;
import com.warrenstrange.googleauth.GoogleAuthenticator;

public class UserLoginCommand implements UserCommand {
	final GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(); 
	static final Logger logger = Logger.getLogger(UserSignupCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		BasicConfigurator.configure();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserDao dao = new UserDao();
		String secretKey = dao.getSecretKey(id);
		logger.debug(secretKey);
		boolean result = googleAuthenticator.authorize(secretKey, Integer.parseInt(request.getParameter("code")));
		logger.debug(result);
		
		try {
			if(result == true) {
				String name = dao.login(id, password);
				
				if(name != null) {
					HttpSession session = request.getSession();
					session.setAttribute("userid", id);
					session.setAttribute("username", name);
					return "list.do";
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "login_view.user";
	}
}
