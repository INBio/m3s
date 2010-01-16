<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/autocompleteScripts.jsp"%>
<%@ taglib uri="/tld/fn.tld" prefix="fn" %>

<%--mediaOwner --%>
<script src="${pageContext.request.contextPath}/javascript/mediaOwner.js" type="text/javascript" language="javascript"></script>


<%-- This JSP controls the ownerType and ownerId value of the media metadata --%>


<%-- mediaOwnerFilter Auto Completes--%>
<script type="text/javascript">
    var mediaOwnerAutoCompleteUrls = new Array(${ fn:length(requestScope['mediaOwnerFilters'])});
    <c:forEach items="${requestScope['mediaOwnerFilters']}" var="mediaOwnerFilter" varStatus="filterStatus" begin="0">
      <c:if test="${not empty mediaOwnerFilter.autoCompleteUrl}">
        mediaOwnerAutoCompleteUrls[${mediaOwnerFilter.id}] = "${pageContext.request.contextPath}/${mediaOwnerFilter.autoCompleteUrl}";
      </c:if> 
    </c:forEach>
</script>


  <%--label of the widget --%>
  <label>
    <b><spring:message code="metadata.label.owner"/>:</b>
  </label>
  
  <label>
    <select name="ownerType" id="ownerTypeId" tabindex="12" onchange="javascript:changeMediaOwnerInput();" onKeyUp="javascript:changeMediaOwnerInput();">
      <c:forEach items="${mediaOwnerFilters}" var="ownerFilter">
        <option value="<c:out value="${ownerFilter.id}"/>"<c:if test="${ownerFilter.id == ownerType}"> selected="selected"</c:if>>
          <spring:message code="${ownerFilter.displayName}"/>
        </option>
      </c:forEach>
    </select>
  </label> 
  
  <label>
    <span id="newOwnerValue">
      <input id="ownerId" type="text" name="ownerValue" value="<c:out value="${ownerValue}"/>" tabindex="13"/>
      <div id="ownerContainer"></div>
    </span>
  </label>

<script type="text/javascript">
  changeMediaOwnerInput();
</script>


  
  
   
