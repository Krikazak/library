package fr.peyrusaubes.library.service.element;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.peyrusaubes.library.data.common.GenericSearchDao;
import fr.peyrusaubes.library.data.element.ElementDao;
import fr.peyrusaubes.library.data.element.SearchElementDao;
import fr.peyrusaubes.library.model.element.Element;
import fr.peyrusaubes.library.service.common.GenericServiceImpl;

@Service("elementService")
@Transactional
public class ElementServiceImpl extends GenericServiceImpl<Element, Long> implements
		ElementService {
	
	private ElementDao elementDao;
	
	private SearchElementDao searchElementDao;

	@Autowired
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	@Autowired
	public void setSearchElementDao(SearchElementDao searchElementDao) {
		this.searchElementDao = searchElementDao;
	}

	@Override
	protected ElementDao getManager() {
		return elementDao;
	}

	@Override
	protected GenericSearchDao<Element, Long> getSearchManager() {
		return searchElementDao;
	}

}
