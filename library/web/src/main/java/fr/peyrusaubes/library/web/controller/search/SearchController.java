package fr.peyrusaubes.library.web.controller.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.service.search.SearchService;
import fr.peyrusaubes.library.service.search.vo.SearchContentResultVo;
import fr.peyrusaubes.library.web.controller.common.ParentController;


@RestController
@RequestMapping("/search")
public class SearchController extends ParentController {
	
	private SearchService searchService;

	@Autowired
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<SearchContentResultVo> findAll(@RequestParam String isbn) throws LibraryTechnicalException {
		return searchService.searchContent(isbn);
	}
}
