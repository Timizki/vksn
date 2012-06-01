package net.kicksass.vksn.taglib;
/*
CalendarTag.java -- a calendar tag for Java Server Pages
written by Boyd Waters, bwaters@nrao.edu

	#-----------------------------------------------------------------------------
	#   Copyright (C) 2007
	#   Associated Universities, Inc. Washington DC, USA.
	#
	#   This program is free software; you can redistribute it and/or modify
	#   it under the terms of the GNU General Public License as published by
	#   the Free Software Foundation; either version 2 of the License, or
	#   (at your option) any later version.
	#
	#   This program is distributed in the hope that it will be useful,
	#   but WITHOUT ANY WARRANTY; without even the implied warranty of
	#   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	#   GNU General Public License for more details.
	#
	#   You should have received a copy of the GNU General Public License
	#   along with this program; if not, write to the Free Software
	#   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
	#
	#   Correspondence concerning AIPS++ should be addressed as follows:
	#          Internet email: aips2-request@nrao.edu.
	#          Postal address: AIPS++ Project Office
	#                          National Radio Astronomy Observatory
	#                          520 Edgemont Road
	#                          Charlottesville, VA 22903-2475 USA
	#
	#-----------------------------------------------------------------------------

*/

/* calendarTag - Decompiled by JODE
* Visit http://jode.sourceforge.net/
*/
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTag {
 private static String[] monthNames;
 private static String[] dayNames;
 
 public static void main(String[] args) {
	long start = new Date().getTime();
	try {
	    GregorianCalendar rightNow
		= (GregorianCalendar) Calendar.getInstance();
	    int mon = rightNow.get(2);
	    int day = rightNow.get(5);
	    int year = rightNow.get(1);
	    String monthName = monthNames[mon];
	    rightNow.set(5, 1);
	    int dow = rightNow.get(7) + 1;
	    System.out.println("<p>");
	    System.out.println("<pre class=\"calendar\">");
	    System.out.println("<span class=\"calMonth\">" + monthName + " "
			       + year + "</span>");
	    System.out.print("<span class=\"calDays\">");
	    for (int d = 1; d < 8; d++)
		System.out.print(" " + dayNames[d]);
	    System.out.println("</span>");
	    int width = dayNames[1].length();
	    int lineLength = 8 * width;
	    int linePosition = (dow - 1) * width;
	    if (dow > 0) {
		System.out.print("<span class=\"calPrev\">");
		rightNow.set(2, mon - 1);
		int maxDays = rightNow.getActualMaximum(5) + 1;
		for (int d = maxDays - dow + 2; d < maxDays; d++) {
		    System.out.print(" " + d + " ");
		    if (width > 3)
			System.out.print(' ');
		}
		System.out.print("</span>");
		rightNow.set(2, mon);
	    }
	    int maxDays = rightNow.getActualMaximum(5) + 1;
	    for (int d = 1; d < maxDays; d++) {
		if (d < 10)
		    System.out.print(' ');
		if (d == day)
		    System.out
			.print("<span class=\"calNow\"> " + d + " </span>");
		else
		    System.out.print(" " + d + " ");
		if (width > 3)
		    System.out.print(' ');
		linePosition += width + 1;
		if (linePosition > lineLength) {
		    linePosition = 0;
		    System.out.println();
		}
	    }
	    if (linePosition > 0 && linePosition < lineLength) {
		System.out.print("<span class=\"calNext\">");
		int d = 1;
		while (linePosition < lineLength) {
		    System.out.print("  " + d + " ");
		    if (width > 3)
			System.out.print(' ');
		    linePosition += width + 1;
		    d++;
		}
		System.out.print("</span>");
	    }
	    if (linePosition > 0)
		System.out.println();
	    System.out.println("</pre>\n</p>");
	    System.out.println("Computation took "
			       + (new Date().getTime() - start)
			       + " milliseconds.");
	} catch (ClassCastException eek) {
	    eek.printStackTrace();
	}
 }
 
 static {
	DateFormatSymbols dfs = new DateFormatSymbols();
	monthNames = dfs.getMonths();
	dayNames = dfs.getShortWeekdays();
 }
}