<%@ include file="/common/taglibs.jsp"%>


<h2><spring:message code="media.profile.title"/></h2>


<table width="100%" border="0" cellpadding="10" cellspacing="0">
  <tr>
    <td>
      <div class="profile-bigMedia">
        <img src="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${id}"/>"  />                
        <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${id}"/>"><spring:message code="media.profile.download"/></a>        
      </div>
    </td>
    <td>
      <div class="profile-thumbMedia">
        <h3><spring:message code="media.profile.other.title"/></h3>
        <img src="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${id}"/>"  />
        <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${id}"/>"><spring:message code="media.profile.download"/></a>
      </div>
    </td>
  </tr>
  
</table>

<tiles:insert page="/WEB-INF/jsp/media/mediaMetadata.jsp"/>

