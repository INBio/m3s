<%@ include file="/common/taglibs.jsp"%>

<div id="insert-metadata-form">
    <form method="post" accept-charset="UTF-8" action="editMedia.html">

      <%--Media Id --%>
      <label>
        <b><spring:message code="metadata.label.id"/>:</b>
        <input type="text" name="mediaId" size="40"/>
      </label>
      <br/>

      <input type="submit" value="<spring:message code="buton.continue"/>" />
    </form>
  </div>