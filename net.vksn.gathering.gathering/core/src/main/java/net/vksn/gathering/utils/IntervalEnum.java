package net.vksn.gathering.utils;

public enum IntervalEnum {
	ONCE(0),
	DAILY(1),
	WEEKLY(7),
	MONTHLY(30),
	ONCE_IN_YEAR(365);
	private int interval;
	IntervalEnum(int interval) {
		this.interval = interval;
	}
	
	public int getValueOfInterval() {
		return this.interval;
	}
}
