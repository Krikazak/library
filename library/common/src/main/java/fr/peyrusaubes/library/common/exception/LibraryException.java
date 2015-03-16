package fr.peyrusaubes.library.common.exception;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

/**
 * Parent exception for the application.
 * @author jcpeyrusaubes
 *
 */
public class LibraryException extends Exception {
	
	/**
	 * HTTP Status for a server error.
	 */
	private static final int HTTP_STATUS_SERVER_ERROR = 500;

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7164483886221062606L;
	
	/**
	 * Code of the exception.
	 */
	private String code;
	
	/**
	 * Parameters of the exception.
	 */
	private Collection<?> parameters;
	
	/**
	 * Default http status.
	 */
	private int httpStatus = HTTP_STATUS_SERVER_ERROR;

	/**
	 * Constructor.
	 * @param pCode exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 */
	public LibraryException(final String pCode) {
		super();
		this.code = pCode;	
	}
	
	/**
	 * Constructor.
	 * @param pCode exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param pMessage exception message
	 */
	public LibraryException(final String pCode, final String pMessage) {
		super(pMessage);
		this.code = pCode;	
	}
	
	/**
	 * Constructor.
	 * @param pCode exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param pMessage exception message
	 * @param throwable Encapsulated exception
	 */
	public LibraryException(final String pCode, final String pMessage, final Throwable throwable) {
		super(pMessage, throwable);
		this.code = pCode;	
	}
	
	/**
	 * 
	 * Constructor.
	 * @param pCode exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param pMessage exception message
	 * @param throwable Encapsulated exception
	 * @param pParameters parameters for the message
	 */
	public LibraryException(final String pCode, final String pMessage, final Throwable throwable, final Object... pParameters) {
		super(formatMessage(pMessage, pParameters), throwable);
		this.code = pCode;	
		this.parameters = Arrays.asList(pParameters);
	}
	
	/**
	 * 
	 * Constructor.
	 * @param pCode exception code. Should be defined in the <code>ExceptionCodes</code> constant class
	 * @param pMessage exception message
	 * @param pParameters parameters for the message
	 */
	public LibraryException(final String pCode, final String pMessage, final Object... pParameters) {
		super(formatMessage(pMessage, pParameters));
		this.code = pCode;	
		this.parameters = Arrays.asList(pParameters);
	}

	/**
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the parameters
	 */
	public Collection<?> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Collection<?> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Format the message with the parameters.
	 * @param message Message with tokens
	 * @param parameters Parameters to replace in message
	 * @return Formatted message
	 */
	private static String formatMessage(String message, Object... parameters) {
		if (parameters == null || parameters.length == 0) {
			return message;
		}
		return MessageFormat.format(message, parameters);
	}

	/**
	 * @return the httpStatus
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
}
