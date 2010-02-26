<%@ include file="/common/taglibs.jsp"%>

<h3><spring:message code="media.profile.info"/></h3>

  <%--Identificador --%>
  <label style="width:80px;">
    <b><spring:message code="metadata.label.id"/></b>
  </label>
  <label>
    <c:out value="${id}"/>
  </label>
  <br/>

  <label>
    <b><spring:message code="media.profile.other.title"/></b>
  </label>
  <br>
  
    <label>
      <a href="javascript:setThumbView('${pageContext.request.contextPath}','${id}');"><spring:message code="media.profile.image.thumb"/></a>
    </label>
    <label>
      <a href="javascript:setBigView('${pageContext.request.contextPath}','${id}');"><spring:message code="media.profile.image.big"/></a>
    </label>
    <br>

  <label>
    <b><spring:message code="media.profile.links"/></b>
  </label>
  <br>
  
    <label style="width:80px;">
      <spring:message code="media.profile.image.big"/>:
    </label>
    <label>
      <input value="<c:out value="${url}"/>/getImage?size=big&id=<c:out value="${id}"/>" readonly size="30" onclick="javascript:select()" />
    </label>
    <br/>
  
    <label style="width:80px;">
      <spring:message code="media.profile.image.thumb"/>:
    </label>
    <label>
      <input value="<c:out value="${url}"/>/getImage?size=thumb&id=<c:out value="${id}"/>" readonly size="30" onclick="javascript:select()" />
    </label>      
    <br/>


<h3><spring:message code="media.profile.actions"/></h3>
  
  <c:if test="${not empty mediaAuthorEmailKey}">
    <label>
      <a href="mailto:<c:out value="${mediaAuthorEmailKey}"/>"><spring:message code="media.profile.contact.author"/></a>
    </label>
    <br/>
  </c:if>

  <label>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/editMedia.html?mediaId=<c:out value="${id}"/>"><spring:message code="edit.title"/></a>  
  </label>
  <br/>

  <label>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?download=1&size=big&id=<c:out value="${id}"/>"><spring:message code="media.profile.download"/> <spring:message code="media.profile.image.big"/></a>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?download=1&size=thumb&id=<c:out value="${id}"/>"><spring:message code="media.profile.download"/> <spring:message code="media.profile.image.thumb"/></a>  
  </label>
  <br/>
  
  
<script type="text/javascript">

function setThumbView(urlContext, id) {
  document.getElementById('actualViewMedia').style.backgroundImage="url("+urlContext+"/getImage?size=thumb&id="+id+")";	  
}

function setBigView(urlContext, id) {
  document.getElementById('actualViewMedia').style.backgroundImage="url("+urlContext+"/getImage?size=big&id="+id+")";
}

</script>

