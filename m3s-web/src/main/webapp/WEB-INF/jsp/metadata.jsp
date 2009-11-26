<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>
<script src="${pageContext.request.contextPath}/javascript/mediaOwner.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/associatedValues.js" type="text/javascript" language="javascript"></script>
<%@ taglib uri="/tld/fn.tld" prefix="fn" %>


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
  </label>
  <label>    
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
    <span id="projectSpan">
      <input id="projectId" name="projects" size="40" value="<c:out value="${projects}"/>" tabindex="4"/>
      <div id="projectContainer" class="yui-skin-m3s"></div>
      <m3s:autoComplete containerId="projectContainer" inputId="projectId" url="${pageContext.request.contextPath}/ajax/projectName" multiValue="true"/>
    </span>
  </label>   
  <br/>
  
  <%--Palabras Clave --%>
  <label>
    <b><spring:message code="metadata.label.key.words"/>:</b>
  </label>
  <label>
    <input id="keywordId" type="text" name="keywords" size="40" value="<c:out value="${keywords}"/>" tabindex="5"/>
    <div id="keywordsContainer" class="yui-skin-m3s"></div>
  </label>
  <m3s:autoComplete containerId="keywordsContainer" inputId="keywordId" url="${pageContext.request.contextPath}/ajax/keyword" multiValue="true"/>
  <br/>
  
  <%--Asociado A --%>
  <label>
    <b><spring:message code="metadata.label.associated.to"/>:</b>
  </label>
  <label>    
    <select id="associatedToValueTypeId" name="associatedToValueType" onchange="javascript:changeAssociatedToValue('${pageContext.request.contextPath}');" tabindex="6">
      <c:forEach items="${associatedToValues}" var="associatedTo">
        <option value="<c:out value="${associatedTo.key}"/>"<c:if test="${associatedTo.key == associatedToValueType}"> selected="selected"</c:if>>
          <spring:message code="${associatedTo.nameKey}"/>
        </option>
      </c:forEach>
    </select>      
  </label>
  <label>
    <input id="associatedToValueId" type="text" name="associatedToValue" value="<c:out value="${associatedToValue}"/>" onchange="javascript:changeAssociatedToValue('${pageContext.request.contextPath}');"  tabindex="7"/>
  </label>
  <br/>
      
  <%--Taxonomia --%>
  <label>
    <b><spring:message code="metadata.label.taxonomy"/>:</b>
  </label>
  <label>
    <input id="taxonomyId" type="text" tabindex="8"/>
    <div id="taxonomyContainer" class="yui-skin-m3s"></div>
  </label>
  <m3s:autoComplete containerId="taxonomyContainer" inputId="taxonomyId" url="${pageContext.request.contextPath}/ajax/taxonName"/>
  <label>
    <input id="kingdomId" type="text" tabindex="9"/>
    <div id="taxonomyKingdomContainer" class="yui-skin-m3s"></div>    
  </label>
  <m3s:autoComplete containerId="taxonomyKingdomContainer" inputId="kingdomId" url="${pageContext.request.contextPath}/ajax/taxonName"/>
  <label>
    <input type="button" name="agregar" value="agregar" onclick="javascript:checkAndAddTaxonomy('${pageContext.request.contextPath}');"/>
  </label>
  <!-- el value debería ser taxonomy-->
  <input type="hidden" id="taxonomyListId" name="taxonomy" value="<c:out value="${taxonomy}"/>" />  
  <br/>
  <%-- Acá irá apareciendo la taxonomía --%>
  <label>
    <span id="taxonomy"></span>
  </label>
  <br/>
  <script type="text/javascript">
  setTaxonomy();
  </script>
      
  <%--Sitio --%>
  <label>
    <b><spring:message code="metadata.label.site"/>:</b>
  </label>
  <label>
    <textarea id="siteDescriptionId" name="siteDescription" rows="4" cols="50" tabindex="10"><c:out value="${siteDescription}"/></textarea>
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
    <div id="personContainer" class="yui-skin-m3s"></div>
  </label>
  <m3s:autoComplete containerId="personContainer" inputId="authorId" url="${pageContext.request.contextPath}/ajax/personName" />
  <br/>
  
  <%--Propietario  --%>
 <%-- mediaOwnerFilter Auto Completes--%>
  <script type="text/javascript">
    var mediaOwnerAutoCompleteUrls = new Array(${ fn:length(requestScope['mediaOwnerFilters'])});
    <c:forEach items="${requestScope['mediaOwnerFilters']}" var="mediaOwnerFilter" varStatus="filterStatus" begin="0">
      <c:if test="${not empty mediaOwnerFilter.autoCompleteUrl}">
        mediaOwnerAutoCompleteUrls[${mediaOwnerFilter.id}] = "${pageContext.request.contextPath}/${mediaOwnerFilter.autoCompleteUrl}";
      </c:if> 
    </c:forEach>
  </script>
  
  <%--label of the widget --%>
  <label>
    <b><spring:message code="metadata.label.owner"/>:</b>
  </label>
  
  <label>
    <select name="ownerType" id="ownerTypeId" tabindex="12" onchange="javascript:changeMediaOwnerInput();" onKeyUp="javascript:changeMediaOwnerInput();">
      <c:forEach items="${mediaOwnerFilters}" var="ownerFilter">
        <option value="<c:out value="${ownerFilter.id}"/>"<c:if test="${ownerFilter.id == ownerType}"> selected="selected"</c:if>>
          <spring:message code="${ownerFilter.displayName}"/>
        </option>
      </c:forEach>
    </select>
  </label> 
  
  <label>
    <span id="newOwnerValue">
      <input id="ownerId" type="text" name="ownerValue" value="<c:out value="${ownerValue}"/>" tabindex="13"/>
      <div id="ownerContainer"></div>
    </span>
  </label>
  
  <script type="text/javascript"> 
    changeMediaOwnerInput();
  </script>
  <%--<tiles:insert page="/WEB-INF/jsp/metadata/mediaOwner.jsp"/>--%>
  <br/>
  
  <%--Politica de Uso --%>
  <label>
    <b><spring:message code="metadata.label.use.policy"/>:</b>
  </label>
  <label>
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
      
  <input type="submit" value="Guardar" onclick="javascript:preSubmit();" tabindex="16"/>

</form>
