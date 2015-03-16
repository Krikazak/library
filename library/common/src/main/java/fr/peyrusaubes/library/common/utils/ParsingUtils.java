package fr.peyrusaubes.library.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.peyrusaubes.library.common.exception.ExceptionManager;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;

public class ParsingUtils {

	private ObjectMapper objectMapper;
	
	private ParsingUtils() {
		objectMapper = new ObjectMapper();
	}
	
	private static ParsingUtils instance;
	
	public static synchronized ParsingUtils getInstance() {
		if (instance == null) {
			instance = new ParsingUtils();
		}
		
		return instance;
	}
	
	public String objectToJsonString(Object o) throws LibraryTechnicalException {
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			ExceptionManager.manageException(e);
		}
		return null;
	}
	
	public <T>T jsonStringToObject(String s, Class<T> type) throws LibraryTechnicalException {
		try {
			return objectMapper.readValue(s, type);
		} catch (IOException e) {
			ExceptionManager.manageException(e);
		}
		return null;
	}
	
	public <T>T jsonStringToObject(byte[] a, Class<T> type) throws LibraryTechnicalException {
		try {
			return objectMapper.readValue(a, type);
		} catch (IOException e) {
			ExceptionManager.manageException(e);
		}
		return null;
	}
}
