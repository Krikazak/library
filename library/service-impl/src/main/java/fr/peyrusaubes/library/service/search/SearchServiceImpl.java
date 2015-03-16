package fr.peyrusaubes.library.service.search;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.apache.GoogleApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import fr.peyrusaubes.library.common.exception.ExceptionCodes;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.service.search.vo.SearchContentResultVo;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Override
	public List<SearchContentResultVo> searchContent(String isbn) throws LibraryTechnicalException {
		return searchGoogleBooks(isbn);
	}

	private JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	private List<SearchContentResultVo> searchGoogleBooks(String isbn) throws LibraryTechnicalException {

		try {
			final Books books = new Books.Builder(GoogleApacheHttpTransport.newTrustedTransport(), jsonFactory, null).build();

			com.google.api.services.books.Books.Volumes.List volumesList = books.volumes().list("isbn:" + isbn);

			Volumes volumes = volumesList.execute();

			List<SearchContentResultVo> result = new ArrayList<SearchContentResultVo>();

			if (volumes != null && volumes.getItems() != null) {
				for (Volume volume : volumes.getItems()) {
					SearchContentResultVo resultVo = new SearchContentResultVo();
					resultVo.setSearchEngine("google.books");
					resultVo.setData(volume);
					result.add(resultVo);
				}
			}

			return result;
		} catch (IOException e) {
			throw new LibraryTechnicalException(ExceptionCodes.TECH_GENERIC_ERROR, ExceptionCodes.TECH_GENERIC_ERROR_MSG, e, e.getMessage());
		} catch (GeneralSecurityException e) {
			throw new LibraryTechnicalException(ExceptionCodes.TECH_GENERIC_ERROR, ExceptionCodes.TECH_GENERIC_ERROR_MSG, e, e.getMessage());
		}
	}
}
