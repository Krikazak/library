package fr.peyrusaubes.library.web.controller.element;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.model.element.Element;
import fr.peyrusaubes.library.service.element.ElementService;
import fr.peyrusaubes.library.web.controller.common.ParentController;

@RestController
@RequestMapping("/element")
public class ElementController extends ParentController {
	
	private ElementService elementService;

	@Autowired
	public void setElementService(ElementService elementService) {
		this.elementService = elementService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Element> search(@RequestParam(value="q", required=false) String query, @RequestParam(defaultValue="") String include) throws LibraryTechnicalException {
		if (query == null) {
			return elementService.findAll(include);
		}
		return elementService.search(query, include);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Element create(@RequestBody Element element, @RequestParam(required=false) String include) throws LibraryTechnicalException {
		return elementService.create(element, include);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Element update(@RequestBody Element element, @RequestParam(required=false) String include) throws LibraryTechnicalException {
		return elementService.update(element, include);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Element findById(@PathVariable Long id, @RequestParam(required=false) String include) {
		return elementService.findById(id, include);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws LibraryTechnicalException {
		elementService.delete(id);
	}
}
