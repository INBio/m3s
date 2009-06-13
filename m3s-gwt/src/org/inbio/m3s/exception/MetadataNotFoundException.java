package org.inbio.m3s.exception;

/**
 * 
 * @author james
 * 
 * Thrown by the implementation clases of metadata readers to indicate that that
 * the attributes given to a method was ok, but the desired metadata was not
 * found.
 * @deprecated
 * 
 */
public class MetadataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846519637630756907L;

	/**
	 * Constructs a new metadata not found exception with null as its detail
	 * message.
	 * 
	 */
	public MetadataNotFoundException() {
		super();
	};

	/**
	 * Constructs a new metadata not found exception with the specified detail
	 * message.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the Throwable.getMessage() method.
	 */
	public MetadataNotFoundException(String message) {
		super(message);
	};

	/**
	 * Constructs a new metadata not found exception with the specified detail
	 * message and cause.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the Throwable.getMessage() method.
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            Throwable.getCause() method). (A null value is permitted, and
	 *            indicates that the cause is nonexistent or unknown.)
	 */
	public MetadataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	};

	/**
	 * Constructs a new metadata not found exception with the specified cause
	 * and a detail message of (cause==null ? null : cause.toString()) (which
	 * typically contains the class and detail message of cause).
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            Throwable.getCause() method). (A null value is permitted, and
	 *            indicates that the cause is nonexistent or unknown.)
	 */
	public MetadataNotFoundException(Throwable cause) {
		super(cause);
	};

}
