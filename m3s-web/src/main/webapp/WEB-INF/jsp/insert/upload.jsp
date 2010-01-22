<%@ include file="/common/taglibs.jsp"%>
<script src="${pageContext.request.contextPath}/javascript/upload.js" type="text/javascript" language="javascript"></script>

<div id="insert-upload-form">
      
      <%-- Radio Buttons  --%>      
      <input type="radio" name="fileType" value="jpgImage" checked="checked" onclick="javascript:viewJpgImageDivs()" >
        <spring:message code="insert.image.jpg"/>
      <br>
      <input type="radio" name="fileType" value="excel" onclick="javascript:viewImportationDivs()" >
        <spring:message code="insert.excel.batch"/>
      <br>
      <input type="radio" name="fileType" value="video" disabled="disabled">
        <spring:message code="insert.video.mov"/>
      <br>

      
      <%--imagenes --%>
      <div id="jpgImageFormDiv" style="display: none">
        <form method="post" enctype="multipart/form-data" action="uploadImages.html">
          <%-- Nombre del Usuario --%>
          <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
          
          Para los JPG
          <p>1. <input type="file" name="file1"></p>
          <br>
          <p>2. <input type="file" name="file2"></p>
          <br>
          <p>3. <input type="file" name="file3"></p>
          <br>
          <p>4. <input type="file" name="file4"></p>
          <br>
          <p>5. <input type="file" name="file5"></p>
          <br>
          <p>6. <input type="file" name="file6"></p>
          <br>
          
          <input type="submit" value="<spring:message code="buton.continue"/>" />
          <br>
        </form>
      </div>
      
      <%--importacion --%>
      <div id="importationFormDiv" style="display: none">
        <form method="post" enctype="multipart/form-data" action="<c:out value="${formAction}"/>">
          <%-- Nombre del Usuario --%>
          <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />

          <%-- Archivo a subir --%>
          Para el import file  
          <p><input type="file" name="file"></p>
          <br>          
          
          <input type="submit" value="<spring:message code="buton.continue"/>" />
          <br>
        </form>
      </div>
      
     

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
      
</div>      