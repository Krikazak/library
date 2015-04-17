package fr.peyrusaubes.library.web.controller.zone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.model.zone.Zone;
import fr.peyrusaubes.library.service.zone.ZoneService;
import fr.peyrusaubes.library.web.controller.common.ParentController;

@RestController
@RequestMapping("/zone")
public class ZoneController extends ParentController {
	
	private ZoneService zoneService;

	@Autowired
	public void setZoneService(ZoneService zoneService) {
		this.zoneService = zoneService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Zone> findAll(@RequestParam(required=false) String include) {
		return zoneService.findAll(include);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Zone create(@RequestBody Zone zone, @RequestParam(required=false) String include) throws LibraryTechnicalException {
		return zoneService.create(zone, include);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Zone update(@RequestBody Zone zone, @RequestParam(required=false) String include) throws LibraryTechnicalException {
		return zoneService.update(zone, include);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Zone findById(@PathVariable Long id, @RequestParam(required=false) String include) {
		return zoneService.findById(id, include);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws LibraryTechnicalException {
		zoneService.delete(id);
	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public List<Zone> findByParent(@RequestParam(required=false) Long parentId, @RequestParam(required=false) String include) {
		return zoneService.findByParent(parentId, include);
	}
}
