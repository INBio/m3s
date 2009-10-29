<%@ include file="/common/taglibs.jsp"%>

<form method="post" accept-charset="UTF-8" action="<c:out value="${formAction}"/>">

  <input type="hidden" name="fileName" value="<c:out value="${mediaId}"/>" />
  <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
  
  <%--Titulo --%>
  <label>
    <b><spring:message code="metadata.label.title"/>:</b>
    <input type="text" name="title" value="<c:out value="${title}"/>" size="40"/>
  </label>
  <br/>
      
  <%--Descripcion --%>
  <label>
    <b><spring:message code="metadata.label.description"/>:</b>
    <textarea name="description" rows="4" cols="50"><c:out value="${description}"/></textarea>
  </label>
  <br/>
  
  <%--Categoria --%>
  <label>
    <b><spring:message code="metadata.label.media.category"/>:</b>
    <select name="<c:out value="${mediaCategories}"/>" id="mediaCategoriesId" tabindex="5">
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
    <input type="text" name="projects" size="40" value="<c:out value="${projects}"/>" />
  </label>
  <br/>
  
  <%--Palabras Clave --%>
  <label>
    <b><spring:message code="metadata.label.key.words"/>:</b>
    <input type="text" name="keywords" size="40" value="<c:out value="${keywords}"/>" />
  </label>
  <br/>
  
  <%--Asociado A --%>
  <label>
    <b><spring:message code="metadata.label.associated.to"/>:</b>
    <select name="<c:out value="${associatedToValueType}"/>" id="associatedToValueTypeId" tabindex="5">
      <c:forEach items="${associatedToValues}" var="associatedTo">
        <option value="<c:out value="${associatedTo.key}"/>"<c:if test="${associatedTo.key == associatedToValueType}"> selected="selected"</c:if>>
          <spring:message code="${associatedTo.nameKey}"/>
        </option>
      </c:forEach>
    </select>
    <input type="text" name="associatedToValue" value="<c:out value="${associatedToValue}"/>"/>      
  </label>
  <br/>
      
  <%--Taxnonomia --%>
  <label>
    <b><spring:message code="metadata.label.taxonomy"/>:</b>
    <input type="text" name="taxonomy" value="<c:out value="${taxonomy}"/>" />
    <input type="text" name="taxonomyKingdom" value="<c:out value="${taxonomyKingdom}"/>" />
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
    <input type="text" name="mediaAuthor" value="<c:out value="${mediaAuthor}"/>"/>
  </label>
  <br/>
  
  <%--Propietario --%>
  <label>
    <b><spring:message code="metadata.label.owner"/>:</b>
    <select name="<c:out value="${ownerType}"/>" id="ownerTypeId" tabindex="5">
      <c:forEach items="${mediaOwners}" var="owner">
        <option value="<c:out value="${owner.key}"/>"<c:if test="${owner.key == ownerType}"> selected="selected"</c:if>>
          <spring:message code="${owner.nameKey}"/>
        </option>
      </c:forEach>
    </select>
    <input type="text" name="ownerValue" value="<c:out value="${ownerValue}"/>"/>
  </label>
  <br/>
  
  <%--Politica de Uso --%>
  <label>
    <b><spring:message code="metadata.label.use.policy"/>:</b>
    <select name="<c:out value="${usePolicy}"/>" id="usePolicyId" tabindex="5">
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
    <input type="checkbox" name="mediaVisible" <c:if test="${not empty mediaVisible}"> checked="checked"</c:if>/>
  </label>
  <br/> 
      
  <input type="submit" value="Guardar" />

</form>
