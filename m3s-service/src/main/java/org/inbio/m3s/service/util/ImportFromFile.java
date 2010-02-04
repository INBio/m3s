/**
 * License must be here...
 */

package org.inbio.m3s.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.message.MediaTypeDTO;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.metadata.util.AssociatedToEntity;
import org.inbio.m3s.dto.metadata.util.ImportationFileEntity;
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
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.SiteManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.service.util.impl.ExcelImportFileParserImpl;
import org.inbio.m3s.util.MediaFileManagement;
import org.inbio.m3s.util.StringUtil;




/**
 * 
 * 
 * @author jgutierrez
 */
public class ImportFromFile {

	//DAO's... deben desaparecer de aca
	SiteDAO siteDAO;
	
	//managers
	MediaManager mediaManager;
	TaxonomyManager taxonomyManager;
	SiteManager siteManager;
	AgentManager agentManager;
	MessageManager messageManager;
	MetadataManager metadataManager;
	
	//util clases
	MediaFileManagement mediaFileManagement;
	
	//constants
	//private String realBatchMediaDir;
	
	private static Logger logger = Logger.getLogger(ImportFromFile.class);

	/**
	 * This method is the one that manages the importation of images...
	 * 
	 * @param importFileName
	 *          name of the import file. The address of the file will be
	 *          'Properties.REAL_IMPORT_FILES_DIR' constant + importFileName
	 * @param fileType
	 *          what kind of file is, by now the only suported is xls
	 * @param importationBatchMediaPath real path where the media is going to be ready for the importation
	 * @param mediaFilesPath the path where the media will be stored          
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public void ImportMedia(String importFileName, ImportationFileEntity fileType, String importationBatchMediaPath, String mediaFilesPath)
			throws FileNotFoundException, IOException, IllegalArgumentException {
		logger.debug("\nImportacion de archivos en lote con archivo: " + importFileName);

		GeneralMetadataDTO gm;
		UsesAndCopyrightsDTO uacm;
		TechnicalMetadataDTO tmDTO;
		ImportFileParser fileParser = getImportFileParser(importFileName, fileType);
		String fileNameColumnValue;
		Integer mediaId = null;

		logger.debug("Total minimo de elementos a importar:" + fileParser.getTotalEntries());

		for (int i = fileParser.getFistEntryIdex(); i <= fileParser.getTotalEntries(); i++) {
			try {
				logger.debug("Importando la fila#:" + i);
				fileNameColumnValue = fileParser.read(i, ImportFileParser.FILE_NAME_DATA);
				String resultStatus = "";

				
				gm = getGM(fileParser, i);
				logger.debug("Ya se obtuvieron los metadatos Generales.");
				
				uacm = getUAC(fileParser, i);
				logger.debug("Ya se obtuvieron los metadatos De Autoría y Derechos de Uso.");

				logger.debug("Importando " + getFileNamesToImport(fileNameColumnValue,importationBatchMediaPath).size() + " elementos correspondientes a la fila");
				// for init
				for (String fileName : getFileNamesToImport(fileNameColumnValue, importationBatchMediaPath)) {
					logger.debug("Extrayendo metadatos técnicos del archivo: '" + fileName + "'");
					tmDTO = metadataManager.getTechMetadataFromFile(gm.getMediaTypeKey(), fileName);
					if(tmDTO==null)
						tmDTO = metadataManager.getTechMetadataByMediaType(gm.getMediaTypeKey());

					if (mediaFileManagement.isFileReadable(fileName))
						mediaId = mediaManager.insertNewMedia(gm, uacm, tmDTO);
					else{
						fileParser.writeResult(i, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede abrir el archivo: '" + fileName + "'.");
						throw new IllegalArgumentException("file '" + fileName + "' is not readable.");
					}
					
					
					// FIXME: only for jpg's... not really a bug! ;).
					// organizeAndCleanFiles(mediaFileName, mediaId.toString() + ".jpg",mediaId);
					// organizeAndCleanFiles(fileName, mediaId.toString() + ".jpg",mediaId);
					mediaFileManagement.organizeAndCleanFiles(fileName, mediaId, Integer.valueOf(gm.getMediaTypeKey()),mediaFilesPath);

					resultStatus = resultStatus.concat("Medio guardado con exito, ID #"	+ mediaId.toString() + ".");
				}
				// for end

				logger.info(resultStatus);
				fileParser.writeFinalResult(i, resultStatus);

			} catch (Exception e) {
				logger.error("La informacion presenta errores.");
				logger.error(e.getMessage());
				logger.error(e.getCause());
				fileParser.writeFinalResult(i, "La información presenta errores, revisar hoja de resultados.");
			}
		}

		fileParser.closeFile();
		logger.info("Importacion concluida.");
	}
	
	
	/**
	 * Get the names of the files to be imported.
	 * 
	 * If the row of the importation file uses a wild card this method will return a list
	 * with many filenames, but if the row only contains a single file that will be returned.
   *
	 * @param fileNameColumnValue
	 * @param importationBatchMediaPath real path where the media is going to be ready for the importation
	 * @return
	 */
	private List<String> getFileNamesToImport(String fileNameColumnValue, String importationBatchMediaPath){
		
		List<String> fileNames = new ArrayList<String>();
		
		// if true means uses wildcard. This means the general metadata and the
		// uses and copyrigth values will be shared for various registries. The image will change.
		if (fileNameColumnValue.lastIndexOf("*") != -1) {
			logger.debug("Use wildcard.");
			String folderName = importationBatchMediaPath;
			if (fileNameColumnValue.lastIndexOf(File.separator) != -1) {
				folderName = folderName.concat(fileNameColumnValue);
				folderName = folderName.substring(0, folderName.lastIndexOf(File.separator) + 1);
			}
			logger.debug("Folder: '" + folderName + "'.");
			String pattern = fileNameColumnValue.substring(fileNameColumnValue.lastIndexOf("*") + 1);
			logger.debug("pattern: '" + pattern + "'.");

			File folder = new File(folderName);
			String[] listOfFiles = folder.list();
			logger.debug("List of files:"+listOfFiles.length);

			for (int j = 0; j < listOfFiles.length; j++) {
				if (listOfFiles[j].endsWith(pattern)) {
					logger.debug("File " + folderName + listOfFiles[j]);
					fileNames.add(folderName + listOfFiles[j]);
				}
			}
			logger.debug("Total de coincidencias: " + fileNames.size());

		} else {
			fileNames.add(importationBatchMediaPath + fileNameColumnValue);
		}
		
		
		return fileNames;
	}

