<%@ include file="/common/taglibs.jsp"%>

<div id="insert-metadata-form">
  <h2><spring:message code="edit.title"/></h2>
  
  <c:if test="${not empty mediaId}">

    <div class="edit-preview-thumbs">
      <div class="media-kind" style="background-image:url(images/image.png)"></div>
      <div class="thumb-image" style="background-image: url(${pageContext.request.contextPath}/getImage?size=thumb&id=<c:out value="${mediaId}"/>);"> </div>
      <div class="thumb-imaName"><c:out value="${title}"/></div>
    </div>
  </c:if>
  
  <tiles:insert page="/WEB-INF/jsp/metadata.jsp"/>
  
</div>