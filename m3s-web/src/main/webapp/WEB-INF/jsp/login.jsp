<%@ include file="/common/taglibs.jsp"%>
<body onload="document.getElementById('userNameInput').focus();"/>

<h2><spring:message code="login.title"/></h2>

<form method="post" accept-charset="UTF-8" action="j_spring_security_check">

  <c:if test="${not empty param.error}">
    <font color="red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</font>
  </c:if>
  
  <%--UserName --%>
  <label>
    <b><spring:message code="login.user.name"/>:</b>
    <input type="text" id="userNameInput" name="j_username" />
  </label>
  <br/>
      
  <%--Password --%>
  <label>
    <b><spring:message code="login.password"/>:</b>
    <input type="password" name="j_password"/>
  </label>
  <br/>
  
  <%--
  <label>
    <b>Remember me on this computer.</b><input type='checkbox' name='_spring_security_remember_me'/>
  </label>
  <br/>
   --%>
  
  <input type="submit" value="<spring:message code="buton.acept"/>" />
  <input type="reset" name="reset" />

</form>