	/**
	 * Inicializa el Archivo de importación, de la fuente de datos que sea.
	 * 
	 * 
	 * @param importFileName
	 * @param fileType
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static ImportFileParser getImportFileParser(String importFileName,
			ImportationFileEntity fileType) throws IllegalArgumentException {
		logger.debug("importFileName ["+importFileName+"], ImportationFileEntity ["+fileType.getId()+"]");
		try {
			if (fileType == ImportationFileEntity.MS_EXCEL_FILE) {
				logger.debug("Importacion desde un archivo de Excel ["+importFileName+"]");
				return new ExcelImportFileParserImpl(importFileName);
			} else {
				logger.error("Tipo de archivo desconocido para la importacion.");
				throw new IllegalArgumentException("the fileType argument is invalid.");
			}
		} catch (Exception e) {
			logger.error("Error grave intentando abrir el archivo de importacion.");
			logger.error(e.getMessage());
			logger.error(e.toString());
			throw new IllegalArgumentException();
		}

	}




	/**
	 * Gets the GeneralmetadaTV indo directly from the importFile
	 * 
	 * @param info
	 *          the representation of the import info file
	 * @param rowNumber
	 *          the number of row being process
	 * @return a GeneralMetadataTV object
	 * @throws IllegalArgumentException
	 *           if some information is wrong or in bad format
	 */
	private GeneralMetadataDTO getGM(ImportFileParser info, int rowNumber) throws IllegalArgumentException {
		GeneralMetadataDTO gmDTO = new GeneralMetadataDTO();
		Integer associationTypeCode;
		String associatedToValue = null;
		String siteDescription;
		String taxonName;
		String kingdom;
		List<TaxonLiteDTO> taxonsList = new ArrayList<TaxonLiteDTO>();
		TaxonLiteDTO tlDTO = null;

		//Media Key
		gmDTO.setMediaKey(null);

		logger.debug("\nGetting general metadata");

		try {
			//Title
			gmDTO.setTitle(info.read(rowNumber, ImportFileParser.TITLE_DATA));
			info.writeResult(rowNumber, ImportFileParser.TITLE_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Title: '" + gmDTO.getTitle() + "'");

			//Description
			gmDTO.setDescription(info.read(rowNumber, ImportFileParser.DESCRIPTION_DATA));
			info.writeResult(rowNumber, ImportFileParser.DESCRIPTION_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Description: '" + gmDTO.getDescription() + "'");

			//Media Type
			MediaTypeDTO mtDTO =  messageManager.getMediaTypeByName(info.read(rowNumber, ImportFileParser.MEDIA_TYPE_DATA));
			gmDTO.setMediaTypeKey(mtDTO.getMediaTypeKey());
			logger.debug("Media Type: '" + gmDTO.getMediaTypeKey() + "'");

			//Projects
			String projects = info.read(rowNumber, ImportFileParser.PROJECTS_DATA);
			//gmDTO.setProjectsList(getProjectLiteList(projects));
			gmDTO.setProjectsList(messageManager.getProjectsFromStringList(projects));
			info.writeResult(rowNumber, ImportFileParser.PROJECTS_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Projects: '" + gmDTO.getProjectsList().size() + "'");

			// keywords
			String keywords = info.read(rowNumber, ImportFileParser.KEYWORDS_DATA);
			//gmDTO.setKeywordsList(getKeywords(keywords));
			gmDTO.setKeywordsList(messageManager.getKeywordsFromStringList(keywords));
			info.writeResult(rowNumber, ImportFileParser.KEYWORDS_DATA,	ImportFileParser.SUCCESFUL);
			logger.debug("Keywords: '" + gmDTO.getKeywordsList().size() + "'");
			
			// asociation type and value
			associationTypeCode = getAssociationTypeCode(info.read(rowNumber,ImportFileParser.ASOCIATION_TYPE_DATA));
			associatedToValue = info.read(rowNumber,ImportFileParser.ASOCIATED_TO_DATA);
			logger.debug("Asociation Type: '"	+ associationTypeCode + "'" +" with associated Value: '"+associatedToValue+"'.");

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
			info.writeResult(rowNumber, ImportFileParser.ASOCIATION_TYPE_DATA,ImportFileParser.SUCCESFUL);
			info.writeResult(rowNumber, ImportFileParser.ASOCIATED_TO_DATA,ImportFileParser.SUCCESFUL);

			
			// taxonomy
			taxonName = info.read(rowNumber, ImportFileParser.TAXONOMY_DATA);
			logger.debug("taxonName: '" + taxonName + "'");
			kingdom = info.read(rowNumber, ImportFileParser.TAXNOMY_KINGDOM);
			logger.debug("kingdom: '" + kingdom + "'");
			if (taxonName == null || taxonName.compareTo("") == 0) {
				// in case the media is associated with the specimen Number of
				// the gathering code the taxonomy should be associated from
				// atta. otherwise do nothing
				if (associationTypeCode.equals(AssociatedToEntity.SPECIMEN_NUMBER.getId())) {
					// TODO revisar si es necesario inicializar este arrayList pues creo
					// que
					// no hace falta.
					tlDTO = taxonomyManager.getTaxonLiteFromSpecimenId(associatedToValue);
					taxonsList.add(tlDTO);
					if(taxonsList==null || taxonsList.size()==0)
						throw new IllegalArgumentException("El número de especímen no tiene taxones asociados");


				} else if (associationTypeCode.equals(AssociatedToEntity.GATHERING_CODE.getId())) {
					taxonsList = new ArrayList<TaxonLiteDTO>();
					for (TaxonLiteDTO tlDTO2 : taxonomyManager.getTaxonLiteFromGatheringCode(associatedToValue)) {
						taxonsList.add(tlDTO2);
					}
					if(taxonsList==null || taxonsList.size()==0)
						throw new IllegalArgumentException("El código de colecta no tiene especímenes(taxones) asociados");
				}
			} else {
				// TODO revisar si es necesario inicializar este arrayList pues creo que
				// no hace falta.
				tlDTO = taxonomyManager.getTaxonLite(taxonName, kingdom);
				if(tlDTO == null)
					logger.error("no Taxon Lite was found");
				taxonsList.add(tlDTO);

			}

			gmDTO.setTaxonsList(taxonsList);
			info.writeResult(rowNumber, ImportFileParser.TAXONOMY_DATA,	ImportFileParser.SUCCESFUL);
			logger.debug("Taxonomy elements: '" + gmDTO.getTaxonsList().size() + "'");

			// this.setSynapticCollectionListValue(generalMetadata
			// .getSynopticColletion());

			
			// TODO: has to fix the siteId stuff
			// site Description
			siteDescription = info.read(rowNumber, ImportFileParser.SITE_DATA);
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
			info.writeResult(rowNumber, ImportFileParser.SITE_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Site Description: '" + gmDTO.getSiteDescription() + "'");
			logger.debug("Site Key: '" + gmDTO.getSiteKey() + "'");
			
			
			logger.debug("Getting general metadataDTO its done\n");
			logger.info(gmDTO.toString());
			
			return gmDTO;

		} catch (MediaTypeNotFoundException mtnfe) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar el Tipo de Medio: '" + mtnfe.getNotFoundMediaType() + "' en la Base de Datos.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + mtnfe.getNotFoundMediaType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(mtnfe.getMessage());
		
		} catch (ProjectNotFoundException pnfe) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar el Proyecto: '" + pnfe.getNotFoundProject() + "' en la Base de Datos.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Proyecto: '" + pnfe.getNotFoundProject() + "' en la Base de Datos.");
			throw new IllegalArgumentException(pnfe.getMessage());
		
		} catch (KeywordNotFoundException knfe) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar la Palabra Clave: '" + knfe.getNotFoundKeyword() + "' en la Base de Datos.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Palabra Clave: '" + knfe.getNotFoundKeyword() + "' en la Base de Datos.");
			throw new IllegalArgumentException(knfe.getMessage());
			
		} catch (TaxonNotFoundException tnfe) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + tnfe.getNotFoundTaxonName() + "' en la Base de Datos.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + tnfe.getNotFoundTaxonName() + "' en la Base de Datos.");
			throw new IllegalArgumentException(tnfe.getMessage());
		
		} catch (AssociationTypeNotFoundException atnfe){
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar el Tipo de Asociación: '" + atnfe.getNotFoundAssociationType()  + "'.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Taxon: '" + atnfe.getNotFoundAssociationType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(atnfe.getMessage());

		}	catch (Exception e) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " '" + e.getMessage() + "'");
			logger.error(ImportFileParser.ERROR + " '" + e.getMessage() + "'");
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * 
	 * Gets the UsesAndCopyrightsTV info directly from the importFile
	 * 
	 * @param info
	 *          the representation of the import info file
	 * @param rowNumber
	 *          the number of row being process
	 * @return a UsesAndCopyrightsTV object
	 * @throws IllegalArgumentException
	 *           if some information is wrong or in bad format
	 */
	private UsesAndCopyrightsDTO getUAC(ImportFileParser info, int rowNumber) throws IllegalArgumentException {
		UsesAndCopyrightsDTO uacDTO = new UsesAndCopyrightsDTO();
		String booleanLiteralValue;

		uacDTO.setMediaKey(null);

		logger.debug("\nGetting uses and copyrigths metadata TV");
		
		try{
	
			// set author
			String authorName = info.read(rowNumber,	ImportFileParser.AUTHOR_PERSON_NAME_DATA);
			PersonLiteDTO plDTO = agentManager.getPersonLiteByName(authorName);
			uacDTO.setAuthorKey(plDTO.getPersonKey());
			info.writeResult(rowNumber, ImportFileParser.AUTHOR_PERSON_NAME_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Author Key: '" + uacDTO.getAuthorKey() + "'");
			
			// owner type: institutionOwnerId and AuthorOwnerId
			String ownerTypeText = info.read(rowNumber, ImportFileParser.OWNER_TYPE_DATA);
			String ownerNameText = info.read(rowNumber, ImportFileParser.OWNER_FIRST_DATA);
			Integer ownerTypeId = getOwnerType(ownerTypeText);
			if (ownerTypeId.equals(OwnerEntity.INSTITUTION.getId())) {
				InstitutionLiteDTO iLiteDTO = agentManager.getInstitutionLiteByName(ownerNameText);
				uacDTO.setInstitutionOwnerKey(iLiteDTO.getInstitutionKey());
				uacDTO.setPersonOwnerKey(null);
			} else if (ownerTypeId.equals(OwnerEntity.PERSON.getId())) {
				PersonLiteDTO oplDTO = agentManager.getPersonLiteByName(ownerNameText);
				uacDTO.setPersonOwnerKey(oplDTO.getPersonKey());
				uacDTO.setInstitutionOwnerKey(null);
			} else {
				logger.error("No valid owner Type... debería tirarse una excepcion");	
			}
			info.writeResult(rowNumber, ImportFileParser.OWNER_TYPE_DATA, ImportFileParser.SUCCESFUL);
			logger.debug("Author Owner Type: '" + uacDTO.getAuthorKey() + "'");
			logger.debug("Institution Owner Type: '" + uacDTO.getInstitutionOwnerKey() + "'");
	
			// use policy
			UsePolicyDTO upDTO = messageManager.getUsePolicyByName(info.read(rowNumber, ImportFileParser.USE_POLICY_DATA));
			uacDTO.setUsePolicyKey(upDTO.getUsePolicyKey());
			info.writeResult(rowNumber, ImportFileParser.USE_POLICY_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Use policy: '" + uacDTO.getUsePolicyKey() + "'");	
			
			// mediaUses
			//String mediaUsesText = info.read(rowNumber,ImportFileParser.MEDIA_USES_DATA);
			//uacDTO.setMediaUsesList(getMediaUsesList(mediaUsesText));
			//info.writeResult(rowNumber, ImportFileParser.MEDIA_USES_DATA,ImportFileParser.SUCCESFUL);
			//logger.debug("Media Uses: '" + uacDTO.getMediaUsesList().size() + "'");
	
			// set is Public
			booleanLiteralValue = info.read(rowNumber, ImportFileParser.IS_PUBLIC_DATA);
			if(isAfirmativeText(booleanLiteralValue))
				uacDTO.setIsPublic(new Character('Y'));
			else
				uacDTO.setIsPublic(new Character('N'));
			info.writeResult(rowNumber, ImportFileParser.IS_PUBLIC_DATA,ImportFileParser.SUCCESFUL);
			logger.debug("Is public: '" + uacDTO.getIsPublic() + "'");
	
			logger.debug("\nGetting uses and copyrigths metadata its done");
			return uacDTO;
		
		}  catch (PersonNotFoundException pnfe) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar la Persona: '" + pnfe.getNotFoundPerson() + "' en la Base de Datos.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Persona: '" + pnfe.getNotFoundPerson() + "' en la Base de Datos.");
			throw new IllegalArgumentException(pnfe.getMessage());
		
		} catch (InstitutionNotFoundException infe) {
		  info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar la Institucion: '" + infe.getNotFoundInstitution()+ "' en la Base de Datos.");
		  logger.error(ImportFileParser.ERROR + " 'No se puede encontrar la Institucion: '" + infe.getNotFoundInstitution() + "' en la Base de Datos.");
		  throw new IllegalArgumentException(infe.getMessage());
		  
		} catch (OwnerTypeNotFoundException otnfe){
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar el Tipo de Dueño: '" + otnfe.getNotFoundOwnerType()  + "'.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Tipo de dueño: '" + otnfe.getNotFoundOwnerType() + "' en la Base de Datos.");
			throw new IllegalArgumentException(otnfe.getMessage());
		
		} catch (MediaUseNotFoundException munfe){
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede encontrar el Uso para el Medio: '" + munfe.getNotFoundMediaUse()  + "'.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el Uso para el Medio: '" + munfe.getNotFoundMediaUse() + "' en la Base de Datos.");
			throw new IllegalArgumentException(munfe.getMessage());

		} catch (YesNoValueNotFoundException ynvnfe){
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,ImportFileParser.ERROR + " 'No se puede reconoger el valor: '" + ynvnfe.getNotFoundYesNoValue() + "' como afirmativo o negativo.");
			logger.error(ImportFileParser.ERROR + " 'No se puede encontrar el valor: '" + ynvnfe.getNotFoundYesNoValue() + "' como afirmativo o negativo..");
			throw new IllegalArgumentException(ynvnfe.getMessage());
		}				
		
	}

