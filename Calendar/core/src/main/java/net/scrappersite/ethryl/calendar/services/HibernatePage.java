package net.scrappersite.ethryl.calendar.services;

/**
 * Orginal author Pietro Polsinelli ppolsinelli@open-lab.com
 * Modified by Timo Paananen 03-2008 paananen.t@gmail.com 
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;

public class HibernatePage {

	  protected Query query;
		
	  protected List elements;
	  protected int pageSize;
	  protected int pageNumber;
	  protected int totalElements = -1;

	  private ScrollableResults scrollableResults;
	  public static final int DEFAULT_PAGE_SIZE = 10 ;
	  private static final Logger log = Logger.getLogger(HibernatePage.class);
	  
	  public HibernatePage(int pageNumber, int pageSize) {
	    this.pageNumber = pageNumber;
	    this.pageSize = pageSize;
	  }

	  public boolean isFirstPage() {
	    return getPageNumber() == 0;
	  }

	  public boolean isLastPage() {
	    return getPageNumber() >= getLastPageNumber();
	  }

	  public boolean hasNextPage() {
	    return !isLastPage();
	  }

	  public boolean hasPreviousPage() {
	    return getPageNumber() > 0;
	  }

	  public int getLastPageNumber() {

	    double totalResults = new Integer(getTotalNumberOfElements()).doubleValue();
	    return new Double(Math.floor(totalResults / getPageSize())).intValue();
	  }

	  public List getThisPageElements() {
	    return elements;
	  }

	  public int getTotalNumberOfElements() {
	    return totalElements;
	  }

	  public int getThisPageFirstElementNumber() {
	    return getPageNumber() * getPageSize() + 1;
	  }

	  public int getThisPageLastElementNumber() {
	    int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
	    return getTotalNumberOfElements() < fullPage ?
	            getTotalNumberOfElements() :
	            fullPage;
	  }

	  public int getNextPageNumber() {
	    return getPageNumber() + 1;
	  }

	  public int getPreviousPageNumber() {
	    return getPageNumber() - 1;
	  }

	  public int getPageSize() {
	    return pageSize;
	  }

	  public int getPageNumber() {
	    return pageNumber;
	  }

	  /**
	   * Construct a new HibernatePage. HibernatePage numbers are zero-based so the
	   * first page is page 0.
	   *
	   * @param query      the Hibernate Query
	   * @param pageNumber the page number (zero-based);
	   *                   if Integer.MAX_VALUE will return the last page for the query
	   * @param pageSize   the number of results to display on the page
	   */
	  protected static HibernatePage getScrollPageInstanceWithTotalByScroll(Query query, int pageNumber, int pageSize) {

	    HibernatePage sp = new HibernatePage(pageNumber, pageSize);
	    sp.query = query;
	    try {
	      sp.scrollableResults.last();
	      sp.totalElements = sp.scrollableResults.getRowNumber();

	      sp.fixThisPageElements();
	      sp.scrollableResults.close();
	    } catch (HibernateException e) {
	      log.error("Failed to create ScrollPage by getScrollPageInstanceWithTotalByScroll: " + e.getMessage());
	      throw new RuntimeException(e);
	    }

	    return sp;
	  }


	  /**
	   * Construct a new HibernatePage. HibernatePage numbers are zero-based so the
	   * first page is page 0.
	   *
	   * @param query      the Hibernate Query
	   * @param pageNumber the page number (zero-based);
	   *                   if Integer.MAX_VALUE will return the last page for the query
	   * @param pageSize   the number of results to display on the page
	   */
	  protected static HibernatePage getScrollPageInstanceWithTotalByList(Query query, int pageNumber, int pageSize) {

	    HibernatePage sp = new HibernatePage(pageNumber, pageSize);
	    sp.query = query;
	    try {

	      sp.totalElements = sp.calculateTotalElementsByList();
	      sp.fixThisPageElements();

	    } catch (HibernateException e) {
	      log.error("Failed to create ScrollPage by getScrollPageInstanceWithTotalByQuery: " + e.getMessage());
	      throw new RuntimeException(e);
	    }

	    return sp;
	  }

	  private void fixThisPageElements() throws HibernateException {

	    if (this.pageSize<=0)
	      this.pageSize=HibernatePage.DEFAULT_PAGE_SIZE;

	    if (Integer.MAX_VALUE == this.pageNumber)
	      this.pageNumber = (getTotalNumberOfElements() / this.pageSize);
	    else if (pageNumber>(totalElements/pageSize))
	      pageNumber = totalElements/pageSize;
	    query = query.setFirstResult(this.pageNumber * this.pageSize);
	    query = query.setMaxResults(this.pageSize);
	    elements = query.list();
	  }

	  private int calculateTotalElementsByList() throws HibernateException {

	    return query.list().size()-1;

	  }
	}