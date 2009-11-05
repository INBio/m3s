<%
	//A Tag Wrapper for Yahoo's Autocomplete 
%>	
<%@ attribute name="url" required="true" %>
<%@ attribute name="inputId" required="true" %>
<%@ attribute name="containerId" required="true" %>
<script>
	setupAutoComplete("${url}", "${inputId}", "${containerId}");
</script>