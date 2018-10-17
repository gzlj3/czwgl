package com.lj.czwgl.domain;

import java.util.Date;
import java.util.List;

/**
 * Interface for HouseDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IHouseDAO {
	/**
	 * Perform an initial save of a previously unsaved House entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHouseDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            House entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(House entity);

	/**
	 * Delete a persistent House entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHouseDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            House entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(House entity);

	/**
	 * Persist a previously saved House entity and return it or a copy of it to
	 * the sender. A copy of the House entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IHouseDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            House entity to update
	 * @return House the persisted House entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public House update(House entity);

	public House findById(String id);

	/**
	 * Find all House entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the House property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<House> found by query
	 */
	public List<House> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<House> findByYyhid(Object yyhid, int... rowStartIdxAndCount);

	public List<House> findByFwmc(Object fwmc, int... rowStartIdxAndCount);

	public List<House> findByZhxm(Object zhxm, int... rowStartIdxAndCount);

	public List<House> findBySfzh(Object sfzh, int... rowStartIdxAndCount);

	public List<House> findByDhhm(Object dhhm, int... rowStartIdxAndCount);

	public List<House> findByCzje(Object czje, int... rowStartIdxAndCount);

	public List<House> findByYj(Object yj, int... rowStartIdxAndCount);

	public List<House> findByDqsds(Object dqsds, int... rowStartIdxAndCount);

	public List<House> findBySqsds(Object sqsds, int... rowStartIdxAndCount);

	public List<House> findByDfdj(Object dfdj, int... rowStartIdxAndCount);

	public List<House> findBySfdj(Object sfdj, int... rowStartIdxAndCount);

	public List<House> findByWlf(Object wlf, int... rowStartIdxAndCount);

	public List<House> findByGlf(Object glf, int... rowStartIdxAndCount);

	public List<House> findByLjf(Object ljf, int... rowStartIdxAndCount);

	public List<House> findByQtf(Object qtf, int... rowStartIdxAndCount);

	public List<House> findByDbcds(Object dbcds, int... rowStartIdxAndCount);

	public List<House> findBySbcds(Object sbcds, int... rowStartIdxAndCount);

	public List<House> findByBz(Object bz, int... rowStartIdxAndCount);

	/**
	 * Find all House entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<House> all House entities
	 */
	public List<House> findAll(int... rowStartIdxAndCount);
}