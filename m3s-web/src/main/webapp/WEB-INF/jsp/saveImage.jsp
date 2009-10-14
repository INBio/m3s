<%@ include file="/common/taglibs.jsp"%>

<spring:message code="insert.title"/>

            
  <h1>Paso 2</h1>
<!-- http://localhost:8180/m3sINBioFiles/TEMP_MEDIA_DIR/unavailable.png -->
	  <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=107851/>">
		<!-- <div class="thumb-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=107851);">-->
		<div class="thumb-image" style="background-image: url(http://coffea.inbio.priv:8180/m3sINBioFiles/TEMP_MEDIA_DIR/<c:out value="${fileName}"/>);"/>
		<div class="gwt-Label imaName"><c:out value="${fileName}"/></div>
		</a>