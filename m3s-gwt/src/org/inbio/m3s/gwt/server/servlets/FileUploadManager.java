package org.inbio.m3s.gwt.server.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.gwt.client.config.ClientProperties;

/**
 * 
 * @author jgutierrez
 * @version
 */
public class FileUploadManager extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3683098268355992943L;

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
		HttpSession session = request.getSession(true);

		boolean multipartContent = ServletFileUpload
				.isMultipartContent(request);
		// Check that we have a file upload request
		boolean isMultipart = multipartContent;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String fileId = "";
		String fileName = "";
		File cfile = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-");
		java.util.Date date = new java.util.Date();

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

						fileId = dateFormat.format(date) + session.getId()
								+ fileName;

						File tosave = new File(Properties.REAL_TEMP_FILES_DIR
								+ fileId);

						try {
							item.write(tosave);
						} catch (Exception ex) {
							ex.printStackTrace();
							out
									.print(ClientProperties.ERROR
											+ ".No se pudo guardar el archivo en el servidor");
							out.close();
						}
					}
				}
				out.print(ClientProperties.OK + "." + fileId);
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

	/**
	 * Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "File upload servlet";
	}

	// </editor-fold>

	/**
	 * Handles write on disk of the upload file, makes an unique name for the
	 * uploaded file which is made of the sessionID + Name of the upload File.
	 * 
	 * @param fileItem:
	 *            that represents the file to write
	 * @param sessionID:
	 *            in orden to make an unique name for the file
	 * @return true on success, false otherwise.
	 * @deprecated
	 */
	// private boolean processUploadedFile(FileItem item, String sessionID) {
	// File cfile = new File(item.getName());
	// System.out.println("its a file: " + sessionID + cfile.getName());
	// File tosave = new File(getServletContext().getRealPath("/")
	// + Properties.TEMP_FILES_DIR, sessionID + cfile.getName());
	// try {
	// item.write(tosave);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// return false;
	// }
	// System.out.println("its a file2: " + sessionID + cfile.getName());
	// return true;
	// }
	private void processFormField(FileItem item) {
		System.out.println("accccccccccccccccccccccca");
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
