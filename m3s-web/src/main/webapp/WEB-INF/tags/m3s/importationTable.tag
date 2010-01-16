<%
	//A Tag Wrapper for Yahoo's tables modified
	
	//@Deprecated (15/01/2010 - james)
%>
<%@ include file="/common/taglibs.jsp"%>	
<%@ attribute name="urlContext" required="true" %>
<%@ attribute name="username" required="true" %>

<script>
  setupImportationTable("${urlContext}", "${username}");
</script>          



