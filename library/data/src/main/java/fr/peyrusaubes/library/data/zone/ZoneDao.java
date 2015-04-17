package fr.peyrusaubes.library.data.zone;

import java.util.List;

import fr.peyrusaubes.library.data.common.GenericDao;
import fr.peyrusaubes.library.model.zone.Zone;

/**
 * Dao handling {@link Zone}.
 * @author jcpeyrusaubes
 *
 */
public interface ZoneDao extends GenericDao<Zone, Long> {
	
	List<Zone> findByParent(Long parentId);
}
