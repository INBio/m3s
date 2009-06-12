/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 * 
 */
public class MediaDAOImpl extends BaseDAOImpl implements MediaDAO {

	private static Logger logger = Logger.getLogger(MediaDAOImpl.class);

		/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.core.impl.MediaDAO#getLastPublicMedia(int)
	 */
	@SuppressWarnings("unchecked")
	public List<MediaLite> getLastPublicMedia(final int quantity)
			throws IllegalArgumentException {
		logger.debug("getting last public media in the DB");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaType.mediaTypeId, m.usePolicy.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic, m.isBackup,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m" + " where m.isPublic = 'Y'"
						+ " order by m.mediaId desc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				query.setFirstResult(0);
				query.setMaxResults(quantity);
				return query.list();
			}
		});
	}

	public MediaLite getMediaLite(final Integer mediaId)
			throws IllegalArgumentException {
		logger.debug("getMediaLite with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (MediaLite) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaType.mediaTypeId, m.usePolicy.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic, m.isBackup,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m" + " where m.mediaId = " + mediaId + " ");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<MediaLite> getMediaLiteForTaxonId(final Integer taxonId) throws IllegalArgumentException {
		logger.debug("getMediaLiteForTaxonId with param [" + taxonId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<MediaLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MediaLite(m.mediaId,"
						+ " m.mediaType.mediaTypeId, m.usePolicy.usePolicyId, m.authorPersonId,"
						+ " m.ownerPersonId, m.ownerInstitutionId, m.description, m.siteId,"
						+ " m.siteDescription, m.title, m.series, m.isPublic, m.isBackup,"
						+ " m.creationDate, m.createdBy, m.lastModificationDate, m.lastModificationBy)"
						+ " from Media as m, TaxonMedia as tm" 
						+ " where tm.id.mediaId = m.mediaId"
						+	" and tm.id.taxonId = "+ taxonId
						+ " and m.isPublic = 'Y'"
						+ " order by m.mediaId desc");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	public GeneralMetadataDTO getGeneralMetadataDTO(final Integer mediaId) {
		logger.debug("getGeneralMetadataDTO with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (GeneralMetadataDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.GeneralMetadataDTO(" +
						"m.title, m.description, m.mediaType.mediaTypeId, m.siteId, "
						+ "m.siteDescription)"
						+ " from Media as m"
						+ " where m.mediaId = "	+ mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	public UsesAndCopyrightsDTO getUsesAndCopyrightsDTO(final Integer mediaId) {
		logger.debug("UsesAndCopyrightsDTO with param [" + mediaId + "]");
		HibernateTemplate template = getHibernateTemplate();
		return (UsesAndCopyrightsDTO) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.UsesAndCopyrightsDTO(" +
						" m.authorPersonId, m.ownerPersonId, m.ownerInstitutionId, "
						+ "m.usePolicy.usePolicyId, m.isBackup, m.isPublic)"
						+ " from Media as m"
						+ " where m.mediaId = "	+ mediaId);
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * esto es mas de manager
	 */
	/*
	public void addProjectsList(Integer mediaId, List<ProjectLite> projectLiteList)
			throws IllegalArgumentException {
		logger.debug("addProjectsList, params: projectList.size["
				+ projectLiteList.size() + "], mediaId[" + mediaId + "].");

		int successful = 0; // for local count of errors

		Session m3sSession = HibernateUtil.openM3SDBSession();
		Transaction m3sTx = m3sSession.beginTransaction();

		Media theMedia = null;
		Project theProject = null;
		MediaProjectId theMediaProjectId = null;
		MediaProject theMediaProject = null;
		int actualIndex = 0;

		try {
			theMedia = (Media) m3sSession.load(Media.class, mediaId);

			for (ProjectLite pl : projectLiteList) {

				theProject = (Project) m3sSession
						.load(Project.class, pl.getProjectId());

				theMediaProjectId = new MediaProjectId(mediaId, (Integer) theProject
						.getProjectId());

				theMediaProject = new MediaProject(theMediaProjectId, theMedia,
						theProject);

				m3sSession.save(theMediaProject);
				m3sSession.flush();
			}

			m3sSession.flush();
			m3sTx.commit();
			successful++;

		} catch (HibernateException he) {
			HibernateUtil.rollback(m3sTx);
			logger.error("Couldn't set the media Project DBId '"
					+ projectLiteList.get(actualIndex).getProjectId()
					+ "' to the media with id '" + mediaId + "'.");
		} catch (NullPointerException npe) {
			HibernateUtil.rollback(m3sTx);
			logger.error(npe.getMessage());
			npe.printStackTrace();
		} finally {
			HibernateUtil.closeSession(m3sSession);
		}

		logger.debug("SetMediaProjects finish with " + successful
				+ " successful results.");
	}
	*/

	/**
	 * manager
	 */
	/*
	public void addKeywordsList(Integer mediaId, List<KeywordDTOLite> keywordsList)
			throws IllegalArgumentException {
		logger.debug("addKeywordsList, params: keywordsList.size["
				+ keywordsList.size() + "], mediaId[" + mediaId + "].");

		int successful = 0; // for local count of errors

		Session m3sSession;
		Transaction m3sTx;

		// Taxon theTaxon;
		MediaKeywordId theMediaKeywordId;
		MediaKeyword theMediaKeyword;

		// sets the taxons of the MEDIA HibernateUtil.closeSession(attaSession);
		for (KeywordDTOLite tl : keywordsList) {
			logger.debug("Asociandolo con el keywordId=" + tl.getKeywordId());
			m3sSession = null;
			m3sTx = null;
			theMediaKeyword = null;
			theMediaKeywordId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				theMediaKeywordId = new MediaKeywordId(mediaId, tl.getKeywordId());

				// sets the Taxon to the media
				theMediaKeyword = new MediaKeyword();
				theMediaKeyword.setId(theMediaKeywordId);

				// saves changes and closes session
				m3sSession.save(theMediaKeyword);
				m3sTx.commit();

				successful = successful + 1;
				logger.info("The keyword id '" + tl.getKeywordId()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't set the keyword id '" + tl.getKeywordId()
						+ "' to the media with id '" + mediaId + "'.");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.info("SetMediaKeywords finish with " + successful
				+ " successful results.");
	}
	*/

	/**
	 * manager
	 */
	/*
	public void deleteKeywords(Integer mediaId, List<KeywordDTOLite> keywordsList)
			throws IllegalArgumentException {
		logger.debug("deleteKeywords, params: keywordsList.size["
				+ keywordsList.size() + "], mediaId[" + mediaId + "].");

		int successful = 0; // for local count of errors

		Session m3sSession;
		Transaction m3sTx;

		// Taxon theTaxon;
		MediaKeywordId theMediaKeywordId;
		MediaKeyword theMediaKeyword;

		for (KeywordDTOLite kl : keywordsList) {
			logger.debug("deleting associated keywords...  deleting keyword:"
					+ kl.getKeywordId());
			// empty variables
			m3sSession = null;
			m3sTx = null;
			// theTaxon = null;
			theMediaKeyword = null;
			theMediaKeywordId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				theMediaKeywordId = new MediaKeywordId(mediaId, kl.getKeywordId());

				// sets the keywords to the media
				theMediaKeyword = new MediaKeyword();
				theMediaKeyword.setId(theMediaKeywordId);

				// saves changes and closes session
				m3sSession.delete(theMediaKeyword);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;
				logger.debug("deleting associated keywords... The keyword id '"
						+ kl.getKeywordId() + "' is now deleted from media '" + mediaId
						+ "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger
						.error("deleting associated keywords... Couldn't delete the taxon id '"
								+ kl.getKeywordId()
								+ "' to the media with id '"
								+ mediaId
								+ "'.");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error("deleting associated keywords... " + npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.debug("deleting associated keywords... delete finish finish with "
				+ successful + " successful results.");
		// return successful;
	}
*/

	/**
	 * manager
	 */
	/*
	public void addMediaUses(Integer mediaId, List<MediaUseLite> mediaUsesList)
			throws IllegalArgumentException {
		logger.debug("setting " + mediaUsesList.size() + " media uses for media:"
				+ mediaId + ".");

		int successful = 0; // for local count of errors

		Session m3sSession = HibernateUtil.openM3SDBSession();
		Transaction m3sTx = m3sSession.beginTransaction();

		Media theMedia = null;
		MediaUse theMediaUse = null;
		MediaUseMedia theMediaUseMedia = null;
		MediaUseMediaId theMediaUseMediaId = null;

		try {
			theMedia = (Media) m3sSession.load(Media.class, mediaId);

			for (MediaUseLite mul : mediaUsesList) {

				theMediaUse = (MediaUse) m3sSession.load(MediaUse.class, mul
						.getMediaUseId());

				theMediaUseMediaId = new MediaUseMediaId(mediaId, (Integer) theMediaUse
						.getMediaUseId());

				theMediaUseMedia = new MediaUseMedia(theMediaUseMediaId, theMedia,
						theMediaUse, 'N');
				// theMediaUseMedia.setId(theMediaUseMediaId);
				// theMediaUseMedia.setMedia(theMedia);
				// theMediaUseMedia.setMediaUse(theMediaUse);
				// theMediaUseMedia.setApproved('N');

				m3sSession.save(theMediaUseMedia);
				m3sSession.flush();

			} // for

			m3sSession.flush();
			m3sTx.commit();
			successful++;

		} catch (HibernateException he) {
			HibernateUtil.rollback(m3sTx);
			logger.error("Couldn't set the mediaUse id '"
					+ (Integer) theMediaUse.getMediaUseId() + "' to the media with id '"
					+ mediaId + "'.");
		} catch (NullPointerException npe) {
			HibernateUtil.rollback(m3sTx);
			logger.error(npe.getMessage());
			npe.printStackTrace();
		} finally {
			HibernateUtil.closeSession(m3sSession);
		}

		logger.info("SetMediaUses finish with " + successful
				+ " successful results.");
	}
*/
	
	/*
	public void deleteMediaUses(Integer mediaId, List<MediaUseLite> mediaUsesList)
			throws IllegalArgumentException {
		logger.debug("deleting media uses... " + mediaUsesList.size()
				+ " media uses for media:" + mediaId + ".");

		int successful = 0; // for local count of errors

		Session m3sSession = HibernateUtil.openM3SDBSession();
		Transaction m3sTx = m3sSession.beginTransaction();

		Media m = null;
		MediaUse mu = null;
		MediaUseMedia mum = null;
		MediaUseMediaId mumId = null;

		try {
			m = (Media) m3sSession.load(Media.class, mediaId);

			// deletes the uses of the MEDIA
			for (MediaUseLite mul : mediaUsesList) {

				mu = (MediaUse) m3sSession.load(MediaUse.class, mul.getMediaUseId());

				mumId = new MediaUseMediaId(m.getMediaId(), mu.getMediaUseId());

				mum = (MediaUseMedia) m3sSession.load(MediaUseMedia.class, mumId);

				m3sSession.delete(mum);
				m3sSession.flush();
				successful++;

			} // for

			m3sSession.flush();
			m3sTx.commit();

		} catch (HibernateException he) {
			HibernateUtil.rollback(m3sTx);
			logger.error(he.getMessage());
			logger.error("deleting media uses... Couldn't delete the mediaUse id '"
					+ (Integer) mu.getMediaUseId() + "' to the media with id '" + mediaId
					+ "'.");
		} catch (NullPointerException npe) {
			HibernateUtil.rollback(m3sTx);
			logger.error(npe.getMessage());
			npe.printStackTrace();
		} finally {
			HibernateUtil.closeSession(m3sSession);
		}

		logger.info("deleting media uses... finish with " + successful
				+ " successfully deleted registries.");
	}
*/
	
	/*
	public void addTaxons(Integer mediaId, List<TaxonLite> taxonsList)
			throws IllegalArgumentException {
		logger.debug("setting " + taxonsList.size()
				+ " associated taxons for media:" + mediaId + ".");

		int successful = 0; // for local count of errors

		Session m3sSession;
		Transaction m3sTx;

		// Taxon theTaxon;
		TaxonMediaId theTaxonMediaId;
		TaxonMediaDAO theTaxonMedia;

		// sets the taxons of the MEDIA HibernateUtil.closeSession(attaSession);
		// for (int i = 0; i < taxonsList.size(); i++) {
		for (TaxonLite tl : taxonsList) {
			logger.debug("Asociandolo con el TaxonId=" + tl.getTaxonId());
			// empty variables
			m3sSession = null;
			m3sTx = null;
			// theTaxon = null;
			theTaxonMedia = null;
			theTaxonMediaId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				theTaxonMediaId = new TaxonMediaId(tl.getTaxonId(), mediaId);

				// sets the Taxon to the media
				theTaxonMedia = new TaxonMediaDAO();
				theTaxonMedia.setId(theTaxonMediaId);

				// saves changes and closes session
				m3sSession.save(theTaxonMedia);
				m3sTx.commit();

				successful = successful + 1;
				logger.debug("The taxon id '" + tl.getTaxonId()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't set the taxon id '" + tl.getTaxonId()
						+ "' to the media with id '" + mediaId + "'.");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.info("SetTaxonMedias finish with " + successful
				+ " successful results.");

	}
*/
	/*
	public void deleteTaxons(Integer mediaId, List<TaxonLite> taxonsList)
			throws IllegalArgumentException {
		logger.debug("deleting [" + taxonsList.size()
				+ "] associated taxons for media:" + mediaId + ".");

		int successful = 0; // for local count of errors

		Session m3sSession;
		Transaction m3sTx;

		// Taxon theTaxon;
		TaxonMediaId theTaxonMediaId;
		TaxonMediaDAO theTaxonMedia;

		// deleteds the previoulsy associated taxons of the MEDIA
		// for (int i = 0; i < oldAssociatedTaxonomy.size(); i++) {
		for (TaxonLite tl : taxonsList) {
			logger.debug("deleting associated taxons...  deleting taxon:"
					+ tl.getTaxonId());
			// empty variables
			m3sSession = null;
			m3sTx = null;
			// theTaxon = null;
			theTaxonMedia = null;
			theTaxonMediaId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				theTaxonMediaId = new TaxonMediaId(tl.getTaxonId(), mediaId);

				// sets the Taxon to the media
				theTaxonMedia = new TaxonMediaDAO();
				theTaxonMedia.setId(theTaxonMediaId);

				// saves changes and closes session
				m3sSession.delete(theTaxonMedia);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;
				logger.info("deleting associated taxons... The taxon id '"
						+ tl.getTaxonId() + "' is now deleted from media '" + mediaId
						+ "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger
						.error("deleting associated taxons... Couldn't delete the taxon id '"
								+ tl.getTaxonId() + "' to the media with id '" + mediaId + "'.");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.info("deleting associated taxons... " + npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.info("deleting associated taxons... delete finish finish with "
				+ successful + " successful results.");
		// return successful;

	}
	*/

	/*
	public void addGatherings(Integer mediaId, List<GatheringLite> gatheringsList)
			throws IllegalArgumentException {
		logger.debug("addGatherings for media:" + mediaId + ".");

		int successful = 0;
		Session m3sSession = null;
		Transaction m3sTx = null;
		GatheringMedia theGatheringMedia;
		GatheringMediaId theGatheringMediaId;
		PersonDAO personDAO = new INBioPersonDAOImpl();
		PersonDTOLite personLite;
		// GatheringData elem = null;

		for (GatheringLite gl : gatheringsList) {
			personLite = personDAO.getGatheringResposiblePersonLite(gl
					.getResponsiblePersonDisplayName());
			logger.debug("GatheringNumber= " + gl.getGatheringId()
					+ " y GatheringDetailPersonId= " + personLite.getPersonId());

			// empty variables
			m3sSession = null;
			m3sTx = null;
			theGatheringMedia = null;
			theGatheringMediaId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				theGatheringMediaId = new GatheringMediaId(mediaId, personLite
						.getPersonId(), gl.getGatheringId());

				// sets the gatherings to the media
				theGatheringMedia = new GatheringMedia();
				theGatheringMedia.setId(theGatheringMediaId);

				// saves changes and closes session
				m3sSession.save(theGatheringMedia);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;
				logger.info("The gatheringNumber '" + gl.getGatheringId()
						+ "' of the INBioPerson ID'" + personLite.getPersonId()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't set The gatheringNumber '" + gl.getGatheringId()
						+ "' of the INBioPerson ID'" + personLite.getPersonId()
						+ "' to the media [id" + mediaId + "].");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				System.out.println(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.info("setAssociatedGatherings finish with " + successful
				+ " successful results.");

	}
*/
	/*
	 * 
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.interfaces.MediaDAO#deleteGatherings(java.lang.Integer,
	 *      java.util.List)
	 */
	/*
	public void deleteGatherings(Integer mediaId,
			List<GatheringLite> gatheringsList) throws IllegalArgumentException {
		logger.debug("deleteAssociatedGatherings for media:" + mediaId + ".");

		int successful = 0;
		Session m3sSession = null;
		Transaction m3sTx = null;
		GatheringMedia oldGatheringMedia;
		GatheringMediaId oldGatheringMediaId;
		PersonDAO personDAO = new INBioPersonDAOImpl();
		PersonDTOLite personLite;

		// deleteds the previoulsy associated gatherings of the MEDIA
		for (GatheringLite gl : gatheringsList) {
			logger.debug("deleteAssociatedGatherings... GatheringNumber="
					+ gl.getGatheringId());
			personLite = personDAO.getGatheringResposiblePersonLite(gl
					.getResponsiblePersonDisplayName());
			// empty variables
			m3sSession = null;
			m3sTx = null;
			oldGatheringMedia = null;
			oldGatheringMediaId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				oldGatheringMediaId = new GatheringMediaId(mediaId, personLite
						.getPersonId(), gl.getGatheringId());

				// sets the Gathering to the media
				oldGatheringMedia = new GatheringMedia();
				oldGatheringMedia.setId(oldGatheringMediaId);

				// saves changes and closes session
				m3sSession.delete(oldGatheringMedia);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;
				logger.info("The GatheringNumber '" + gl.getGatheringId()
						+ "' of userId '" + personLite.getPersonId()
						+ "' was deleted from the media '" + mediaId + "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't delet The GatheringNumber '"
						+ gl.getGatheringId() + "' of userId '" + personLite.getPersonId()
						+ "' from the media [id" + mediaId + "].");

			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				System.out.println(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.info("deleteAssociatedGatherings finish with " + successful
				+ " successful deletes.");
	}
*/
	
	/*
	public void addSpecimens(Integer mediaId, List<SpecimenLite> specimensList)
			throws IllegalArgumentException {
		logger.debug("setting associated specimens for media:" + mediaId + ".");

		int successful = 0;
		Session m3sSession = null;
		Transaction m3sTx = null;
		SpecimenMedia theSpecimenMedia;
		SpecimenMediaId theSpecimenMediaId;

		for (SpecimenLite sl : specimensList) {

			logger.debug("con SpecimenId=" + sl.getSpecimenId());
			// empty variables
			m3sSession = null;
			m3sTx = null;
			theSpecimenMedia = null;
			theSpecimenMediaId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				theSpecimenMediaId = new SpecimenMediaId(sl.getSpecimenId(), mediaId);

				// sets the Specimen to the media
				theSpecimenMedia = new SpecimenMedia();
				theSpecimenMedia.setId(theSpecimenMediaId);

				// saves changes and closes session
				m3sSession.save(theSpecimenMedia);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;
				logger.debug("The Specimen id '" + sl.getSpecimenId()
						+ "'is set with the media with id '" + mediaId + "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't set the Speciemen [id=" + sl.getSpecimenId()
						+ "] to the media [id" + mediaId + "].");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.debug("SetSpecimenMedia finish with " + successful
				+ " successful results.");
	}
*/
	
	/*
	public void deleteSpecimens(Integer mediaId, List<SpecimenLite> specimensList)
			throws IllegalArgumentException {
		logger.debug("deleting associated specimens for media:" + mediaId + ".");

		int successful = 0;
		Session m3sSession = null;
		Transaction m3sTx = null;
		SpecimenMedia oldSpecimenMedia;
		SpecimenMediaId oldSpecimenMediaId;

		// deleteds the previoulsy associated specimens of the MEDIA
		for(SpecimenLite sl : specimensList) {
			logger.debug("deleting associated specimens... SpecimenId=" + sl.getSpecimenId());
			// empty variables
			m3sSession = null;
			m3sTx = null;
			oldSpecimenMedia = null;
			oldSpecimenMediaId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				oldSpecimenMediaId = new SpecimenMediaId(
						sl.getSpecimenId(), mediaId);

				// sets the Specimen to the media
				oldSpecimenMedia = new SpecimenMedia();
				oldSpecimenMedia.setId(oldSpecimenMediaId);

				// saves changes and closes session
				m3sSession.delete(oldSpecimenMedia);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;
				logger.debug("The Specimen id '" + sl.getSpecimenId()
						+ "' was deleted from the media '" + mediaId + "'.");

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't delet the Speciemen [id="
						+ sl.getSpecimenId() + "] from the media [id" + mediaId	+ "].");

			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
			}

		} // for
		logger.info("deleteAssociatedSpecimens finish with " + successful
				+ " successful deletes.");
	}
*/
	
	/*
	public void addObservations(Integer mediaId, List<ObservationLite> observationsList) throws IllegalArgumentException {
		logger.debug("setting " + observationsList.size()
				+ " associated observations for media:" + mediaId + ".");

		int successful = 0;

		Session m3sSession;
		Transaction m3sTx;
		ObservedTaxonMedia theOTM;
		ObservedTaxonMediaId theOTMId;

		// sets the ObservedTaxons of the MEDIA
		for(ObservationLite ol : observationsList) {
			logger.debug("con observationsList=" + ol.getObservationId());

			// empty variables
			m3sSession = null;
			m3sTx = null;
			theOTM = null;
			theOTMId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				// observationTaxonMediaId
				theOTMId = new ObservedTaxonMediaId(ol.getObservationId(), mediaId);

				theOTM = new ObservedTaxonMedia();
				theOTM.setId(theOTMId);

				m3sSession.save(theOTM);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("Couldn't set the Observation["
						+ ol.getObservationId()	+ "] to the media [id" + mediaId + "].");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
				successful = successful + 1;
			}

		} // for
		logger.info("setObservationMedia finish with " + successful
				+ " successful results.");
	}
*/
	
	/*
	public void deleteObservations(Integer mediaId, List<ObservationLite> observationsList) throws IllegalArgumentException {
		logger.debug("deleting associated observations for media:" + mediaId
				+ ".");

		int successful = 0;

		Session m3sSession;
		Transaction m3sTx;
		ObservedTaxonMedia oldOTM;
		ObservedTaxonMediaId oldOTMId;

		for(ObservationLite ol : observationsList) {
			logger
					.debug("deleting associated observations... con observationsList="
							+ ol.getObservationId());

			// empty variables
			m3sSession = null;
			m3sTx = null;
			oldOTM = null;
			oldOTMId = null;

			try {
				m3sSession = HibernateUtil.openM3SDBSession();
				m3sTx = m3sSession.beginTransaction();

				// observationTaxonMediaId
				oldOTMId = new ObservedTaxonMediaId(ol.getObservationId(), mediaId);

				oldOTM = new ObservedTaxonMedia();
				oldOTM.setId(oldOTMId);

				m3sSession.delete(oldOTM);
				m3sSession.flush();
				m3sTx.commit();

				successful = successful + 1;

			} catch (HibernateException he) {
				HibernateUtil.rollback(m3sTx);
				logger.error("deleting associated observations... "
						+ "Couldn't delete the Observation["
						+ ol.getObservationId()
						+ "] from the media [id" + mediaId + "].");
			} catch (NullPointerException npe) {
				HibernateUtil.rollback(m3sTx);
				logger.error(npe.getMessage());
				npe.printStackTrace();
			} finally {
				HibernateUtil.closeSession(m3sSession);
				successful = successful + 1;
			}

		} // for
		logger.debug("deleting associated observations... finish with "
				+ successful + " successful deleted registries.");
	}
*/


}
