<%@ include file="/common/taglibs.jsp"%>


<h2><spring:message code="media.profile.title"/></h2>


<div style="position: relative; height: 510px;"> 
 
  <div class="profile-viewMedia">
    
    <c:if test="${mediaTypeId == 1 || mediaTypeId == 5}">
      <div id="actualViewMedia" class="viewMedia" style="background-image: url(<c:out value="${pageContext.request.contextPath}"/>/getImage?size=big&id=<c:out value="${id}"/>);">
      </div>
    </c:if>
    
    <c:if test="${mediaTypeId == 4}">
      <tiles:insert page="/WEB-INF/jsp/media/flvFlowPlayer.jsp"/>
    </c:if> 
    
    <c:if test="${mediaTypeId == 6}">
      <tiles:insert page="/WEB-INF/jsp/media/youtube.jsp"/>
    </c:if>
    
  </div>
 
  <div class="profile-infoAndActions">
    <tiles:insert page="/WEB-INF/jsp/media/infoAndActions.jsp"/>
  </div> 

</div>

<%-- Media Metadata --%>
<div  style="position: relative;">
  <tiles:insert page="/WEB-INF/jsp/media/mediaMetadata.jsp"/>
</div>


<%-- Media Attributes --%>
<div  style="position: relative;">
  <h2><spring:message code="media.attribute.title"/></h2>

  <c:forEach items="${mDTO.items}" var="item">

    <label>
      <b><spring:message code="${item.mediaAttributeName}"/></b>
    </label>

    <label>
      <c:out value="${item.value}"/>
    </label>
    <br/>  
  
  </c:forEach>
</div>

<c:if test="${gpsLatitudeKey != null && gpsLongitudeKey !=null}">
  <h2>GPS Information</h2>
  <tiles:insert page="/WEB-INF/jsp/media/googleMap.jsp"/>
</c:if>