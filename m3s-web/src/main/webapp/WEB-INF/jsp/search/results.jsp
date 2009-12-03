<%@ include file="/common/taglibs.jsp"%>

<c:if test="${not empty outputMediaList}">
  <h2><spring:message code="search.results"/></h2>
  
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