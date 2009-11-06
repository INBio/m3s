<%@ include file="/common/taglibs.jsp"%>

<strong>
  <spring:message code="statistics.title"/>
</strong>
<br>

<spring:message code="statistics.count.multimedia"/>: <c:out value="${multimediaCount}"/>
<br>

<spring:message code="statistics.count.images"/>: <c:out value="${imagesCount}"/>
<br>

<spring:message code="statistics.count.videos"/>: <c:out value="${videosCount}"/>
<br>

<%--
<spring:message code="statistics.count.sonidos"/>: <c:out value="${multimediaCount}"/>
<br>

--%>