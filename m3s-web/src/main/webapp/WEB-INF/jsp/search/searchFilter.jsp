<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>
<%@ taglib uri="/tld/fn.tld" prefix="fn" %>

<%-- Filter Auto Completes--%>
<script type="text/javascript">
    var autoCompleteUrls = new Array(${ fn:length(requestScope['filters'])});
    <c:forEach items="${requestScope['filters']}" var="actualFilter" varStatus="filterStatus" begin="0">
      <c:if test="${not empty actualFilter.autoCompleteUrl}">
        autoCompleteUrls[${actualFilter.id}] = "${pageContext.request.contextPath}/${actualFilter.autoCompleteUrl}";
      </c:if> 
    </c:forEach>
</script>


<h2><spring:message code="search.title"/></h2>
 

<c:if test="${not empty error}">
  <label>
    <font color="red"><c:out value="${error}"/></font>
  </label>
  <br>
</c:if>
<c:if test="${not empty errorMessageKey}">  
  <label>
    <font color="red"><spring:message code="${errorMessageKey}"/></font>
  </label>
  <br>
</c:if>

<form method="get" accept-charset="UTF-8" action="<c:out value="${pageContext.request.contextPath}${formAction}"/>">
  
  <input type="hidden" name="criteria" value="<c:out value="${criteria}"/>" /> <!-- criteria = 'is' -->
  <input type="hidden" id="firstId" name="first" value="<c:out value="${first}"/>" /> <!-- firstPage  ${filtersMap.filters}-->
  <input type="hidden" id="lastId" name="last" value="<c:out value="${last}"/>" /> <!-- lastPage -->
   
  
  <%--Search Criteria --%>
  <label>
    <b><spring:message code="search.set.params"/>:</b>
                

    <select name="filter" id="actualFilterId" tabindex="5" onchange="javascript:changeObjectInput(this);" onKeyUp="javascript:changeObjectInput(this);">    
      <c:forEach items="${filters}" var="actualFilter">
        <option value="<c:out value="${actualFilter.id}"/>"<c:if test="${actualFilter.id == filter}"> selected="selected"</c:if>>
          <spring:message code="${actualFilter.displayName}"/>
        </option>
      </c:forEach>
    </select>
  </label>

  <label>  
    <span id="newFilterValue" class="value">
    <input id="valueId" class="statesinput" name="value" value="<c:out value="${value}"/>" tabindex="7"/>
    </span>   
  </label>
  
  <label>
    <input type="submit" onclick="javascript:resetValue();" value="Buscar" />
  </label>
  <script type="text/javascript">
    function resetValue(){
    	var firstElement = document.getElementById('firstId');
    	var lastElement = document.getElementById('lastId');
        firstElement.value="1";
        lastElement.value="10";
    }
  </script>

<br>

<p>
  <label>
    <a href="http://larus.inbio.ac.cr:8090/multimedios/doku.php?id=buscar"><spring:message code="search.help"/></a>
    <br>
  </label>
  <br>
</p>

</form>

<br>

<c:if test="${empty outputMediaList && not empty filter && not empty value}">
    <%--<spring:message code="search.no.more.results"/>
    No Hay resultados para la búsqueda por ${value}
     --%>
    <spring:message code="search.no.results"/>
</c:if>    

<tiles:insert page="results.jsp"/>
