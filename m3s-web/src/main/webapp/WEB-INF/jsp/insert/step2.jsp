<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.jpg.title"/></h2>

<div id="insert-metadata-form">
  
  <tiles:insert page="imagePreview.jsp"/>
  
  <tiles:insert page="/WEB-INF/jsp/insert/metadata.jsp"/>
  <%--<tiles:insert page="/WEB-INF/jsp/metadata.jsp"/>--%>
  
</div>

