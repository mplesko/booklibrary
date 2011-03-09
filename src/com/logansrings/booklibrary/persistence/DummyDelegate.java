package com.logansrings.booklibrary.persistence;

import java.util.List;

public class DummyDelegate implements PersistenceDelegate {
	public boolean persistReturnValue;
	public Persistable findOneReturnValue;
<<<<<<< HEAD
	public List<Persistable> findAnyReturnValue;
=======
	public List<List<Object>> findAnyReturnValue;
>>>>>>> 808f988503fddcb65c6d1914df21508bf05e8c3e
	public boolean existsReturnValue;

	public void setFindOneReturnValue(Persistable findOneReturnValue) {
		this.findOneReturnValue = findOneReturnValue;
	}

	public boolean persist(Persistable persistable) {
		return persistReturnValue;
	}

	public void setPersistReturnValue(boolean persistReturnValue) {
		this.persistReturnValue = persistReturnValue;
	}

	public Persistable findOne(Persistable persistable) {
		return findOneReturnValue;
	}

	public boolean exists(Persistable persistable) {
		return existsReturnValue;
	}

	public List<Persistable> findAny(Persistable persistable) {
		return findAnyReturnValue;
	}

	@Override
	public boolean delete(Persistable persistable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Persistable> findAll(Persistable persistable) {
		// TODO Auto-generated method stub
		return null;
	}
}
