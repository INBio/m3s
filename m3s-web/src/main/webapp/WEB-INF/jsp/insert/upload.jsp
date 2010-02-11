<%@ include file="/common/taglibs.jsp"%>
<%--
Usado por:

  * ../insert/step1.jsp
  * ../insert/step3.jsp
 --%>
 
 
<div id="insertRadioButtonsDiv" class="insert-type">

  <%-- Radio Buttons  --%>      
  <input type="radio" name="fileType" value="jpgImage" checked="checked" onclick="javascript:viewJpgImageDivs()" >
  <spring:message code="insert.image.jpg"/>
  <br>

  <input type="radio" name="fileType" value="excel" onclick="javascript:viewImportationDivs()" >
  <spring:message code="insert.excel.batch"/>
  <br>
  
  <input type="radio" name="fileType" value="video" onclick="javascript:viewYoutubeVideoDivs()" >
  <spring:message code="insert.youtube.title"/>
  <br>
  
</div>
      
<div id="uploadMethodDiv" class="upload-files-form">
  <%--imagenes --%>
  <div id="jpgImageFormDiv" style="display: block">
    <form method="post" enctype="multipart/form-data" action="uploadImages.html">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>"/>
          
        <label>1.<input type="file" name="file1"></label>
        <label>2.<input type="file" name="file2"></label>
        <label>3. <input type="file" name="file3"></label>
        <label>4. <input type="file" name="file4"></label>
        <label>5. <input type="file" name="file5"></label>
        <label>6. <input type="file" name="file6"></label>
        <br>
          
      <input type="submit" value="<spring:message code="buton.continue"/>" />
      <br>
    </form>
     <div id="jpgImageHelpDiv" class="upload-help">      
      <p>
        <label>
          <spring:message code="insert.jpg.help1"/>
          <br>
        </label>
        <br>
      </p>
    </div>
  </div>
      
  <%--importacion --%>
  <div id="importationFormDiv" style="display: none">
    <form method="post" enctype="multipart/form-data" action="uploadImportationFile.html">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />

      <%-- Archivo a subir --%>
      <p><input type="file" name="file"></p>
      <br>          
      
      <input type="submit" value="<spring:message code="buton.continue"/>" />
      <br>
    </form>
    <div id="importationHelpDiv" class="upload-help">      
      <p>
        <label>
          <spring:message code="insert.excel.help1"/>
          <br>
          <spring:message code="insert.excel.help2"/>
          <br>            
          <a href="<c:out value="${pageContext.request.contextPath}"/>/importationFileTable.html?username=<sec:authentication property="principal.username"/>"><spring:message code="insert.excel.view.history"/></a>
          <br>
        </label>
        <br>
      </p>
    </div>
  </div>
  
  <%--youtube videos --%>
  <div id="youtubeVideoFormDiv" style="display: none">
  <form method="post" enctype="multipart/form-data" action="insertYoutubeVideo.html">
      <%-- Nombre del Usuario --%>
      <input type="hidden" name="username" value="<sec:authentication property="principal.username"/>" />

      <%-- Id del video a subir --%>
      <label>
        <b><spring:message code="metadata.label.youtube.id"/>:</b>
      </label>
      <label>
        <input type="text" name="youtubeVideoId" value="<c:out value="${youtubeVideoId}"/>" size="40" tabindex="1"/>
      </label>
      <br>          
      
      <input type="submit" value="<spring:message code="buton.continue"/>" />
      <br>
    </form>
    <div id="youtubeVideoHelpDiv" class="upload-help">      
      <p>
        <label>
          <spring:message code="insert.youtube.help1"/>
          <br>
          <spring:message code="insert.youtube.help2"/>
          <br>
        </label>
        <br>
      </p>
    </div>
  </div>
</div><%-- selection mode --%>

 
<script type="text/javascript">
<%--
/*
 * Limpia los divs de ayuda que  
 */
 --%>
function cleanHelpDivs(){   
  document.getElementById('jpgImageFormDiv').style.display='none';
  document.getElementById('importationFormDiv').style.display='none';
  document.getElementById('youtubeVideoFormDiv').style.display='none';
}

function viewJpgImageDivs(){
  cleanHelpDivs();
  document.getElementById('jpgImageFormDiv').style.display='block';
}

function viewImportationDivs(){
  cleanHelpDivs();
  document.getElementById('importationFormDiv').style.display='block';
}

function viewYoutubeVideoDivs(){
  cleanHelpDivs();
  document.getElementById('youtubeVideoFormDiv').style.display='block';
}
</script>