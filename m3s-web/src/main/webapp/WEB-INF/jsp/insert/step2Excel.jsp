<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.excel.title"/></h2>

<br>
  Este procedimiento puede tardar varios minutos dependiendo del tamaño del archivo.
  En cuanto este terminado se podrá observar en la tabla.
</br>

<display:table 
    name="icDTOList" 
    export="false" 
    class="ImportControlDTOFull" 
    uid="importControlDTOs" 
    requestURI="/excel.html?"
    sort="external"
    defaultsort="1"
    pagesize="20"
    size="resultSize"
>

  <display:column titleKey="insert.excel.table.filename" property="userFileName" maxLength="100"/>
    
  <display:column titleKey="insert.excel.table.status" property="status">
  </display:column>   
  <display:column titleKey="insert.excel.table.date" property="creationDate">
  </display:column>  
  <display:column titleKey="insert.excel.table.download" property="systemFileName" autolink="true" maxLength="40">
    <%--<a href=""><spring:message code="buton.click.here"/></a>--%>
  </display:column>  

  <display:setProperty name="basic.msg.empty_list"><spring:message code="search.no.results"/></display:setProperty>   
  <display:setProperty name="paging.banner.onepage"> </display:setProperty>   
  <display:setProperty name="basic.empty.showtable">true</display:setProperty>   
  <display:setProperty name="basic.msg.empty_list_row">
    <tr class="empty">
        <td colspan="13"><spring:message code="search.no.results"/></td>
    </tr>
  </display:setProperty>      
  <display:setProperty name="paging.banner.no_items_found"> </display:setProperty>    
  <display:setProperty name="pagination.pagenumber.param">pageno</display:setProperty>
  <display:setProperty name="paging.banner.placement">both</display:setProperty>      
  
  
</display:table>