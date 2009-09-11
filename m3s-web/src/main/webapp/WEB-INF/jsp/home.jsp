<%@ include file="/common/taglibs.jsp"%>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="85%" align="left" valign="top" class="MainPanel">
      
      <!-- Titulo de la Pagina-->
      <table width="100%"  border="0" cellpadding="10" cellspacing="0">
        <tr>
          <td height="28" colspan="2" class="MainPanel-Title">
            <spring:message code="home.title"/>
          </td>
        </tr>

	    <!-- Zona de los paneles centrales -->
	    <tr>
	      <!-- Panel Izquierdo -->
	      <td width="51%" height="65" align="left" valign="top">
		      <div class="contenido" id="layer1">
		        <p>
		          <a href="#">Ayuda*</a><br>
		          <a href="#">Pol&iacute;ticas de uso*</a><br>
		          <a href="#">Protocolo de captura de informaci&oacute;n*</a>
		        </p>
		        <div id="leftPanelContentDiv"></div>
		      </div>
		    </td>
		      
		    <!-- Panel derecho -->
		    <td width="49%" align="left" valign="top">
		      <div class="contenido" id="layer1">
		        <p class="Home-RightPanelTitle">
		          <strong><spring:message code="home.preview.subtitle"/></strong>
		        </p>
		        <div id="rightPanelTitleDiv"></div>
		        <ul>
		          <c:forEach items="${medias}" var="media">
		            <li>
		              <div class="imagesRightPanel">
		                <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${media.mediaId}"/>">
		                  <div class="thumb-image">
		                   <img src="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${media.mediaId}"/>">
		                  </div>
						  <div class="gwt-Label imaName"><c:out value="${media.authorPersonId}"/></div>
						  <div class="imaInfo"><c:out value="${media.title}"/></div>
						  <div class="imaInfo"><c:out value="${media.title}"/></div>
						</a>
				      </div>
		            </li>
		          </c:forEach>
		        </ul>
		      </div>
		    </td>
		      
		  </tr> <!-- FIN de Zona de los paneles centrales -->
</table>

    </td>
  </tr>
</table>