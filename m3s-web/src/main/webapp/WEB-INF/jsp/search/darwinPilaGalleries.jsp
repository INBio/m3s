<%@ include file="/common/taglibs.jsp"%>

<%-- 
  Personalizado completamente para el proyecto Darwin-Pila

  Este tiles tiene los controles para moverse a través de las 
  búsquedas y el JSP de resultados 
--%>
<c:if test="${not empty outputMediaList}">
  
  <p><spring:message code="search.total.results"/>: ${totalResults} (<spring:message code="search.showing.results"/>: ${showing} <spring:message code="search.showing.from"/>: ${first} <spring:message code="search.showing.to"/>: ${last})</p>

  <p>

  <c:if test="${not empty previousParams}">
    <a href="${pageContext.request.contextPath}${formAction}?criteria=${criteria}&filter=${filter}&value=${value}${previousParams}">
    <spring:message code="search.previous.results"/> </a>
  </c:if>
  <c:if test="${empty previousParams}">
    <spring:message code="search.no.more.results"/>
  </c:if>    
     ||  
  <c:if test="${not empty nextParams}">
    <a href ="${pageContext.request.contextPath}${formAction}?criteria=${criteria}&filter=${filter}&value=${value}${nextParams}">
    <spring:message code="search.next.results"/> </a>
  </c:if>  
  <c:if test="${empty nextParams}">
    <spring:message code="search.no.more.results"/>
  </c:if>
      
  </p>
                   
  
  <%--
Este JSP recibe como parametro una lista de objetos de tipo media(corroborar este dato) y hace una galería
 de cada uno de los elementos de la lista, maravilloso
 --%>
<c:if test="${not empty outputMediaList}">
  <div id="thumb">
    <c:forEach items="${outputMediaList}" var="media">
      <div class="imagesRightPanel">
        <%--<div class="media-kind" style="background-image: url(images/image.png);"/> --%>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${media.mediaKey}"/>">
          <div class="thumb-image" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?size=thumb&id=<c:out value="${media.mediaKey}"/>);">
          </div>
          <div class="gwt-Label imaName"><c:out value="${media.title}"/></div>
        </a>
        <div class="imaInfo"><c:out value="${media.info1}"/></div>
        <div class="imaInfo"><c:out value="${media.info2}"/></div>
        <div class="imaInfo"><c:out value="${media.info3}"/></div>
      </div>
    </c:forEach>
    <div class="clear">
  </div>
</c:if>  
</c:if>