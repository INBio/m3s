/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.gwt.controlledtext.client.dto.TextInfo;
import org.inbio.gwt.controlledtext.client.exception.ControlledTextException;
import org.inbio.gwt.controlledtext.client.rpc.ControlledTextRPC;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.util.ServiceUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 * 
 */
public class KeywordsRPCImpl extends RemoteServiceServlet implements ControlledTextRPC {

	/**	 */
	private static final long serialVersionUID = 5121913669318916491L;

	private MessageManager messageManager = (MessageManager) ServiceUtil.appContext.getBean(Properties.MESSAGE_MANAGER);
	

	/**
	 * 
	 */
	public TextInfo getTextInfo(String text) throws ControlledTextException {
		//KeywordDAO kDAO = (KeywordDAO) ServiceUtil.appContext.getBean("keywordDAO");
		//KeywordLiteDTO klDTO = kDAO.getKeywordLite(text, MessageManager.DEFAULT_LANGUAGE);
		
		KeywordDTO klDTO = messageManager.getKeywordLite(text, MessageManager.DEFAULT_LANGUAGE);

		if (klDTO != null){
			return new TextInfo(new Integer(klDTO.getKeywordKey()),klDTO.getName());
		
		} else{
		throw new ControlledTextException(
					"La palabra '"+text+"' no es válida.");
		}
	}


	/**
	 * 
	 */
	public List<String> getAllTexts() {
		
		List<KeywordDTO> keywords = messageManager.getAllKeywordLite(MessageManager.ESPANYOL);
		
		List<String> result = new ArrayList<String>();

		for(KeywordDTO kl : keywords)
			result.add(kl.getName());

		return result;
	}


	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}


	/**
	 * @return the messageManager
	 */
	public MessageManager getMessageManager() {
		return messageManager;
	}



}
