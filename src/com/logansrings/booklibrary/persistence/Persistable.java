package com.logansrings.booklibrary.persistence;

import java.util.List;

public interface Persistable {

	Long getId();
	void setId(Long id);
	String getTableName();
	String[] getColumnNames();
	Object[] getColumnValues();
	int getColumnCount();
	Persistable newFromDBColumns(List<Object> objectList);
<<<<<<< HEAD
=======

>>>>>>> 808f988503fddcb65c6d1914df21508bf05e8c3e
}
