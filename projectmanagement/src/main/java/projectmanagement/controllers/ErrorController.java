package projectmanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
//Tällä kontrollorilla ohjataan virheelliset .do sivupyynnöt virheilmoitus sivulle
public class ErrorController extends ParameterizableViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("error", "error", request.getContextPath());
	}


}
