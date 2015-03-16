package fr.peyrusaubes.library.data.element;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.peyrusaubes.library.data.common.GenericDaoImpl;
import fr.peyrusaubes.library.model.element.Element;

@Repository("elementDao")
public class ElementDaoImpl extends GenericDaoImpl<Element, Long> implements ElementDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


}
