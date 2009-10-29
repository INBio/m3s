
<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.title"/></h2>

<h1>Insertado con éxito</h1>

id de la nueva imagen: <c:out value="${mediaId}"/>.
<br>
</br>

<%--
<tiles:insert page="imagePreview.jsp"/>
 --%>

<tiles:insert page="upload.jsp"/>