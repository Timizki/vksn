package projectmanagement.queries;

import java.util.Date;

public class ProjectQuery extends Query {
	private Integer stateMedian;
	private Date startDate;
	private Date endDate;	


	public Integer getStateMedian() {
		return stateMedian;
	}

	public void setStateMedian(Integer stateMedian) {
		this.stateMedian = stateMedian;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
