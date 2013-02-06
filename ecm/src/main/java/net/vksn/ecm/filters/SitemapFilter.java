package net.vksn.ecm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.services.SitemapService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SitemapFilter implements Filter {
	private static final Logger log = LogManager.getLogger(SitemapFilter.class);
	public static final String CURRENT_SITEMAP = "currentSitemapId";
	private SitemapService sitemapService;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		if(request.getSession().getAttribute(CURRENT_SITEMAP) == null) {
			Sitemap sitemap = sitemapService.getDefaultSitemap();
			request.getSession().setAttribute(CURRENT_SITEMAP, sitemap.getId());
			if(log.isDebugEnabled()) {
				log.debug("Requestista ei löytynyt nykyistä sitemappia: asetettiin default");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(log.isDebugEnabled()) {
			log.debug("sitemapFilter initialisoidaan");
		}
		ServletContext sc = filterConfig.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		sitemapService = (SitemapService)springContext.getBean("sitemapServiceImpl");
	}
	
	@Override
	public void destroy() {
		this.sitemapService = null;
	}


}
