package fr.peyrusaubes.library.common.exception;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class handly exception management
 * @author jcpeyrusaubes
 * 
 */
public final class ExceptionManager {
	protected static final Logger logger = LoggerFactory.getLogger(ExceptionManager.class);
	
	private ExceptionManager() {
		super();
	}
	
    /**
     * Handle exception in parameter and wrap it in a <code>ArtboxTechnicalException</code> if it's not an applicative exception.
     * @param e {@link Exception} to handle
     * @return Wrapped or handled exception
     */
    public static LibraryException manageException(Exception e) {
        if (e instanceof LibraryException) {
        	return manageException((LibraryException) e);
        } else {
			logger.error(e.getMessage(), e);
        	return new LibraryException(ExceptionCodes.TECH_GENERIC_ERROR, MessageFormat.format(ExceptionCodes.TECH_GENERIC_ERROR_MSG, e.getMessage()), e);
        }
    }

    /**
     * To specific treatments for applicative exceptions
     * @param e {@link LibraryException} to handle
     * @return managed exception
     */
    private static LibraryException manageException(LibraryException e) {

		logger.error(e.getMessage(), e);
        return e;
    }

}
