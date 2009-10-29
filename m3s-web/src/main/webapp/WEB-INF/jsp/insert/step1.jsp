<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.title"/></h2>

<p>Welcome: <sec:authentication property="principal.username"/> </p>

<tiles:insert page="upload.jsp"/>