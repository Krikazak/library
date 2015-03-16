package fr.peyrusaubes.library.common.exception;


/**
 * Parent class for technical exceptions of the application.
 * @author jcpeyrusaubes
 *
 */
public class LibraryTechnicalException extends LibraryException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1823453144484416342L;

	/**
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 */
	public LibraryTechnicalException(String code) {
		super(code);
	}
	
	/**
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 */
	public LibraryTechnicalException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 * @param throwable Encapsulated exception
	 */
	public LibraryTechnicalException(String code, String message, Throwable throwable) {
		super(code, message, throwable);
	}
	
	/**
	 * 
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 * @param throwable Encapsulated exception
	 * @param parameters parameters for the message
	 */
	public LibraryTechnicalException(String code, String message, Throwable throwable, Object... parameters) {
		super(code, message, throwable, parameters);
	}
	
	/**
	 * 
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 * @param parameters parameters for the message
	 */
	public LibraryTechnicalException(String code, String message, Object... parameters) {
		super(code, message, parameters);
	}
}
