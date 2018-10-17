package com.lj.czwgl.domain;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for House
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.lj.czwgl.domain.House
 * @author MyEclipse Persistence Tools
 */
public class HouseDAO implements IHouseDAO {
	// property constants
	public static final String YYHID = "yyhid";
	public static final String FWMC = "fwmc";
	public static final String ZHXM = "zhxm";
	public static final String SFZH = "sfzh";
	public static final String DHHM = "dhhm";
	public static final String CZJE = "czje";
	public static final String YJ = "yj";
	public static final String DQSDS = "dqsds";
	public static final String SQSDS = "sqsds";
	public static final String DFDJ = "dfdj";
	public static final String SFDJ = "sfdj";
	public static final String WLF = "wlf";
	public static final String GLF = "glf";
	public static final String LJF = "ljf";
	public static final String QTF = "qtf";
	public static final String DBCDS = "dbcds";
	public static final String SBCDS = "sbcds";
	public static final String BZ = "bz";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * HouseDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            House entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(House entity) {
		EntityManagerHelper.log("saving House instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent House entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * HouseDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            House entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(House entity) {
		EntityManagerHelper.log("deleting House instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(House.class,
					entity.getHouseid());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = HouseDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            House entity to update
	 * @return House the persisted House entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public House update(House entity) {
		EntityManagerHelper.log("updating House instance", Level.INFO, null);
		try {
			House result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public House findById(String id) {
		EntityManagerHelper.log("finding House instance with id: " + id,
				Level.INFO, null);
		try {
			House instance = getEntityManager().find(House.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<House> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<House> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding House instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from House model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<House> findByYyhid(Object yyhid, int... rowStartIdxAndCount) {
		return findByProperty(YYHID, yyhid, rowStartIdxAndCount);
	}

	public List<House> findByFwmc(Object fwmc, int... rowStartIdxAndCount) {
		return findByProperty(FWMC, fwmc, rowStartIdxAndCount);
	}

	public List<House> findByZhxm(Object zhxm, int... rowStartIdxAndCount) {
		return findByProperty(ZHXM, zhxm, rowStartIdxAndCount);
	}

	public List<House> findBySfzh(Object sfzh, int... rowStartIdxAndCount) {
		return findByProperty(SFZH, sfzh, rowStartIdxAndCount);
	}

	public List<House> findByDhhm(Object dhhm, int... rowStartIdxAndCount) {
		return findByProperty(DHHM, dhhm, rowStartIdxAndCount);
	}

	public List<House> findByCzje(Object czje, int... rowStartIdxAndCount) {
		return findByProperty(CZJE, czje, rowStartIdxAndCount);
	}

	public List<House> findByYj(Object yj, int... rowStartIdxAndCount) {
		return findByProperty(YJ, yj, rowStartIdxAndCount);
	}

	public List<House> findByDqsds(Object dqsds, int... rowStartIdxAndCount) {
		return findByProperty(DQSDS, dqsds, rowStartIdxAndCount);
	}

	public List<House> findBySqsds(Object sqsds, int... rowStartIdxAndCount) {
		return findByProperty(SQSDS, sqsds, rowStartIdxAndCount);
	}

	public List<House> findByDfdj(Object dfdj, int... rowStartIdxAndCount) {
		return findByProperty(DFDJ, dfdj, rowStartIdxAndCount);
	}

	public List<House> findBySfdj(Object sfdj, int... rowStartIdxAndCount) {
		return findByProperty(SFDJ, sfdj, rowStartIdxAndCount);
	}

	public List<House> findByWlf(Object wlf, int... rowStartIdxAndCount) {
		return findByProperty(WLF, wlf, rowStartIdxAndCount);
	}

	public List<House> findByGlf(Object glf, int... rowStartIdxAndCount) {
		return findByProperty(GLF, glf, rowStartIdxAndCount);
	}

	public List<House> findByLjf(Object ljf, int... rowStartIdxAndCount) {
		return findByProperty(LJF, ljf, rowStartIdxAndCount);
	}

	public List<House> findByQtf(Object qtf, int... rowStartIdxAndCount) {
		return findByProperty(QTF, qtf, rowStartIdxAndCount);
	}

	public List<House> findByDbcds(Object dbcds, int... rowStartIdxAndCount) {
		return findByProperty(DBCDS, dbcds, rowStartIdxAndCount);
	}

	public List<House> findBySbcds(Object sbcds, int... rowStartIdxAndCount) {
		return findByProperty(SBCDS, sbcds, rowStartIdxAndCount);
	}

	public List<House> findByBz(Object bz, int... rowStartIdxAndCount) {
		return findByProperty(BZ, bz, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<House> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper
				.log("finding all House instances", Level.INFO, null);
		try {
			final String queryString = "select model from House model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}