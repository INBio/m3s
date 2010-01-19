<%@ include file="/common/taglibs.jsp"%>

<c:if test="${not empty fileName}">
  <div class="insert-preview-thumbs"><c:out value="${preview.title}"/>
    <div class="media-kind" style="background-image:url(images/image.png)"></div>
        <div class="thumb-image" style="background-image: url(${pageContext.request.contextPath}/getImage?temporal=thumb-<c:out value="${fileName}"/>);"> </div>    
    <div class="thumb-imaName"><c:out value="${fileName}"/></div>
  </div>
</c:if>
