<%@ include file="/common/taglibs.jsp"%>


<h2><spring:message code="media.profile.title"/></h2>
<%-- --%>

<c:if test="${mediaTypeId == 1}">
  <tiles:insert page="/WEB-INF/jsp/media/image.jsp"/>
</c:if>
<c:if test="${mediaTypeId == 5}">
  <tiles:insert page="/WEB-INF/jsp/media/image.jsp"/>
</c:if>
<c:if test="${mediaTypeId == 4}">
  <tiles:insert page="/WEB-INF/jsp/media/flvFlowPlayer.jsp"/>
</c:if> 
<c:if test="${mediaTypeId == 6}">
  <tiles:insert page="/WEB-INF/jsp/media/youtube.jsp"/>
</c:if>  
 
 

<tiles:insert page="/WEB-INF/jsp/media/mediaMetadata.jsp"/>

