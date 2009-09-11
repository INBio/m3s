<%@ include file="/common/taglibs.jsp"%>
<h2>
  <spring:message code="home.title"/>
</h2>

<h3>
  <spring:message code="home.preview.subtitle"/>
</h3>

  <ul>
    <c:forEach items="${medias}" var="media">
      <li>
        <c:out value="${media.mediaId}"/>
        <c:out value="${media.authorPersonId}"/>
        <c:out value="${media.title}"/>
	  </li>
	</c:forEach>
  </ul>