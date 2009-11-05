<%@ include file="/common/taglibs.jsp"%>

<form method="post" accept-charset="UTF-8" action="saveMetadata.html">

  <input type="hidden" name="fileName" value="<c:out value="${fileName}"/>" />
  <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
    
  <%--Titulo --%>
  <label>
    <b><spring:message code="metadata.label.title"/>:</b>
  </label>
  <label><input type="text" name="title" size="40" tabindex="1"/></label>
  <br/>
  
      
  <%--Descripcion --%>
  <label>
    <b><spring:message code="metadata.label.description"/>:</b>
  </label>
  <textarea name="description" rows="4" cols="50" tabindex="2"></textarea>      
  <br/>
      
      <%--Categoria --%>
      <label>
        <b><spring:message code="metadata.label.media.category"/>:</b>
        <select name="mediaCategories" id="mediaCategoriesId" tabindex="3">
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
	  </label>
        <label><input type="text" name="projects" size="40" tabindex="4"/></label>	  
	  <br/>
      <%--Palabras Clave --%>
	  <label>
	    <b><spring:message code="metadata.label.key.words"/>:</b>
	  </label>
      <label><input type="text" name="keywords" size="40" tabindex="5"/></label>	  
	  <br/>
	  <%--Asociado A --%>
	  <label> 
	    <b><spring:message code="metadata.label.associated.to"/>:</b>
        <select name="associatedToValueType" id="associatedToValueTypeId" tabindex="6">
          <c:forEach items="${associatedToValues}" var="associatedTo">
            <option value=<c:out value="${associatedTo.key}"/>>
            <spring:message code="${associatedTo.nameKey}"/>
          </option>
          </c:forEach>
        </select> 
      </label>
      <label><input type="text" name="associatedToValue" tabindex="7"/>  </label>   
      <br/>
      
      <%--Taxnonomia --%>
      <label>
	    <b><spring:message code="metadata.label.taxonomy"/>:</b>
      </label>
      <label><input type="text" name="taxonomy" tabindex="8"/></label>
      <label><input type="text" name="taxonomyKingdom" tabindex="9"/></label>      
      <br/>
      
      <%--Sitio --%>
      <label>
	    <b><spring:message code="metadata.label.site"/>:</b>
      </label>  
      <textarea name="siteDescription" rows="4" cols="50" tabindex="10"></textarea>      
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
      </label>
      <label><input type="text" name="mediaAuthor" tabindex="11"/></label>      
      <br/>
      <%--Propietario --%>
      <label>
	    <b><spring:message code="metadata.label.owner"/>:</b>
        <select name="ownerType" id="ownerTypeId" tabindex="12">
          <c:forEach items="${mediaOwners}" var="owner">
            <option value=<c:out value="${owner.key}"/>>
            <spring:message code="${owner.nameKey}"/>
          </option>
          </c:forEach>
        </select>
      </label>
      <label><input type="text" name="ownerValue" tabindex="13"/></label>
      <br/>
      <%--Politica de Uso --%>
      <label>	  
	    <b><spring:message code="metadata.label.use.policy"/>:</b>
	    <select name="usePolicy" id="usePolicyId" tabindex="14">
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
        <input type="checkbox" name="mediaVisible" checked="checked" tabindex="15"/>
	  </label>
	  <br/> 
      
      <input type="submit" value="Guardar" />

</form>
