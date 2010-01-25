<%@ include file="/common/taglibs.jsp"%>

<%--
@deprecated [2010.01.22]

Usado por:
 *

Este JSP recibe como parametro una lista de objetos de tipo media(corroborar este dato) y hace una galería
 de cada uno de los elementos de la lista, maravilloso
 --%>
<c:if test="${not empty fileNames}">

  <c:forEach items="${fileNames}" var="media">
    <div class="insert-preview-thumbs">
      <div class="media-kind" style="background-image:url(images/image.png)"></div>
      <div class="thumb-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?temporal=thumb-<c:out value="${media.mediaKey}"/>);"/></div>
      <div class="thumb-imaName"><c:out value="${media.title}"/></div>
    </div>
  </c:forEach>
  <div class="clear"></div>
</c:if>   
