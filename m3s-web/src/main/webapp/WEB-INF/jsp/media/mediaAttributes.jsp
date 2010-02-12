<%@ include file="/common/taglibs.jsp"%>

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

