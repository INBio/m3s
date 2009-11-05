<%
	//A Tag Wrapper for Yahoo's Autocomplete 
%>	
<%@ attribute name="url" required="true" %>
<%@ attribute name="inputId" required="true" %>
<%@ attribute name="containerId" required="true" %>
<%@ attribute name="multiValue" required="false" %>
<script>
	setupAutoComplete("${url}", "${inputId}", "${containerId}", "${multiValue}");
</script>