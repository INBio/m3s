package org.inbio.m3s.model.atta;


import java.util.Date;

import org.inbio.m3s.model.general.Site;

public class INBioSite extends Site {

	private static final long serialVersionUID = 1L;
	

	public INBioSite() {
	}
	
	/**
	 * @param siteId
	 * @param description
	 * @param longitude
	 * @param latitude
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param lambertX
	 * @param lambertY
	 * @param lambertProjection
	 * @param siteCalculationMethodId
	 * @param crtmEast
	 * @param crtmNorth
	 * @param baseProjectionId
	 */
	public INBioSite(Integer siteId, String description, String longitude,
			String latitude, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy, Integer lambertX,
			Integer lambertY, String lambertProjection,
			Integer siteCalculationMethodId, Integer crtmEast, Integer crtmNorth,
			Integer baseProjectionId) {
		super(siteId, description, longitude, latitude, creationDate, createdBy,
				lastModificationDate, lastModificationBy, lambertX, lambertY,
				lambertProjection, siteCalculationMethodId, crtmEast, crtmNorth,
				baseProjectionId);
	}


	/**
	 * @param siteId
	 * @param longitude
	 * @param latitude
	 */
	public INBioSite(Integer siteId, String longitude, String latitude) {
		super(siteId, longitude, latitude);
	}

}
