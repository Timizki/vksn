package net.vksn.bedrock.web.editors;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.services.UserService;

import org.springframework.beans.propertyeditors.ClassEditor;

public class UserEditor extends ClassEditor {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void setAsText(String userId) throws IllegalArgumentException {
		User user = null;
		try {
			Integer id = Integer.valueOf(userId);
			user = userService.get(id);
		}
		catch(NumberFormatException e) {
			user = new User();
		}
		catch(EntityNotFoundException e) {
			user = new User();
		}
		setValue(user);
	}

	@Override
	public String getAsText() {
		if(getValue() != null) {
			return getValue().toString();
		}
		return "";
	}
	

}
