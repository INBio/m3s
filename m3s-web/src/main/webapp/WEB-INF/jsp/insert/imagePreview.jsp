<%@ include file="/common/taglibs.jsp"%>

Image Preview

  <c:if test="${not empty fileName}"> 
    <div id="insert-image-thumbs">	        
      <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=107851/>">
        <div class="thumb-image" style="background-image: url(http://localhost:8180/m3sINBioFiles/TEMP_MEDIA_DIR/<c:out value="${fileName}"/>);"/>
        <div class="gwt-Label imaName"><c:out value="${fileName}"/></div>
      </a>
    </div>
  </c:if>