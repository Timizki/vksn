package net.scrappersite.ethryl.calendar.service.impl;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.scrappersite.ethryl.calendar.DAO.MarkDAO;
import net.scrappersite.ethryl.calendar.model.Mark;
import net.scrappersite.ethryl.calendar.services.HibernatePage;
import net.scrappersite.ethryl.calendar.services.MarkQuery;
import net.scrappersite.ethryl.calendar.services.MarkService;
import net.scrappersite.ethryl.calendar.services.QueryResults;
import net.scrappersite.ethryl.exceptions.NotFoundException;

public class MarkServiceImpl implements MarkService {

	private MarkDAO markDAO;
	
	private void setMarkDAO(MarkDAO markDAO) {
		this.markDAO = markDAO;
	}
	
	public void deleteMark(int id)  throws NotFoundException{
		this.markDAO.deleteMark(id);
	}

	public Mark getMark(int id) throws NotFoundException {
		Mark mark = this.markDAO.getMark(id);
		return mark;
	}

	public QueryResults getMarks(MarkQuery query) {
		QueryResults results = this.markDAO.getMarks(query); 
		return results;
	}

	public void storeMark(Mark mark) {
		this.markDAO.storeMark(mark);
		
	}
	
	public Mark undeleteMark(int id) throws NotFoundException {
		return this.markDAO.undeleteMark(id);
	}
	
	public void removeMark(int id) throws NotFoundException {
		this.markDAO.removeMark(id);
	}

}
