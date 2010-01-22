<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.jpg.title"/></h2>

  <h3><spring:message code="preview.title"/></h3>
  <tiles:insert page="imagePreview.jsp"/>


  <div id="insert-metadata-form">
    <h3><spring:message code="metadata.title"/></h3>
    <tiles:insert page="/WEB-INF/jsp/metadata.jsp"/>
  </div>

