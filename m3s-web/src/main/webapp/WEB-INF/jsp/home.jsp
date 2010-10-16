<%@ include file="/common/taglibs.jsp"%>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="85%" align="left" valign="top" class="MainPanel">
      
      <table width="100%"  border="0" cellpadding="10" cellspacing="0">
        <tr>
          <td height="28" colspan="2" class="MainPanel-Title">
            <h2><spring:message code="home.title"/></h2>
          </td>
        </tr>

	    <tr>
	      <td width="25%" height="65" align="left" valign="top">
		      <div class="contenido" id="layer1">
		        <p>
		        
		          <a href="http://larus.inbio.ac.cr/dokuwiki/multimedios/doku.php"><spring:message code="home.help"/></a><br>
		          <a href="http://larus.inbio.ac.cr/dokuwiki/multimedios/doku.php?id=politicas_de_uso"><spring:message code="home.use.policies"/></a><br>
		        </p>
		        <div id="leftPanelContentDiv"></div>
		      </div>
		      
		      <%-- stadistics --%>
		      <strong>
		        <spring:message code="statistics.title"/>
		      </strong>
		      <br>
		      <spring:message code="statistics.count.multimedia"/>: <c:out value="${multimediaCount}"/>
		      <br>
		      <spring:message code="statistics.count.images"/>: <c:out value="${imagesCount}"/>
              <br>
              <spring:message code="statistics.count.videos"/>: <c:out value="${videosCount}"/>
              <br>
              <%--
              <spring:message code="statistics.count.sonidos"/>: <c:out value="${multimediaCount}"/>
              <br>
              --%>
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
