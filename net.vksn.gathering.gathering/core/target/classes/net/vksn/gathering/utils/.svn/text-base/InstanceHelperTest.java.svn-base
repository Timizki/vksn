package net.vksn.gathering.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.vksn.gathering.model.Event;
import net.vksn.gathering.model.Instance;

import org.junit.Test;

public class InstanceHelperTest {
	@Test
	public void testGetOneInstance() {
		GregorianCalendar originalCalendar = new GregorianCalendar();
		originalCalendar.set(2009, 11, 9);
		Date begin = originalCalendar.getTime();
		originalCalendar.set(2009, 11, 13);
		Date end = originalCalendar.getTime();
		List<Date> dates = InstanceHelper.getInstanceDates(begin, end,
				IntervalEnum.ONCE);
		assertTrue("Expected only one date but there was " + dates.size(),
				dates.size() == 1);
		GregorianCalendar comparisionCalendar = new GregorianCalendar();
		originalCalendar.setTime(begin);
		comparisionCalendar.setTime(dates.get(0));
		assertEquals(originalCalendar.getTime(), comparisionCalendar.getTime());
	}

	@Test
	public void testGetDailyInstances() {

		GregorianCalendar originalCalendar = new GregorianCalendar();
		originalCalendar.set(2009, 11, 9);
		Date begin = originalCalendar.getTime();
		originalCalendar.set(2009, 11, 13);
		Date end = originalCalendar.getTime();
		List<Date> dates = InstanceHelper.getInstanceDates(begin, end,
				IntervalEnum.DAILY);
		assertTrue(dates.size() == 5);
		assertTrue(begin.equals(dates.get(0)));
		GregorianCalendar comparisionCalendar = new GregorianCalendar();
		originalCalendar.setTime(begin);
		for (int i = 1; i < dates.size(); i++) {
			comparisionCalendar.setTime(dates.get(i));
			originalCalendar.roll(Calendar.DAY_OF_MONTH, true);
			assertEquals("Expect: ", originalCalendar.getTime(),
					comparisionCalendar.getTime());
			assertEquals(originalCalendar.get(Calendar.DAY_OF_WEEK),
					comparisionCalendar.get(Calendar.DAY_OF_WEEK));
			assertEquals(originalCalendar.get(Calendar.MONTH),
					comparisionCalendar.get(Calendar.MONTH));
			assertEquals(originalCalendar.get(Calendar.YEAR),
					comparisionCalendar.get(Calendar.YEAR));
			if (dates.get(i).after(end)) {
				fail("Date list contains date which is after event end date");
			}
		}
	}

	@Test
	public void testGetWeeklyInstances() {
		GregorianCalendar originalCalendar = new GregorianCalendar();
		originalCalendar.set(2009, 11, 9);
		Date begin = originalCalendar.getTime();
		originalCalendar.set(2010, 0, 4);
		Date end = originalCalendar.getTime();
		List<Date> dates = InstanceHelper.getInstanceDates(begin, end,
				IntervalEnum.WEEKLY);
		assertTrue(begin.equals(dates.get(0)));
		GregorianCalendar comparisionCalendar = new GregorianCalendar();
		originalCalendar.setTime(begin);
		for (int i = 1; i < dates.size(); i++) {
			comparisionCalendar.setTime(dates.get(i));
			originalCalendar.add(Calendar.DAY_OF_MONTH, 7);
			assertEquals("Expect: ", originalCalendar.getTime(),
					comparisionCalendar.getTime());
			assertEquals(originalCalendar.get(Calendar.DAY_OF_WEEK),
					comparisionCalendar.get(Calendar.DAY_OF_WEEK));
			assertEquals(originalCalendar.get(Calendar.MONTH),
					comparisionCalendar.get(Calendar.MONTH));
			assertEquals(originalCalendar.get(Calendar.YEAR),
					comparisionCalendar.get(Calendar.YEAR));
			if (dates.get(i).after(end)) {
				fail("Date list contains date which is after event end date. Date "
						+ dates.get(i) + " after date " + end);
			}
		}
	}

	@Test
	public void testGetMonthlyInstances() {
		GregorianCalendar originalCalendar = new GregorianCalendar();
		originalCalendar.set(2009, 10, 9);
		Date begin = originalCalendar.getTime();
		originalCalendar.set(2010, 11, 4);
		Date end = originalCalendar.getTime();
		List<Date> dates = InstanceHelper.getInstanceDates(begin, end,
				IntervalEnum.MONTHLY);
		assertTrue(begin.equals(dates.get(0)));
		GregorianCalendar comparisionCalendar = new GregorianCalendar();
		originalCalendar.setTime(begin);
		for (int i = 1; i < dates.size(); i++) {
			comparisionCalendar.setTime(dates.get(i));
			originalCalendar.add(Calendar.DAY_OF_MONTH, 30);
			assertEquals("Expect: ", originalCalendar.getTime(),
					comparisionCalendar.getTime());
			assertEquals(originalCalendar.get(Calendar.DAY_OF_WEEK),
					comparisionCalendar.get(Calendar.DAY_OF_WEEK));
			assertEquals(originalCalendar.get(Calendar.MONTH),
					comparisionCalendar.get(Calendar.MONTH));
			assertEquals(originalCalendar.get(Calendar.YEAR),
					comparisionCalendar.get(Calendar.YEAR));
			if (dates.get(i).after(end)) {
				fail("Date list contains date which is after event end date");
			}
		}
	}

