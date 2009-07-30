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
import org.inbio.m3s.dto.message.ProjectDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.util.ServiceUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 *
 */
public class ProjectsRPCImpl extends RemoteServiceServlet implements ControlledTextRPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8406703151409380736L;
	
	private MessageManager messageManager = (MessageManager) ServiceUtil.appContext.getBean(Properties.MESSAGE_MANAGER);
	
	public List<String> getAllTexts() {
		List<ProjectDTO> projects = messageManager.getAllProjects();
		
		List<String> result = new ArrayList<String>();

		for(ProjectDTO pDTO : projects)
			result.add(pDTO.getName());

		return result;
	}

	public TextInfo getTextInfo(String text) throws ControlledTextException {
		ProjectDTO pDTO = messageManager.getProjectByName(text);

		if (pDTO != null){
			return new TextInfo(new Integer(pDTO.getProjectKey()),pDTO.getName());
		
		} else{
		throw new ControlledTextException(
					"La palabra '"+text+"' no es válida.");
		}
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
