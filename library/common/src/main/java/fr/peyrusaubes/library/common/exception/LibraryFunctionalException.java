package fr.peyrusaubes.library.common.exception;

/**
 * Parent class for functional exceptions of the application.
 * @author jcpeyrusaubes
 *
 */
public class LibraryFunctionalException extends LibraryException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7235498668755162834L;


	/**
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 */
	public LibraryFunctionalException(String code) {
		super(code);
	}
	
	/**
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 */
	public LibraryFunctionalException(String code, String message) {
		super(code, message);
	}
	
	/**
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 * @param throwable Encapsulated exception
	 */
	public LibraryFunctionalException(String code, String message, Throwable throwable) {
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
	public LibraryFunctionalException(String code, String message, Throwable throwable, Object ...parameters) {
		super(code, message, throwable, parameters);
	}
	
	/**
	 * 
	 * Constructor.
	 * @param code exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param message exception message
	 * @param parameters parameters for the message
	 */
	public LibraryFunctionalException(String code, String message, Object ...parameters) {
		super(code, message, parameters);
	}
}
