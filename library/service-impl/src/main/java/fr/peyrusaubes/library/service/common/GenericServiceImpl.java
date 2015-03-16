package fr.peyrusaubes.library.service.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.peyrusaubes.library.common.exception.ExceptionCodes;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.data.common.GenericDao;
import fr.peyrusaubes.library.data.common.GenericSearchDao;
import fr.peyrusaubes.library.model.common.PersistentEntity;
import fr.peyrusaubes.library.service.utils.ClonerBase;

/**
 * 
 * @author jcpeyrusaubes
 * 
 * @param <T>
 */
@Transactional
public abstract class GenericServiceImpl<T extends PersistentEntity<K>, K> implements GenericService<T, K> {
	protected static final Logger logger = LoggerFactory.getLogger(GenericServiceImpl.class);

	protected abstract GenericDao<T, K> getManager();

	protected GenericSearchDao<T, K> getSearchManager() {
		return null;
	}

	public T create(T t, String include) throws LibraryTechnicalException {
		getManager().persist(t);
		
		if (getSearchManager() != null) {
			getSearchManager().index(t);
		}
		return findById(t.getId(), include);
	}

	public void delete(K id) throws LibraryTechnicalException {
		getManager().delete(id);
	}

	public void delete(T t) throws LibraryTechnicalException {
		if (t != null) {
			getManager().delete(t.getId());
		}
	}

	public T update(T t, String include) throws LibraryTechnicalException {
		getManager().merge(t);
		t = getManager().findById(t.getId());
		return new ClonerBase<T, K>().include(include).copy(t);
	}

	public List<T> findAll(String include) {
		List<T> result = getManager().findAll();
		result = new ClonerBase<T, K>().include(include).copy(result);
		return result;
	}

	public List<T> findAll(String orderByAttribute, String include) {
		return new ClonerBase<T, K>().include(include).copy(getManager().findAll(orderByAttribute));
	}

	public T findById(K id, String include) {
		T result = getManager().findById(id);
		return new ClonerBase<T, K>().include(include).copy(result);
	}

	public List<T> findByIds(List<K> ids, String include) {
		return new ClonerBase<T, K>().include(include).copy(getManager().findByIds(ids));
	}

	public List<T> search(String query) throws LibraryTechnicalException {
		if (getSearchManager() == null) {
			throw new LibraryTechnicalException(ExceptionCodes.TECH_NO_SEARCH_MANAGER, ExceptionCodes.TECH_NO_SEARCH_MANAGER_MSG, getManager().getEntityBeanType().getName());
		}
		return getSearchManager().search(query);
	}
}
