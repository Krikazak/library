package fr.peyrusaubes.library.common.exception;


/**
 * Exception code constants.
 * 
 * @author jcpeyrusaubes
 * 
 */
public final class ExceptionCodes {

	private ExceptionCodes() {

	}

	public static final String TECH_DATA_ERROR = "TECH_DATA_ERROR";
	public static final String TECH_DATA_ERROR_MSG = "Dao generic error : {0}";

	public static final String TECH_GENERIC_ERROR = "TECH_GENERIC_ERROR";
	public static final String TECH_GENERIC_ERROR_MSG = "Technical generic error : {0}";

	public static final String TECH_WRONG_DATE_FORMAT_ERROR = "TECH_WRONG_DATE_FORMAT_ERROR";
	public static final String TECH_WRONG_DATE_FORMAT_ERROR_MSG = "Wrong date format for value : {0}";

	public static final String FUNC_OBJECT_NOT_FOUND = "FUNC_OBJECT_NOT_FOUND";
	public static final String FUNC_OBJECT_NOT_FOUND_MSG = "No object of type {0} found for id {1}";

	public static final String FUNC_OBJECT_NOT_FOUND_FOR_ATTRIBUTE = "FUNC_OBJECT_NOT_FOUND_FOR_ATTRIBUTE";
	public static final String FUNC_OBJECT_NOT_FOUND_FOR_ATTRIBUTE_MSG = "No object of type {0} found for attribute {1} with value {2}";

	public static final String FUNC_LOCALE_NOT_FOUND = "FUNC_LOCALE_NOT_FOUND";
	public static final String FUNC_LOCALE_NOT_FOUND_MSG = "No locale found for value {0}";

	public static final String TECH_NO_SEARCH_MANAGER = "TECH_NO_SEARCH_MANAGER";
	public static final String TECH_NO_SEARCH_MANAGER_MSG = "No search manager defined for {0}";
}
