package net.vksn.ecm.controllers;

import javax.servlet.http.HttpServletRequest;

import net.vksn.ecm.filters.SitemapFilter;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class HomeController {

	@Autowired
	private SitemapService sitemapService;
	
	@Autowired
	private SitemapItemService sitemapItemService;
	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(value = "/*.html", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		String path = request.getServletPath();	
		SitemapItem item = null;
		try {
			int sitemapId = (Integer)request.getSession().getAttribute(SitemapFilter.CURRENT_SITEMAP);
			path = path.substring(1, path.lastIndexOf("."));
			String[] pathSlices = path.split("/");
			item = sitemapItemService.getItemByPath(sitemapId, pathSlices);
			model.addAttribute("sitemapItem", item);
		} catch (Exception e) {			
			e.printStackTrace();
			model.addAttribute("error", e);
			return "error";
		}
		
		return item.getDecorationName();
	}

}
