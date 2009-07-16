package org.inbio.m3s.model.ara;

import java.util.Date;

import org.inbio.m3s.model.general.Site;


/**
 * 
 * @author jgutierrez
 *
 */
public class ARASite extends Site {
	
	/** */
	private static final long serialVersionUID = 1L;
	
	private Integer precision;
	
	/**
	 * 
	 */
	public ARASite() {
		super();
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
		public ARASite(Integer siteId, String description, String longitude,
			String latitude, Date creationDate, String createdBy,
			Date lastModificationDate, String lastModificationBy, Integer lambertX,
			Integer lambertY, String lambertProjection,
			Integer siteCalculationMethodId, Integer crtmEast, Integer crtmNorth,
			Integer baseProjectionId,Integer precision) {
		super(siteId, description, longitude, latitude, creationDate, createdBy,
				lastModificationDate, lastModificationBy, lambertX, lambertY,
				lambertProjection, siteCalculationMethodId, crtmEast, crtmNorth,
				baseProjectionId);
		this.setPrecision(precision);
	}

	/**
	 * @param siteId
	 * @param longitude
	 * @param latitude
	 */
	public ARASite(Integer siteId, String longitude, String latitude) {
		super(siteId, longitude, latitude);
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	/**
	 * @return the precision
	 */
	public Integer getPrecision() {
		return precision;
	}

}
