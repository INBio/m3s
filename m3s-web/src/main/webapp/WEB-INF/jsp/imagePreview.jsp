<%@ include file="/common/taglibs.jsp"%>

<c:if test="${not empty mediaId}">

  <div class="insert-preview-thumbs">
    <div class="media-kind" style="background-image:url(images/image.png)"></div>
    <div class="thumb-image" style="background-image: url(${pageContext.request.contextPath}/getImage?size=thumb&id=<c:out value="${mediaId}"/>);"> </div>
    <div class="thumb-imaName"><c:out value="${title}"/></div>
  </div>

<%--
  <div id="insert-preview-thumbs"><c:out value="${preview.title}"/><a href="/m3sINBio-dev/getImage?size=big&id=<c:out value="${mediaId}"/>">
    <div class="thumb-image" style="background-image: url(/m3sINBio-dev/getImage?size=thumb&id=<c:out value="${mediaId}"/>);"></div>
    <div class="gwt-Label imaName"><c:out value="${title}"/></div>
    </a>
  </div>
--%>
</c:if>

