/**
 * 
 */
package org.inbio.m3s.gwt.client.exception;

import java.io.Serializable;

/**
 * @author jgutierrez
 * 
 */
public class RPCIllegalArgumentException extends Exception implements
		Serializable {

	private String message;

	/** */
	private static final long serialVersionUID = -417716819778167152L;

	/**
	 * 
	 * 
	 */
	public RPCIllegalArgumentException() {
		super();
	}

	/**
	 * 
	 * @param msg
	 */
	public RPCIllegalArgumentException(String msg) {
		super(msg);
		this.message = msg;
	}

	/**
	 * 
	 * @param cause
	 */
	public RPCIllegalArgumentException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param msg
	 * @param cause
	 */
	public RPCIllegalArgumentException(String msg, Throwable cause) {
		super(msg, cause);
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

}
