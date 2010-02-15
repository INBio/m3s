<%@ include file="/common/taglibs.jsp"%>

<table width="100%" border="0" cellpadding="10" cellspacing="0">
  <tr>
    <td>
      <div class="profile-bigMedia">
        <img src="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${id}"/>"  />                
        <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?download=1&size=big&id=<c:out value="${id}"/>"><spring:message code="media.profile.download"/></a>
      </div>
    </td>
    <td>
      <div class="profile-thumbMedia">
        <h3><spring:message code="media.profile.other.title"/></h3>
        <img src="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${id}"/>"  />
        <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?download=1&size=thumb&id=<c:out value="${id}"/>"><spring:message code="media.profile.download"/></a>
      </div>
    </td>
  </tr>   
</table>

<h3><spring:message code="media.profile.links"/></h3>

<label>
  <b><spring:message code="media.profile.image.big"/>: </b> <c:out value="${url}"/>/getImage?size=big&id=<c:out value="${id}"/>
</label>
<br/>
  
<label>
  <b><spring:message code="media.profile.image.thumb"/>: </b> <c:out value="${url}"/>/getImage?size=thumb&id=<c:out value="${id}"/>
</label>

<br/>
<br/>
  