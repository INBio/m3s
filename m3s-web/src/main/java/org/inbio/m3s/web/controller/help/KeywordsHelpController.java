/**
 * 
 */
package org.inbio.m3s.web.controller.help;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.web.controller.reusable.SimpleController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class KeywordsHelpController extends SimpleController {
	
	MessageManager messageManager;
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.web.controller.reusable.SimpleController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView mav = super.handleRequestInternal(request, response);
		
		List<KeywordDTO> kDTOList = messageManager.getAllKeywordLite(MessageManager.DEFAULT_LOCALE);
		mav.addObject("kListDTO", kDTOList);
		
		return mav;
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
}
