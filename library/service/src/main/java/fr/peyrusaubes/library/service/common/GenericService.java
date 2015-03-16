package fr.peyrusaubes.library.service.common;

import java.util.List;

import fr.peyrusaubes.library.common.exception.LibraryException;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.model.common.PersistentEntity;


/**
 * Generic interface for business services.
 * @author jcpeyrusaubes
 *
 * @param <T>
 */
public interface GenericService<T extends PersistentEntity<K>, K> {
	
	/**
	 * Creates the entity in parameter.
	 * @param t Entity to persist.
	 * @param include Entity attributes to complete
	 * @return Created entity.
	 * @throws LibraryException exception
	 */
	T create(T t, String include) throws LibraryTechnicalException;
	
	/**
	 * Updates the entity in parameter.
	 * @param t Entity to update.
	 * @param include Entity attributes to complete
	 * @return Updated entity.
	 * @throws LibraryException exception
	 */
	T update(T t, String include) throws LibraryTechnicalException;
	
	/**
	 * Deletes the entity for the id.
	 * @param id Unique identifier.
	 * @throws LibraryException exception
	 */
	void delete(K id) throws LibraryTechnicalException;
	
	/**
	 * Deletes the entity.
	 * @param t Entity to delete.
	 * @throws LibraryException exception
	 */
	void delete(T t) throws LibraryTechnicalException;
	
	/**
	 * Returns the entity for the id, completing the attributes mentionned in the include parameter.<br/>
	 * @see ClonerBase
	 * @param id Unique id
	 * @param include Entity attributes to complete
	 * @return Found Entity, or null if none found
	 * @throws LibraryException exception
	 */
	T findById(K id, String include);
	
	/**
	 * Retourne l'ensemble des entités.
	 * @return Liste des entités
	 * @param include Attribut entités à compléter
	 * @throws LibraryException exception
	 */
	List<T> findAll(String include);
	
	/**
	 * Return all entities.
	 * @return List of entities
	 * @param orderByAttribute Attribute used for sort order
	 * @param include Entity attributes to complete
	 * @throws LibraryException exception
	 */
	List<T> findAll(String orderByAttribute, String include);
	
	/**
	 * Return all entities for the ids.
	 * @return List of entities
	 * @param ids ids of entities to retrieve
	 * @param include Entity attributes to complete
	 * @throws LibraryException exception
	 */
	List<T> findByIds(List<K> ids, String include);

	/**
	 * Search entities using the query
	 * @param query
	 * @return List of entities
	 * @throws LibraryTechnicalException exception
	 */
	List<T> search(String query) throws LibraryTechnicalException;
}
