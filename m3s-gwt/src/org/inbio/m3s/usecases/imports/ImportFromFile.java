/**
 * License must be here...
 */

package org.inbio.m3s.usecases.imports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.gwt.associatedto.client.dto.AssociatedToConstants;
import org.inbio.gwt.associatedto.client.dto.AssociatedToInfo;
import org.inbio.gwt.controlledtext.client.dto.TextInfo;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.converters.MetadataConverter;
import org.inbio.m3s.dao.core.KeywordDAO;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.dto.message.KeywordLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.MediaOwnerConstants;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.TechnicalMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.TechnicalMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.service.MediaManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.SiteManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.usecases.admin.TechnicalAttributesManager;
import org.inbio.m3s.usecases.imports.impl.ExcelImportFileParserImpl;
import org.inbio.m3s.util.MediaFileManagement;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.util.StringUtil;

/**
 * 
 * 
 * @author jgutierrez
 * @deprecated
 */
public class ImportFromFile {

	private static Logger logger = Logger.getLogger(ImportFromFile.class);

	/**
	 * This method is the one that manages the importation of images...
	 * 
	 * @param importFileName
	 *          name of the import file. The address of the file will be
	 *          'Properties.REAL_IMPORT_FILES_DIR' constant + importFileName
	 * @param fileType
	 *          what kind of file is, by now the only suported is xls
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static void ImportMedia(String importFileName, int fileType)
			throws FileNotFoundException, IOException, IllegalArgumentException {
		logger.debug("\nImportacion de archivos en lote con archivo: "
				+ importFileName);

		GeneralMetadataTV gmtv;
		GeneralMetadataDTO gm;
		UsesAndCopyrightsTV uactv;
		UsesAndCopyrightsDTO uacm;
		TechnicalMetadataTV tmtv;
		TechnicalMetadataDTO tm;
		ImportFileParser fileParser = getImportFileParser(importFileName, fileType);
		String mediaFileName;
		Integer mediaId = null;
		
		MediaManager mediaManager = (MediaManager) ServiceUtil.appContext.getBean("mediaManager");

		logger.debug("Total minimo de elementos a importar:"
				+ fileParser.getTotalEntries());

		for (int i = fileParser.getFistEntryIdex(); i <= fileParser
				.getTotalEntries(); i++) {
			try {
				logger.debug("Importando la fila#:" + i);
				mediaFileName = fileParser.read(i, ImportFileParser.FILE_NAME_DATA);
				String resultStatus = "";
				List<String> fileNames = new ArrayList<String>();

				gmtv = getGMTV(fileParser, i);
				gm = MetadataConverter.toDBValues(gmtv);
				logger.debug("Ya se obtuvieron los metadatos Generales.");
				uactv = getUACTV(fileParser, i);
				uacm = MetadataConverter.toDBValues(uactv);
				logger
						.debug("Ya se obtuvieron los metadatos De Autoría y Derechos de Uso.");

				// if true means uses wildcard. This means the general metadata and the
				// uses and copyrigth
				// values will be shared for various registries. The image will change.
				if (mediaFileName.lastIndexOf("*") != -1) {
					logger.debug("Use wildcard.");
					String folderName = Properties.REAL_BATCH_MEDIA_DIR;
					if (mediaFileName.lastIndexOf(File.separator) != -1) {
						folderName = folderName.concat(mediaFileName);
						folderName = folderName.substring(0, folderName
								.lastIndexOf(File.separator) + 1);
					}
					logger.debug("Folder: '" + folderName + "'.");
					String pattern = mediaFileName.substring(mediaFileName
							.lastIndexOf("*") + 1);
					logger.debug("pattern: '" + pattern + "'.");

					File folder = new File(folderName);
					String[] listOfFiles = folder.list();

					for (int j = 0; j < listOfFiles.length; j++) {
						if (listOfFiles[j].endsWith(pattern)) {
							logger.debug("File " + folderName + listOfFiles[j]);
							fileNames.add(folderName + listOfFiles[j]);
						}
					}
					logger.debug("Total de coincidencias: " + fileNames.size());

				} else
					fileNames.add(Properties.REAL_BATCH_MEDIA_DIR + mediaFileName);
				logger.debug("Importando " + fileNames.size()
						+ " elementos correspondientes a la fila");
				// for init
				for (String fileName : fileNames) {
					logger.debug("Extrayendo metadatos técnicos del archivo: '"
							+ fileName + "'");
					tmtv = TechnicalAttributesManager.getTechnicalMetadataTVFromFile(
							fileName, gmtv.getMediaType());
					tm = MetadataConverter.toDBValues(tmtv);

					if (MediaFileManagement.isFileReadable(fileName))
						//mediaId = MetadataManager.insertNewMedia(gm, uacm, tm);
						mediaId = mediaManager.insertNewMedia(gm, uacm, tm);
						
					else
						throw new IllegalArgumentException("file '" + fileName + "' is not accesible.");
					
					
					// FIXME: only for jpg's... not really a bug! ;).
					// organizeAndCleanFiles(mediaFileName, mediaId.toString() + ".jpg",
					// mediaId);
					// organizeAndCleanFiles(fileName, mediaId.toString() + ".jpg",
					// mediaId);

					MediaFileManagement.organizeAndCleanFiles(fileName, mediaId, gm.getMediaTypeId());

					resultStatus = resultStatus.concat("Medio guardado con exito, ID #"
							+ mediaId.toString() + ".");
				}
				// for end

				logger.info(resultStatus);
				fileParser.writeFinalResult(i, resultStatus);

			} catch (Exception e) {
				logger.error("La información presenta errores.");
				logger.error(e.getMessage());
				logger.error(e.getCause());
				fileParser.writeFinalResult(i, "La información presenta "
						+ "errores, revisar hoja de resultados.");
			}
		}

		fileParser.closeFile();
		logger.info("Importacion concluida.");
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
			int fileType) throws IllegalArgumentException {

		try {
			if (fileType == ImportFromFile.MS_EXCEL_FILE) {
				logger.debug("Importacion desde un archivo de Excel");
				return new ExcelImportFileParserImpl(Properties.REAL_IMPORT_FILES_DIR
						+ importFileName);
			} else {
				logger.error("Tipo de archivo desconocido para la importacion.");
				throw new IllegalArgumentException("the fileType argument is invalid.");
			}
		} catch (Exception e) {
			logger.error("Error grave intentando abrir el archivo de importacion.");
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
	private static GeneralMetadataTV getGMTV(ImportFileParser info, int rowNumber)
			throws IllegalArgumentException {
		GeneralMetadataTV gmtv = new GeneralMetadataTV();
		Integer associationTypeCode;
		String associatedToValue = null;
		String siteDescription;
		String taxonName;
		String kingdom;
		List<Integer> taxonIds = new ArrayList<Integer>();
		List<String> literalTaxonIds = new ArrayList<String>();
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean("taxonomyManager");
		TaxonLiteDTO tlDTO = null;
		List<TextInfo> keywordsTV;
		SiteDAO siteDAO = (SiteDAO) ServiceUtil.appContext.getBean("INBioSiteDAO");
		SiteManager siteManager = (SiteManager) ServiceUtil.appContext.getBean("siteManager");

		gmtv.setMediaId(null);

		logger.debug("\nGetting general metadata TV");

		try {
			gmtv.setTitle(info.read(rowNumber, ImportFileParser.TITLE_DATA));
			info.writeResult(rowNumber, ImportFileParser.TITLE_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Title: '" + gmtv.getTitle() + "'");

			gmtv.setDescription(info.read(rowNumber,
					ImportFileParser.DESCRIPTION_DATA));
			info.writeResult(rowNumber, ImportFileParser.DESCRIPTION_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Description: '" + gmtv.getDescription() + "'");

			gmtv.setMediaCategory(info.read(rowNumber,
					ImportFileParser.MEDIA_CATEGORY_DATA));
			info.writeResult(rowNumber, ImportFileParser.MEDIA_CATEGORY_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Media Category: '" + gmtv.getMediaCategory() + "'");

			gmtv.setMediaType(info.read(rowNumber, ImportFileParser.MEDIA_TYPE_DATA));
			info.writeResult(rowNumber, ImportFileParser.MEDIA_TYPE_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Media Type: '" + gmtv.getMediaType() + "'");

			// asociation type and value
			associationTypeCode = getAssociationTypeCode(info.read(rowNumber,
					ImportFileParser.ASOCIATION_TYPE_DATA));
			//gmtv.setAsociationType(associationTypeCode);
			info.writeResult(rowNumber, ImportFileParser.ASOCIATION_TYPE_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Asociation Type: '"
					+ info.read(rowNumber, ImportFileParser.ASOCIATION_TYPE_DATA) + "'");

			// associated To value
			if (associationTypeCode.equals(AssociatedToConstants.NO_ASSOCIATION) == true) {
				gmtv.setAssociatedToInfo(new AssociatedToInfo(associationTypeCode, "")) ;
			} else {
				associatedToValue = info.read(rowNumber, ImportFileParser.ASOCIATED_TO_DATA);
				gmtv.setAssociatedToInfo(new AssociatedToInfo(associationTypeCode, associatedToValue)) ;

				info.writeResult(rowNumber, ImportFileParser.ASOCIATED_TO_DATA,
						ImportFileParser.SUCCESFUL);
				logger.debug("Asociated To: '" + gmtv.getAssociatedToInfo().getValue() + "'");
			}

			// projects
			gmtv.setProjects(info.read(rowNumber, ImportFileParser.PROJECTS_DATA));
			info.writeResult(rowNumber, ImportFileParser.PROJECTS_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Projects: '" + gmtv.getProjects() + "'");

			// taxonomy
			taxonName = info.read(rowNumber, ImportFileParser.TAXONOMY_DATA);
			kingdom = info.read(rowNumber, ImportFileParser.TAXNOMY_KINGDOM);
			if (taxonName == null || taxonName.compareTo("") == 0) {
				// in case the media is associated with the specimen Number of
				// the gathering code the taxonomy should be associated from
				// atta. otherwise do nothing
				if (associationTypeCode.equals(AssociatedToConstants.SPECIMEN_NUMBER)) {
					// TODO revisar si es necesario inicializar este arrayList pues creo
					// que
					// no hace falta.
					taxonIds = new ArrayList<Integer>();
					tlDTO = taxonomyManager.getTaxonLiteFromSpecimenId(associatedToValue);
					taxonIds.add(new Integer(tlDTO.getTaxonKey()));

				} else if (associationTypeCode.equals(AssociatedToConstants.GATHERING_CODE)) {
					taxonIds = new ArrayList<Integer>();
					for (TaxonLiteDTO tlDTO2 : taxonomyManager.getTaxonLiteFromGatheringCode(associatedToValue)) {
						taxonIds.add(new Integer(tlDTO2.getTaxonKey()));
					}
				}
			} else {
				// TODO revisar si es necesario inicializar este arrayList pues creo que
				// no hace falta.
				taxonIds = new ArrayList<Integer>();
				tlDTO = taxonomyManager.getTaxonLite(taxonName, kingdom);
				taxonIds.add(new Integer(tlDTO.getTaxonKey()));

			}
			for (int i = 0; i < taxonIds.size(); i++) {
				literalTaxonIds.add((String) taxonIds.get(i).toString());
			}
			gmtv.setTaxonomyInfo(literalTaxonIds);
			info.writeResult(rowNumber, ImportFileParser.TAXONOMY_DATA,
					ImportFileParser.SUCCESFUL);
			logger
					.debug("Taxonomy elements: '" + gmtv.getTaxonomyInfo().size() + "'");

			// this.setSynapticCollectionListValue(generalMetadata
			// .getSynopticColletion());
			// keywords

			keywordsTV = getKeywords(info.read(rowNumber,
					ImportFileParser.KEYWORDS_DATA));
			gmtv.setKeyWords(keywordsTV);
			info.writeResult(rowNumber, ImportFileParser.KEYWORDS_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Keywords: '" + gmtv.getKeyWords() + "'");

			// TODO: has to fix the siteId stuff
			// site Description
			siteDescription = info.read(rowNumber, ImportFileParser.SITE_DATA);
			if (siteDescription == null || siteDescription.compareTo("") == 0) {

				if (associationTypeCode.equals(AssociatedToConstants.SPECIMEN_NUMBER)) {
					siteDescription = siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(
							associatedToValue));
				} else if (associationTypeCode.equals(AssociatedToConstants.OBSERVATION_NUMBER)) {
					siteDescription = siteDAO
							.getiteDBIdFromObservationNumber(new Integer(associatedToValue));
				} else if (associationTypeCode.equals(AssociatedToConstants.GATHERING_CODE)) {
					siteDescription = siteManager.getSiteFromGatheringCode(associatedToValue);
				}
			}
			gmtv.setSiteDescription(siteDescription);
			info.writeResult(rowNumber, ImportFileParser.SITE_DATA,
					ImportFileParser.SUCCESFUL);
			logger.debug("Site Description: '" + gmtv.getSiteDescription() + "'");

			logger.debug("Getting general metadata TV its done\n");
			return gmtv;

		} catch (Exception e) {
			info.writeResult(rowNumber, ImportFileParser.FINAL_RESULT,
					ImportFileParser.ERROR + " '" + e.getMessage() + "'");
			logger.debug(ImportFileParser.ERROR + " '" + e.getMessage() + "'");

			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Parsea el texto que viene del archivo excell, que tiene una estructura de
	 * valores separados por ';' y devuelve una lista de objetos TextInfo.
	 * 
	 * @param textualKeywords
	 *          String values separated by the default delimiter. (probably ';')
	 * @return
	 */
	private static List<TextInfo> getKeywords(String textualKeywords) {
		
		KeywordDAO kDAO = (KeywordDAO) ServiceUtil.appContext.getBean("keywordDAO");
		KeywordLiteDTO klDTO;
		List<TextInfo> keywords = new ArrayList<TextInfo>();
		
		List<Object> separatedValues = StringUtil.getIndividualItems(textualKeywords,
				java.lang.String.class);
		
		for(Object elem : separatedValues){
			klDTO = kDAO.getKeywordLite((String)elem, MessageManager.DEFAULT_LANGUAGE);
			keywords.add(new TextInfo(new Integer(klDTO.getKeywordKey()), klDTO.getName()));
		}
		
		return keywords;
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
	private static UsesAndCopyrightsTV getUACTV(ImportFileParser info,
			int rowNumber) throws IllegalArgumentException {
		UsesAndCopyrightsTV uactv = new UsesAndCopyrightsTV();
		String booleanLiteralValue;
		String ownerTypeText;

		uactv.setMediaId(null);

		logger.debug("\nGetting uses and copyrigths metadata TV");

		// set author
		uactv.setAuthor(info.read(rowNumber,
				ImportFileParser.AUTHOR_PERSON_NAME_DATA));
		info.writeResult(rowNumber, ImportFileParser.AUTHOR_PERSON_NAME_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Author Name: '" + uactv.getAuthor() + "'");

		// owner type
		ownerTypeText = info.read(rowNumber, ImportFileParser.OWNER_TYPE_DATA);
		uactv.setOwnerType(getOwnerType(ownerTypeText));
		info.writeResult(rowNumber, ImportFileParser.OWNER_TYPE_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Owner Type: '" + uactv.getOwnerType() + "'");

		// owner value
		uactv.setOwner(info.read(rowNumber, ImportFileParser.OWNER_FIRST_DATA));
		info.writeResult(rowNumber, ImportFileParser.OWNER_FIRST_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Owner Value: '" + uactv.getOwner() + "'");

		// use policy
		uactv.setUsePolicy(info.read(rowNumber, ImportFileParser.USE_POLICY_DATA));
		info.writeResult(rowNumber, ImportFileParser.USE_POLICY_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Use policy: '" + uactv.getUsePolicy() + "'");

		// mediaUses
		uactv.setMultimediaUses(info.read(rowNumber,
				ImportFileParser.MEDIA_USES_DATA));
		info.writeResult(rowNumber, ImportFileParser.MEDIA_USES_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Media Uses: '" + uactv.getMultimediaUses() + "'");

		// backup value
		booleanLiteralValue = info.read(rowNumber, ImportFileParser.IS_BACKUP_DATA);
		uactv.setIsBackup(isAfirmativeText(booleanLiteralValue));
		info.writeResult(rowNumber, ImportFileParser.IS_BACKUP_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Is backup: '" + uactv.getIsBackup() + "'");

		// set is Public
		booleanLiteralValue = info.read(rowNumber, ImportFileParser.IS_PUBLIC_DATA);
		uactv.setIsPublic(isAfirmativeText(booleanLiteralValue));
		info.writeResult(rowNumber, ImportFileParser.IS_PUBLIC_DATA,
				ImportFileParser.SUCCESFUL);
		logger.debug("Is public: '" + uactv.getIsPublic() + "'");

		logger.debug("\nGetting uses and copyrigths metadata TV its done");
		return uactv;
	}

	/**
	 * 
	 * @param text
	 *          to be test if says SI or NO.
	 * @return true is the text is equal to "ImportFromFile.POSITIVE_TEXT"
	 * @throws IllegalArgumentException
	 */
	private static boolean isAfirmativeText(String text)
			throws IllegalArgumentException {

		if (text == null) {
			// logger.error("Falta valor de SI/NO");
			throw new IllegalArgumentException("Falta valor de SI/NO");
		} else if (text.compareToIgnoreCase(ImportFromFile.POSITIVE_TEXT) == 0) {
			// logger.debug("text = true");
			return true;
		} else if (text.compareToIgnoreCase(ImportFromFile.NEGATIVE_TEXT) == 0) {
			// logger.debug("text = false");
			return false;
		} else {
			// logger.error("Falta valor de SI/NO");
			throw new IllegalArgumentException("Falta valor de SI/NO");
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
	private static Integer getOwnerType(String literalOwnerType)
			throws IllegalArgumentException {

		if (literalOwnerType == null) {
			// info.writeResult(entryNum, "Falta el tipo de propietario");
			throw new IllegalArgumentException("Falta el tipo de propietario");
		}

		if (literalOwnerType
				.compareToIgnoreCase(ImportFromFile.OWNER_INSTITUTION_TEXT) == 0)
			return MediaOwnerConstants.OWNER_INSTITUTION;
		else if (literalOwnerType
				.compareToIgnoreCase(ImportFromFile.OWNER_PERSON_TEXT) == 0)
			return MediaOwnerConstants.OWNER_PERSON;
		else {
			// info.writeResult(entryNum, "Tipo de propietario invalido");
			throw new IllegalArgumentException("Tipo de propietario invalido");
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
	private static Integer getAssociationTypeCode(String literalAssociationType)
			throws IllegalArgumentException {

		if (literalAssociationType == null) {
			throw new IllegalArgumentException("invalid Association Type");
		}

		if (literalAssociationType
				.compareToIgnoreCase(ImportFromFile.SPECIMEN_NUMBER_TEXT) == 0) {
			return AssociatedToConstants.SPECIMEN_NUMBER;
		} else if (literalAssociationType
				.compareToIgnoreCase(ImportFromFile.OBSERVATION_NUMBER_TEXT) == 0) {
			return AssociatedToConstants.OBSERVATION_NUMBER;
		} else if (literalAssociationType
				.compareToIgnoreCase(ImportFromFile.COLLECT_NUMBER_TEXT) == 0) {
			return AssociatedToConstants.GATHERING_CODE;
		} else if (literalAssociationType
				.compareToIgnoreCase(ImportFromFile.NO_ASSOCIATION_TEXT) == 0) {
			return AssociatedToConstants.NO_ASSOCIATION;
		} else {
			throw new IllegalArgumentException("invalid Association Type");
		}
	}

	// types of files
	public final static int MS_EXCEL_FILE = 0;

	// Texts and types of association
	private final static String SPECIMEN_NUMBER_TEXT = "Numero de especimen";

	private final static String OBSERVATION_NUMBER_TEXT = "Numero de observacion";

	private final static String COLLECT_NUMBER_TEXT = "Numero de colecta";

	private final static String NO_ASSOCIATION_TEXT = "Ninguna";

	// Texts and types of owners
	private final static String OWNER_INSTITUTION_TEXT = "Institucion";

	private final static String OWNER_PERSON_TEXT = "Persona";

	// Texts for yes and no
	private final static String POSITIVE_TEXT = "Si";

	private final static String NEGATIVE_TEXT = "No";



}
