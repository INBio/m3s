<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>

  <%--Palabras Clave --%>
  <label>
    <b><spring:message code="metadata.label.key.words"/>:</b>
  </label>
  <label>
    <input id="keywordId" type="text" name="keywords" size="40" value="<c:out value="${keywords}"/>" tabindex="5"/>
    <div id="keywordsContainer"></div>
  </label>
  <m3s:autoComplete containerId="keywordsContainer" inputId="keywordId" url="${pageContext.request.contextPath}/ajax/keyword" multiValue="true"/>