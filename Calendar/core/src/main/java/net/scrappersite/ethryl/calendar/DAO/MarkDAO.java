package net.scrappersite.ethryl.calendar.DAO;

import net.scrappersite.ethryl.calendar.model.Mark;
import net.scrappersite.ethryl.calendar.services.MarkQuery;
import net.scrappersite.ethryl.calendar.services.QueryResults;
import net.scrappersite.ethryl.exceptions.NotFoundException;

public interface MarkDAO {
	
	/**
	 * Returns all marks
	 * 
	 * @param query
	 * @return
	 */
	QueryResults getMarks(MarkQuery query);
	
	/**
	 * Returns the <code>Mark</code> with given id
	 * 
	 * @param the <code>Mark</code>s id which to get. 
	 * @return the <code>Mark</code> with given id
	 */
	Mark getMark(int id) throws NotFoundException;
	
	
	/**
	 * Stores the given <code>Mark</code>
	 * 
	 * @param mark the <code>Mark</code> which to store
	 * @return the <code>Mark</code> which was stored
	 */
	void storeMark(Mark mark);
	
	/**
	 * Removes the <code>Mark</code> which has given id
	 * 
	 * @param mark the <code>Mark</code> id which will be deleted 
	 * @return mark the <code>Mark</code> which has been deleted
	 */
	void deleteMark(int id) throws NotFoundException;
	
	Mark undeleteMark(int id) throws NotFoundException;
	
	void removeMark(int id) throws NotFoundException;
}
