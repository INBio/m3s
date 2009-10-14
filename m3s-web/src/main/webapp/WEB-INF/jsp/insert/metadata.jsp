<%@ include file="/common/taglibs.jsp"%>

<%--
<%@page import="java.util.List"%>
<%@page import="org.inbio.m3s.dto.message.MediaTypeDTO"%>
		List<MediaTypeDTO> mediaTypes = (List<MediaTypeDTO>) request.getAttribute("mediaTypes");
		pageContext.setAttribute("mediaTypes", mediaTypes);		 
--%>	

 

<%--
<spring:message code="insert.title"/>Este titulo no va!
--%>

  <div id="insert-metadata-form">
    <form method="post" accept-charset="UTF-8" action="saveMetadata.html">
    
    <input type="hidden" name="fileName" value="<c:out value="${fileName}"/>" />
<%--
	<fieldset>
		<b>Protocolo:</b>
		<input style="background-color: #ced9aa; id="harvestingProtocol" type="search" name="harvestingProtocol" <c:if test="${not empty searchString}"> value="${searchString}"</c:if> placeholder="<spring:message code="blanket.search.placeholder"/>" autosave="gbif.blanketsearch1" results="5" tabindex="1" size="50"/>
	</fieldset>	
 --%>


	  <%--Titulo --%>
      <b><spring:message code="metadata.label.title"/>:</b>
      <input type="text" name="title" size="40"/><br/>
      <%--Descripcion --%>
      <b><spring:message code="metadata.label.description"/>:</b>
      <textarea name="description" rows="4" cols="50"></textarea><br/>
      
      <%--Categoria --%>
      <b><spring:message code="metadata.label.media.category"/>:</b>
      <select name="mediaCategories" id="mediaCategoriesId" tabindex="5">
        <c:forEach items="${mediaTypes}" var="mediaType">
          <option value=<c:out value="${mediaType.mediaTypeKey}"/>>
          <c:out value="${mediaType.mediaTypeName}"/>
          <%--
          <spring:message code="${mediaType.mediaTypeName}"/>
          --%>
        </option>
        </c:forEach>
      </select>
      <br/>
      
      <%--Proyectos --%>
	  <b><spring:message code="metadata.label.projects"/>:</b>
	  <input type="text" name="projects" size="40" />
	  <br/>
      <%--Palabras Clave --%>
	  <b><spring:message code="metadata.label.key.words"/>:</b>
	  <input type="text" name="keywords" size="40"/>
	  <br/>
	  <%--Asociado A --%> 
	  <b><spring:message code="metadata.label.associated.to"/>:</b>
      <select name="associatedToValueType" id="associatedToValueTypeId" tabindex="5">
        <c:forEach items="${associatedToValues}" var="associatedTo">
          <option value=<c:out value="${associatedTo.key}"/>>
          <spring:message code="${associatedTo.nameKey}"/>
        </option>
        </c:forEach>
      </select>
      <input type="text" name="associatedToValue"/>      
      <br/>
      <%--Taxnonomia --%>
	  <b><spring:message code="metadata.label.taxonomy"/>:</b>
	  <input type="text" name="taxonomy"/>
	  <input type="text" name="taxonomyKingdom"/>
      <br/>
      <%--Sitio --%>
	  <b><spring:message code="metadata.label.site"/>:</b>
      <textarea name="siteDescription" rows="4" cols="50"></textarea><br/>


            
      <%-- 
	  <b>Series:</b>
      <textarea name="media.description" rows="4" cols="50"></textarea>
      --%>
	  <%--
	  <b>Coleccion Sinóptica:</b>
      <textarea name="media.description" rows="1" cols="50"></textarea>
       --%>     
      <%--Autor --%>
	  <b><spring:message code="metadata.label.author"/>:</b>
      <input type="text" name="mediaAuthor"/>
      <br/>
      <%--Propietario --%>
	  <b><spring:message code="metadata.label.owner"/>:</b>
      <select name="ownerType" id="ownerTypeId" tabindex="5">
        <c:forEach items="${mediaOwners}" var="owner">
          <option value=<c:out value="${owner.key}"/>>
          <spring:message code="${owner.nameKey}"/>
        </option>
        </c:forEach>
      </select>
      <input type="text" name="ownerValue"/>
      <br/>
      <%--Politica de Uso --%>	  
	  <b><spring:message code="metadata.label.use.policy"/>:</b>
	  <select name="usePolicy" id="usePolicyId" tabindex="5">
        <c:forEach items="${usePolicies}" var="usePolicy">
          <option value=<c:out value="${usePolicy.usePolicyKey}"/>>
          <c:out value="${usePolicy.name}"/>
          <%-- 
          <spring:message code="${usePolicy.name}"/>
           --%>
        </option>
        </c:forEach>
      </select><br/>
      <%--Es visible --%>
	  <b><spring:message code="metadata.label.visible.to.all"/>:</b>
	  <input type="checkbox" name="mediaVisible" checked="checked" /><br/> 
      
      <input type="submit" value="Guardar" />

    </form>
  </div>
