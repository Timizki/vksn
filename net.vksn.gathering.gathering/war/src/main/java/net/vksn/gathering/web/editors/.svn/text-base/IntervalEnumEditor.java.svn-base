package net.vksn.gathering.web.editors;

import net.vksn.gathering.utils.IntervalEnum;

public class IntervalEnumEditor extends org.springframework.beans.propertyeditors.PropertiesEditor {

	@Override
	public void setAsText(String enumText) throws IllegalArgumentException {
		IntervalEnum result = null;
		for(IntervalEnum interval : IntervalEnum.values()) {
			if(interval.name().equals(enumText)) {
				result = interval;
				break;
			}
		}
		//if correct enum cannot be resolved default is used.
		if(result == null) {
			result = IntervalEnum.WEEKLY;
		}
		setValue(result);
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof IntervalEnum) {
			setAsText(((IntervalEnum)value).name());
		}
		
	}

}
