<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.title"/></h2>

<c:if test="${not empty errorMessageKey}">  
  <label>
    <font color="red"><spring:message code="error.title"/>: <spring:message code="${errorMessageKey}"/></font>
  </label>
  <br>
</c:if>

<tiles:insert page="upload.jsp"/>     


