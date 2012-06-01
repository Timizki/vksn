package net.vksn.gathering.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import net.vksn.gathering.model.Event;
import net.vksn.gathering.model.Instance;

import org.apache.log4j.Logger;

public class InstanceHelper {
	private static final Logger log = Logger.getLogger(InstanceHelper.class);

	public static Instance getNextInstance(Event event) {
		List<Instance> instances = (List<Instance>) event.getInstances();
		Date today = new Date();
		Instance nextInstance = null;
		for (Iterator<Instance> i = instances.iterator(); i.hasNext();) {
			Instance instance = (Instance) i.next();
			if (instance.getDate().after(today)) {
				nextInstance = instance;
				break;
			}
		}
		return nextInstance;
	}

	public static List<Date> getInstanceDates(Date beginDate, Date endDate,
			IntervalEnum interval) {
		log.info("Resolving dates for timeframe " + beginDate +" - "+endDate+ " with interval " + interval.name());
		Date rollingDate = beginDate;
		List<Date> dates = new ArrayList<Date>();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(beginDate);
		dates.add(beginDate);
		
		if (!IntervalEnum.ONCE.equals(interval)) {
			while (endDate.after(rollingDate)) {
				calendar.add(Calendar.DAY_OF_MONTH, interval
						.getValueOfInterval());
				rollingDate = calendar.getTime();
				if (rollingDate.before(endDate) || isSameDate(rollingDate, endDate)) {
					log.debug("Resolved for instance date " + rollingDate);
					dates.add((Date) rollingDate.clone());
				} else {
					log
							.debug("Rest of dates does not fit to interval breaking on "
									+ rollingDate
									+ " and end date is "
									+ endDate);
					break;
				}
			}
		}
		return dates;
	}

	public static boolean isSameDate(Date d1, Date d2) {
		GregorianCalendar c1 = new GregorianCalendar();
		GregorianCalendar c2 = new GregorianCalendar();
		c1.setTime(d1);
		c2.setTime(d2);
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
			return false;
	}

	public static Instance getFirstInstance(Event event) {
		List<Instance> instances = (List<Instance>) event.getInstances();
		Instance first = null;
		for (Instance instance : instances) {
			if (first == null || instance.getDate().before(first.getDate())) {
				first = instance;
			}

		}
		return first;
	}

	public static Instance getLastInstance(Event event) {
		List<Instance> instances = (List<Instance>) event.getInstances();
		Instance last = null;
		for (Instance instance : instances) {
			if (last == null || instance.getDate().after(last.getDate())) {
				last = instance;
			}

		}
		return last;
	}
	
	public static Instance getNextInstanceFromDate(Event event, Date date) {
		List<Instance> instances = (List<Instance>)event.getInstances();
		Instance result = getLastInstance(event);
		Instance temp = null;
		for(Instance instance : instances) {
			if(instance.getDate().after(date)) {
				temp = instance;
			}
			if(temp != null && temp.getDate().before(result.getDate())) {
				result = instance;
			}			
		}
		return result;
	}
}
