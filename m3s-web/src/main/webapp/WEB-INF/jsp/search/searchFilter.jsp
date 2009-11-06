<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>

<h2><spring:message code="search.title"/></h2>

 
 Por favor agregue al menos un criterio de Búsqueda...
 
<%--${pageContext.request.contextPath} --%> 
<form method="get" accept-charset="UTF-8" action="<c:out value="${pageContext.request.contextPath}${formAction}"/>">
  
  <input type="hidden" name="criteria" value="0" /> <!-- criteria = 'is' -->
  <input type="hidden" name="first" value="0" /> <!-- firstPage  ${filtersMap.filters}-->
  <input type="hidden" name="last" value="10" /> <!-- lastPage --> 
   
  
  <%--Search Criteria --%>
  <label>
    <b><spring:message code="search.set.params"/>:</b>

<!-- value="<c:out value="${actualFilter}"/>" -->
    <select name="filter" id="actualFilterId" tabindex="5">
      <c:forEach items="${filtersMap.filters}" var="filter">
        <option value="<c:out value="${filter.id}"/>"<c:if test="${filter.id == actualFilter}"> selected="selected"</c:if>>
          <spring:message code="${filter.displayName}"/>
        </option>
      </c:forEach>
    </select>
  </label>
  <%--
        <span id="newFilterValue" class="value">
            <input id="statesinput" class="statesinput" name="value" value="<c:out value="${value}"/>" tabindex="7"/>
            <c:if test="${not empty filter.autoCompleteUrl}">
                <div id="statescontainer" class="statescontainer"></div>
                <!--<gbiftag:autoComplete url="${pageContext.request.contextPath}/${filter.autoCompleteUrl}" inputId="statesinput" containerId="statescontainer"/>-->
                <gbiftag:autoComplete url="${pageContext.request.contextPath}/ajax/taxonName/view/ajaxTaxonName" inputId="statesinput" containerId="statescontainer"/>
            </c:if> 
        </span>
  --%>
  
  <label>
    <input id="valueId" name="value" value="<c:out value="${value}"/>"/>
    <div id="valueContainer"/>
    <gbiftag:autoComplete containerId="valueContainer" inputId="valueId" url="${pageContext.request.contextPath}/ajax/taxonName" multiValue="true"/>
  </label>  
<%--
<span id="newFilterValue" class="value">
            <input id="statesinput" class="statesinput" name="c[0].o"  value="" tabindex="7"/>
            <c:if test="${not empty filter.autoCompleteUrl}">
                <div id="statescontainer" class="statescontainer"></div>
                <gbiftag:autoComplete url="${pageContext.request.contextPath}/${filter.autoCompleteUrl}" inputId="statesinput" containerId="statescontainer"/>
            </c:if> 
        </span> 
--%>
  
  <label>
    <input type="submit" value="Buscar" />
  </label>

</form>

<br>


<p>
Aqui debe ir una explicación del filtro y criterio de búsqueda seleccionado
</p>

<c:if test="${not empty outputMediaList}">
  <h2>Resultados</h2>
  
  
<%--  "<p>Total de resultados: "+totalResults+" (Mostrando: "+showing+")</p>"
                + "<p> "+prevResults+"  || "+nextResults+" </p>";
--%>               
<p><spring:message code="search.total.results"/>: ${totalResults} (<spring:message code="search.showing.results"/>: ${showing} <spring:message code="search.showing.from"/>: ${first} <spring:message code="search.showing.to"/>: ${last})</p>

<p>

<c:if test="${not empty previousParams}">
  <a href="${pageContext.request.contextPath}${formAction}?criteria=${criteria}&filter=${filter}&value=${value}${previousParams}">
  <spring:message code="search.previous.results"/> </a>
</c:if>
<c:if test="${empty previousParams}">
  <spring:message code="search.no.more.results"/>
</c:if>    
     ||  
<c:if test="${not empty nextParams}">
  <a href ="${pageContext.request.contextPath}${formAction}?criteria=${criteria}&filter=${filter}&value=${value}${nextParams}">
  <spring:message code="search.next.results"/> </a>
</c:if>  
<c:if test="${empty nextParams}">
  <spring:message code="search.no.more.results"/>
</c:if>
      
</p>
                   
  
  <tiles:insert page="/WEB-INF/jsp/displayThumbs.jsp"/>
</c:if>