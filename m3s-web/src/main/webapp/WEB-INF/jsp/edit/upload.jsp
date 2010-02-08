<%@ include file="/common/taglibs.jsp"%>
<body onload="document.getElementById('editValueInput').focus();"/>
<%--
Usado por:

  * ../edit/step1.jsp
  * ../edit/step3.jsp
 --%>

<div id="insert-metadata-form">
  <form method="post" accept-charset="UTF-8" action="<c:out value="${editFormAction}"/>">

    <%--Media Id --%>
    <label>
      <b><spring:message code="metadata.label.id"/>:</b>
      <input type="text" name="mediaId" size="40"/>
    </label>
    <br/>

    <input id="editValueInput" type="submit" value="<spring:message code="buton.continue"/>" />
  </form>
</div>