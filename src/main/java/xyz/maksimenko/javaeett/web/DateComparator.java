package xyz.maksimenko.javaeett.web;

import java.util.Comparator;

import xyz.maksimenko.javaeett.Message;

public class DateComparator implements Comparator<Message> {

	@Override
	public int compare(Message o1, Message o2) {
		long d1 = o1.getDate(),
				d2 = o2.getDate();
		if(d1 < d2) return 1;
		else if(d1 > d2) return -1;
		return 0;
	}

}
