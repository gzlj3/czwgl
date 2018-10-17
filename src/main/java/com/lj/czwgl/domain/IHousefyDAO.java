package com.lj.czwgl.domain;

import java.util.Date;
import java.util.List;

/**
 * Interface for HousefyDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IHousefyDAO {
	/**
	 * Perform an initial save of a previously unsaved Housefy entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHousefyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Housefy entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Housefy entity);

	/**
	 * Delete a persistent Housefy entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHousefyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Housefy entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Housefy entity);

	/**
	 * Persist a previously saved Housefy entity and return it or a copy of it
	 * to the sender. A copy of the Housefy entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IHousefyDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Housefy entity to update
	 * @return Housefy the persisted Housefy entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Housefy update(Housefy entity);

	public Housefy findById(String id);

	/**
	 * Find all Housefy entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Housefy property to query
	 * @param value
	 *            the property value to match
	 * @return List<Housefy> found by query
	 */
	public List<Housefy> findByProperty(String propertyName, Object value);

	public List<Housefy> findByHouseid(Object houseid);

	public List<Housefy> findByFwmc(Object fwmc);

	public List<Housefy> findByZhxm(Object zhxm);

	public List<Housefy> findByDscds(Object dscds);

	public List<Housefy> findByDbcds(Object dbcds);

	public List<Housefy> findByDgtds(Object dgtds);

	public List<Housefy> findByDdj(Object ddj);

	public List<Housefy> findBySscds(Object sscds);

	public List<Housefy> findBySbcds(Object sbcds);

	public List<Housefy> findBySgtds(Object sgtds);

	public List<Housefy> findBySdj(Object sdj);

	public List<Housefy> findByWlf(Object wlf);

	public List<Housefy> findByGlf(Object glf);

	public List<Housefy> findByLjf(Object ljf);

	public List<Housefy> findByQtf(Object qtf);

	public List<Housefy> findByCzje(Object czje);

	public List<Housefy> findBySfsz(Object sfsz);

	public List<Housefy> findByBz(Object bz);

	/**
	 * Find all Housefy entities.
	 * 
	 * @return List<Housefy> all Housefy entities
	 */
	public List<Housefy> findAll();
}