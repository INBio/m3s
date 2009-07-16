package org.inbio.m3s.model.atta;

import java.util.Date;
import java.util.Set;

/**
 * 
 * @author jgutierrez
 *
 */
public class INBioSpecimen extends org.inbio.m3s.model.general.Specimen {

	private static final long serialVersionUID = 1212115617854666568L;

	private Gathering gathering;

	public INBioSpecimen() {
	}

	public INBioSpecimen(Integer specimenId, Gathering gathering,
			Integer specimenCategoryId, String discarded, Integer collectionId) {
		super(specimenId, specimenCategoryId,discarded, collectionId);
		this.setGathering(gathering);
	}

	public INBioSpecimen(Integer specimenId, Gathering gathering,
			Integer specimenCategoryId, Integer specimenTypeId,
			Integer storageTypeId, Integer substrateId, Integer originId,
			Integer preservationMediumId, String discarded,
			Integer gatheringDetailPersonId, Integer gatheringNumber,
			Integer morphologicalDescriptionId, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy, Integer collectionId,
			Integer extractionTypeId, Integer numberWhole,
			Integer numberFragments, String externalSpecimen,
			Set<Identification> identifications) {
		
		super(specimenId,specimenCategoryId,specimenTypeId,storageTypeId,substrateId,
				originId,preservationMediumId,discarded,gatheringDetailPersonId,gatheringNumber,
				morphologicalDescriptionId,creationDate,createdBy,lastModificationDate,
				lastModificationBy,collectionId,extractionTypeId,numberWhole,numberFragments,
				externalSpecimen);
		this.gathering = gathering;
		}


	public Gathering getGathering() {
		return this.gathering;
	}

	public void setGathering(Gathering gathering) {
		this.gathering = gathering;
	}

}
