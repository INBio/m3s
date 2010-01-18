<%@ include file="/common/taglibs.jsp"%>


<h2><spring:message code="media.profile.title"/></h2>

mediaId = '${mediaId}'

id = ${id}


<div class="big-imagePanel">
<%--
  <div class="big-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${mediaId}"/>);">
  </div>
--%>
  <img alt="Imagen Grande" src="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${mediaId}"/>"  />
  download image link
</div>


