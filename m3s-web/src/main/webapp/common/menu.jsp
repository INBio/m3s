<%@ include file="/common/taglibs.jsp"%>

<div id="menu">
  <li>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/home.html" title="<spring:message code='m3s.menu.home'/>"><spring:message code='m3s.menu.home'/></a>
  </li>
  <li>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/insert.html" title="<spring:message code='m3s.menu.insert'/>"><spring:message code='m3s.menu.insert'/></a>
  </li>   
  <li>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/import.html" title="<spring:message code='m3s.menu.import'/>"><spring:message code='m3s.menu.import'/></a>
  </li>
  <li>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/search.html" title="<spring:message code='m3s.menu.search'/>"><spring:message code='m3s.menu.search'/></a>
  </li>
  <li>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/edit.html" title="<spring:message code='m3s.menu.edit'/>"><spring:message code='m3s.menu.edit'/></a>
  </li>
</div>