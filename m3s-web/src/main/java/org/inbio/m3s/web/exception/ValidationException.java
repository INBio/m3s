/**
 * 
 */
package org.inbio.m3s.web.exception;

import java.util.Map;

/**
 * This exception will be used for the data validation in all controllers.
 * 
 * @author jgutierrez
 *
 */
public class ValidationException extends RuntimeException {

	/** The view name that will present the adequate error message*/
	String viewName;
	/** The model elements needed by the view that will be rendered (if required)*/
	Map<String,Object> modelElements;
	/** The message to display*/
	String errorMessageKey;
	
	/** */
	private static final long serialVersionUID = 1L;

  /**
   * Constructs an <code>ValidationException</code> with no 
   * detail message. 
   */
  public ValidationException() {
  	super();
  }

  /**
   * Constructs an <code>ValidationException</code> with the 
   * specified detail message. 
   *
   * @param   s   the detail message.
   */
  public ValidationException(String s) {
  	super(s);
  }

  /**
   * Constructs a new exception with the specified detail message and
   * cause.
   *
   * <p>Note that the detail message associated with <code>cause</code> is
   * <i>not</i> automatically incorporated in this exception's detail
   * message.
   *
   * @param  message the detail message (which is saved for later retrieval
   *         by the {@link Throwable#getMessage()} method).
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value
   *         is permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   * @since 1.5
   */
  public ValidationException(String message, Throwable cause) {
      super(message, cause);
  }

  /**
   * Constructs a new exception with the specified cause and a detail
   * message of <tt>(cause==null ? null : cause.toString())</tt> (which
   * typically contains the class and detail message of <tt>cause</tt>).
   * This constructor is useful for exceptions that are little more than
   * wrappers for other throwables (for example, {@link
   * java.security.PrivilegedActionException}).
   *
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   * @since  1.5
   */
  public ValidationException(Throwable cause) {
      super(cause);
  }

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @return the modelElements
	 */
	public Map<String, Object> getModelElements() {
		return modelElements;
	}

	/**
	 * @param modelElements the modelElements to set
	 */
	public void setModelElements(Map<String, Object> modelElements) {
		this.modelElements = modelElements;
	}

	/**
	 * @return the errorMessageKey
	 */
	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	/**
	 * @param errorMessageKey the errorMessageKey to set
	 */
	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}
	

}
