package xyz.maksimenko.javaeett.web;

import java.util.Comparator;

import xyz.maksimenko.javaeett.Message;

public class FromComparator implements Comparator<Message> {

	@Override
	public int compare(Message o1, Message o2) {
		return o1.getFromuserlogin().compareToIgnoreCase(o2.getFromuserlogin());
	}

}
