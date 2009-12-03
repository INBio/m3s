<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="edit.title"/></h2>

<h3><spring:message code="m3s.login.welcome"/>: <sec:authentication property="principal.username"/></h3>

<c:if test="${not empty errorMessageKey}">  
  <label>
    <font color="red"><spring:message code="error.title"/>: <spring:message code="${errorMessageKey}"/></font>
  </label>
  <br>
</c:if>

<tiles:insert page="upload.jsp"/>