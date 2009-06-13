/**
 * 
 */
package org.inbio.m3s.gwt.server.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.gwt.client.config.ClientProperties;

/**
 * @author jgutierrez
 * 
 */
public class ImportFileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*
		 * TODO output your page here out.println("<html>"); out.println("<head>");
		 * out.println("<title>Servlet FileUploadManager</title>");
		 * out.println("</head>"); out.println("<body>"); out.println("<h1>Servlet
		 * FileUploadManager at " + request.getContextPath () + "</h1>");
		 * out.println("</body>"); out.println("</html>");
		 */
		out.close();
	}

	/**
	 * Handles the file Upload from the client
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creates a new HttpSession so the other servlets could identify
		// the file that has been upload

		boolean multipartContent = ServletFileUpload
				.isMultipartContent(request);
		// Check that we have a file upload request
		boolean isMultipart = multipartContent;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String fileName = "";
		File cfile = null;

		// Create a factory for disk-based file items
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Parse the request
			try {
				List items = upload.parseRequest(request);

				// Process the uploaded items
				Iterator iter = items.iterator();
				while (iter.hasNext()) {

					FileItem item = (FileItem) iter.next();

					if (item.isFormField() == false) {
						cfile = new File(item.getName());

						fileName = cfile.getName();
						fileName = fileName.replace(' ', '-');
						fileName = fileName.replace('\\', '-');
						fileName = fileName.replace('/', '-');
						fileName = fileName.replace(':', '-');

						File tosave = new File(Properties.REAL_TEMP_FILES_DIR
								+ fileName);

						try {
							item.write(tosave);
						} catch (Exception ex) {
							ex.printStackTrace();
							out
									.print(ClientProperties.ERROR
											+ ".No se pudo guardar el archivo en el servidor");
						}
					}
				}
				out.print(ClientProperties.OK + "." + fileName);
			} catch (FileUploadException e) {
				e.printStackTrace();
				out
						.print(ClientProperties.ERROR
								+ ".No se encontro el archivo");
				System.out.println("ERRROR EXCEPCION VOLO: ");
			}

		} else {
			System.out.println("isMultipartContent = false");
		}

		out.close();

	}
}
