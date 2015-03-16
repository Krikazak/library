package fr.peyrusaubes.library.service.search;

import java.util.List;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.service.search.vo.SearchContentResultVo;

public interface SearchService {

	List<SearchContentResultVo> searchContent(String isbn) throws LibraryTechnicalException;
}
