<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value='/javascript/highlight.js'/>" type="text/javascript" language="javascript"></script>

<div id="menu">
  <ul>
  <%-- class="active-item" --%>
    <li> <a href="<c:out value="${pageContext.request.contextPath}"/>/home.html" title="<spring:message code='m3s.menu.home'/>"><spring:message code='m3s.menu.home'/></a> </li>
    <li> <a href="<c:out value="${pageContext.request.contextPath}"/>/insert.html" title="<spring:message code='m3s.menu.insert'/>"><spring:message code='m3s.menu.insert'/></a> </li>
    <li> <a href="<c:out value="${pageContext.request.contextPath}"/>/search/" title="<spring:message code='m3s.menu.search'/>"><spring:message code='m3s.menu.search'/></a> </li>
    <li> <a href="<c:out value="${pageContext.request.contextPath}"/>/edit.html" title="<spring:message code='m3s.menu.edit'/>"><spring:message code='m3s.menu.edit'/></a> </li>
  </ul>
</div>

<script type="text/javascript">
  setPage()
</script>
