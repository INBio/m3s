<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="metadata.title"/></h2>

<c:if test="${not empty error}">
  <label>
    <font color="red">Error[sin exception] <c:out value="${error}"/></font>
  </label>
  <label>
    <font color="red">Error[properties] -> <spring:message code="${errorMessageKey}"/></font>
  </label>
</c:if>

  
<%--Titulo --%>
<label>
  <b><spring:message code="metadata.label.title"/></b>
</label>
<label>
  <c:out value="${title}"/>
</label>
<br/>
      
  <%--Descripcion --%> 
  <label>
    <b><spring:message code="metadata.label.description"/></b>
  </label>
  <label>
    <c:out value="${description}"/>
  </label>
  <br/>
  
  
  <%--Categoria --%> 
  <label>
    <b><spring:message code="metadata.label.media.category"/></b>
  </label>
  <label>
    <c:out value="${mediaType}"/>
  </label>
  <br/> 
  
      
  <%--Proyectos --%>
  <label>
    <b><spring:message code="metadata.label.projects"/></b>
  </label>
  <label>
    <c:out value="${projects}"/>
  </label>   
  <br/>
  
  
  <%--Palabras Clave --%>
  <label>
    <b><spring:message code="metadata.label.key.words"/></b>
  </label>
  <label>
    <c:out value="${keywords}"/>
  </label>
  <br/>

  
  <%--Asociado A--%> 
  <label>
    <b><spring:message code="${associatedToValueType}"/></b>
  </label>
  <label>
    <c:out value="${associatedToValue}"/>
  </label>
  <br/> 
      
  <%--Taxonomia --%>
  <label>
    <b><spring:message code="metadata.label.taxonomy"/>:</b>
  </label>
  <label>
    <c:out value="${taxonomy}"/>
  </label>
  <br/>

      
  <%--Sitio --%>   
  <label>
    <b><spring:message code="metadata.label.site"/>:</b>
  </label>
  <label>
    <c:out value="${siteDescription}"/>
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
    <c:out value="${mediaAuthor}"/>
  </label>
  <br/>
  
  <%--Propietario  --%> 
  <label>
    <b><spring:message code="metadata.label.owner"/>:</b>
  </label>
  <label>
    <c:out value="${ownerValue}"/>
  </label>
  <br/>
  
  <%--Politica de Uso --%>
  <label>
    <b><spring:message code="metadata.label.use.policy"/>:</b>
  </label>
  <label>
    <c:out value="${usePolicy}"/>
  </label>
  <br/>
  
      
  <%--Es visible --%> 
  <label>
    <b><spring:message code="metadata.label.visible.to.all"/>:</b>
  </label>
  <label>
    <input type="checkbox" name="mediaVisible" <c:if test="${not empty mediaVisible}"> checked="checked"</c:if> tabindex="15" disabled="disabled"/>
  </label>
  <br/>
  
      
      