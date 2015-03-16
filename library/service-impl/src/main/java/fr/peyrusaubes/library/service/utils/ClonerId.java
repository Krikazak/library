package fr.peyrusaubes.library.service.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.peyrusaubes.library.model.common.PersistentEntity;


/**
 * Base implementation of the <code>Cloner</code> interface.
 * @author jcpeyrusaubes
 *
 * @param <E>
 */
public class ClonerId<E extends PersistentEntity<K>, K> implements Cloner<E, K> {
	private static Logger log = LoggerFactory.getLogger(ClonerBase.class);


	/**
	 * Create a copy of the original object.<br/>
	 * Copy only the id of original object.
	 * @param original Original entity
	 * @return a copy according to the contract
	 */
	@SuppressWarnings("unchecked")
	public E copy(E original) {
		E copy = null;
		if (original == null) {
			return null;
		}
		
		try {
			copy = (E) original.getClass().newInstance();
			
			copy.setId(original.getId());
			copy.setOnlyId(true);
			
			return copy;
		} catch (InstantiationException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			log.error(e.getMessage(), e);
		}
		return copy;
	}

	/**
	 * Copy a collection of originals using the copy(E original) method on each element.
	 * @param originals Original collection
	 * @return List of copy
	 */
	public List<E> copy(Collection<? extends E> originals) {
		if (originals == null) {
			return null;
		}
		List<E> copys = new ArrayList<E>();
		for (E original : originals) {
			copys.add(this.copy(original));
		}
		return copys;
	}
}
