package fr.peyrusaubes.library.web.controller.common;

import java.util.Collection;

public class ExceptionVO {
		private String code;
		private String message;
		private Collection<?> parameters;

		public ExceptionVO(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public ExceptionVO(String code, String message, Collection<?> parameters) {
			this(code, message);
			this.parameters = parameters;
		}
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Collection<?> getParameters() {
			return parameters;
		}
		public void setParameters(Collection<?> parameters) {
			this.parameters = parameters;
		}

		
	}