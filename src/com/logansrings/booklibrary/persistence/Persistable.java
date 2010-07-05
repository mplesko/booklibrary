package com.logansrings.booklibrary.persistence;

public interface Persistable {

	String getTableName();
	String[] getColumnNames();
	Object[] getColumnValues();
	int getColumnCount();
}
