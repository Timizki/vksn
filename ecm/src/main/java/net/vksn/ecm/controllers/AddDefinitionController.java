package net.vksn.ecm.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.vksn.ecm.model.TilesDefinition;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.apache.tiles.definition.dao.DefinitionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
@SessionAttributes("definition")
public class AddDefinitionController {

	@Autowired
	private DefinitionDAO<String> definitionDao;

	private static final Logger logger = LoggerFactory
			.getLogger(AddDefinitionController.class);

	@Autowired
	private SitemapService sitemapService;

	@Autowired
	private SitemapItemService sitemapItemService;

	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(value = "*.do", method = RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request) {
		TilesDefinition definition = null;
		if (!model.containsAttribute("definition")) {
			Map modelMap = model.asMap();
			definition = (TilesDefinition) modelMap.get("definition");
		} else {
			definition = new TilesDefinition();
		}
		return "form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("definition") TilesDefinition definition,
			BindingResult result, SessionStatus status) {
		// definitionDao.storeDefinition(definition);
		return "home";
	}

}