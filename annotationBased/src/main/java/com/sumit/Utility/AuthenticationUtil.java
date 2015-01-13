package com.sumit.Utility;

import com.sumit.model.User;

public class AuthenticationUtil {
	
	public static User user;

	public  static User getCurrentUser(){
		return user;
		
	}

	public static void setCurrentUser(com.sumit.model.User authorizedUser) {
		user=authorizedUser;
		
	}
	
}
