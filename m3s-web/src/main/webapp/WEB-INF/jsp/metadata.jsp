<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>

<form method="post" accept-charset="UTF-8" action="<c:out value="${formAction}"/>">

  <input type="hidden" name="fileName" value="<c:out value="${mediaId}"/>" />
  <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
  
  <%--Titulo --%>
  <label>
    <b><spring:message code="metadata.label.title"/>:</b>
  </label>
  <label>
    <input type="text" name="title" value="<c:out value="${title}"/>" size="40" tabindex="1"/>
  </label>
  <br/>
      
  <%--Descripcion --%>
  <label>
    <b><spring:message code="metadata.label.description"/>:</b>
  </label>
  <label>
    <textarea name="description" rows="4" cols="50" tabindex="2"><c:out value="${description}"/></textarea>
  </label>
  <br/>
  
  <%--Categoria --%>
  <label>
    <b><spring:message code="metadata.label.media.category"/>:</b>
    <select name="mediaCategories" id="mediaCategoriesId" tabindex="3">
      <c:forEach items="${mediaTypes}" var="mediaType">
        <option value="<c:out value="${mediaType.mediaTypeKey}"/>"<c:if test="${mediaType.mediaTypeKey == mediaCategories}"> selected="selected"</c:if>>
          <c:out value="${mediaType.mediaTypeName}"/>
        </option>
      </c:forEach>
    </select>
  </label>
  <br/> 
      
  <%--Proyectos --%>
  <label>
    <b><spring:message code="metadata.label.projects"/>:</b>
  </label>
  <label>
    <input id="projectId" name="projects" size="40" value="<c:out value="${projects}"/>" tabindex="4"/>
    <div id="projectContainer"></div>
  </label>
  <m3s:autoComplete containerId="projectContainer" inputId="projectId" url="${pageContext.request.contextPath}/ajax/projectName" multiValue="true"/>   
  <br/>
  
  <%--Palabras Clave --%>
  <label>
    <b><spring:message code="metadata.label.key.words"/>:</b>
  </label>
  <label>
    <input id="keywordId" type="text" name="keywords" size="40" value="<c:out value="${keywords}"/>" tabindex="5"/>
    <div id="keywordsContainer"></div>
  </label>
  <m3s:autoComplete containerId="keywordsContainer" inputId="keywordId" url="${pageContext.request.contextPath}/ajax/keyword" multiValue="true"/>
  <br/>
  
  <%--Asociado A --%>
  <label>
    <b><spring:message code="metadata.label.associated.to"/>:</b>
    <select name="associatedToValueType" id="associatedToValueTypeId" tabindex="6">
      <c:forEach items="${associatedToValues}" var="associatedTo">
        <option value="<c:out value="${associatedTo.key}"/>"<c:if test="${associatedTo.key == associatedToValueType}"> selected="selected"</c:if>>
          <spring:message code="${associatedTo.nameKey}"/>
        </option>
      </c:forEach>
    </select>      
  </label>
  <label>
    <input type="text" name="associatedToValue" value="<c:out value="${associatedToValue}"/>" tabindex="7"/>
  </label>
  <br/>
      
  <%--Taxonomia --%>
  <label>
    <b><spring:message code="metadata.label.taxonomy"/>:</b>
  </label>
  <label>
    <input id="taxonomyId" name="taxonomy" value="<c:out value="${taxonomy}"/>" tabindex="8"/>
    <div id="taxonomyContainer"></div>
  </label>
  <label>
    <input type="text" name="taxonomyKingdom" value="<c:out value="${taxonomyKingdom}"/>" tabindex="9"/>
  </label>
  <m3s:autoComplete containerId="taxonomyContainer" inputId="taxonomyId" url="${pageContext.request.contextPath}/ajax/taxonName" multiValue="true"/>  
  <br/>
      
  <%--Sitio --%>
  <label>
    <b><spring:message code="metadata.label.site"/>:</b>
  </label>
  <label>
    <textarea name="siteDescription" rows="4" cols="50" tabindex="10"></textarea>
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
  </label>
  <label>
    <input id="authorId" name="mediaAuthor" value="<c:out value="${mediaAuthor}"/>" tabindex="11"/>
    <div id="personContainer"></div>
  </label>
  <m3s:autoComplete containerId="personContainer" inputId="authorId" url="${pageContext.request.contextPath}/ajax/personName" />
  <br/>
  
  <%--Propietario --%>
  <label>
    <b><spring:message code="metadata.label.owner"/>:</b>
    <select name="ownerType" id="ownerTypeId" tabindex="12">
      <c:forEach items="${mediaOwners}" var="owner">
        <option value="<c:out value="${owner.key}"/>"<c:if test="${owner.key == ownerType}"> selected="selected"</c:if>>
          <spring:message code="${owner.nameKey}"/>
        </option>
      </c:forEach>
    </select>
  </label>
  <label>
    <input id="ownerId" type="text" name="ownerValue" value="<c:out value="${ownerValue}"/>" tabindex="13"/>
    <div id="ownerContainer"></div>
  </label>
  <m3s:autoComplete containerId="ownerContainer" inputId="ownerId" url="${pageContext.request.contextPath}/ajax/institutionName" />  
  <br/>
  
  <%--Politica de Uso --%>
  <label>
    <b><spring:message code="metadata.label.use.policy"/>:</b>
    <select name="usePolicy" id="usePolicyId" tabindex="14">
      <c:forEach items="${usePolicies}" var="upItem">
        <option value="<c:out value="${upItem.usePolicyKey}"/>"<c:if test="${upItem.usePolicyKey == usePolicy}"> selected="selected"</c:if>>
          <c:out value="${upItem.name}"/>
        </option>
      </c:forEach>
    </select>
  </label>
  <br/>
      
  <%--Es visible --%>
  <label>
    <b><spring:message code="metadata.label.visible.to.all"/>:</b>
  </label>
  <label>
    <input type="checkbox" name="mediaVisible" <c:if test="${not empty mediaVisible}"> checked="checked"</c:if> tabindex="15"/>
  </label>
  <br/> 
      
  <input type="submit" value="Guardar" tabindex="16"/>

</form>
