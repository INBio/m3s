<%@ include file="/common/taglibs.jsp"%>

<div id="insert-metadata-form">
  <h2><spring:message code="insert.title"/></h2>
  
  <tiles:insert page="imagePreview.jsp"/>
  
  <tiles:insert page="/WEB-INF/jsp/insert/metadata.jsp"/>
  
</div>

