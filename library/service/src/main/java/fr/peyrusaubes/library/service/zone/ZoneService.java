package fr.peyrusaubes.library.service.zone;

import java.util.List;

import fr.peyrusaubes.library.model.zone.Zone;
import fr.peyrusaubes.library.service.common.GenericService;

public interface ZoneService extends GenericService<Zone, Long> {

	List<Zone> findByParent(Long parentId, String entities);
}
