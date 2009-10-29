<%@ include file="/common/taglibs.jsp"%>

<div id="user-info"><a href="<c:out value="${pageContext.request.contextPath}"/>/login.html" title="<spring:message code='m3s.login'/>"><spring:message code='m3s.login'/></a> | <a href="<c:url value="/j_spring_security_logout"/>"><spring:message code='m3s.logout'/></a>
</div>