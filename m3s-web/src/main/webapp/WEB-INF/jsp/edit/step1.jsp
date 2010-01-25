<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="edit.title"/></h2>

<h3><spring:message code="m3s.login.welcome"/>: <sec:authentication property="principal.username"/></h3>

<c:if test="${not empty errorMessageKey}">  
  <label>
    <font color="red"><spring:message code="error.title"/>: <spring:message code="${errorMessageKey}"/></font>
  </label>
  <br>
</c:if>

<div id="insert-metadata-form">
  <form method="post" accept-charset="UTF-8" action="<c:out value="${editFormAction}"/>">

    <%--Media Id --%>
    <label>
      <b><spring:message code="metadata.label.id"/>:</b>
      <input type="text" name="mediaId" size="40"/>
    </label>
    <br/>

    <input type="submit" value="<spring:message code="buton.continue"/>" />
  </form>
</div>