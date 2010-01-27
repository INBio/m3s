<%@ include file="/common/taglibs.jsp"%>


<div id="user-info">

 <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
  <spring:message code="m3s.login.welcome"/>: <sec:authentication property="principal.username"/> | <a href="<c:url value="/j_spring_security_logout"/>"><spring:message code='m3s.logout'/></a>
 </sec:authorize>  

 <sec:authorize ifNotGranted="ROLE_ADMIN,ROLE_USER">
    <a href="<c:out value="${pageContext.request.contextPath}"/>/login.html" title="<spring:message code='m3s.login'/>"><spring:message code='m3s.login'/>
    </a> | <a href="<c:url value="/j_spring_security_logout"/>"><spring:message code='m3s.logout'/>
    </a>
  </sec:authorize>  
  
</div>