	/**
	 * 
	 * @param text
	 *          to be test if says SI or NO.
	 * @return true is the text is equal to "ImportFromFile.POSITIVE_TEXT"
	 * @throws IllegalArgumentException
	 */
	private static boolean isAfirmativeText(String text) throws YesNoValueNotFoundException {

		if (text == null) {
			// logger.error("Falta valor de SI/NO");
			throw new MediaUseNotFoundException("The value ["+text+"] cannot be recognized as a yes or no", null, text);
		} else if (text.compareToIgnoreCase(ImportFromFile.POSITIVE_TEXT) == 0) {
			// logger.debug("text = true");
			return true;
		} else if (text.compareToIgnoreCase(ImportFromFile.NEGATIVE_TEXT) == 0) {
			// logger.debug("text = false");
			return false;
		} else {
			// logger.error("Falta valor de SI/NO");
			throw new MediaUseNotFoundException("The value ["+text+"] cannot be recognized as a yes or no", null, text);
		}
	}

	/**
	 * process the owner type of the media
	 * 
	 * @param literalOwnerType
	 * @return constant of the owner type from the class "MediaOwnerSelector"
	 * @throws IllegalArgumentException
	 *           if the given literal Owner Type doesnt match with the predefined
	 *           ones
	 * 
	 * TODO: inlude the language in this method. Language should be get from the
	 * constants file
	 * 
	 */
	private static Integer getOwnerType(String literalOwnerType)throws OwnerTypeNotFoundException {

		if (literalOwnerType == null)
			throw new OwnerTypeNotFoundException("the Association Type ["+literalOwnerType+"] is not valid", null, literalOwnerType);

		if (literalOwnerType.compareToIgnoreCase(OwnerEntity.INSTITUTION.getName()) == 0)
			return OwnerEntity.INSTITUTION.getId();
		else if (literalOwnerType.compareToIgnoreCase(OwnerEntity.PERSON.getName()) == 0)
			return OwnerEntity.PERSON.getId();
		else {
			throw new OwnerTypeNotFoundException("the Association Type ["+literalOwnerType+"] is not valid", null, literalOwnerType);
		}
	}

