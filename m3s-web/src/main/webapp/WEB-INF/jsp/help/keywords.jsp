<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="help.keywords.title"/></h2>


<c:forEach items="${kListDTO}" var="keyword">

  <label>
    <b><c:out value="${keyword.keywordKey}"/></b>
  </label>

  <label>
    <c:out value="${keyword.name}"/>
  </label>
  <br/>  
  
</c:forEach>
