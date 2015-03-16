package fr.peyrusaubes.library.data.zone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.peyrusaubes.library.data.common.GenericDaoImpl;
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


}