	/**
	 * process the type of asociation of the media
	 * 
	 * @param literalAssociationType
	 * @return int association type that corresponds to a constant of the
	 *         AssociatedTo class
	 * @throws IllegalArgumentException
	 *           if the given literal Association Type doesnt match with the
	 *           predefined ones
	 * 
	 * TODO: inlude the language in this method. Language should be get from the
	 * constants file
	 */
	private static Integer getAssociationTypeCode(String literalAssociationType) throws AssociationTypeNotFoundException {

		if (literalAssociationType == null)
			throw new AssociationTypeNotFoundException("the Association Type ["+literalAssociationType+"] is not valid", null, literalAssociationType);

		if (literalAssociationType.compareToIgnoreCase(ImportFromFile.SPECIMEN_NUMBER_TEXT) == 0) {
			return AssociatedToEntity.SPECIMEN_NUMBER.getId();
		} else if (literalAssociationType.compareToIgnoreCase(ImportFromFile.OBSERVATION_NUMBER_TEXT) == 0) {
			return AssociatedToEntity.OBSERVATION_NUMBER.getId();
		} else if (literalAssociationType.compareToIgnoreCase(ImportFromFile.COLLECT_NUMBER_TEXT) == 0) {
			return AssociatedToEntity.GATHERING_CODE.getId();
		} else if (literalAssociationType.compareToIgnoreCase(ImportFromFile.NO_ASSOCIATION_TEXT) == 0) {
			return AssociatedToEntity.NO_ASSOCIATION.getId();
		} else {
			throw new AssociationTypeNotFoundException("the Association Type ["+literalAssociationType+"] is not valid", null, literalAssociationType);
		}
	}

	// Texts and types of association
	private final static String SPECIMEN_NUMBER_TEXT = "Numero de especimen";

	private final static String OBSERVATION_NUMBER_TEXT = "Numero de observacion";

	private final static String COLLECT_NUMBER_TEXT = "Numero de colecta";

	private final static String NO_ASSOCIATION_TEXT = "Ninguna";


	// Texts for yes and no
	private final static String POSITIVE_TEXT = "Si";

	private final static String NEGATIVE_TEXT = "No";

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
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
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
	 * @return the metadataManager
	 */
	public MetadataManager getMetadataManager() {
		return metadataManager;
	}

	/**
	 * @param metadataManager the metadataManager to set
	 */
	public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}


	/**
	 * @return the mediaFileManagement
	 */
	public MediaFileManagement getMediaFileManagement() {
		return mediaFileManagement;
	}


	/**
	 * @param mediaFileManagement the mediaFileManagement to set
	 */
	public void setMediaFileManagement(MediaFileManagement mediaFileManagement) {
		this.mediaFileManagement = mediaFileManagement;
	}





}
