package fr.peyrusaubes.library.data.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.peyrusaubes.library.common.exception.ExceptionCodes;
import fr.peyrusaubes.library.common.exception.LibraryException;
import fr.peyrusaubes.library.common.exception.LibraryFunctionalException;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.data.common.GenericDao;
import fr.peyrusaubes.library.data.utils.DBUtils;
import fr.peyrusaubes.library.model.common.PersistentEntity;

/**
 * Implémentation générique d'un dao.
 * 
 * @author jcpeyrusaubes
 * 
 * @param <T>
 *            persistent entity
 */
public abstract class GenericDaoImpl<T extends PersistentEntity<K>, K>
		implements GenericDao<T, K> {
	private static Logger logger = LoggerFactory
			.getLogger(GenericDaoImpl.class);

	private Class<T> entityBeanType;

	/**
	 * Constructor.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.entityBeanType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Return the manager of the entity.
	 * 
	 * @return Entity manager
	 */
	public abstract EntityManager getEntityManager();

	/**
	 * Return the class of the managed entity.
	 * 
	 * @return Class
	 */
	public Class<T> getEntityBeanType() {
		return entityBeanType;
	}

	/**
	 * Find all instances of the entity sorted accorded to the parameter.
	 * 
	 * @param orderByAttribute
	 *            name of the sort attribute
	 * @return Sorted list
	 */
	@Override
	public List<T> findAll(String orderByAttribute) {
		StringBuffer sb = new StringBuffer();
		sb.append("select i from ").append(entityBeanType.getSimpleName())
				.append(" i");
		if (orderByAttribute != null && orderByAttribute.trim().length() > 0) {
			sb.append(" order by i.").append(orderByAttribute);
		}
		return castResultList(getEntityManager().createQuery(sb.toString())
				.getResultList());
	}

	/**
	 * Find all instances of the entity.
	 * 
	 * @return Sorted list
	 */
	@Override
	public List<T> findAll() {
		return castResultList(getEntityManager().createQuery(
				"select i from " + entityBeanType.getSimpleName() + " i")
				.getResultList());
	}

	/**
	 * Find the entity identified by its id.
	 * 
	 * @param id
	 *            of the entity
	 * @return found entity, null if none found
	 */
	@Override
	public T findById(K id) {
		return getEntityManager().find(entityBeanType, id);
	}

	/**
	 * Returns a reference (proxy) of the identity identified by its id.
	 * 
	 * @param id
	 *            of the entity
	 * @return proxy of the entity. Using the proxy may cause EntityNotFound
	 *         exceptions if no entity exists for the id.
	 */
	@Override
	public T getReference(K id) {
		return getEntityManager().getReference(entityBeanType, id);
	}

	/**
	 * Delete the entity.
	 * 
	 * @param t
	 *            entity to delete
	 */
	@Override
	public void delete(T t) {
		t = getEntityManager().merge(t);
		getEntityManager().remove(t);
	}

	@Override
	public void delete(K id) {
		T t = findById(id);
		getEntityManager().remove(t);
	}

	@Override
	public void persist(T t) {
		getEntityManager().persist(t);
	}

	@Override
	public void merge(T t) {
		getEntityManager().merge(t);
	}

	/**
	 * Flush pending database operations.
	 * 
	 */
	@Override
	public void flush() {
		getEntityManager().flush();
	}

	/**
	 * Commit the current transaction.
	 * 
	 */
	@Override
	public void commitTransaction() {
		getEntityManager().getTransaction().commit();
	}

	/**
	 * Refresh the entity.
	 * 
	 * @see EntityManager.refresh(T t)
	 * @param t
	 *            entity to refresh
	 */
	@Override
	public void refresh(T t) {
		getEntityManager().refresh(t);
	}

	/**
	 * Utility method for queries returning one or no results.
	 * 
	 * @param query
	 * @return unique result or null
	 * @throws ECTechnicalException
	 *             exception
	 */
	protected T findUniqueOrNull(Query query) {
		@SuppressWarnings("unchecked")
		List<T> result = query.getResultList();
		if (result.size() != 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Log exception and encapsulate it in an ECTechnicalException.
	 * 
	 * @param e
	 *            exception to handle
	 * @throws ECTechnicalException
	 */
	protected void handleException(Exception e)
			throws LibraryTechnicalException {
		logger.error(e.getMessage(), e);
		throw new LibraryTechnicalException(ExceptionCodes.TECH_DATA_ERROR,
				MessageFormat.format(ExceptionCodes.TECH_DATA_ERROR_MSG,
						e.getMessage()), e);
	}

	/**
	 * Suppress unchecked.warnings.
	 * 
	 * @param list
	 *            list to be cast
	 * @return casted list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<T> castResultList(List list) {
		return list;
	}

	@Override
	public DataSource getDatasource() throws LibraryTechnicalException {
		return DBUtils.getDatasource(getEntityManager());
	}

	@Override
	public List<T> findByIds(List<K> ids) {
		if (ids == null || ids.size() == 0) {
			return new ArrayList<T>();
		}

		StringBuilder queryString = new StringBuilder();
		queryString.append("select i from ");
		queryString.append(entityBeanType.getSimpleName());
		queryString.append(" i where i.id in (");
		boolean firstId = true;
		for (K id : ids) {
			if (firstId) {
				firstId = false;
			} else {
				queryString.append(',');
			}
			queryString.append(id);
		}
		queryString.append(")");
		return castResultList(getEntityManager().createQuery(
				queryString.toString()).getResultList());
	}

	/**
	 * Return the max of ids for the entity.
	 * 
	 * @return Max of ids
	 */
	@SuppressWarnings("unchecked")
	@Override
	public K getLastId() {
		StringBuffer sb = new StringBuffer();
		sb.append("select max(i.id) from ")
				.append(entityBeanType.getSimpleName()).append(" i");

		return (K) getEntityManager().createQuery(sb.toString())
				.getSingleResult();
	}

	@Override
	public void applyChanges(K id, Map<String, Object> changes)
			throws LibraryException {
		T original = findById(id);

		if (original == null) {
			throw new LibraryFunctionalException(
					ExceptionCodes.FUNC_OBJECT_NOT_FOUND,
					ExceptionCodes.FUNC_OBJECT_NOT_FOUND_MSG,
					getEntityBeanType().getName(), id);
		}

		changes = new HashMap<String, Object>(changes);
		applyChangesToAssociations(original, changes);

		for (String propertyName : changes.keySet()) {
			try {
				// WARNING ne PAS utiliser BeanUtils.setProperty(original,
				// propertyName, changes.get(propertyName));
				PropertyUtils.setProperty(original, propertyName,
						changes.get(propertyName));
			} catch (IllegalAccessException e) {
				handleException(e);
			} catch (InvocationTargetException e) {
				handleException(e);
			} catch (NoSuchMethodException e) {
				handleException(e);
			}
		}
	}

	/**
	 * Pré-traitement sur les associations de l'objet original.<br/>
	 * Cette méthode permet de remplacer les entités présentent dans la map
	 * changes par la version chargée en utilisant hibernate afin d'éviter les
	 * exceptions de rattachement à la session.
	 * 
	 * @param original
	 *            Objet oiginal à modifier (il s'agit de la version chargée avec
	 *            hibernate)
	 * @param changes
	 *            Map des changements provenant du client flex (objet non
	 *            associés à la session hibernate).
	 */
	protected void applyChangesToAssociations(T original,
			Map<String, Object> changes) {

	}

}
