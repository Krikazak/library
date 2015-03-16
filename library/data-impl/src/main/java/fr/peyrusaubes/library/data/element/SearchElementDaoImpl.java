package fr.peyrusaubes.library.data.element;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.peyrusaubes.library.data.common.GenericSearchDaoImpl;
import fr.peyrusaubes.library.model.element.Element;

@Repository("searchElementDao")
public class SearchElementDaoImpl extends GenericSearchDaoImpl<Element, Long> implements SearchElementDao {

	@Autowired
	private Client client;

	@Override
	public Client getEntityManager() {
		return client;
	}
	
	


}
