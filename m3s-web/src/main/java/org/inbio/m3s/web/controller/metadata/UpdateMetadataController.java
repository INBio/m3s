/**
 * 
 */
package org.inbio.m3s.web.controller.metadata;


import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.OwnerEntity;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.exception.AssociationTypeNotFoundException;
import org.inbio.m3s.exception.InstitutionNotFoundException;
import org.inbio.m3s.exception.KeywordNotFoundException;
import org.inbio.m3s.exception.MediaTypeNotFoundException;
import org.inbio.m3s.exception.MediaUseNotFoundException;
import org.inbio.m3s.exception.OwnerTypeNotFoundException;
import org.inbio.m3s.exception.PersonNotFoundException;
import org.inbio.m3s.exception.ProjectNotFoundException;
import org.inbio.m3s.exception.TaxonNotFoundException;
import org.inbio.m3s.exception.YesNoValueNotFoundException;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.SiteManager;
import org.inbio.m3s.service.util.ImportFileParser;
import org.inbio.m3s.util.StringUtil;
import org.inbio.m3s.web.converter.TaxonGuiOrDTOConverter;
import org.inbio.m3s.web.exception.ValidationException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author jgutierrez
 *
 */
public class UpdateMetadataController implements Controller {

	protected static Log logger = LogFactory.getLog(UpdateMetadataController.class);	

	//view and model
	private String viewName;//=editStep3	
	private String formActionKey;//=editFormAction
	private String formActionValue;//=editMedia.html
	
	//metadata Form
	private String fileNameCode;
	private String metadataUsername;
	private String metadataTitle;
	private String metadataDescription;
	private String metadataMediaCategory;
	private String metadataProjects;
	private String metadataKeywords;
	private String metadataAssociatedToValueType;
	private String metadataAssociatedToValue;
	private String metadataTaxonomy; 
	private String metadataSiteDescription;
	private String metadataMediaAuthor;
	private String metadataOwnerType;
	private String metadataOwnerValue;
	private String metadataUsePolicy;
	private String metadataMediaVisible;	

	//Error management
	private String errorViewName; //=editStep2
	private String errorFormActionKey;//=formAction
	private String errorFormActionValue;//=updateMetadata.html
	
	//Managers, Handlers and Utils (etc)
	private TaxonGuiOrDTOConverter taxonGuiOrDTOConverter;
	private MetadataHandler metadataHandler;
	private MessageManager messageManager;
	private MediaManager mediaManager;
	private AgentManager agentManager;
	private SiteManager siteManager;
	private SiteDAO siteDAO;
	
	
	
