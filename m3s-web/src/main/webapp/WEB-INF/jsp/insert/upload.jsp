<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/javascript/upload.js" type="text/javascript" language="javascript"></script>

<div id="insert-upload-form">
    <form method="post" enctype="multipart/form-data" action="<c:out value="${formAction}"/>">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
      
      <%-- Radio Buttons  --%>      
      <input type="radio" name="fileType" value="jpgImage" checked="checked" onclick="javascript:viewJpgImageHelp()" >
        <spring:message code="insert.image.jpg"/>
      <br>
      <input type="radio" name="fileType" value="excel" onclick="javascript:viewImportationHelp()" >
        <spring:message code="insert.excel.batch"/>
      <br>
      <input type="radio" name="fileType" value="video" disabled="disabled">
        <spring:message code="insert.video.mov"/>
      <br>
      </div>
      
      <%-- Archivo a subir --%>  
      <input type="file" name="file" value="<spring:message code="buton.search"/>"/>      
      
      <input type="submit" value="<spring:message code="buton.continue"/>" />
      <br>

      <div id="uploadHelpDiv">
      
        <div id="jpgImageHelpDiv" style="display: none">      
          <p>
            <label>
              <spring:message code="insert.jpg.help1"/>
              <br>
            </label>
            <br>
          </p>
        </div>

        <div id="importationHelpDiv" style="display: none">      
          <p>
            <label>
              <spring:message code="insert.excel.help1"/>
              <br>
              <spring:message code="insert.excel.help2"/>
              <br>            
              <a href="<c:out value="${pageContext.request.contextPath}"/>/excel.html?username=<sec:authentication property="principal.username"/>"><spring:message code="insert.excel.view.history"/></a>
              <br>
            </label>
            <br>
          </p>
        </div>
      </div>  
    </form>
  </div>