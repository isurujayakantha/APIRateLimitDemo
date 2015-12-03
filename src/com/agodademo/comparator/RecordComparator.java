package com.agodademo.comparator;

import java.util.Comparator;

import com.agodademo.model.Record;

public class RecordComparator implements Comparator<Record> { 

	@Override
	public int compare(Record arg0, Record arg1) { 
		return arg0.getCity().compareTo(arg1.getCity());
	}
}