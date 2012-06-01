package net.vksn.gathering.web.controllers;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.services.RoleService;
import net.vksn.bedrock.services.UserService;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class EditUserController extends SimpleFormController {
	private UserService userService;
	private RoleService roleService;
	@SuppressWarnings("unchecked")
	private Map<Class, PropertyEditor> editors;
	
	@SuppressWarnings("unchecked")
	public void setEditors(Map<Class, PropertyEditor> editors) {
		this.editors = editors;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		User user = null;
		String userId = request.getParameter("id");
		try  {
			user = this.userService.get(Integer.valueOf(userId));
		}
		catch (NumberFormatException e) {
			user = new User();
		}
	
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		for(Class clazz : editors.keySet()) {
			binder.registerCustomEditor(clazz, editors.get(clazz));
		}
	}

	
	@Override
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		ModelAndView mav = super.showForm(request, response, errors);
		List<Role> roles = (List<Role>)roleService.getAllRoles();
		mav.addObject("roles", roles);
		return mav;
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		User user = (User)command;
		userService.store(user);
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;
	}
	
}
