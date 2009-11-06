<%
	//A Tag Wrapper for Yahoo's Autocomplete modified
%>
<%@ include file="/common/taglibs.jsp"%>	
<%@ attribute name="url" required="true" %>
<%@ attribute name="inputId" required="true" %>
<%@ attribute name="containerId" required="true" %>
<%@ attribute name="multiValue" required="false" %> 

<c:choose>
    <c:when test="${multiValue}">
        <script>
          setupMultiValueAutoComplete("${url}", "${inputId}", "${containerId}");
        </script>          
    </c:when>
    <c:otherwise>
        <script>
          setupAutoComplete("${url}", "${inputId}", "${containerId}");
        </script>          
    </c:otherwise>
</c:choose>


