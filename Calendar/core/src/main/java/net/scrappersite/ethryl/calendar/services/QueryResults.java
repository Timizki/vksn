package net.scrappersite.ethryl.calendar.services;

import java.util.Collection;
import java.util.HashSet;


public class QueryResults {
	private Integer maxPagesElements;
	private Integer currentPage;
	private Collection elements;
	
	public Integer getMaxPagesElements() {
		return maxPagesElements;
	}
	
	public void setMaxPagesElements(Integer maxPagesElements) {
		this.maxPagesElements = maxPagesElements;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Collection getElements() {
		if (elements == null) {
			elements = new HashSet();
		}
		return elements;
	}
	
	public void setElements(Collection elements) {
		this.elements = elements;
	}	
}
