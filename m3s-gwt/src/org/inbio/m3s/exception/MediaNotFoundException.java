/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * Thrown by the media Dispatcher indicating that the media doesn't existis, or
 * it' private not found.
 * 
 * @author jgutierrez
 * @deprecated
 */
public class MediaNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 */
	public MediaNotFoundException() {
		super();
	};

	/**
	 * Constructs a new Media not found exception with the specified detail
	 * message.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the Throwable.getMessage() method.
	 */
	public MediaNotFoundException(String message) {
		super(message);
	};

	/**
	 * Constructs a new media not found exception with the specified detail
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
	public MediaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	};

	/**
	 * Constructs a new media not found exception with the specified cause and a
	 * detail message of (cause==null ? null : cause.toString()) (which
	 * typically contains the class and detail message of cause).
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            Throwable.getCause() method). (A null value is permitted, and
	 *            indicates that the cause is nonexistent or unknown.)
	 */
	public MediaNotFoundException(Throwable cause) {
		super(cause);
	};

}
