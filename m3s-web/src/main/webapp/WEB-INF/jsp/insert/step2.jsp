<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.jpg.title"/></h2>

  <h3><spring:message code="preview.title"/></h3>
  <%--
   Este JSP recibe como parametro una lista de objetos de tipo media(corroborar este dato) y hace una galería
   de cada uno de los elementos de la lista, maravilloso
  --%>
  <c:if test="${not empty temporalMediaList}">
    <c:forEach items="${temporalMediaList}" var="temporalMedia">
      <div class="insert-preview-thumbs">
        <div class="media-kind" style="background-image:url(images/image.png)"></div>
        <div class="thumb-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?temporal=thumb-<c:out value="${temporalMedia.mediaKey}"/>);"/></div>
        <div class="thumb-imaName"><c:out value="${temporalMedia.title}"/></div>
      </div>
    </c:forEach>
    <div class="clear"></div>
  </c:if>   


  <div id="insert-metadata-form">
    <h3><spring:message code="metadata.title"/></h3>
    <tiles:insert page="/WEB-INF/jsp/metadata.jsp"/>
  </div>

