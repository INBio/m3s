<%@ include file="/common/taglibs.jsp"%>

<c:if test="${not empty fileName}">
  <div id="insert-preview-thumbs"><c:out value="${preview.title}"/><a href="/m3sINBio-dev/getImage?size=big&id=107851/>">
    <div class="thumb-image" style="background-image: url(http://coffea.inbio.priv:8180/m3sINBioFiles/TEMP_MEDIA_DIR/<c:out value="${fileName}"/>);"></div>
    <div class="gwt-Label imaName"><c:out value="${fileName}"/></div>
    </a>
  </div>
</c:if>
