
<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="edit.title"/></h2>

<h1><spring:message code="edit.done"/></h1>


<strong><spring:message code="edit.again"/></strong>

<tiles:insert page="upload.jsp"/>

<%--
<div id="insert-metadata-form">
  <form method="post" accept-charset="UTF-8" action="editMedia.html">

    <label>
      <b><spring:message code="metadata.label.id"/>:</b>
      <input type="text" name="mediaId" size="40"/>
    </label>
    <br/>

    <input type="submit" value="<spring:message code="buton.continue"/>" />
  </form>
</div>
 --%>