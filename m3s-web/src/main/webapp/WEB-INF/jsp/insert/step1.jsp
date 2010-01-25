<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.title"/></h2>

<h3><spring:message code="m3s.login.welcome"/>: <sec:authentication property="principal.username"/></h3>
<br/>

<c:if test="${not empty errorMessageKey}">  
  <label>
    <font color="red"><spring:message code="error.title"/>: <spring:message code="${errorMessageKey}"/></font>
  </label>
  <br>
</c:if>

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
    <form method="post" enctype="multipart/form-data" action="<c:out value="${jpgImagesFormAction}"/>">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />
          
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
    <form method="post" enctype="multipart/form-data" action="<c:out value="${excelFormAction}"/>">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />

      <%-- Archivo a subir --%>
      <p><input type="file" name="file"></p>
      <br>          
      
      <input type="submit" value="<spring:message code="buton.continue"/>" />
      <br>
    </form>
  </div>
  
  <%-- Help --%>    
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
          <a href="<c:out value="${pageContext.request.contextPath}"/>/uploadImportationFile.html?username=<sec:authentication property="principal.username"/>"><spring:message code="insert.excel.view.history"/></a>
          <br>
        </label>
        <br>
      </p>
    </div>
  
  </div>  
</div>      

<script type="text/javascript">
<%--
/*
 * Limpia los divs de ayuda que  
 */
 --%>
function cleanHelpDivs(){   
  document.getElementById('jpgImageHelpDiv').style.display='none';
  document.getElementById('importationHelpDiv').style.display='none';
  document.getElementById('jpgImageFormDiv').style.display='none';
  document.getElementById('importationFormDiv').style.display='none';
  
}

function viewJpgImageDivs(){
  cleanHelpDivs();
  document.getElementById('jpgImageHelpDiv').style.display='block';
  document.getElementById('jpgImageFormDiv').style.display='block';
}

function viewImportationDivs(){
  cleanHelpDivs();
  document.getElementById('importationHelpDiv').style.display='block';
  document.getElementById('importationFormDiv').style.display='block';
}
</script>
