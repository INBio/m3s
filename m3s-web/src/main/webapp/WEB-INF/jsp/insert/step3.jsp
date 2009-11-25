
<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.title"/></h2>

<h1><spring:message code="insert.done"/></h1>

<spring:message code="insert.new.media.id"/>:<c:out value="${mediaId}"/>. 
<br>

<strong><spring:message code="insert.again"/></strong>

<tiles:insert page="upload.jsp"/>