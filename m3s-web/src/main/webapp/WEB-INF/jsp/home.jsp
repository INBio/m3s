<%@ include file="/common/taglibs.jsp"%>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="85%" align="left" valign="top" class="MainPanel">
      
      <table width="100%"  border="0" cellpadding="10" cellspacing="0">
        <tr>
          <td height="28" colspan="2" class="MainPanel-Title">
            <h2><spring:message code="home.title"/></h2>
          </td>
          <%--
          <td>
            <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
              <p><spring:message code="m3s.login.welcome"/> <sec:authentication property="principal.username"/></p>
            </sec:authorize>              
          </td>
          --%>
        </tr>

	    <tr>
	      <td width="25%" height="65" align="left" valign="top">
		      <div class="contenido" id="layer1">
		        <p>
		          <a href="http://larus.inbio.ac.cr:8090/multimedios/doku.php"><spring:message code="home.help"/></a><br>
		          <a href="http://larus.inbio.ac.cr:8090/multimedios/doku.php?id=politicas_de_uso"><spring:message code="home.use.policies"/></a><br>
		        </p>
		        <div id="leftPanelContentDiv"></div>
		      </div>
		      <tiles:insert page="/WEB-INF/jsp/statistics.jsp"/>
		    </td>
		      
		    <td width="75%" align="left" valign="top">
		      <div class="contenido" id="layer1">
		        <p class="Home-RightPanelTitle">
		          <strong><spring:message code="home.preview.subtitle"/></strong>
		        </p>
		        
                <tiles:insert page="/WEB-INF/jsp/displayThumbs.jsp"/>
		      
		      </div>
		    </td>
		      
		  </tr>
</table>

    </td>
  </tr>
</table>
