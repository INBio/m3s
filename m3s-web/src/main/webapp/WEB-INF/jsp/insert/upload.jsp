<%@ include file="/common/taglibs.jsp"%>

<div id="insert-upload-form">
    <form method="post" enctype="multipart/form-data" action="<c:out value="${formAction}"/>">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
      
      <%-- Radio Button  --%>
      <input type="radio" name="fileType" value="jpgImage" checked="checked">
        <spring:message code="insert.image.jpg"/>
      <br>
      <input type="radio" name="fileType" value="excel">
        <spring:message code="insert.excel.batch"/>
      <br>
      <input type="radio" name="fileType" value="video" disabled="disabled">
        <spring:message code="insert.video.mov"/>
      <br>
      
      <%-- Archivo a subir --%>  
      <input type="file" name="file" value="<spring:message code="buton.search"/>"/>      
      
      <input type="submit" value="<spring:message code="buton.continue"/>" />
    </form>
  </div>