<%@ include file="/common/taglibs.jsp"%>

<form method="post" accept-charset="UTF-8" action="saveMetadata.html">

  <input type="hidden" name="fileName" value="<c:out value="${fileName}"/>" />
  <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
    
  <%--Titulo --%>
  <label>
    <b><spring:message code="metadata.label.title"/>:</b>
    <input type="text" name="title" size="40"/>
  </label>
  <br/>
  
      
      <%--Descripcion --%>
      <label>
        <b><spring:message code="metadata.label.description"/>:</b>
        <textarea name="description" rows="4" cols="50"></textarea>
      </label>
      <br/>
      
      <%--Categoria --%>
      <label>
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
      </label>
      <br/>
      
      <%--Proyectos --%>
      <label>
	    <b><spring:message code="metadata.label.projects"/>:</b>
	    <input type="text" name="projects" size="40" />
	  </label>
	  <br/>
      <%--Palabras Clave --%>
	  <label>
	    <b><spring:message code="metadata.label.key.words"/>:</b>
	    <input type="text" name="keywords" size="40"/>
	  </label>
	  <br/>
	  <%--Asociado A --%>
	  <label> 
	    <b><spring:message code="metadata.label.associated.to"/>:</b>
        <select name="associatedToValueType" id="associatedToValueTypeId" tabindex="5">
          <c:forEach items="${associatedToValues}" var="associatedTo">
            <option value=<c:out value="${associatedTo.key}"/>>
            <spring:message code="${associatedTo.nameKey}"/>
          </option>
          </c:forEach>
        </select>
        <input type="text" name="associatedToValue"/>      
      </label>
      <br/>
      
      <%--Taxnonomia --%>
      <label>
	    <b><spring:message code="metadata.label.taxonomy"/>:</b>
	    <input type="text" name="taxonomy"/>
	    <input type="text" name="taxonomyKingdom"/>
      </label>
      <br/>
      
      <%--Sitio --%>
      <label>
	    <b><spring:message code="metadata.label.site"/>:</b>
        <textarea name="siteDescription" rows="4" cols="50"></textarea>
      </label>  
      <br/>
            
      <%-- 
	  <b>Series:</b>
      <textarea name="media.description" rows="4" cols="50"></textarea>
      --%>
	  <%--
	  <b>Coleccion Sinóptica:</b>
      <textarea name="media.description" rows="1" cols="50"></textarea>
       --%>     
      <%--Autor --%>
      <label>
	    <b><spring:message code="metadata.label.author"/>:</b>
        <input type="text" name="mediaAuthor"/>
      </label>
      <br/>
      <%--Propietario --%>
      <label>
	    <b><spring:message code="metadata.label.owner"/>:</b>
        <select name="ownerType" id="ownerTypeId" tabindex="5">
          <c:forEach items="${mediaOwners}" var="owner">
            <option value=<c:out value="${owner.key}"/>>
            <spring:message code="${owner.nameKey}"/>
          </option>
          </c:forEach>
        </select>
        <input type="text" name="ownerValue"/>
      </label>
      <br/>
      <%--Politica de Uso --%>
      <label>	  
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
        </select>
      </label>
      <br/>
      
      <%--Es visible --%>
      <label>
	    <b><spring:message code="metadata.label.visible.to.all"/>:</b>
	    <input type="checkbox" name="mediaVisible" checked="checked" />
	  </label>
	  <br/> 
      
      <input type="submit" value="Guardar" />

</form>
