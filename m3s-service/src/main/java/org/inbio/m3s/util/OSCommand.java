/**
 * 
 */
package org.inbio.m3s.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * @author jgutierrez
 * 
 */
public class OSCommand {
	
	private static Logger logger = Logger.getLogger(OSCommand.class);

	/**
	 * 
	 * @param command
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 *  Do not use this method for SO dependent commands
	 */
	public static String run(String[] command) throws IllegalArgumentException {
		String output = null;
		String commadResult = "";
		logger.debug("runing: " + command);

		try {
			Process p = Runtime.getRuntime().exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));

			// read the output from the command
			while ((output = stdInput.readLine()) != null) {
				commadResult = commadResult.concat(output);
			}

			// read any errors from the attempted command
			while ((output = stdError.readLine()) != null) {
				logger.error("error \n" + output);
			}

			//System.out.println("the output is: " + commadResult);
			return commadResult;

		} catch (IOException ioe) {
			logger.error("exception happened - here's what I know: ");
			ioe.printStackTrace();
			throw new IllegalArgumentException("wrong command", ioe);
		}
	}

}
