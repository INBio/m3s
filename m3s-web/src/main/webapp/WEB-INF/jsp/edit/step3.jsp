
<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="edit.title"/></h2>

<h1>Actualizado con éxito</h1>

id de la imagen: <c:out value="${mediaId}"/>.
<br>
</br>

<%--
<tiles:insert page="imagePreview.jsp"/>
 --%>

<tiles:insert page="upload.jsp"/>