	public ModelAndView handleRequest(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
	
		//Create the view
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject(formActionKey, formActionValue);

		String mediaId = request.getParameter(fileNameCode);
		String userName = request.getParameter(metadataUsername);
		String title = request.getParameter(metadataTitle);
		String description = request.getParameter(metadataDescription);
		String mediaTypeId = request.getParameter(metadataMediaCategory);
		String projects = request.getParameter(metadataProjects);
		String keywords = request.getParameter(metadataKeywords);
		Integer associationTypeCode = Integer.valueOf(request.getParameter(metadataAssociatedToValueType));
		String associatedToValue = request.getParameter(metadataAssociatedToValue);
		String taxonomy = request.getParameter(metadataTaxonomy);
		String siteDescription = request.getParameter(metadataSiteDescription);
		String authorName = request.getParameter(metadataMediaAuthor);
		authorName = URLDecoder.decode(authorName, "UTF-8");
		Integer ownerTypeId = Integer.valueOf(request.getParameter(metadataOwnerType));
		String ownerName = request.getParameter(metadataOwnerValue);
		ownerName = URLDecoder.decode(ownerName, "UTF-8");
		String usePolicyKey = request.getParameter(metadataUsePolicy);
		String mediaVisible = request.getParameter(metadataMediaVisible);
		
		logger.debug("["+fileNameCode+"] mediaId: "+mediaId);
		logger.debug("userName: "+userName);
		logger.debug("title: "+title);
		logger.debug("description: "+description);
		logger.debug("mediaTypeId: "+mediaTypeId);
		logger.debug("projects: "+projects);
		logger.debug("keywords: "+keywords);
		logger.debug("associationTypeCode: "+associationTypeCode);
		logger.debug("associatedToValue: "+associatedToValue);
		logger.debug("taxonomy: "+taxonomy);
		logger.debug("siteDescription: "+siteDescription);
		logger.debug("author: "+authorName);
		logger.debug("ownerType: "+ownerTypeId);
		logger.debug("ownerValue: "+ownerName);
		logger.debug("usePolicy: "+usePolicyKey);
		logger.debug("mediaVisible: "+mediaVisible);
		
		try{
		GeneralMetadataDTO gmDTO = getGM(mediaId, title,description,mediaTypeId,siteDescription,projects,keywords,
				associationTypeCode, associatedToValue, taxonomy);
		gmDTO.setUsername(userName);
		
		UsesAndCopyrightsDTO uacDTO = getUAC(mediaId, authorName, ownerTypeId, ownerName, usePolicyKey, mediaVisible);
		uacDTO.setUsername(userName);
		
		
		logger.debug("updating metadata...");

		mediaManager.updateGM(gmDTO);
		logger.debug("saving Metadata... general metadata saved");

		mediaManager.updateUACM(uacDTO);
		logger.debug("saving Metadata... uses and copyrigths metadata saved");
		
		mav.addObject("mediaId", mediaId);
		logger.debug("Updated Media Id = "+mediaId);
		} catch(IllegalArgumentException iae){
			ValidationException ve = new ValidationException(iae.getMessage(), iae.getCause());
			
			ve.setViewName(errorViewName);
			ve.setErrorMessageKey("error.metadata.01");
			
			Map<String, Object> modelElements = new HashMap<String, Object>();
			modelElements.put("error", "ERROR: "+iae.getMessage());
			
			/*		 */
			modelElements.put(errorFormActionKey, errorFormActionValue);
			
			modelElements.put(fileNameCode, mediaId);
			modelElements.put("mediaId", mediaId);
			//modelElements.put(metadataUsername, userName);
			modelElements.put(metadataTitle, title);
			modelElements.put(metadataDescription, description);
			modelElements.put(metadataMediaCategory, mediaTypeId);
			modelElements.put(metadataProjects, projects);
			modelElements.put(metadataKeywords, keywords);
			modelElements.put(metadataAssociatedToValueType, associationTypeCode.toString());
			modelElements.put(metadataAssociatedToValue, associatedToValue);
			modelElements.put(metadataTaxonomy, taxonomy);
			modelElements.put(metadataSiteDescription, siteDescription);
			modelElements.put(metadataMediaAuthor, authorName);
			modelElements.put(metadataOwnerType, ownerTypeId.toString());
			modelElements.put(metadataOwnerValue, ownerName);
			modelElements.put(metadataUsePolicy, usePolicyKey);
			modelElements.put(metadataMediaVisible, mediaVisible);
			
			modelElements = metadataHandler.getMetadata(modelElements);
			
			ve.setModelElements(modelElements);
			logger.debug("throw ValidationException");
			throw ve;
		}
		
		return mav;
	}
	
	
	/**
	 * 
	 * Gets the UsesAndCopyrightsTV info directly from the importFile
	 * @param authorName 
	 * @param ownerName 
	 * @param ownerTypeId 
	 * @param mediaVisible 
	 * 
	 * @param info
	 *          the representation of the import info file
	 * @param rowNumber
	 *          the number of row being process
	 * @return a UsesAndCopyrightsTV object
	 * @throws IllegalArgumentException
	 *           if some information is wrong or in bad format
	 */
	private UsesAndCopyrightsDTO getUAC(String mediaId, String authorName, Integer ownerTypeId, String ownerName, 
			String usePolicyKey, String mediaVisible) throws IllegalArgumentException {
		UsesAndCopyrightsDTO uacDTO = new UsesAndCopyrightsDTO();

		uacDTO.setMediaKey(mediaId);

		logger.debug("Getting uses and copyrigths metadata TV");
		
		try{
	
			// set author
			PersonLiteDTO plDTO = agentManager.getPersonLiteByName(authorName);
			uacDTO.setAuthorKey(plDTO.getPersonKey());
			logger.debug("Author Key: '" + uacDTO.getAuthorKey() + "'");
			
			if (ownerTypeId.equals(OwnerEntity.INSTITUTION.getId())) {
				InstitutionLiteDTO iLiteDTO = agentManager.getInstitutionLiteByName(ownerName);
				uacDTO.setInstitutionOwnerKey(iLiteDTO.getInstitutionKey());
				uacDTO.setPersonOwnerKey(null);
			} else if (ownerTypeId.equals(OwnerEntity.PERSON.getId())) {
				PersonLiteDTO oplDTO = agentManager.getPersonLiteByName(ownerName);
				uacDTO.setPersonOwnerKey(oplDTO.getPersonKey());
				uacDTO.setInstitutionOwnerKey(null);
			} else {
				logger.error("No valid owner Type... debería tirarse una excepcion");	
			}
			logger.debug("Author Owner Type: '" + uacDTO.getAuthorKey() + "'");
			logger.debug("Institution Owner Type: '" + uacDTO.getInstitutionOwnerKey() + "'");
	
			// use policy
			uacDTO.setUsePolicyKey(usePolicyKey);
			logger.debug("Use policy: '" + uacDTO.getUsePolicyKey() + "'");	
			
			/*	mediaUses */
			uacDTO.setMediaUsesList(new ArrayList<MediaUseDTO>());
	
			// backup and visible value
			if(mediaVisible == null){
				uacDTO.setIsBackup(new Character('N'));
				uacDTO.setIsPublic(new Character('N'));			
			} else{
				uacDTO.setIsBackup(new Character('Y'));
				uacDTO.setIsPublic(new Character('Y'));
			}
			
			logger.debug("Is backup: '" + uacDTO.getIsBackup() + "'");
			logger.debug("Is public: '" + uacDTO.getIsPublic() + "'");
	
			logger.debug("Getting uses and copyrigths metadata its done");
			return uacDTO;
		
		}  catch (PersonNotFoundException pnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Persona: '" + pnfe.getNotFoundPerson() + "' en la Base de Datos.");
			throw new IllegalArgumentException(pnfe.getMessage());
		
		} catch (InstitutionNotFoundException infe) {
		  logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Institucion: '" + infe.getNotFoundInstitution() + "' en la Base de Datos.");
		  throw new IllegalArgumentException(infe.getMessage());
		  
		} catch (OwnerTypeNotFoundException otnfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Tipo de dueño: '" + otnfe.getNotFoundOwnerType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(otnfe.getMessage());
		
		} catch (MediaUseNotFoundException munfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Uso para el Medio: '" + munfe.getNotFoundMediaUse() + "' en la Base de Datos.");
			throw new IllegalArgumentException(munfe.getMessage());

		} catch (YesNoValueNotFoundException ynvnfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el valor: '" + ynvnfe.getNotFoundYesNoValue() + "' como afirmativo o negativo..");
			throw new IllegalArgumentException(ynvnfe.getMessage());
		}				
		
	}
	
	
	/*
	 *  Copy & Paste de ImportFromFile.java
	 * 
	 */
	private GeneralMetadataDTO getGM(String mediaId, String title, String description, String mediaTypeId,
			String siteDescription, String projects, String keywords, Integer associationTypeCode, 
			String associatedToValue, String taxonomy) throws IllegalArgumentException {
		
		GeneralMetadataDTO gmDTO = new GeneralMetadataDTO(mediaId,title,description,mediaTypeId,null,siteDescription);
		List<TaxonLiteDTO> taxonsList = new ArrayList<TaxonLiteDTO>();
		
		try {
			//el primer nulo es el mediaId, como es nuevo siempre ira en null
			//y el el otro valor que esta en null es el siteKey... ¿?
			gmDTO.setProjectsList(messageManager.getProjectsFromStringList(projects));
			gmDTO.setKeywordsList(messageManager.getKeywordsFromStringList(keywords));
			
			//AssociatedTo....			
			List<SpecimenLiteDTO> associatedSpecimensList = new ArrayList<SpecimenLiteDTO>();
			gmDTO.setAssociatedSpecimensList(associatedSpecimensList);
			List<ObservationLiteDTO> associatedObservationsList =  new ArrayList<ObservationLiteDTO>();
			gmDTO.setAssociatedObservationsList(associatedObservationsList);
			List<GatheringLiteDTO> associatedGatheringsList = new ArrayList<GatheringLiteDTO>();
			gmDTO.setAssociatedGatheringsList(associatedGatheringsList);
			
			if (associationTypeCode.equals(AssociatedToEntity.SPECIMEN_NUMBER.getId())) {
				logger.debug("Associated to Specimen Number");
					
					SpecimenLiteDTO slDTO = new SpecimenLiteDTO(associatedToValue);
					associatedSpecimensList.add(slDTO);
					gmDTO.setAssociatedSpecimensList(associatedSpecimensList);
					logger.debug("Associated To Specimen Number... done");
			
			} else if (associationTypeCode.equals(AssociatedToEntity.OBSERVATION_NUMBER.getId())) {
				logger.debug("Associated to Observation Number");
				ObservationLiteDTO olDTO = new ObservationLiteDTO(associatedToValue);
				associatedObservationsList.add(olDTO);
				gmDTO.setAssociatedObservationsList(associatedObservationsList);
				logger.debug("Associated To Observation Number... done");

			} else if (associationTypeCode.equals(AssociatedToEntity.GATHERING_CODE.getId())) {
				logger.debug("Associated to Gathering Code");
				List<Object> temp = StringUtil.getIndividualItems(associatedToValue,java.lang.String.class);
				
				String gatheringPersonName = (String) temp.get(0);
				String gatheringNumber = (String) temp.get(1);
				PersonLiteDTO pLite = agentManager.getGatheringResposibleLiteByName(gatheringPersonName);
				logger.debug(pLite.toString());
				GatheringLiteDTO glDTO = new GatheringLiteDTO(gatheringNumber, pLite.getName());
				logger.debug(glDTO.toString());
				
				associatedGatheringsList.add(glDTO);
				gmDTO.setAssociatedGatheringsList(associatedGatheringsList);
					
				logger.debug("Associated To Collect Number... done");
			}

			//taxonomy
			logger.debug("taxonomy: '" + taxonomy + "'");
			taxonsList = taxonGuiOrDTOConverter.toDTOList(taxonomy);
			gmDTO.setTaxonsList(taxonsList);
			logger.debug("Taxonomy elements: '" + gmDTO.getTaxonsList().size() + "'");

			
		// TODO: has to fix the siteId stuff
			// site Description
			if (siteDescription == null || siteDescription.compareTo("") == 0) {

				if (associationTypeCode.equals(AssociatedToEntity.SPECIMEN_NUMBER.getId())) {
					siteDescription = siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(associatedToValue));
				} else if (associationTypeCode.equals(AssociatedToEntity.OBSERVATION_NUMBER.getId())) {
					siteDescription = siteDAO.getiteDBIdFromObservationNumber(new Integer(associatedToValue));
				} else if (associationTypeCode.equals(AssociatedToEntity.GATHERING_CODE.getId())) {
					siteDescription = siteManager.getSiteFromGatheringCode(associatedToValue);
				}
			}
			gmDTO.setSiteDescription(siteDescription);
			gmDTO.setSiteKey(null);
			logger.debug("Site Description: '" + gmDTO.getSiteDescription() + "'");
			logger.debug("Site Key: '" + gmDTO.getSiteKey() + "'");
			
			return gmDTO;
			
		} catch (MediaTypeNotFoundException mtnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + mtnfe.getNotFoundMediaType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(mtnfe.getMessage());
		
		} catch (ProjectNotFoundException pnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Proyecto: '" + pnfe.getNotFoundProject() + "' en la Base de Datos.");
			throw new IllegalArgumentException(pnfe.getMessage());
		
		} catch (KeywordNotFoundException knfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Palabra Clave: '" + knfe.getNotFoundKeyword() + "' en la Base de Datos.");
			throw new IllegalArgumentException(knfe.getMessage());
			
		} catch (TaxonNotFoundException tnfe) {
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + tnfe.getNotFoundTaxonName() + "' en la Base de Datos.");
			throw new IllegalArgumentException(tnfe.getMessage());
		
		} catch (AssociationTypeNotFoundException atnfe){
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + atnfe.getNotFoundAssociationType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(atnfe.getMessage());

		}	catch (Exception e) {
			logger.error(ImportFileParser.ERROR + " '" + e.getMessage() + "'");
			throw new IllegalArgumentException(e.getMessage());
		}
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
	 * @return the formActionKey
	 */
	public String getFormActionKey() {
		return formActionKey;
	}


	/**
	 * @param formActionKey the formActionKey to set
	 */
	public void setFormActionKey(String formActionKey) {
		this.formActionKey = formActionKey;
	}


	/**
	 * @return the formActionValue
	 */
	public String getFormActionValue() {
		return formActionValue;
	}


	/**
	 * @param formActionValue the formActionValue to set
	 */
	public void setFormActionValue(String formActionValue) {
		this.formActionValue = formActionValue;
	}


	/**
	 * @return the fileNameCode
	 */
	public String getFileNameCode() {
		return fileNameCode;
	}


	/**
	 * @param fileNameCode the fileNameCode to set
	 */
	public void setFileNameCode(String fileNameCode) {
		this.fileNameCode = fileNameCode;
	}


	/**
	 * @return the metadataUsername
	 */
	public String getMetadataUsername() {
		return metadataUsername;
	}


	/**
	 * @param metadataUsername the metadataUsername to set
	 */
	public void setMetadataUsername(String metadataUsername) {
		this.metadataUsername = metadataUsername;
	}


	/**
	 * @return the metadataTitle
	 */
	public String getMetadataTitle() {
		return metadataTitle;
	}


	/**
	 * @param metadataTitle the metadataTitle to set
	 */
	public void setMetadataTitle(String metadataTitle) {
		this.metadataTitle = metadataTitle;
	}


	/**
	 * @return the metadataDescription
	 */
	public String getMetadataDescription() {
		return metadataDescription;
	}


	/**
	 * @param metadataDescription the metadataDescription to set
	 */
	public void setMetadataDescription(String metadataDescription) {
		this.metadataDescription = metadataDescription;
	}


	/**
	 * @return the metadataMediaCategory
	 */
	public String getMetadataMediaCategory() {
		return metadataMediaCategory;
	}


	/**
	 * @param metadataMediaCategory the metadataMediaCategory to set
	 */
	public void setMetadataMediaCategory(String metadataMediaCategory) {
		this.metadataMediaCategory = metadataMediaCategory;
	}


	/**
	 * @return the metadataProjects
	 */
	public String getMetadataProjects() {
		return metadataProjects;
	}


	/**
	 * @param metadataProjects the metadataProjects to set
	 */
	public void setMetadataProjects(String metadataProjects) {
		this.metadataProjects = metadataProjects;
	}


	/**
	 * @return the metadataKeywords
	 */
	public String getMetadataKeywords() {
		return metadataKeywords;
	}


	/**
	 * @param metadataKeywords the metadataKeywords to set
	 */
	public void setMetadataKeywords(String metadataKeywords) {
		this.metadataKeywords = metadataKeywords;
	}


	/**
	 * @return the metadataAssociatedToValueType
	 */
	public String getMetadataAssociatedToValueType() {
		return metadataAssociatedToValueType;
	}


	/**
	 * @param metadataAssociatedToValueType the metadataAssociatedToValueType to set
	 */
	public void setMetadataAssociatedToValueType(
			String metadataAssociatedToValueType) {
		this.metadataAssociatedToValueType = metadataAssociatedToValueType;
	}


	/**
	 * @return the metadataAssociatedToValue
	 */
	public String getMetadataAssociatedToValue() {
		return metadataAssociatedToValue;
	}


	/**
	 * @param metadataAssociatedToValue the metadataAssociatedToValue to set
	 */
	public void setMetadataAssociatedToValue(String metadataAssociatedToValue) {
		this.metadataAssociatedToValue = metadataAssociatedToValue;
	}


	/**
	 * @return the metadataTaxonomy
	 */
	public String getMetadataTaxonomy() {
		return metadataTaxonomy;
	}


	/**
	 * @param metadataTaxonomy the metadataTaxonomy to set
	 */
	public void setMetadataTaxonomy(String metadataTaxonomy) {
		this.metadataTaxonomy = metadataTaxonomy;
	}


	/**
	 * @return the metadataSiteDescription
	 */
	public String getMetadataSiteDescription() {
		return metadataSiteDescription;
	}


	/**
	 * @param metadataSiteDescription the metadataSiteDescription to set
	 */
	public void setMetadataSiteDescription(String metadataSiteDescription) {
		this.metadataSiteDescription = metadataSiteDescription;
	}


	/**
	 * @return the metadataMediaAuthor
	 */
	public String getMetadataMediaAuthor() {
		return metadataMediaAuthor;
	}


	/**
	 * @param metadataMediaAuthor the metadataMediaAuthor to set
	 */
	public void setMetadataMediaAuthor(String metadataMediaAuthor) {
		this.metadataMediaAuthor = metadataMediaAuthor;
	}


	/**
	 * @return the metadataOwnerType
	 */
	public String getMetadataOwnerType() {
		return metadataOwnerType;
	}


	/**
	 * @param metadataOwnerType the metadataOwnerType to set
	 */
	public void setMetadataOwnerType(String metadataOwnerType) {
		this.metadataOwnerType = metadataOwnerType;
	}


	/**
	 * @return the metadataOwnerValue
	 */
	public String getMetadataOwnerValue() {
		return metadataOwnerValue;
	}


	/**
	 * @param metadataOwnerValue the metadataOwnerValue to set
	 */
	public void setMetadataOwnerValue(String metadataOwnerValue) {
		this.metadataOwnerValue = metadataOwnerValue;
	}


	/**
	 * @return the metadataUsePolicy
	 */
	public String getMetadataUsePolicy() {
		return metadataUsePolicy;
	}


	/**
	 * @param metadataUsePolicy the metadataUsePolicy to set
	 */
	public void setMetadataUsePolicy(String metadataUsePolicy) {
		this.metadataUsePolicy = metadataUsePolicy;
	}


	/**
	 * @return the metadataMediaVisible
	 */
	public String getMetadataMediaVisible() {
		return metadataMediaVisible;
	}


	/**
	 * @param metadataMediaVisible the metadataMediaVisible to set
	 */
	public void setMetadataMediaVisible(String metadataMediaVisible) {
		this.metadataMediaVisible = metadataMediaVisible;
	}


	/**
	 * @return the errorViewName
	 */
	public String getErrorViewName() {
		return errorViewName;
	}


	/**
	 * @param errorViewName the errorViewName to set
	 */
	public void setErrorViewName(String errorViewName) {
		this.errorViewName = errorViewName;
	}


	/**
	 * @return the errorFormActionKey
	 */
	public String getErrorFormActionKey() {
		return errorFormActionKey;
	}


	/**
	 * @param errorFormActionKey the errorFormActionKey to set
	 */
	public void setErrorFormActionKey(String errorFormActionKey) {
		this.errorFormActionKey = errorFormActionKey;
	}


	/**
	 * @return the errorFormActionValue
	 */
	public String getErrorFormActionValue() {
		return errorFormActionValue;
	}


	/**
	 * @param errorFormActionValue the errorFormActionValue to set
	 */
	public void setErrorFormActionValue(String errorFormActionValue) {
		this.errorFormActionValue = errorFormActionValue;
	}


	/**
	 * @return the taxonGuiOrDTOConverter
	 */
	public TaxonGuiOrDTOConverter getTaxonGuiOrDTOConverter() {
		return taxonGuiOrDTOConverter;
	}


	/**
	 * @param taxonGuiOrDTOConverter the taxonGuiOrDTOConverter to set
	 */
	public void setTaxonGuiOrDTOConverter(
			TaxonGuiOrDTOConverter taxonGuiOrDTOConverter) {
		this.taxonGuiOrDTOConverter = taxonGuiOrDTOConverter;
	}


	/**
	 * @return the metadataHandler
	 */
	public MetadataHandler getMetadataHandler() {
		return metadataHandler;
	}


	/**
	 * @param metadataHandler the metadataHandler to set
	 */
	public void setMetadataHandler(MetadataHandler metadataHandler) {
		this.metadataHandler = metadataHandler;
	}


	/**
	 * @return the messageManager
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}


	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}


	/**
	 * @return the mediaManager
	 */
	public MediaManager getMediaManager() {
		return mediaManager;
	}


	/**
	 * @param mediaManager the mediaManager to set
	 */
	public void setMediaManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
	}


	/**
	 * @return the agentManager
	 */
	public AgentManager getAgentManager() {
		return agentManager;
	}


	/**
	 * @param agentManager the agentManager to set
	 */
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}


	/**
	 * @return the siteManager
	 */
	public SiteManager getSiteManager() {
		return siteManager;
	}


	/**
	 * @param siteManager the siteManager to set
	 */
	public void setSiteManager(SiteManager siteManager) {
		this.siteManager = siteManager;
	}


	/**
	 * @return the siteDAO
	 */
	public SiteDAO getSiteDAO() {
		return siteDAO;
	}


	/**
	 * @param siteDAO the siteDAO to set
	 */
	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}


}
