<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="search.title"/></h2>

 
 Por favor agregue al menos un criterio de Búsqueda...
 
<form id="filterSearchForm" name="filterSearch" accept-charset="UTF-8" method="get" action="${pageContext.request.contextPath}<c:out value="${formAction}"/>">
  
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
  <input type="text" name="value" value="<c:out value="${value}"/>"/>
  <br/>
  
  <input type="submit" value="Buscar" />

</form>

<p>
Aqui debe ir una explicación del filtro y criterio de búsqueda seleccionado
</p>

<c:if test="${not empty medias}">
  <b>Posibles Resultados deben ir acá</>
  <tiles:insert page="/WEB-INF/jsp/displayThumbs.jsp"/>
</c:if>