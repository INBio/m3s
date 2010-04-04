/* M3S - multimedia management system
*
* Copyright (C) 2009  INBio - Instituto Nacional de Biodiversidad, Costa Rica
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.inbio.m3s.dao.core;


import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.model.core.Keyword;

/**
 * @author jgutierrez
 *	
 */
public interface KeywordDAO extends GenericBaseDAO<Keyword,Integer> {

	
	/*
	 * @deprecated
	 * @use findByName(final String keywordName) but be careful because the languageId parameter disappear!
	 */
	public KeywordDTO getKeywordLite(String keywordName, String locale) throws IllegalArgumentException;
	
	/**
	 * Looks for all the coincidences for the keyword name, this includes all languages and
	 * also this method will not be case sensitive
	 * 
	 * @param keywordName
	 * @return the list of Keywords that match* the name (* ignoring case and language)
	 * @throws IllegalArgumentException
	 */
	public Keyword findByName(final String keywordName) throws IllegalArgumentException;
	/*
	 * @deprecated
	 * @use findAllByMedia(final Integer mediaId) 
	 */
	public List<KeywordDTO> getAllKeywordLiteForMedia(Integer mediaId, String locale) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param mediaId
	 * @return the list of keywords for the MediaId
	 * @throws IllegalArgumentException
	 */
	public List<Keyword> findAllByMedia(final Integer mediaId)  throws IllegalArgumentException;

	
	/*
	 * @deprecated
	 * @use findAllByPartialNamePaginated(String partialKeywrod, int maxResults)
	 */
	public List<KeywordDTO> findAllByPartialNamePaginated(String partialKeywrod, int maxResults, String locale);

	/**
	 * Looks by partial name ignoring case and language.
	 * 
	 * @param partialKeywrod
	 * @param maxResults
	 * @return the list of Keywords that match* the name (* ignoring case and language)
	 */
	public List<Keyword> findAllByPartialNamePaginated(String partialKeywrod, int maxResults);
}
