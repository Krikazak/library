package fr.peyrusaubes.library.web.controller.common;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.peyrusaubes.library.common.exception.ExceptionCodes;
import fr.peyrusaubes.library.common.exception.LibraryException;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;

public abstract class ParentController {
	protected static final Logger logger = LoggerFactory.getLogger(ParentController.class);
	
	protected User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	protected String getCurrentUserLogin() {
		User result = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (result == null) {
			return null;
		} else {
			return result.getUsername();
		}
	}

	@ExceptionHandler(LibraryException.class)
	@ResponseBody
	public ExceptionVO handleArtboxException(HttpServletResponse response, LibraryException ex) {
		ExceptionVO result = new ExceptionVO(ex.getCode(), ex.getMessage(), ex.getParameters());

		response.setStatus(ex.getHttpStatus());
		
		return result;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ExceptionVO handleAllException(HttpServletResponse response, Exception ex) {
		logger.error(ex.getMessage(), ex);
		
		LibraryTechnicalException ate = new LibraryTechnicalException(ExceptionCodes.TECH_GENERIC_ERROR, ExceptionCodes.TECH_GENERIC_ERROR_MSG, ex.getMessage());

		return handleArtboxException(response, ate);

	}
}
