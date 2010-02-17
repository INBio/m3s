<%@ include file="/common/taglibs.jsp"%>
<%--
 usado en:
 *  ../search/results.jsp
 * home.jsp
 --%>


<%--
Este JSP recibe como parametro una lista de objetos de tipo media(corroborar este dato) y hace una galería
 de cada uno de los elementos de la lista, maravilloso
 --%>
<c:if test="${not empty outputMediaList}">
  <div id="thumb">
    <c:forEach items="${outputMediaList}" var="media">
      <div class="imagesRightPanel">
        
        <%-- <div class="media-kind-image">--%>
        
        <a href="<c:out value="${pageContext.request.contextPath}"/>/media?id=<c:out value="${media.mediaKey}"/>">
          <div class="thumb-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${media.mediaKey}"/>);">
          </div>
          
          <div class="thumb-imaName"><c:out value="${media.title}"/></div>
        </a>
        <div class="thumb-text1"><c:out value="${media.info1}"/></div>
        <div class="thumb-text2"><c:out value="${media.info2}"/></div>
        <div class="thumb-date"><spring:message code="media.log.creation.date"/>: <c:out value="${media.info3}"/></div>
        <%--</div> Esto cierra el div media-kind-image--%>
      </div>
    </c:forEach>
    <div class="clear">
  </div>
</c:if>             