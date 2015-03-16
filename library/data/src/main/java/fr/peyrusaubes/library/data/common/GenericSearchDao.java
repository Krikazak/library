package fr.peyrusaubes.library.data.common;

import java.util.List;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.model.common.PersistentEntity;

/**
 * Generic search dao interface.
 * 
 * @author jcpeyrusaubes
 * 
 * @param <T>
 *            persistent entity
 * @param <K>
 *            key type
 */
public interface GenericSearchDao<T extends PersistentEntity<K>, K> {

	void index(T t) throws LibraryTechnicalException;
	
	List<T> search(String query) throws LibraryTechnicalException;
}
