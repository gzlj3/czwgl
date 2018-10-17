package com.lj.czwgl.domain;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Housefy entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.lj.czwgl.domain.Housefy
 * @author MyEclipse Persistence Tools
 */
public class HousefyDAO implements IHousefyDAO {
	// property constants
	public static final String HOUSEID = "houseid";
	public static final String FWMC = "fwmc";
	public static final String ZHXM = "zhxm";
	public static final String DSCDS = "dscds";
	public static final String DBCDS = "dbcds";
	public static final String DGTDS = "dgtds";
	public static final String DDJ = "ddj";
	public static final String SSCDS = "sscds";
	public static final String SBCDS = "sbcds";
	public static final String SGTDS = "sgtds";
	public static final String SDJ = "sdj";
	public static final String WLF = "wlf";
	public static final String GLF = "glf";
	public static final String LJF = "ljf";
	public static final String QTF = "qtf";
	public static final String CZJE = "czje";
	public static final String SFSZ = "sfsz";
	public static final String BZ = "bz";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * HousefyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Housefy entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Housefy entity) {
		EntityManagerHelper.log("saving Housefy instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Housefy entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * HousefyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Housefy entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Housefy entity) {
		EntityManagerHelper.log("deleting Housefy instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Housefy.class,
					entity.getHousefyid());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = HousefyDAO.update(entity);
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
	public Housefy update(Housefy entity) {
		EntityManagerHelper.log("updating Housefy instance", Level.INFO, null);
		try {
			Housefy result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Housefy findById(String id) {
		EntityManagerHelper.log("finding Housefy instance with id: " + id,
				Level.INFO, null);
		try {
			Housefy instance = getEntityManager().find(Housefy.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Housefy entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Housefy property to query
	 * @param value
	 *            the property value to match
	 * @return List<Housefy> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Housefy> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Housefy instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Housefy model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Housefy> findByHouseid(Object houseid) {
		return findByProperty(HOUSEID, houseid);
	}

	public List<Housefy> findByFwmc(Object fwmc) {
		return findByProperty(FWMC, fwmc);
	}

	public List<Housefy> findByZhxm(Object zhxm) {
		return findByProperty(ZHXM, zhxm);
	}

	public List<Housefy> findByDscds(Object dscds) {
		return findByProperty(DSCDS, dscds);
	}

	public List<Housefy> findByDbcds(Object dbcds) {
		return findByProperty(DBCDS, dbcds);
	}

	public List<Housefy> findByDgtds(Object dgtds) {
		return findByProperty(DGTDS, dgtds);
	}

	public List<Housefy> findByDdj(Object ddj) {
		return findByProperty(DDJ, ddj);
	}

	public List<Housefy> findBySscds(Object sscds) {
		return findByProperty(SSCDS, sscds);
	}

	public List<Housefy> findBySbcds(Object sbcds) {
		return findByProperty(SBCDS, sbcds);
	}

	public List<Housefy> findBySgtds(Object sgtds) {
		return findByProperty(SGTDS, sgtds);
	}

	public List<Housefy> findBySdj(Object sdj) {
		return findByProperty(SDJ, sdj);
	}

	public List<Housefy> findByWlf(Object wlf) {
		return findByProperty(WLF, wlf);
	}

	public List<Housefy> findByGlf(Object glf) {
		return findByProperty(GLF, glf);
	}

	public List<Housefy> findByLjf(Object ljf) {
		return findByProperty(LJF, ljf);
	}

	public List<Housefy> findByQtf(Object qtf) {
		return findByProperty(QTF, qtf);
	}

	public List<Housefy> findByCzje(Object czje) {
		return findByProperty(CZJE, czje);
	}

	public List<Housefy> findBySfsz(Object sfsz) {
		return findByProperty(SFSZ, sfsz);
	}

	public List<Housefy> findByBz(Object bz) {
		return findByProperty(BZ, bz);
	}

	/**
	 * Find all Housefy entities.
	 * 
	 * @return List<Housefy> all Housefy entities
	 */
	@SuppressWarnings("unchecked")
	public List<Housefy> findAll() {
		EntityManagerHelper.log("finding all Housefy instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Housefy model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}