	@Test
	public void testGetOnceInYearInstances() {
		GregorianCalendar originalCalendar = new GregorianCalendar();
		originalCalendar.set(2009, 10, 9);
		Date begin = originalCalendar.getTime();
		originalCalendar.set(2015, 11, 4);
		Date end = originalCalendar.getTime();
		List<Date> dates = InstanceHelper.getInstanceDates(begin, end,
				IntervalEnum.ONCE_IN_YEAR);
		assertTrue(begin.equals(dates.get(0)));
		GregorianCalendar comparisionCalendar = new GregorianCalendar();
		originalCalendar.setTime(begin);
		for (int i = 1; i < dates.size(); i++) {
			comparisionCalendar.setTime(dates.get(i));
			originalCalendar.add(Calendar.DAY_OF_MONTH, 365);
			assertEquals(originalCalendar.getTime(), comparisionCalendar
					.getTime());
			assertEquals(originalCalendar.get(Calendar.DAY_OF_WEEK),
					comparisionCalendar.get(Calendar.DAY_OF_WEEK));
			assertEquals(originalCalendar.get(Calendar.MONTH),
					comparisionCalendar.get(Calendar.MONTH));
			assertEquals(originalCalendar.get(Calendar.YEAR),
					comparisionCalendar.get(Calendar.YEAR));
			if (dates.get(i).after(end)) {
				fail("Date list contains date which is after event end date");
			}
		}
	}

	@Test
	public void testGetNextInstance() {
		Event event = createEvent();
		GregorianCalendar originalCalendar = new GregorianCalendar();
		originalCalendar.setTime(new Date());
		Date begin = originalCalendar.getTime();
		originalCalendar.add(Calendar.YEAR, 1);
		originalCalendar.add(Calendar.MONTH, 1);
		originalCalendar.roll(Calendar.DAY_OF_MONTH, 4);
		Date end = originalCalendar.getTime();
		List<Date> dates = InstanceHelper.getInstanceDates(begin, end,
				IntervalEnum.WEEKLY);
		Collection<Instance> instances = new ArrayList<Instance>();
		for (Date date : dates) {
			Instance instance = new Instance();
			instance.setDate(date);
			instances.add(instance);
		}
		event.setInstances(instances);

		Instance instance = InstanceHelper.getNextInstance(event);
		GregorianCalendar comparisationcalendar = new GregorianCalendar();
		comparisationcalendar.setTime(instance.getDate());
		originalCalendar.setTime(begin);
		originalCalendar.add(Calendar.DAY_OF_MONTH, 7);
		assertEquals(originalCalendar.getTime(), comparisationcalendar
				.getTime());

	}

	@Test
	public void testGetFirstInstance() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2009, 10, 1);
		Date begin = calendar.getTime();
		calendar.clear();
		calendar.set(2009, 11, 1);
		Date end = calendar.getTime();
		Event event = createEventAndPopulateInstancies(begin, end,
				IntervalEnum.WEEKLY);
		Instance instance = InstanceHelper.getFirstInstance(event);
		assertEquals(begin, instance.getDate());

	}

	@Test
	public void testGetLastInstance() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2009, 10, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begin = calendar.getTime();
		calendar.clear();
		calendar.set(2009, 10, 29);
		Date end = calendar.getTime();
		Event event = createEventAndPopulateInstancies(begin, end,
				IntervalEnum.WEEKLY);
		Instance instance = InstanceHelper.getLastInstance(event);
		assertTrue(InstanceHelper.isSameDate(end, instance.getDate()));
	}

	@Test
	public void testGetNextInstanceFromDate() {
		List<Date> dates = new ArrayList<Date>();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2009, 10, 1);
		dates.add(calendar.getTime());
		
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		dates.add(calendar.getTime());
		Date currentDate = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		dates.add(calendar.getTime());
		Date dateToFind = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		dates.add(calendar.getTime());
		
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		dates.add(calendar.getTime());
		
		Event event = createEvent();
		populateEventInstances(event, dates);
		Date instanceDate = InstanceHelper.getNextInstanceFromDate(event, currentDate).getDate();
		assertEquals(dateToFind, instanceDate);
	}
	
	private Event createEvent() {
		Event event = new Event();
		event.setId(1);
		event.setCreated(new Date());
		event.setDescription("Event for testing instance helper");
		event.setName("Test event");
		return event;
	}

	private Event createEventAndPopulateInstancies(Date begin, Date end,
			IntervalEnum interval) {
		Event event = createEvent();
		populateEventsInstances(event, begin, end, interval);
		return event;
	}

	private void populateEventsInstances(Event event, Date begin, Date end,
			IntervalEnum interval) {
		List<Date> dates = InstanceHelper
				.getInstanceDates(begin, end, interval);
		populateEventInstances(event, dates);
	}
	
	private void populateEventInstances(Event event, List<Date> dates) {
		for (Date date : dates) {
			Instance instance = new Instance();
			instance.setDate(date);
			event.getInstances().add(instance);
		}
		
	}
}
