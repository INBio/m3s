/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a project..
 * 
 * @author jgutierrez
 *
 */
public class ProjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundProject;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundProject
	 */
	public ProjectNotFoundException(String message, Throwable cause, String notFoundProject) {
		super(message, cause);
		this.setNotFoundProject(notFoundProject);
	}

	/**
	 * @param notFoundProject the notFoundProject to set
	 */
	public void setNotFoundProject(String notFoundProject) {
		this.notFoundProject = notFoundProject;
	}

	/**
	 * @return the notFoundProject
	 */
	public String getNotFoundProject() {
		return notFoundProject;
	};
	
}
