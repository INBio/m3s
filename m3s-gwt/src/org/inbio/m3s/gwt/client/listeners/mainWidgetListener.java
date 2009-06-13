/**
 * 
 */
package org.inbio.m3s.gwt.client.listeners;

import java.util.EventListener;

import com.google.gwt.user.client.ui.Composite;

/**
 * @author jgutierrez
 * 
 */
public interface mainWidgetListener extends EventListener {

	void closeWidet(Composite source);

}
