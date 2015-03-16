package fr.peyrusaubes.library.service.zone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.peyrusaubes.library.data.zone.ZoneDao;
import fr.peyrusaubes.library.model.zone.Zone;
import fr.peyrusaubes.library.service.common.GenericServiceImpl;

@Service("zoneService")
@Transactional
public class ZoneServiceImpl extends GenericServiceImpl<Zone, Long> implements
		ZoneService {
	
	private ZoneDao zoneDao;

	@Autowired
	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}

	@Override
	protected ZoneDao getManager() {
		return zoneDao;
	}

}
