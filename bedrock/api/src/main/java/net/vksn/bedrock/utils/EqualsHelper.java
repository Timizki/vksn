package net.vksn.bedrock.utils;

import java.util.Date;

public class EqualsHelper {

	public static boolean areEquals(Date thisDate, Date thatDate) {
		if((thisDate == null && thatDate != null) ||
			(thisDate != null && thatDate == null)) {
			return false;
		}
		if(thisDate != null && thatDate != null) {
			return thisDate.equals(thatDate);
		}
		return true;
	}
	
	public static boolean areEquals(String thisString, String thatString) {
		if((thisString == null && thatString != null) ||
			(thisString != null && thatString == null)) {
			return false;
		}
		if(thisString != null && thatString != null) {
			return thisString.equals(thatString);
		}
		return true;
	}
}
