<%@ include file="/common/taglibs.jsp"%>

<!-- yui js and css -->
<link rel="stylesheet" type="text/css" href="resources/yui/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="resources/yui/fonts/fonts-min.css">

<script src="${pageContext.request.contextPath}/javascript/yui/yahoo-dom-event/yahoo-dom-event.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/yui/connection/connection-min.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/yui/element/element-min.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/yui/datasource/datasource-min.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/yui/datatable/datatable-min.js" type="text/javascript" language="javascript"></script>


<h2><spring:message code="admin.users.title"/></h2>

boton-new boton-edit boton-delete


<%-- Usuario --%>
<p>Select User<p/>
View by:  username  fullname

<div id="usersList">
  <div id="userId-"></div>
</div>


<label>
  <b><spring:message code="metadata.label.author"/>:</b>
</label>
<label>
  <input id="authorId" name="mediaAuthor" value="<c:out value="${mediaAuthor}"/>" tabindex="11"/>
  <div id="personContainer" class="yui-skin-m3s"></div>
</label>
<m3s:autoComplete containerId="personContainer" inputId="authorId" url="${pageContext.request.contextPath}/ajax/personName" />
<br/>