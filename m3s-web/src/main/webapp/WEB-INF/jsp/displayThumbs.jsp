<%@ include file="/common/taglibs.jsp"%>

<%--
Este JSP recibe como parametro una lista de objetos de tipo media(corroborar este dato) y hace una galería
 de cada uno de los elementos de la lista, maravilloso
 --%>

<c:if test="${not empty medias}">
  <div id="thumb">
    <c:forEach items="${medias}" var="media">
      <div class="imagesRightPanel">
        <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${media.mediaId}"/>">
          <div class="thumb-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${media.mediaId}"/>);">
          </div>
          <div class="gwt-Label imaName"><c:out value="${media.authorPersonId}"/></div>
          <div class="imaInfo"><c:out value="${media.title}"/></div>
          <div class="imaInfo"><c:out value="${media.title}"/></div>
        </a>
      </div>
    </c:forEach>
    <div class="clear">
  </div>
</c:if>

<%--
<div class="insert-preview-thumbs">
            Image Preview
            <div style="background-image: url(images/image.png);" class="media-kind"/>
             <a href="link">
              <div style="background-image: url(http://attila.inbio.ac.cr:7777/pls/portal30//IMAGEDB.GET_BFILE_IMAGE?p_imageId=3299&amp;p_imageResolutionId=1);" class="thumb-image"> </div>
              <div class="thumb-imaName">La prueba</div>
              </a>
              <div class="thumb-text1">texto</div>
              <div class="thumb-text2">texto</div>
              <div class="thumb-date">15 octubre 2009</div>
               </div>
 --%>               