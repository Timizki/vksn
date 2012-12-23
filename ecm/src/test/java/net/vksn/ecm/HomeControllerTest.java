package net.vksn.ecm;

import junit.framework.Assert;

import net.vksn.ecm.controllers.HomeController;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class HomeControllerTest {

	public void testController() {
		HomeController controller = new HomeController();
		Model model = new ExtendedModelMap();
		
		MockHttpServletRequest requestMock = new MockHttpServletRequest();
		requestMock.setServletPath("/home.html");
		Assert.assertEquals("home",controller.home(model, requestMock));
		
		Object message = model.asMap().get("controllerMessage");
		Assert.assertEquals("This is the message from the controller!",message);
		
	}
}
