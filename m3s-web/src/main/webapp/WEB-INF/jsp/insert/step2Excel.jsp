<%@ include file="/common/taglibs.jsp" %>

<h2><spring:message code="insert.excel.title"/></h2>

<br>
  <p>Este procedimiento puede tardar varios minutos dependiendo del tama�o del archivo.</p>
  <p>En cuanto este terminado se podr� observar en la tabla.</p>
</br>


<tiles:insert page="/WEB-INF/jsp/insert/importationTable.jsp"/>

<br>
  <p>Dando clic en el nombre del archivo podr� descargarlo</p>
</br>