
<%@ include file="/common/taglibs.jsp"%>

<spring:message code="insert.title"/>

<h1>Insertado con �xito</h1>

id de la nueva imagen: <c:out value="${mediaId}"/>.
<br>
</br>

<%--
<tiles:insert page="imagePreview.jsp"/>
 --%>

<tiles:insert page="upload.jsp"/>