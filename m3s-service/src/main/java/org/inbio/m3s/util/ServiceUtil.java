/**
 * 
 */
package org.inbio.m3s.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jgutierrez
 * 
 */
public class ServiceUtil {

	private static String[] locations = {
		"classpath*:org/inbio/m3s/dao/applicationContext-dao-ara.xml",
		"classpath*:org/inbio/m3s/dao/applicationContext-dao-atta.xml",
		"classpath*:org/inbio/m3s/dao/applicationContext-dao.xml",
		"classpath*:org/inbio/m3s/dao/applicationContext-factories.xml",
		"classpath*:org/inbio/m3s/dto/applicationContext-factories.xml",
		"classpath*:org/inbio/m3s/service/applicationContext-service-ara.xml",
		"classpath*:org/inbio/m3s/service/applicationContext-service-atta.xml",
		"classpath*:org/inbio/m3s/service/applicationContext-service.xml",
		"classpath*:org/inbio/m3s/util/applicationContext-util.xml"
	};
	
	public static ApplicationContext appContext = new ClassPathXmlApplicationContext(locations);
	
}
