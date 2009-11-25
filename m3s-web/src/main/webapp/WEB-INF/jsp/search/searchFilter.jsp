<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>
<%@ taglib uri="/tld/fn.tld" prefix="fn" %>

<%-- Filter Auto Completes--%>
<script type="text/javascript">
    var autoCompleteUrls = new Array(${ fn:length(requestScope['filters'])});
    <c:forEach items="${requestScope['filters']}" var="filter" varStatus="filterStatus" begin="0">
      <c:if test="${not empty filter.autoCompleteUrl}">
        autoCompleteUrls[${filter.id}] = "${pageContext.request.contextPath}/${filter.autoCompleteUrl}";
      </c:if> 
    </c:forEach>
</script>

<%-- Filter Help -
<script type="text/javascript">
    var helpViews = new Array(${ fn:length(requestScope['filters'])});
    <c:forEach items="${requestScope['filters']}" var="filter" varStatus="filterStatus" begin="0">
    <c:if test="${not empty filter.helpView}">
        helpViews[${filter.id}] = "${pageContext.request.contextPath}<string:trim>${filter.helpView}</string:trim>";
    </c:if> 
    </c:forEach>
</script>
-%>

<h2><spring:message code="search.title"/></h2>
 
<%--${pageContext.request.contextPath} --%>
<form method="get" accept-charset="UTF-8" action="<c:out value="${pageContext.request.contextPath}${formAction}"/>">
  
  <input type="hidden" name="criteria" value="0" /> <!-- criteria = 'is' -->
  <input type="hidden" name="first" value="0" /> <!-- firstPage  ${filtersMap.filters}-->
  <input type="hidden" name="last" value="10" /> <!-- lastPage --> 
   
  
  <%--Search Criteria --%>
  <label>
    <b><spring:message code="search.set.params"/>:</b>
                

    <select name="filter" id="actualFilterId" tabindex="5" onchange="javascript:changeObjectInput(this);" onKeyUp="javascript:changeObjectInput(this);">    
      <c:forEach items="${filtersMap.filters}" var="filter">
        <option value="<c:out value="${filter.id}"/>"<c:if test="${filter.id == actualFilter}"> selected="selected"</c:if>>
          <spring:message code="${filter.displayName}"/>
        </option>
      </c:forEach>
    </select>
  </label>

  <label>  
    <span id="newFilterValue" class="value">
    <input id="valueId" class="statesinput" name="value" value="" tabindex="7"/>
  <%--
    <c:if test="${not empty filter.autoCompleteUrl}">
      <div id="statescontainer" class="statescontainer"></div>
        <gbiftag:autoComplete url="${pageContext.request.contextPath}/${filter.autoCompleteUrl}" inputId="statesinput" containerId="statescontainer"/>
    </c:if>
  --%> 
    </span>   
  </label>

  <%--
  <label>
    <input id="valueId" name="value" value="<c:out value="${value}"/>"/>
    <div id="valueContainer"></div>
  </label>  
 --%>
  
  <label>
    <input type="submit" value="Buscar" />
  </label>

<br>
<p>
<label>
<a href="http://larus.inbio.ac.cr:8090/multimedios/doku.php?id=buscar"><spring:message code="search.help"/></a><br>


</label>
<br>
</p>

</form>

<br>

<tiles:insert page="results.jsp"/>
