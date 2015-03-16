package fr.peyrusaubes.library.data.common;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import fr.peyrusaubes.library.common.exception.LibraryException;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.model.common.PersistentEntity;

/**
 * Generic dao interface.
 * 
 * @author jcpeyrusaubes
 * 
 * @param <T>
 *            persistent entity
 * @param <K>
 *            key type
 */
public interface GenericDao<T extends PersistentEntity<K>, K> {

	/**
	 * Find the entity identified by its id.
	 * 
	 * @param id
	 *            of the entity
	 * @return the entity, or <code>null</code> if none found
	 */
	T findById(K id);

	/**
	 * Find all instances of the entity.
	 * 
	 * @return Sorted list
	 */
	List<T> findAll();

	/**
	 * Deletes the entity identified by its id.
	 * 
	 * @param id
	 *            id of entity to delete
	 */
	void delete(K id);

	/**
	 * Deletes the entity.
	 * 
	 * @param t
	 *            entity to delete
	 */
	void delete(T t);

	/**
	 * Return entities identified by one of the ids in the list.
	 * 
	 * @param ids
	 *            Identifier list
	 * @return Entity list
	 */
	List<T> findByIds(List<K> ids);

	/**
	 * Find all instances of the entity sorted accorded to the parameter.
	 * 
	 * @param orderByAttribute
	 *            name of the sort attribute
	 * @return Sorted list
	 */
	List<T> findAll(String orderByAttribute);

	/**
	 * Return the max of ids for the entity.
	 * 
	 * @return Max of ids
	 */
	K getLastId();

	/**
	 * Returns a reference (proxy) of the identity identified by its id.
	 * 
	 * @param id
	 *            of the entity
	 * @return proxy of the entity. Using the proxy may cause EntityNotFound
	 *         exceptions if no entity exists for the id.
	 */
	T getReference(K id);

	/**
	 * Persist the entity.
	 * 
	 * @see EntityManager.persist(T t)
	 * @param t
	 *            entity to persist
	 */
	void persist(T t);

	/**
	 * Merge the entity.
	 * 
	 * @see EntityManager.merge(T t)
	 * @param t
	 *            entity to merge
	 */
	void merge(T t);

	/**
	 * Flush pending database operations.
	 * 
	 */
	void flush();

	/**
	 * Commit the current transaction.
	 * 
	 */
	void commitTransaction();

	/**
	 * Refresh the entity.
	 * 
	 * @see EntityManager.refresh(T t)
	 * @param t
	 *            entity to refresh
	 */
	void refresh(T t);

	/**
	 * Return the datasource associated to the entity manager.
	 * 
	 * @return the datasource
	 * @throws LibraryTechnicalException
	 *             exception
	 */
	public DataSource getDatasource() throws LibraryTechnicalException;

	/**
	 * Updates the entity using the key/values attributes in the map.
	 * @param id Identifier of the entity to updates
	 * @param changes Key/value map of the attributes to modify
	 * @throws LibraryException exception
	 */
	void applyChanges(K id, Map<String, Object> changes)
			throws LibraryException;
	
	/**
	 * Returns the managed entity class
	 * @return Class<T>
	 */
	Class<T> getEntityBeanType();
}
