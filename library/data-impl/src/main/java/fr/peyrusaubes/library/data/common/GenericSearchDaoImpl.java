package fr.peyrusaubes.library.data.common;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.common.utils.ParsingUtils;
import fr.peyrusaubes.library.model.common.PersistentEntity;

/**
 * Implémentation générique d'un dao de recherche.
 * 
 * @author jcpeyrusaubes
 * 
 * @param <T>
 *            persistent entity
 */
public abstract class GenericSearchDaoImpl<T extends PersistentEntity<K>, K> implements GenericSearchDao<T, K> {

	private Class<T> entityBeanType;

	/**
	 * Constructor.
	 */
	@SuppressWarnings("unchecked")
	public GenericSearchDaoImpl() {
		this.entityBeanType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Return the elastic search client.
	 * 
	 * @return Client
	 */
	public abstract Client getEntityManager();

	/**
	 * Return the class of the managed entity.
	 * 
	 * @return Class
	 */
	public Class<T> getEntityBeanType() {
		return entityBeanType;
	}

	protected String getIndexName() {
		return entityBeanType.getSimpleName().toLowerCase();
	}

	@Override
	public void index(T t) throws LibraryTechnicalException {
		// Sérialise en JSON
		String source = ParsingUtils.getInstance().objectToJsonString(t);

		// Prépare l'indexation
		IndexRequestBuilder indexRequest = getEntityManager().prepareIndex(getIndexName(), "simple").setId(t.getId().toString()).setSource(source);

		// Exécute l'indexation
		indexRequest.setRefresh(true).execute().actionGet();
	}

	@Override
	public List<T> search(String query) throws LibraryTechnicalException {
		// Prépare la recherche
		SearchRequestBuilder searchRequest = getEntityManager().prepareSearch(getIndexName()).setTypes("simple").setQuery(QueryBuilders.simpleQueryString(query));

		// Execute la requête
		SearchResponse searchResponse = searchRequest.execute().actionGet();

		// Extrait les résultats
		SearchHits searchHits = searchResponse.getHits();
		List<T> results = new ArrayList<T>();
		for (SearchHit searchHit : searchHits) {
			T t;
			t = ParsingUtils.getInstance().jsonStringToObject(searchHit.source(), getEntityBeanType());
			results.add(t);
		}
		return results;
	}

}
