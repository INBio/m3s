<%@ include file="/common/taglibs.jsp" %>

<h3><spring:message code="insert.excel.history"/></h3>

<display:table 
  name="icDTOList" 
  export="false" 
  class="results"   
  pagesize="5" 
  sort="external"
  defaultsort="1"
  size="resultSize"
  
  requestURI="${pageContext.request.contextPath}/uploadImportationFile.html?">
  
  <display:column property="userFileName" titleKey="insert.excel.table.filename" 
                  href="${pageContext.request.contextPath}/getImportationFile" 
                  paramId="id" paramProperty="systemFileName"/>
  <display:column property="status" titleKey="insert.excel.table.status"/>
  <display:column property="creationDate" titleKey="insert.excel.table.date"/>


  <display:setProperty name="paging.banner.no_items_found"> </display:setProperty>    
  <display:setProperty name="pagination.pagenumber.param">pageno</display:setProperty>
  <display:setProperty name="paging.banner.placement">both</display:setProperty>  

</display:table>

  