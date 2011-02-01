package com.logansrings.booklibrary.persistence;

import java.util.List;

public interface Persistable {

	Long getId();
	String getTableName();
	String[] getColumnNames();
	Object[] getColumnValues();
	int getColumnCount();
	Persistable newFromDBColumns(List<Object> objectList);

}
