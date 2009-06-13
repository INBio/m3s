/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.listener;

import com.google.gwt.user.client.EventListener;

/**
 * @author jgutierrez
 * 
 */
public interface MetadataListener extends EventListener {

	/**
	 * Fires when the metadataContainer widget saves the medatata on the DB
	 * successfully
	 * 
	 * @param mediaId
	 * 
	 */
	void metadataSaved(Integer mediaId);

	/**
	 * Fires when the metadataContainer widget cannot save the medatata on the
	 * Data base
	 * 
	 * @param caught
	 * 
	 */
	void errorSavingMetadata(Throwable caught);

}
