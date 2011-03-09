package com.logansrings.booklibrary.persistence;

import java.util.List;

public interface PersistenceDelegate {

	boolean exists(Persistable persistable);
	
	boolean persist(Persistable persistable);
	
	boolean delete(Persistable persistable);
	
	/**
	 * @param persistable
	 * @return the persistable that matches the persistable argument's values 
	 * or null if not found.
	 */
<<<<<<< HEAD
	Persistable findOne(Persistable persistable);

	/**
	 * @param persistable
	 * @return a list of persistables that match the persistable argument's values
	 * or an empty list if none found.
=======
//	List<Object> findOne(Persistable persistable);

	/**
	 * @param persistable
	 * @return a persistable that's a match for the persistable argument 
	 * or null if not found.
	 */
	Persistable findOne(Persistable persistable);
	
	/**
	 * @param persistable
	 * @return a list of objects that are the column values of any peristable
	 * matching the persistable's values as filters, or an empty list if none found.
>>>>>>> 808f988503fddcb65c6d1914df21508bf05e8c3e
	 */
	List<Persistable> findAny(Persistable persistable);
	
	/**
	 * @param persistable
	 * @return all the object in the persistable argument's table
	 */
	List<Persistable> findAll(Persistable persistable);

}
