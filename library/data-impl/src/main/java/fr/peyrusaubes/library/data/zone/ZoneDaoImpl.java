package fr.peyrusaubes.library.data.zone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.peyrusaubes.library.data.common.GenericDaoImpl;
import fr.peyrusaubes.library.data.common.PersistenceContants;
import fr.peyrusaubes.library.model.zone.Zone;

@Repository("zoneDao")
public class ZoneDaoImpl extends GenericDaoImpl<Zone, Long> implements ZoneDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Zone> findByParent(Long parentId) {
		Query query = getEntityManager().createNamedQuery(PersistenceContants.QUERY_NAME_ZONE_FIND_BY_PARENT);
		query.setParameter("parentId", parentId);
		
		return castResultList(query.getResultList());
	}


}
