package fr.peyrusaubes.library.service.utils;

import java.util.Collection;
import java.util.List;

import fr.peyrusaubes.library.model.common.PersistentEntity;


/**
 * Cloner interface for entities.
 * @author jcpeyrusaubes
 *
 * @param <E>
 */
public interface Cloner<E extends PersistentEntity<K>, K> {
	
	/**
	 * Create a copy (new instance) of the original object.
	 * @param original Original entity
	 * @return a copy of the original object
	 */
	E copy(E original);
	
	/**
	 * Copy a collection of originals.
	 * @param originals Collection of originals
	 * @return List of copy
	 */
	List<E> copy(Collection<? extends E> originals);
}
